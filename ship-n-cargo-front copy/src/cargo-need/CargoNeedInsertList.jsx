import React, {useState, useContext} from 'react';
import AuthContext from '../context/AuthContext';
import Form from "react-bootstrap/Form";
import {Button, FloatingLabel, Modal} from "react-bootstrap";

function CargoNeedInsertList({onCargoAdded}) {
    const [contractNumber, setContractNumber] = useState('');
    const [cargoId, setCargoId] = useState('');
    const [showModal, setShowModal] = useState(false);
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Validation (simple example)
        if (!contractNumber || !cargoId) {
            alert("Please fill in both contract number and cargo ID.");
            return;
        }

        const url = `${apiURL}/cargo-need/insert-list?contractNumber=${contractNumber}&cargoId=${cargoId}`;

        try {
            const response = await fetch(url, {
                method: 'POST', headers: {
                    'Authorization': `Bearer ${token}`,
                }
            });
            const data = await response.json();
            if (data.code === 2002) {
                alert("Insert failed, cargo does not exist.");
            } else if (response.ok) {
                alert('Cargo need inserted successfully');
                onCargoAdded(); // Callback to refresh the list or clear form
            } else {
                alert('Failed to insert cargo need. Please try again.');
            }
        } catch (error) {
            console.error('Error inserting cargo need:', error);
            alert('Error inserting cargo need. Please try again.');
        }
    };

    return (<>
        <Button variant="primary" onClick={() => setShowModal(true)}>添加货运需求</Button>
        <Modal show={showModal} onHide={() => setShowModal(false)}>
            <Modal.Header closeButton>
                <Modal.Title>添加货运需求</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit}>
                    <FloatingLabel controlId="contractNumber" label="Contract Number" className="mb-3">
                        <Form.Control
                            type="text"
                            value={contractNumber}
                            onChange={e => setContractNumber(e.target.value)}
                        />
                    </FloatingLabel>
                    <FloatingLabel controlId="cargoId" label="Cargo ID" className="mb-3">
                        <Form.Control
                            type="text"
                            value={cargoId}
                            onChange={e => setCargoId(e.target.value)}
                        />
                    </FloatingLabel>
                    <Button variant="primary" type="submit">添加</Button>
                </Form>
            </Modal.Body>
        </Modal>
    </>);
}

export default CargoNeedInsertList;
