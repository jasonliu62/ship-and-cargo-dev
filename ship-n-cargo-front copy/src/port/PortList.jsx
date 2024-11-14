import React, {useState, useEffect, useContext} from 'react';
import AuthContext from '../context/AuthContext';
import PageSizeSelect from '../util/PageSizeSelect';
import PortInsert from './PortInsert'; // Assume similar functionality to ShipInsert
import DeleteButton from "../util/DeleteButton.jsx";
import EditButton from "../util/EditButton.jsx";
import SortFieldSelect from "../util/SortFieldSelect.jsx";
import SortDirectionSelect from "../util/SortDirectionSelect.jsx";
import PageSelector from "../util/PageSelector.jsx";
import {Alert, Col, Row, Table} from "react-bootstrap";
import Container from "react-bootstrap/Container"; // Placeholder for future functionality

function PortList() {
    const [ports, setPorts] = useState([]);
    const [totalPage, setTotalPage] = useState(1);
    const [pageSize, setPageSize] = useState(5);
    const [currentPage, setCurrentPage] = useState(1);
    const [sortField, setSortField] = useState('portId');
    const [sortDirection, setSortDirection] = useState('asc');
    const [refresh, setRefresh] = useState(false);
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    useEffect(() => {
        const fetchPorts = async () => {
            try {
                const response = await fetch(`${apiURL}/port/list`, {
                    method: 'POST', headers: {
                        'Content-Type': 'application/json', 'Authorization': `Bearer ${token}`,
                    }, body: JSON.stringify({
                        pagination: {
                            current: currentPage, pageSize: pageSize, sortBy: [`${sortField}:${sortDirection}`]
                        }
                    })
                });
                const data = await response.json();
                if (data.code === 0 && data.data.list) {
                    setPorts(data.data.list);
                    setTotalPage(Math.ceil((data.data.paging.total) / pageSize))
                }
            } catch (error) {
                console.error('Failed to fetch ports:', error);
            }
        };

        fetchPorts();
    }, [token, pageSize, refresh, sortDirection, sortField, currentPage]);

    const refreshPorts = () => {
        setRefresh(!refresh);
    };

    return (<Container>
        <br/>
        <h2>港口属性信息</h2>
        <br/>
        <Row style={{whiteSpace: 'nowrap'}}>
            <Col>
                <PageSizeSelect pageSize={pageSize} onPageSizeChange={(e) => {
                    setPageSize(e.target.value)
                    setCurrentPage(1)
                }}/>
            </Col>
            <Col>
                <SortFieldSelect selectedField={sortField} onSortFieldChange={(e) => setSortField(e.target.value)}
                                 sortFields={['portId', 'nameCHN', 'nameENG', 'latitude', 'longitude', 'minDraft']}/>
            </Col>
            <Col>
                <SortDirectionSelect selectedDirection={sortDirection}
                                     onSortDirectionChange={(e) => setSortDirection(e.target.value)}/>
            </Col>
        </Row>
        <br/>
        <PageSelector currentPage={currentPage} totalPages={totalPage} onPageChange={(e) => {
            setCurrentPage(e)
        }}/>
        <br/>
        <PortInsert onPortAdded={refreshPorts}/>
        <br/>
        <br/>
        {ports.length > 0 ? (<Table striped bordered hover style={{whiteSpace: 'nowrap'}}>
            <thead>
            <tr>
                <th>Port ID</th>
                <th>中文名称</th>
                <th>英文名称</th>
                <th>纬度</th>
                <th>经度</th>
                <th>港口最小水深</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            {ports.map((port, index) => (<tr key={index}>
                <td>{port.portId}</td>
                <td>{port.nameCHN}</td>
                <td>{port.nameENG}</td>
                <td>{port.latitude}</td>
                <td>{port.longitude}</td>
                <td>{port.minDraft}</td>
                <td>
                    <DeleteButton
                        type="portId"
                        deleteUrl="port"
                        identifier={port.portId}
                        onDeleted={refreshPorts}
                    />
                    {/*<EditButton/>*/}
                </td>
            </tr>))}
            </tbody>
        </Table>) : (<Alert variant="info">未找到港口属性</Alert>)}
    </Container>);
}

export default PortList;
