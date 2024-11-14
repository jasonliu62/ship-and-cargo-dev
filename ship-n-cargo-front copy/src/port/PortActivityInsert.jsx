// PortActivityInsert.jsx
import React, { useState, useContext } from 'react';
import AuthContext from '../context/AuthContext';
import ModalTemplate from '../util/ModalTemplate';
import {Button, FloatingLabel, Modal} from "react-bootstrap";
import Form from "react-bootstrap/Form"; // Make sure you have a modal component

const apiURL = import.meta.env.VITE_API_URL; // Ensure this is defined in your environment

function PortActivityInsert({ onActivityAdded }) {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [newActivity, setNewActivity] = useState({
        portId: '',
        shipAmount: ''
    });
    const { token } = useContext(AuthContext);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewActivity(prevState => ({ ...prevState, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Basic validation: Check if all fields are filled
        if (!newActivity.portId.trim() || !newActivity.shipAmount.trim()) {
            alert('Please fill out all fields before submitting.');
            return;
        }

        try {
            const response = await fetch(`${apiURL}/port-activity/insert`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify(newActivity)
            });

            if (response.ok) {
                setIsModalOpen(false); // Close the modal on success
                // Reset the form state
                setNewActivity({
                    portId: '',
                    shipAmount: ''
                });
                onActivityAdded(); // Trigger to refresh the parent component's activity list
            } else {
                const errorData = await response.json();
                alert(errorData.msg || 'Failed to add the port activity. Please check the information provided.');
            }
        } catch (error) {
            console.error('Error adding port activity:', error);
            alert('Failed to add the port activity. Error: ' + error.message);
        }
    };

    return (
        <>
            <Button variant="primary" onClick={() => setIsModalOpen(true)}>添加港口动态</Button>
            <Modal show={isModalOpen} onHide={() => setIsModalOpen(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>添加港口动态</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit={handleSubmit}>
                        <FloatingLabel controlId="portId" label="Port ID" className="mb-3">
                            <Form.Control type="text" name="portId" value={newActivity.portId} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="shipAmount" label="Ship Amount" className="mb-3">
                            <Form.Control type="number" name="shipAmount" value={newActivity.shipAmount} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <Button variant="primary" type="submit">添加</Button>
                    </Form>
                </Modal.Body>
            </Modal>
        </>
    );
}

export default PortActivityInsert;
