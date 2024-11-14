import React, {useState, useContext} from 'react';
import AuthContext from '../context/AuthContext'; // Adjust path as necessary
import ModalTemplate from '../util/ModalTemplate';
import {Button, FloatingLabel, Modal} from "react-bootstrap";
import Form from "react-bootstrap/Form";

const apiURL = import.meta.env.VITE_API_URL;

function ShipInsert({onShipAdded}) {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [newShip, setNewShip] = useState({
        // Initialize all fields as empty strings to represent they need to be filled
        imo: '',
        deadWeight: '',
        shipType: '',
        shipName: '',
        shipConstant: '',
        deadDraft: '',
        emptyDraft: '',
        ballastDraft: '',
        shipCubic: '',
        flagState: '',
        shipTag: '',
        owner: '',
        operator: ''
    });
    const {token} = useContext(AuthContext);

    const handleInputChange = (e) => {
        setNewShip({...newShip, [e.target.name]: e.target.value});
    };


    const handleSubmit = async (e) => {
        e.preventDefault();
        if (Object.values(newShip).some(value => value.trim() === '')) {
            alert('Please fill out all fields before submitting.');
            return;
        }

        try {
            const response = await fetch(`${apiURL}/ship/insert`, {
                method: 'POST', headers: {
                    'Content-Type': 'application/json', 'Authorization': `Bearer ${token}`,
                }, body: JSON.stringify(newShip)
            });

            if (response.ok) {
                setIsModalOpen(false); // Close the modal on success
                setNewShip({ // Reset the form state
                    imo: '',
                    deadWeight: '',
                    shipType: '',
                    shipName: '',
                    shipConstant: '',
                    deadDraft: '',
                    emptyDraft: '',
                    ballastDraft: '',
                    shipCubic: '',
                    flagState: '',
                    shipTag: '',
                    owner: '',
                    operator: ''
                });
                onShipAdded(); // Trigger to refresh the parent component's ship list
            } else {
                const errorData = await response.json();
                alert(errorData.msg || 'Failed to add the ship. Please check the information provided.');
            }
        } catch (error) {
            console.error('Error adding ship:', error);
            alert('Failed to add the ship. Error: ' + error.message);
        }
    };

    return (        <>
        <Button variant="primary" onClick={() => setIsModalOpen(true)}>添加船舶</Button>
        <Modal show={isModalOpen} onHide={() => setIsModalOpen(false)}>
            <Modal.Header closeButton>
                <Modal.Title>添加船舶</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit}>
                    <FloatingLabel controlId="imo" label="IMO" className="mb-3">
                        <Form.Control type="number" name="imo" value={newShip.imo} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="deadWeight" label="Dead Weight" className="mb-3">
                        <Form.Control type="number" name="deadWeight" value={newShip.deadWeight} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="shipType" label="Ship Type" className="mb-3">
                        <Form.Control type="text" name="shipType" value={newShip.shipType} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="shipName" label="Ship Name" className="mb-3">
                        <Form.Control type="text" name="shipName" value={newShip.shipName} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="shipConstant" label="Ship Constant" className="mb-3">
                        <Form.Control type="number" name="shipConstant" value={newShip.shipConstant} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="deadDraft" label="Dead Draft" className="mb-3">
                        <Form.Control type="number" name="deadDraft" value={newShip.deadDraft} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="emptyDraft" label="Empty Draft" className="mb-3">
                        <Form.Control type="number" name="emptyDraft" value={newShip.emptyDraft} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="ballastDraft" label="Ballast Draft" className="mb-3">
                        <Form.Control type="number" name="ballastDraft" value={newShip.ballastDraft} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="shipCubic" label="Ship Cubic" className="mb-3">
                        <Form.Control type="number" name="shipCubic" value={newShip.shipCubic} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="flagState" label="Flag State" className="mb-3">
                        <Form.Control type="text" name="flagState" value={newShip.flagState} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="shipTag" label="Ship Tag" className="mb-3">
                        <Form.Control type="text" name="shipTag" value={newShip.shipTag} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="owner" label="Owner" className="mb-3">
                        <Form.Control type="text" name="owner" value={newShip.owner} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="operator" label="Operator" className="mb-3">
                        <Form.Control type="text" name="operator" value={newShip.operator} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <Button variant="primary" type="submit">添加</Button>
                </Form>
            </Modal.Body>
        </Modal>
    </>);
}

export default ShipInsert;
