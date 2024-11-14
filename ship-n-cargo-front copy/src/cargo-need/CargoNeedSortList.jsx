import React, {useState, useEffect, useContext} from 'react';
import AuthContext from '../context/AuthContext';
import CargoNeedDeleteSingle from "./CargoNeedDeleteSingle.jsx";
import CargoNeedInsertList from "./CargoNeedInsertList.jsx";
import CargoNeedDetail from "./CargoNeedDetail.jsx";
import CargoNeedDeleteAll from "./CargoNeedDeleteAll.jsx";
import {Alert, Table} from "react-bootstrap";
import Container from "react-bootstrap/Container";

function CargoNeedSortList() {
    const [cargoNeeds, setCargoNeeds] = useState([]);
    const [refresh, setRefresh] = useState(false);
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    useEffect(() => {
        const fetchCargoNeeds = async () => {
            try {
                const response = await fetch(`${apiURL}/cargo-need/sort-list`, {
                    method: 'POST',
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    },
                });
                const data = await response.json();
                if (data.code === 0 && data.data && data.data.list) {
                    setCargoNeeds(data.data.list);
                }
            } catch (error) {
                console.error('Failed to fetch cargo needs:', error);
            }
        };

        fetchCargoNeeds();
    }, [token, refresh]);

    const refreshOverallList = () => {
        setRefresh(!refresh);
    };

    return (<Container>
        <br/>
        <h2>货运需求参数</h2>
        <br/>
        <CargoNeedInsertList onCargoAdded={refreshOverallList}/>
        <br/>
        <br/>
        <br/>
        {cargoNeeds.length > 0 ? (
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>参数设定</th>
                    <th>合同编号</th>
                    <th>货物ID</th>
                    <th>装货港</th>
                    <th>卸货港</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                {cargoNeeds.map((cargoNeed, index) => (
                    <tr key={index}>
                        <td>
                            <CargoNeedDetail cargoId={cargoNeed.cargoId}/>
                        </td>
                        <td>{cargoNeed.contractNumber}</td>
                        <td>{cargoNeed.cargoId}</td>
                        <td>{cargoNeed.loadPortId}</td>
                        <td>{cargoNeed.unloadPortId}</td>
                        <td>
                            <CargoNeedDeleteSingle
                                type="cargoId"
                                deleteUrl="cargo-need"
                                identifier={cargoNeed.cargoId}
                                onDeleted={refreshOverallList}
                            />
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
        ) : (
            <Alert variant="info">未找到货运需求</Alert>
        )}
        <br/>
        <br/>
        <CargoNeedDeleteAll deleteUrl="cargo-need" onDeleted={refreshOverallList}/>
    </Container>);
}

export default CargoNeedSortList;
