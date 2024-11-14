import React, {useState, useEffect, useContext} from 'react';
import AuthContext from '../context/AuthContext'; // Adjust the import path as necessary
import StatusSelect from './StatusSelect'; // Adjust the import path as necessary
import PageSizeSelect from '../util/PageSizeSelect.jsx';
import SortFieldSelect from "../util/SortFieldSelect.jsx";
import SortDirectionSelect from "../util/SortDirectionSelect.jsx";
import PageSelector from "../util/PageSelector.jsx"; // Adjust the import path as necessary
import {Table, Container, Alert, Row, Col} from 'react-bootstrap';


function CargoList() {
    const [cargos, setCargos] = useState([]);
    const [status, setStatus] = useState(0);
    const [pageSize, setPageSize] = useState(5);
    const [totalPage, setTotalPage] = useState(1);
    const [currentPage, setCurrentPage] = useState(1);
    const [sortField, setSortField] = useState('cargoId');
    const [sortDirection, setSortDirection] = useState('asc');
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    useEffect(() => {
        const fetchCargos = async () => {
            try {
                const response = await fetch(`${apiURL}/cargo/list`, {
                    method: 'POST', headers: {
                        'Content-Type': 'application/json', 'Authorization': `Bearer ${token}`,
                    }, body: JSON.stringify({
                        pagination: {
                            current: currentPage, pageSize: pageSize, sortBy: [`${sortField}:${sortDirection}`],
                        }, status: status
                    })
                });

                const {data} = await response.json();
                if (data && data.list) {
                    setCargos(data.list);
                    setTotalPage(Math.ceil((data.paging.total) / pageSize))
                }
            } catch (error) {
                console.error('Failed to fetch cargos:', error);
            }
        };

        if (token) {
            fetchCargos().then(r => console.log(r));
        }
    }, [token, status, pageSize, sortField, sortDirection, currentPage]);

    const formatDate = (value) => {
        if (typeof value === 'string' && value.includes('-')) {
            const options = {
                year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit'
            };
            return new Date(value).toLocaleDateString(undefined, options);
        }
        return value;
    };

    return (<Container className="cargoList">
        <br/>
        <h2>货物列表</h2>
        <br/>
        <StatusSelect status={status} onStatusChange={(newStatus) => setStatus(newStatus)}/>
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
                                 sortFields={['contractNumber', 'contractType', 'cargoId', 'cargoVolume', 'cargoflowArea', 'loadPortId', 'unloadPortId', 'layDay', 'dischargeDay', 'freightRate', 'volumeRate', 'loadportDepth', 'voyageNumber', 'voyageVolume', 'voyagePeriod']}/>
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
        {cargos.length > 0 ? (<Table striped bordered hover>
            <thead>
            <tr style={{whiteSpace: 'nowrap'}}>
                <th>合同编号</th>
                <th>合同类型</th>
                <th>货物ID</th>
                <th>货量</th>
                <th>货种</th>
                <th>货流起讫点</th>
                <th>装货港</th>
                <th>卸货港</th>
                <th>约定装货时间</th>
                <th>约定卸货时间</th>
                <th>运费率</th>
                <th>货运量运载差率</th>
                <th>装货港最小水深</th>
                <th>卸货港最小水深</th>
                <th>约定载运航次数</th>
                <th>单航次载运量</th>
                <th>载运周期</th>
            </tr>
            </thead>
            <tbody>
            {cargos.map((cargo, index) => (<tr key={index}>
                <td>{cargo.contractNumber}</td>
                <td>{cargo.contractType}</td>
                <td>{cargo.cargoId}</td>
                <td>{cargo.cargoVolume}</td>
                <td>{cargo.cargoType}</td>
                <td>{cargo.cargoflowArea}</td>
                <td>{cargo.loadPortId}</td>
                <td>{cargo.unloadPortId}</td>
                <td>{cargo.layDay}</td>
                <td>{cargo.dischargeDay}</td>
                <td>{cargo.freightRate}</td>
                <td>{cargo.volumeRate}</td>
                <td>{cargo.loadportDepth}</td>
                <td>{cargo.unloadportDepth}</td>
                <td>{cargo.voyageNumber}</td>
                <td>{cargo.voyageVolume}</td>
                <td>{cargo.voyagePeriod}</td>
            </tr>))}
            </tbody>
        </Table>) : (<Alert variant="info">未找到货物</Alert>)}

    </Container>);
}

export default CargoList;
