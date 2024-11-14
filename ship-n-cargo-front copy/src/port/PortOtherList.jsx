// PortOtherList.jsx
import React, {useState, useEffect, useContext} from 'react';
import AuthContext from '../context/AuthContext';
import PageSizeSelect from '../util/PageSizeSelect';
import PortOtherInsert from "./PortOtherInsert.jsx";
import DeleteButton from "../util/DeleteButton.jsx";
import EditButton from "../util/EditButton.jsx";
import SortFieldSelect from "../util/SortFieldSelect.jsx";
import SortDirectionSelect from "../util/SortDirectionSelect.jsx";
import PageSelector from "../util/PageSelector.jsx";
import {Alert, Col, Row, Table} from "react-bootstrap";
import Container from "react-bootstrap/Container";

function PortOtherList() {
    const [portOthers, setPortOthers] = useState([]);
    const [totalPage, setTotalPage] = useState(1);
    const [pageSize, setPageSize] = useState(5);
    const [currentPage, setCurrentPage] = useState(1);
    const [sortField, setSortField] = useState('portId');
    const [sortDirection, setSortDirection] = useState('asc');
    const [refresh, serRefresh] = useState(false);
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    useEffect(() => {
        const fetchPortOthers = async () => {
            try {
                const response = await fetch(`${apiURL}/port-other/list`, {
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
                    setPortOthers(data.data.list);
                    setTotalPage(Math.ceil((data.data.paging.total) / pageSize))
                }
            } catch (error) {
                console.error('Failed to fetch port other details:', error);
            }
        };

        fetchPortOthers();
    }, [token, pageSize, refresh, sortDirection, sortField], currentPage);

    const refreshPortOthers = () => {
        serRefresh(!refresh);
    }

    return (<Container>
        <br/>
        <h2>港口其他信息</h2>
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
                                 sortFields={['portId', 'distance', 'oilPrice', 'avgTimeStay', 'loadTime', 'loadEfficiency', 'unloadTime', 'unloadEfficiency', 'portFee', 'avgAnchorTime']}/>
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
        <PortOtherInsert onPortOtherAdded={refreshPortOthers}/>
        <br/>
        <br/>

        {portOthers.length > 0 ? (<Table striped bordered hover>
            <thead>
            <tr>
                <th>Port ID</th>
                <th>Distance</th>
                <th>Oil Price</th>
                <th>平均在港时间</th>
                <th>Load Time</th>
                <th>装船效率</th>
                <th>Unload Time</th>
                <th>卸船效率</th>
                <th>Port Fee</th>
                <th>平均锚泊时间</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            {portOthers.map((detail, index) => (<tr key={index}>
                <td>{detail.portId}</td>
                <td>{detail.distance}</td>
                <td>{detail.oilPrice}</td>
                <td>{detail.avgTimeStay}</td>
                <td>{detail.loadTime}</td>
                <td>{detail.loadEfficiency}</td>
                <td>{detail.unloadTime}</td>
                <td>{detail.unloadEfficiency}</td>
                <td>{detail.portFee}</td>
                <td>{detail.avgAnchorTime}</td>
                <td>
                    <DeleteButton
                        type="portId"
                        deleteUrl="port-other"
                        identifier={detail.portId}
                        onDeleted={refreshPortOthers}
                    />
                    {/*<EditButton/>*/}
                </td>
            </tr>))}</tbody>
        </Table>) : (<Alert variant="info">未找到港口其他信息</Alert>)}
    </Container>);
}

export default PortOtherList;
