// ShipEconSortList.jsx
import React, {useState, useEffect, useContext} from 'react';
import AuthContext from '../context/AuthContext';
import ShipEconDeleteSingle from "./ShipEconDeleteSingle.jsx";
import ShipEconInsertList from "./ShipEconInsertList.jsx";
import ShipEconDetail from "./ShipEconDetail.jsx";
import ShipEconDeleteAll from "./ShipEconDeleteAll.jsx";
import {Alert, Table} from "react-bootstrap";
import Container from "react-bootstrap/Container";

function ShipEconSortList() {
    const [shipEcons, setShipEcons] = useState([]);
    const [refresh, setRefresh] = useState(false);
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    useEffect(() => {
        const fetchShipEcon = async () => {
            try {
                const response = await fetch(`${apiURL}/ship-econ-indicator/sort-list`, {
                    method: 'POST', headers: {
                        'Authorization': `Bearer ${token}`,
                    },
                });
                const data = await response.json();
                if (data.code === 0 && data.data && data.data.list) {
                    setShipEcons(data.data.list);
                }
            } catch (error) {
                console.error('Failed to fetch Ship Econ Indicator:', error);
            }
        };

        fetchShipEcon();
    }, [token, refresh]); // Dependency array includes token to refetch if auth changes

    const refreshOverallList = () => {
        setRefresh(!refresh);
    };

    return (<Container>
        <br/>
        <h2>船舶经济指标参数</h2>
        <br/>
        <ShipEconInsertList onCargoAdded={refreshOverallList}/>
        <br/>
        <br/>
        <br/>
        {shipEcons.length > 0 ? (<Table striped bordered hover>
            <thead>
            <tr>
                <th>参赛设定</th>
                <th>imo</th>
                <th>船舶类型</th>
                <th>船舶名称</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            {shipEcons.map((shipEcon, index) => (<tr key={index}>
                <td>
                    <ShipEconDetail imo={shipEcon.imo}/>
                </td>
                <td>{shipEcon.imo}</td>
                <td>{shipEcon.shipType}</td>
                <td>{shipEcon.shipName}</td>
                <td><ShipEconDeleteSingle
                    type="imo"
                    deleteUrl="ship-econ-indicator"
                    identifier={shipEcon.imo}
                    onDeleted={refreshOverallList}
                /></td>
            </tr>))}
            </tbody>
        </Table>) : (<Alert variant="info">未找到经济指标</Alert>)}
        <br/>
        <br/>
        <ShipEconDeleteAll onDeleted={refreshOverallList}/>
    </Container>);
}

export default ShipEconSortList;
