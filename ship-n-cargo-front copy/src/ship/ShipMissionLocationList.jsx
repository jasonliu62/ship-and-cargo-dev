import React, {useState, useEffect, useContext} from 'react';
import AuthContext from '../context/AuthContext'; // Ensure the correct path
import PageSizeSelect from '../util/PageSizeSelect';
import DeleteButton from "../util/DeleteButton.jsx";
import EditButton from "../util/EditButton.jsx";
import ShipMissionLocationInsert from "./ShipMissionLoactionInsert.jsx";
import SortFieldSelect from "../util/SortFieldSelect.jsx";
import SortDirectionSelect from "../util/SortDirectionSelect.jsx";
import PageSelector from "../util/PageSelector.jsx";
import {Alert, Col, Row, Table} from "react-bootstrap";
import Container from "react-bootstrap/Container"; // Ensure the correct path

function ShipMissionLocationList() {
    const [shipLocations, setShipLocations] = useState([]);
    const [pageSize, setPageSize] = useState(5);
    const [totalPage, setTotalPage] = useState(1);
    const [currentPage, setCurrentPage] = useState(1);
    const [sortField, setSortField] = useState('imo');
    const [sortDirection, setSortDirection] = useState('asc');
    const [refresh, setRefresh] = useState(false); // Used to trigger refresh
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL; // Ensure this is defined in your .env file

    useEffect(() => {
        const fetchShipLocations = async () => {
            try {
                const response = await fetch(`${apiURL}/ship-mission-location/list`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`,
                    },
                    body: JSON.stringify({
                        pagination: {
                            current: currentPage,
                            pageSize: pageSize,
                            sortBy: [`${sortField}:${sortDirection}`]
                        }
                    })
                });
                const {data} = await response.json();
                if (data && data.list) {
                    setShipLocations(data.list);
                    setTotalPage(Math.ceil((data.paging.total) / pageSize))
                }
            } catch (error) {
                console.error('Failed to fetch ship locations:', error);
            }
        };

        fetchShipLocations().then(r => console.log(r));
    }, [token, pageSize, refresh, sortDirection, sortField, currentPage]);

    const refreshShipLocations = () => {
        setRefresh(!refresh);
    };

    return (
        <Container className="shipActiveLocation">
            <br/>
            <h2>船舶实时定位信息</h2>
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
                                     sortFields={['imo', 'latitude', 'longitude', 'postPortId', 'nextPortId', 'speed'
                                         , 'departureDistance', 'arrivalDistance', 'currentTime']}/>
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
            <ShipMissionLocationInsert onLocationAdded={refreshShipLocations}/>
            <br/>
            <br/>
            {shipLocations.length > 0 ? (
                <Table striped bordered hover style={{whiteSpace: 'nowrap'}}>
                    <thead>
                    <tr>
                        <th>IMO</th>
                        <th>纬度</th>
                        <th>经度</th>
                        <th>上一驶离港</th>
                        <th>下一到达港</th>
                        <th>速度</th>
                        <th>上一驶离港距离</th>
                        <th>下一到达港距离</th>
                        <th>时间</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    {shipLocations.map((location, index) => (
                        <tr key={index}>

                            <td>{location.imo}</td>
                            <td>{location.latitude}</td>
                            <td>{location.longitude}</td>
                            <td>{location.postPortId}</td>
                            <td>{location.nextPortId}</td>
                            <td>{location.speed}</td>
                            <td>{location.departureDistance}</td>
                            <td>{location.arrivalDistance}</td>
                            <td>{location.currentTime}</td>
                            <td>
                                <DeleteButton
                                    type="imo"
                                    deleteUrl="ship-mission-location"
                                    identifier={location.imo}
                                    onDeleted={refreshShipLocations}
                                />
                                {/*<EditButton/>*/}
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </Table>
            ) : (
                <Alert variant="info">未找到船舶定位信息</Alert>
            )}
        </Container>
    );
}

export default ShipMissionLocationList;
