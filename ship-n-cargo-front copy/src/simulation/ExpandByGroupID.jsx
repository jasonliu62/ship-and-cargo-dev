import React, {useState, useContext, useEffect} from 'react';
import AuthContext from '../context/AuthContext';
import ExpandByOutputID from "./ExpandByOutputID.jsx";
import PageSizeSelect from "../util/PageSizeSelect.jsx";
import SortFieldSelect from "../util/SortFieldSelect.jsx";
import SortDirectionSelect from "../util/SortDirectionSelect.jsx";
import PageSelector from "../util/PageSelector.jsx";
import {Button, Col, Row, Table} from "react-bootstrap";
import Container from "react-bootstrap/Container";

function ExpandByGroupID({groupId}) {
    const [resultsByGroupId, setResultsByGroupId] = useState([]);
    const [pageSize, setPageSize] = useState(5);
    const [totalPage, setTotalPage] = useState(1);
    const [currentPage, setCurrentPage] = useState(1);
    const [expandedRow, setExpandedRow] = useState(null);
    const [sortField, setSortField] = useState('id');
    const [sortDirection, setSortDirection] = useState('asc');
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    useEffect(() => {
        const fetchByGroupId = async () => {
            try {
                const response = await fetch(`${apiURL}/simulation/list-output?groupId=${groupId}`, {
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
                    setResultsByGroupId(data.data.list);
                    setTotalPage(Math.ceil((data.data.paging.total) / pageSize))
                } else {
                    alert("Failed to fetch details: " + data.msg);
                }
            } catch (error) {
                console.error('Failed to fetch details:', error);
                alert('Failed to execute the request. Please try again.');
            }
        }
        if (token) {
            fetchByGroupId().then(r => console.log(r));
        }
    }, [pageSize, token, sortField, sortDirection, currentPage]);

    const handleToggleExpand = (outputId) => {
        setExpandedRow(prevOutputId => prevOutputId === outputId ? null : outputId);
    };


    return (        <Container>
        <Row style={{whiteSpace: 'nowrap'}}>
            <Col>
                <PageSizeSelect pageSize={pageSize} onPageSizeChange={(e) => {
                    setPageSize(e.target.value)
                    setCurrentPage(1)
                }}/>
            </Col>
            <Col>
                <SortFieldSelect selectedField={sortField} onSortFieldChange={(e) => setSortField(e.target.value)}
                                 sortFields={['id', 'actual_own', 'actual_rent', 'actual_all', 'virtual_own', 'virtual_rent'
                                     , 'virtual_all', 'spot_own', 'spot_rent','spot_all', 'total']}/>
            </Col>
            <Col>
                <SortDirectionSelect selectedDirection={sortDirection}
                                     onSortDirectionChange={(e) => setSortDirection(e.target.value)}/>
            </Col>
        </Row>
        <br/>
        <PageSelector currentPage={currentPage} totalPages={totalPage} onPageChange={(e) => {setCurrentPage(e)}}/>

        <Table striped bordered hover style={{whiteSpace: 'nowrap'}}>
            <thead>
            <tr>
                <th>方案编号</th>
                <th>Actual Own</th>
                <th>Actual Rent</th>
                <th>Actual All</th>
                <th>Virtual Own</th>
                <th>Virtual Rent</th>
                <th>Virtual All</th>
                <th>Spot Own</th>
                <th>Spot Rent</th>
                <th>Spot All</th>
                <th>Total</th>
                <th>盈亏明细</th>
            </tr>
            </thead>
            <tbody>
            {resultsByGroupId.map(result => (
                <React.Fragment key={result.id}>
                    <tr>
                        <td>{result.id}</td>
                        <td>{result.actual_own}</td>
                        <td>{result.actual_rent}</td>
                        <td>{result.actual_all}</td>
                        <td>{result.virtual_own}</td>
                        <td>{result.virtual_rent}</td>
                        <td>{result.virtual_all}</td>
                        <td>{result.spot_own}</td>
                        <td>{result.spot_rent}</td>
                        <td>{result.spot_all}</td>
                        <td>{result.total}</td>
                        <td>
                            <Button variant="primary" onClick={() => handleToggleExpand(result.id)}>
                                {expandedRow === result.id ? '隐藏明细' : '显示明细'}
                            </Button>
                        </td>
                    </tr>
                    {expandedRow === result.id && (
                        <tr>
                            <td colSpan="12">
                                <ExpandByOutputID groupId={result.groupId} outputId={result.id}/>
                            </td>
                        </tr>
                    )}
                </React.Fragment>
            ))}
            </tbody>
        </Table>
    </Container>);
}

export default ExpandByGroupID;
