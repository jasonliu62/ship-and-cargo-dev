// ShipIdleLocationInsert.jsx
import React, { useState, useContext } from 'react';
import AuthContext from '../context/AuthContext';
import ModalTemplate from '../util/ModalTemplate';
import {Button, FloatingLabel, Modal} from "react-bootstrap";
import Form from "react-bootstrap/Form";

const apiURL = import.meta.env.VITE_API_URL;

function ShipIdleLocationInsert({ onLocationAdded }) {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [newLocation, setNewLocation] = useState({
        imo: '',
        portId: '',
    });
    const { token } = useContext(AuthContext);

    const handleInputChange = (e) => {
        setNewLocation({ ...newLocation, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (Object.values(newLocation).some(value => value.trim() === '')) {
            alert('Please fill out all fields before submitting.');
            return;
        }

        try {
            const response = await fetch(`${apiURL}/ship-idle-location/insert`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify(newLocation)
            });

            if (response.ok) {
                setIsModalOpen(false);
                setNewLocation({
                    imo: '',
                    portId: '',
                });
                onLocationAdded();
            } else {
                const errorData = await response.json();
                alert(errorData.msg || 'Failed to add the idle ship location. Please check the information provided.');
            }
        } catch (error) {
            console.error('Error adding idle ship location:', error);
            alert('Failed to add the idle ship location. Error: ' + error.message);
        }
    };

    return (
        <>
            <Button variant="primary" onClick={() => setIsModalOpen(true)}>添加船舶在港位置</Button>
            <Modal show={isModalOpen} onHide={() => setIsModalOpen(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>船舶在港位置</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit={handleSubmit}>
                        <FloatingLabel controlId="imo" label="IMO" className="mb-3">
                            <Form.Control type="text" name="imo" value={newLocation.imo} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="portId" label="Port ID" className="mb-3">
                            <Form.Control type="text" name="portId" value={newLocation.portId} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <Button variant="primary" type="submit">添加</Button>
                    </Form>
                </Modal.Body>
            </Modal>
        </>
    );
}

export default ShipIdleLocationInsert;
