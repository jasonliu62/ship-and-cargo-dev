import React, {useState, useEffect, useContext} from 'react';
import AuthContext from '../context/AuthContext';
import PageSizeSelect from '../util/PageSizeSelect';
import PortActivityInsert from "./PortActivityInsert.jsx";
import DeleteButton from "../util/DeleteButton.jsx";
import EditButton from "../util/EditButton.jsx";
import SortFieldSelect from "../util/SortFieldSelect.jsx";
import SortDirectionSelect from "../util/SortDirectionSelect.jsx";
import PortInsert from "./PortInsert.jsx";
import PageSelector from "../util/PageSelector.jsx";
import {Alert, Col, Row, Table} from "react-bootstrap";
import Container from "react-bootstrap/Container"; // Assuming you have a component for page size selection

function PortActivityList() {
    const [activities, setActivities] = useState([]);
    const [totalPage, setTotalPage] = useState(1);
    const [pageSize, setPageSize] = useState(5);
    const [currentPage, setCurrentPage] = useState(1);
    const [sortField, setSortField] = useState('portId');
    const [sortDirection, setSortDirection] = useState('asc');
    const [refresh, setRefresh] = useState(false); // For triggering re-fetching
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    useEffect(() => {
        const fetchPortActivities = async () => {
            try {
                const response = await fetch(`${apiURL}/port-activity/list`, {
                    method: 'POST', headers: {
                        'Content-Type': 'application/json', 'Authorization': `Bearer ${token}`,
                    }, body: JSON.stringify({
                        pagination: {
                            current: currentPage, pageSize: pageSize, sortBy: [`${sortField}:${sortDirection}`]
                        }
                    })
                });
                const data = await response.json();
                if (data.code === 0 && data.data && data.data.list) {
                    setActivities(data.data.list);
                    setTotalPage(Math.ceil((data.data.paging.total) / pageSize))
                } else {
                    console.error('Failed to fetch port activities: ', data.msg);
                }
            } catch (error) {
                console.error('Failed to fetch port activities:', error);
            }
        };

        fetchPortActivities();
    }, [token, pageSize, refresh, sortDirection, sortField, currentPage]);

    const refreshPorts = () => {
        setRefresh(!refresh);
    };

    return (<Container>
        <br/>
        <h2>港口动态信息</h2>
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
                                 sortFields={['portId', 'shipAmount']}/>
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
        <PortActivityInsert onActivityAdded={refreshPorts}/>
        <br/>
        <br/>

        {activities.length > 0 ? (<Table striped bordered hover>
            <thead>
            <tr>
                <th>Port ID</th>
                <th>船舶数量</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            {activities.map((activity, index) => (<tr key={index}>
                    <td>{activity.portId}</td>
                    <td>{activity.shipAmount}</td>
                    <td>
                        <DeleteButton
                            type="portId"
                            deleteUrl="port-activity"
                            identifier={activity.portId}
                            onDeleted={refreshPorts}
                        />
                        {/*<EditButton/>*/}
                    </td>
                </tr>

            ))}
            </tbody>
        </Table>) : (<Alert variant="info">未找到港口动态</Alert>)}
    </Container>);
}

export default PortActivityList;
