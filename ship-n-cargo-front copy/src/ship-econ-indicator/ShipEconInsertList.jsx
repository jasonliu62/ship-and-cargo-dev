// ShipEconInsertList.jsx
import React, {useState, useContext} from 'react';
import AuthContext from '../context/AuthContext';
import {Button, FloatingLabel, Modal} from "react-bootstrap";
import Form from "react-bootstrap/Form";

function ShipEconInsertList({onCargoAdded}) {
    const [imo, setImo] = useState('');
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;
    const [isModalOpen, setIsModalOpen] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!imo) {
            alert("Please fill imo.");
            return;
        }

        const url = `${apiURL}/ship-econ-indicator/insert-list?imo=${imo}`;

        try {
            const response = await fetch(url, {
                method: 'POST', headers: {
                    'Authorization': `Bearer ${token}`,
                }
            });
            const data = await response.json();
            if (data.message === "Ship does not exist.") {
                alert("Insert failed, ship does not exist.");
            } else if (response.ok) {
                alert('ship inserted successfully');
                onCargoAdded(); // Callback to refresh the list or clear form
            } else {
                alert('Failed to insert ship. Please try again.');
            }
        } catch (error) {
            console.error('Error inserting ship:', error);
            alert('Error inserting ship. Please try again.');
        }
    };

    return (<>
        <Button variant="primary" onClick={() => setIsModalOpen(true)}>添加船舶经济指标</Button>
        <Modal show={isModalOpen} onHide={() => setIsModalOpen(false)}>
            <Modal.Header closeButton>
                <Modal.Title>添加船舶经济指标</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit}>
                    <FloatingLabel controlId="imo" label="IMO" className="mb-3">
                        <Form.Control type="text" value={imo} onChange={e => setImo(e.target.value)} required />
                    </FloatingLabel>
                    <Button variant="primary" type="submit">添加</Button>
                </Form>
            </Modal.Body>
        </Modal>
    </>);
}

export default ShipEconInsertList;
