import React, {useState, useEffect, useContext} from 'react';
import AuthContext from '../context/AuthContext';
import PageSizeSelect from '../util/PageSizeSelect';
import ShipIdleLocationInsert from './ShipIdleLocationInsert';
import EditButton from "../util/EditButton.jsx";
import DeleteButton from "../util/DeleteButton.jsx";
import SortFieldSelect from "../util/SortFieldSelect.jsx";
import SortDirectionSelect from "../util/SortDirectionSelect.jsx";
import PageSelector from "../util/PageSelector.jsx";
import {Alert, Col, Row, Table} from "react-bootstrap";
import Container from "react-bootstrap/Container";

function ShipIdleLocationList() {
    const [locations, setLocations] = useState([]);
    const [totalPage, setTotalPage] = useState(1);
    const [pageSize, setPageSize] = useState(5);
    const [currentPage, setCurrentPage] = useState(1);
    const [sortField, setSortField] = useState('imo');
    const [sortDirection, setSortDirection] = useState('asc');
    const [refresh, setRefresh] = useState(false);
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    useEffect(() => {
        const fetchLocations = async () => {
            try {
                const response = await fetch(`${apiURL}/ship-idle-location/list`, {
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
                const data = await response.json();
                if (data && data.data.list) {
                    setLocations(data.data.list);
                    setTotalPage(Math.ceil((data.data.paging.total) / pageSize))
                }
            } catch (error) {
                console.error('Failed to fetch idle ship locations:', error);
            }
        };

        fetchLocations().then(r => console.log(r))
    }, [token, pageSize, refresh, sortDirection, sortField, currentPage]);

    const refreshLocations = () => {
        setRefresh(!refresh);
    };

    return (
        <Container className="shipIdleLocationList">
            <br/>
            <h2>船舶在港位置</h2>
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
                                     sortFields={['imo', 'portId']}/>
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
            <ShipIdleLocationInsert onLocationAdded={refreshLocations}/>
            <br/>
            <br/>
            {locations.length > 0 ? (
                <Table striped bordered hover style={{whiteSpace: 'nowrap'}}>
                    <thead>
                    <tr>
                        <th>IMO</th>
                        <th>Port ID</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    {locations.map((location, index) => (
                        <tr key={index}>

                            <td>{location.imo}</td>
                            <td>{location.portId}</td>
                            <td>
                                <DeleteButton
                                    type="imo"
                                    deleteUrl={`ship-idle-location`}
                                    identifier={location.imo}
                                    onDeleted={refreshLocations}
                                    token={token}
                                />
                                {/*<EditButton/>*/}
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </Table>
            ) : (
                <Alert variant="info">未找到船舶在港位置</Alert>
            )}
        </Container>
    );
}

export default ShipIdleLocationList;
