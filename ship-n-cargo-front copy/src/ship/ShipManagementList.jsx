import React, {useState, useEffect, useContext} from 'react';
import AuthContext from '../context/AuthContext';
import PageSizeSelect from '../util/PageSizeSelect';
import ShipManagementInsert from "./ShipManagementInsert.jsx";
import DeleteButton from "../util/DeleteButton.jsx";
import EditButton from "../util/EditButton.jsx";
import SortFieldSelect from "../util/SortFieldSelect.jsx";
import SortDirectionSelect from "../util/SortDirectionSelect.jsx";
import PageSelector from "../util/PageSelector.jsx";
import Container from "react-bootstrap/Container";
import {Alert, Col, Row, Table} from "react-bootstrap";

function ShipManagementList() {
    const [shipManagements, setShipManagements] = useState([]);
    const [pageSize, setPageSize] = useState(5);
    const [totalPage, setTotalPage] = useState(1);
    const [currentPage, setCurrentPage] = useState(1);
    const [sortField, setSortField] = useState('imo');
    const [sortDirection, setSortDirection] = useState('asc');
    const [refresh, setRefresh] = useState(false);
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    useEffect(() => {
        const fetchShipManagements = async () => {
            try {
                const response = await fetch(`${apiURL}/ship-management/list`, {
                    method: 'POST', headers: {
                        'Content-Type': 'application/json', 'Authorization': `Bearer ${token}`,
                    }, body: JSON.stringify({
                        pagination: {
                            current: currentPage,
                            pageSize: pageSize,
                            sortBy: [`${sortField}:${sortDirection}`]
                        }
                    })
                });
                const {data} = await response.json();
                if (data && data.list) {
                    setShipManagements(data.list);
                    setTotalPage(Math.ceil((data.paging.total)/pageSize))
                }
            } catch (error) {
                console.error('Failed to fetch ship managements:', error);
            }
        };

        if (token) {
            fetchShipManagements().then(r => console.log(r));
        }
    }, [token, pageSize, refresh, sortDirection, sortField, currentPage]);

    const refreshShips = () => {
        setRefresh(!refresh);
    };

    return (        <Container className="shipManagementList">
        <br/>
        <h2>船舶经营信息</h2>
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
                                 sortFields={['imo', 'shipOperatingDay', 'shipTotalTime', 'dailyCapitalCost', 'dailyOperatingCost', 'acceptableCargoType'
                                     ,'owner','operator','navigationArea','shipDepartment','shipEmptyPort','shipEmptyTime']}/>
            </Col>
            <Col>
                <SortDirectionSelect selectedDirection={sortDirection}
                                     onSortDirectionChange={(e) => setSortDirection(e.target.value)}/>
            </Col>
        </Row>
        <br/>
        <PageSelector currentPage={currentPage} totalPages={totalPage} onPageChange={(e) => {setCurrentPage(e)}}/>
        <br/>
        <ShipManagementInsert onShipManagementAdded={refreshShips}/>
        <br/>
        <br/>
        {shipManagements.length > 0 ? (
            <Table striped bordered hover>
                <thead>
                <tr style={{whiteSpace: 'nowrap'}}>
                    <th>IMO</th>
                    <th>船舶营运天</th>
                    <th>在册时间</th>
                    <th>每日资本成本</th>
                    <th>每日营运成本</th>
                    <th>可载货物</th>
                    <th>所有权公司</th>
                    <th>经营权公司</th>
                    <th>运营航区</th>
                    <th>所属部门</th>
                    <th>预空港口</th>
                    <th>预空时间</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                {shipManagements.map((ship, index) => (
                    <tr key={index}>

                        <td>{ship.imo}</td>
                        <td>{ship.shipOperatingDay}</td>
                        <td>{ship.shipTotalTime}</td>
                        <td>{ship.dailyCapitalCost}</td>
                        <td>{ship.dailyOperatingCost}</td>
                        <td>{ship.acceptableCargoType}</td>
                        <td>{ship.owner}</td>
                        <td>{ship.operator}</td>
                        <td>{ship.navigationArea}</td>
                        <td>{ship.shipDepartment}</td>
                        <td>{ship.shipEmptyPort}</td>
                        <td>{ship.shipEmptyTime}</td>
                        <td>
                            <DeleteButton
                                type="imo"
                                deleteUrl="ship-management"
                                identifier={ship.imo}
                                onDeleted={refreshShips}
                            />
                            {/*<EditButton/>*/}
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
        ) : (
            <Alert variant="info">未找到船舶经营信息</Alert>
        )}
    </Container>);
}

export default ShipManagementList;
