import React, {useState, useEffect, useContext} from 'react';
import AuthContext from '../context/AuthContext';
import ModalTemplate from '../util/ModalTemplate';
import ShipEconUpdate from "./ShipEconUpdate.jsx";
import {Button, Modal} from "react-bootstrap";

function ShipEconDetail({imo}) {
    const [shipEconDetails, setShipEconDetails] = useState(null);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [refresh, setRefresh] = useState(false);
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    useEffect(() => {
        const fetchCargoDetails = async () => {
            try {
                const response = await fetch(`${apiURL}/ship-econ-indicator/list?imo=${imo}`, {
                    method: 'POST', headers: {
                        'Authorization': `Bearer ${token}`,
                    }
                });

                const data = await response.json();
                if (data.code === 0) {
                    setShipEconDetails(data.data);
                } else {
                    alert(data.msg || 'Failed to fetch ship econ details.');
                }
            } catch (error) {
                console.error('Error fetching ship econ details:', error);
                alert('Error fetching ship econ details. Please try again.');
            }
        };

        fetchCargoDetails().then(r => console.log(r));

    }, [imo, refresh]);

    const refreshDetail = () => {
        setRefresh(!refresh);
    }

    return (<>
            <Button variant="primary" onClick={() => setIsModalOpen(true)}>参数设定</Button>
            <Modal show={isModalOpen} onHide={() => setIsModalOpen(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>船舶经济指标</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <ShipEconUpdate
                        imo={imo}
                        details={shipEconDetails}
                        onUpdate={refreshDetail}
                    />
                    {/*{shipEconDetails ? (*/}
                    {/*    <ul>*/}
                    {/*        {Object.entries(shipEconDetails).map(([key, value]) => (*/}
                    {/*            <li key={key}>{`${key}: ${value}`}</li>*/}
                    {/*        ))}*/}
                    {/*    </ul>*/}
                    {/*) : (*/}
                    {/*    <p>Loading ship econ details...</p>*/}
                    {/*)}*/}
                </Modal.Body>
            </Modal>
        </>
    );
}

export default ShipEconDetail;
