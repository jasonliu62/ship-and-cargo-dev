// PortInsert.jsx
import React, { useState, useContext } from 'react';
import AuthContext from '../context/AuthContext'; // Adjust path as necessary
import ModalTemplate from '../util/ModalTemplate';
import {Button, FloatingLabel, Modal} from "react-bootstrap";
import Form from "react-bootstrap/Form"; // Ensure you have this component or similar for modals

const apiURL = import.meta.env.VITE_API_URL; // Ensure this is defined in your environment

function PortInsert({ onPortAdded }) {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [newPort, setNewPort] = useState({
        portId: '',
        nameCHN: '',
        nameENG: '',
        latitude: '',
        longitude: '',
        minDraft: ''
    });
    const { token } = useContext(AuthContext);

    const handleInputChange = (e) => {
        setNewPort({ ...newPort, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Basic validation: Check if all fields are filled
        if (Object.values(newPort).some(value => value.trim() === '')) {
            alert('Please fill out all fields before submitting.');
            return;
        }

        try {
            const response = await fetch(`${apiURL}/port/insert`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify(newPort)
            });

            if (response.ok) {
                setIsModalOpen(false); // Close the modal on success
                // Reset the form state
                setNewPort({
                    portId: '',
                    nameCHN: '',
                    nameENG: '',
                    latitude: '',
                    longitude: '',
                    minDraft: ''
                });
                onPortAdded(); // Trigger to refresh the parent component's port list
            } else {
                const errorData = await response.json();
                alert(errorData.msg || 'Failed to add the port. Please check the information provided.');
            }
        } catch (error) {
            console.error('Error adding port:', error);
            alert('Failed to add the port. Error: ' + error.message);
        }
    };

    return (
        <>
            <Button variant="primary" onClick={() => setIsModalOpen(true)}>添加港口属性</Button>
            <Modal show={isModalOpen} onHide={() => setIsModalOpen(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>添加港口属性</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit={handleSubmit}>
                        <FloatingLabel controlId="portId" label="Port ID" className="mb-3">
                            <Form.Control type="text" name="portId" value={newPort.portId} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="nameCHN" label="Name (CHN)" className="mb-3">
                            <Form.Control type="text" name="nameCHN" value={newPort.nameCHN} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="nameENG" label="Name (ENG)" className="mb-3">
                            <Form.Control type="text" name="nameENG" value={newPort.nameENG} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="latitude" label="Latitude" className="mb-3">
                            <Form.Control type="text" name="latitude" value={newPort.latitude} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="longitude" label="Longitude" className="mb-3">
                            <Form.Control type="text" name="longitude" value={newPort.longitude} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="minDraft" label="Min Draft" className="mb-3">
                            <Form.Control type="text" name="minDraft" value={newPort.minDraft} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <Button variant="primary" type="submit">添加</Button>
                    </Form>
                </Modal.Body>
            </Modal>
        </>
    );
}

export default PortInsert;
