import React, {useState, useEffect, useContext} from 'react';
import AuthContext from '../context/AuthContext';
import PageSizeSelect from '../util/PageSizeSelect';
import ExpandByGroupID from "./ExpandByGroupID.jsx";
import SortFieldSelect from "../util/SortFieldSelect.jsx";
import SortDirectionSelect from "../util/SortDirectionSelect.jsx";
import PageSelector from "../util/PageSelector.jsx";
import Container from "react-bootstrap/Container";
import {Button, Col, Row, Table} from "react-bootstrap";

function SimulationHistoryList() {
    const [simulationHistory, setSimulationHistory] = useState([]);
    const [pageSize, setPageSize] = useState(5);
    const [totalPage, setTotalPage] = useState(1);
    const [currentPage, setCurrentPage] = useState(1);
    const [sortField, setSortField] = useState('groupId');
    const [sortDirection, setSortDirection] = useState('asc');
    const [expandedRow, setExpandedRow] = useState(null);
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    useEffect(() => {
        const fetchSimulationHistory = async () => {
            try {
                const response = await fetch(`${apiURL}/simulation/list-simulation-history`, {
                    method: 'POST', headers: {
                        'Content-Type': 'application/json', 'Authorization': `Bearer ${token}`,
                    }, body: JSON.stringify({
                        pagination: {
                            current: currentPage, pageSize: pageSize, sortBy: [`${sortField}:${sortDirection}`],
                        }
                    })
                });
                const data = await response.json();
                if (data.code === 0 && data.data.list) {
                    setSimulationHistory(data.data.list);
                    setTotalPage(Math.ceil((data.data.paging.total) / pageSize))
                }
            } catch (error) {
                console.error('Failed to fetch simulation history:', error);
            }
        };
        if (token) {
            fetchSimulationHistory().then(r => console.log(r));
        }
    }, [pageSize, token, sortField, sortDirection, currentPage]);

    const handleToggleExpand = (groupId) => {
        setExpandedRow(prevGroupId => prevGroupId === groupId ? null : groupId);
    };

    return (<Container>
        <br/>
        <h2>模拟结果历史</h2>
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
                                 sortFields={['groupId', 'cargoIdCombo', 'imoCombo', 'startDay', 'endDay', 'createdAt']}/>
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
        <Table striped bordered hover style={{whiteSpace: 'nowrap'}}>
            <thead>
            <tr>
                <th>第n次模拟</th>
                <th>货物ID组合</th>
                <th>船舶IMO组合</th>
                <th>模拟起始时间</th>
                <th>模拟结束时间</th>
                <th>模拟创建时间</th>
                <th>查看方案</th>
            </tr>
            </thead>
            <tbody>
            {simulationHistory.map(result => (
                <React.Fragment key={result.groupId}>
                    <tr>
                        <td>{result.groupId}</td>
                        <td>{result.cargoIdCombo}</td>
                        <td>{result.imoCombo}</td>
                        <td>{result.startDay}</td>
                        <td>{result.endDay}</td>
                        <td>{new Date(result.createdAt).toLocaleString()}</td>
                        <td>
                            <Button variant="primary" onClick={() => handleToggleExpand(result.groupId)}>
                                {expandedRow === result.groupId ? '隐藏方案' : '显示方案'}
                            </Button>
                        </td>
                    </tr>
                    {expandedRow === result.groupId && (
                        <tr>
                            <td colSpan="7">
                                <ExpandByGroupID groupId={result.groupId}/>
                            </td>
                        </tr>
                    )}
                </React.Fragment>
            ))}
            </tbody>
        </Table>
    </Container>);
}

export default SimulationHistoryList;
