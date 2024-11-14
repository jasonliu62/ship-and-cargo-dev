import React, {useState, useContext, useEffect} from 'react';
import AuthContext from '../context/AuthContext';
import PageSizeSelect from "../util/PageSizeSelect.jsx";
import SortFieldSelect from "../util/SortFieldSelect.jsx";
import SortDirectionSelect from "../util/SortDirectionSelect.jsx";
import PageSelector from "../util/PageSelector.jsx";
import Container from "react-bootstrap/Container";
import {Col, Row, Table} from "react-bootstrap";

function ExpandByOutputID({groupId, outputId}) {
    const [resultsByOutputId, setResultsByOutputId] = useState([]);
    const [pageSize, setPageSize] = useState(5);
    const [totalPage, setTotalPage] = useState(1);
    const [currentPage, setCurrentPage] = useState(1);
    const [sortField, setSortField] = useState('cargoId');
    const [sortDirection, setSortDirection] = useState('asc');
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    useEffect(() => {
        const fetchByOutputId = async () => {
            try {
                const response = await fetch(`${apiURL}/simulation/list-output-each?groupId=${groupId}&outputId=${outputId}`, {
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
                    setResultsByOutputId(data.data.list);
                    setTotalPage(Math.ceil((data.data.paging.total) / pageSize))
                } else {
                    alert("Failed to fetch more details: " + data.msg);
                }
            } catch (error) {
                console.error('Failed to fetch more details:', error);
                alert('Failed to execute the request. Please try again.');
            }
        }
        if (token) {
            fetchByOutputId().then(r => console.log(r));
        }
    }, [pageSize, token, sortField, sortDirection]);


    return (<Container>
        <Row style={{whiteSpace: 'nowrap'}}>
            <Col>
                <PageSizeSelect pageSize={pageSize} onPageSizeChange={(e) => {
                    setPageSize(e.target.value)
                    setCurrentPage(1)
                }}/>
            </Col>
            <Col>
                <SortFieldSelect selectedField={sortField} onSortFieldChange={(e) => setSortField(e.target.value)}
                                 sortFields={['cargoId', 'cargoType', 'shipCombo', 'profit']}/>
            </Col>
            <Col>
                <SortDirectionSelect selectedDirection={sortDirection}
                                     onSortDirectionChange={(e) => setSortDirection(e.target.value)}/>
            </Col>
        </Row>
        <br/>
        <PageSelector currentPage={currentPage} totalPages={totalPage} onPageChange={(e) => {
            setCurrentPage(e.target.value)
        }}/>
        <Table striped bordered hover>
            <thead>
            <tr>
                <th>货物ID</th>
                <th>货种</th>
                <th>船舶IMO组合</th>
                <th>利润/亏损</th>
            </tr>
            </thead>
            <tbody>
            {resultsByOutputId.map(result => (
                <tr key={result.cargoId}>
                    <td>{result.cargoId}</td>
                    <td>{result.cargoType}</td>
                    <td>{result.shipCombo}</td>
                    <td>{result.profit.toFixed(2)}</td>
                </tr>
            ))}
            </tbody>
        </Table>
    </Container>);
}

export default ExpandByOutputID;
