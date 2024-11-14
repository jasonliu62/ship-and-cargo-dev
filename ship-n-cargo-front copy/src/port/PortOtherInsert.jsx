// PortOtherInsert.jsx
import React, { useState, useContext } from 'react';
import AuthContext from '../context/AuthContext';
import ModalTemplate from '../util/ModalTemplate';
import {Button, FloatingLabel, Modal} from "react-bootstrap";
import Form from "react-bootstrap/Form"; // Make sure this matches your project

const apiURL = import.meta.env.VITE_API_URL;

function PortOtherInsert({ onPortOtherAdded }) {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [newDetail, setNewDetail] = useState({
        portId: '',
        distance: '',
        oilPrice: '',
        avgTimeStay: '',
        loadTime: '',
        loadEfficiency: '',
        unloadTime: '',
        unloadEfficiency: '',
        portFee: '',
        avgAnchorTime: ''
    });
    const { token } = useContext(AuthContext);

    const handleInputChange = (e) => {
        setNewDetail({ ...newDetail, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (Object.values(newDetail).some(value => value.trim() === '')) {
            alert('Please fill out all fields before submitting.');
            return;
        }

        try {
            const response = await fetch(`${apiURL}/port-other/insert`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify(newDetail)
            });

            if (response.ok) {
                setIsModalOpen(false);
                setNewDetail({
                    portId: '',
                    distance: '',
                    oilPrice: '',
                    avgTimeStay: '',
                    loadTime: '',
                    loadEfficiency: '',
                    unloadTime: '',
                    unloadEfficiency: '',
                    portFee: '',
                    avgAnchorTime: ''
                });
                onPortOtherAdded();
            } else {
                const errorData = await response.json();
                alert(errorData.msg || 'Failed to add the port details. Please check the information provided.');
            }
        } catch (error) {
            console.error('Error adding port details:', error);
            alert('Failed to add the port details. Error: ' + error.message);
        }
    };

    return (
        <>
            <Button variant="primary" onClick={() => setIsModalOpen(true)}>添加港口其他信息</Button>
            <Modal show={isModalOpen} onHide={() => setIsModalOpen(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>添加港口其他信息</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit={handleSubmit}>
                        <FloatingLabel controlId="portId" label="Port ID" className="mb-3">
                            <Form.Control type="text" name="portId" value={newDetail.portId} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="distance" label="Distance" className="mb-3">
                            <Form.Control type="number" name="distance" value={newDetail.distance} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="oilPrice" label="Oil Price" className="mb-3">
                            <Form.Control type="number" name="oilPrice" value={newDetail.oilPrice} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="avgTimeStay" label="Avg Time Stay" className="mb-3">
                            <Form.Control type="number" name="avgTimeStay" value={newDetail.avgTimeStay} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="loadTime" label="Load Time" className="mb-3">
                            <Form.Control type="number" name="loadTime" value={newDetail.loadTime} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="loadEfficiency" label="Load Efficiency" className="mb-3">
                            <Form.Control type="number" name="loadEfficiency" value={newDetail.loadEfficiency} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="unloadTime" label="Unload Time" className="mb-3">
                            <Form.Control type="number" name="unloadTime" value={newDetail.unloadTime} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="unloadEfficiency" label="Unload Efficiency" className="mb-3">
                            <Form.Control type="number" name="unloadEfficiency" value={newDetail.unloadEfficiency} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="portFee" label="Port Fee" className="mb-3">
                            <Form.Control type="number" name="portFee" value={newDetail.portFee} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="avgAnchorTime" label="Avg Anchor Time" className="mb-3">
                            <Form.Control type="number" name="avgAnchorTime" value={newDetail.avgAnchorTime} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <Button variant="primary" type="submit">添加</Button>
                    </Form>
                </Modal.Body>
            </Modal>
        </>
    );
}

export default PortOtherInsert;

