import React, {useState, useEffect, useContext} from 'react';
import AuthContext from '../context/AuthContext';
import PageSizeSelect from '../util/PageSizeSelect';
import ShipInsert from './ShipInsert';
import DeleteButton from "../util/DeleteButton.jsx";
import EditButton from "../util/EditButton.jsx";
import SortFieldSelect from "../util/SortFieldSelect.jsx";
import SortDirectionSelect from "../util/SortDirectionSelect.jsx";
import PageSelector from "../util/PageSelector.jsx";
import {Alert, Col, Row, Table} from "react-bootstrap";
import Container from "react-bootstrap/Container";

function ShipList() {
    const [ships, setShips] = useState([]);
    const [pageSize, setPageSize] = useState(5);
    const [totalPage, setTotalPage] = useState(1);
    const [currentPage, setCurrentPage] = useState(1);
    const [sortField, setSortField] = useState('imo');
    const [sortDirection, setSortDirection] = useState('asc');
    const [refresh, setRefresh] = useState(false);
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    useEffect(() => {
        const fetchShips = async () => {
            try {
                const response = await fetch(`${apiURL}/ship/list`, {
                    method: 'POST', headers: {
                        'Content-Type': 'application/json', 'Authorization': `Bearer ${token}`,
                    }, body: JSON.stringify({
                        pagination: {
                            current: currentPage, pageSize: pageSize, sortBy: [`${sortField}:${sortDirection}`]
                        }
                    })
                });
                const {data} = await response.json();
                if (data && data.list) {
                    setShips(data.list);
                    setTotalPage(Math.ceil((data.paging.total) / pageSize))
                }
            } catch (error) {
                console.error('Failed to fetch ships:', error);
            }
        };

        if (token) {
            fetchShips().then(r => console.log(r));
        }
    }, [token, pageSize, refresh, sortDirection, sortField, currentPage]);

    const refreshShips = () => {
        setRefresh(!refresh);
    };

    return (<Container className="shipList">
        <br/>
        <h2>船舶列表</h2>
        <br/>
        <Row>
            <Col>
                <PageSizeSelect pageSize={pageSize} onPageSizeChange={(e) => {
                    setPageSize(e.target.value)
                    setCurrentPage(1)
                }}/>
            </Col>
            <Col>
                <SortFieldSelect selectedField={sortField} onSortFieldChange={(e) => setSortField(e.target.value)}
                                 sortFields={['imo', 'deadWeight', 'shipType', 'shipName', 'shipConstant', 'deadDraft', 'emptyDraft', 'ballastDraft', 'shipCubic', 'flagState', 'shipTag', 'owner', 'operator']}/>
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
        <ShipInsert onShipAdded={refreshShips}/>
        <br/>
        <br/>
        {ships.length > 0 ? (<Table striped bordered hover>
            <thead>
            <tr style={{whiteSpace: 'nowrap'}}>
                <th>IMO</th>
                <th>定额载重吨</th>
                <th>船舶类型</th>
                <th>船舶名称</th>
                <th>常定重量</th>
                <th>满载吃水</th>
                <th>空载吃水</th>
                <th>压载吃水</th>
                <th>船舶舱容</th>
                <th>船旗国籍</th>
                <th>性质</th>
                <th>所有权公司</th>
                <th>经营权公司</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            {ships.map((ship, index) => (<tr key={index}>

                <td>{ship.imo}</td>
                <td>{ship.deadWeight}</td>
                <td>{ship.shipType}</td>
                <td>{ship.shipName}</td>
                <td>{ship.shipConstant}</td>
                <td>{ship.deadDraft}</td>
                <td>{ship.emptyDraft}</td>
                <td>{ship.ballastDraft}</td>
                <td>{ship.shipCubic}</td>
                <td>{ship.flagState}</td>
                <td>{ship.shipTag}</td>
                <td>{ship.owner}</td>
                <td>{ship.operator}</td>
                <td>
                    <DeleteButton
                        type="imo"
                        deleteUrl="ship"
                        identifier={ship.imo}
                        onDeleted={refreshShips}
                    />
                    {/*<EditButton/>*/}
                </td>
            </tr>))}
            </tbody>
        </Table>) : (<Alert variant="info">未找到船舶</Alert>)}
    </Container>);
}

export default ShipList;
