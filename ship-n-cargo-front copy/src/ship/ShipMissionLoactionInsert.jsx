import React, { useState, useContext } from 'react';
import AuthContext from '../context/AuthContext'; // Adjust path as necessary
import ModalTemplate from '../util/ModalTemplate';
import {Button, FloatingLabel, Modal} from "react-bootstrap";
import Form from "react-bootstrap/Form"; // Ensure you have this component

const apiURL = import.meta.env.VITE_API_URL; // Ensure this is defined in your environment

function ShipMissionLocationInsert({ onLocationAdded }) {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [newLocation, setNewLocation] = useState({
        imo: '',
        latitude: '',
        longitude: '',
        postPortId: '',
        nextPortId: '',
        speed: '',
        departureDistance: '',
        arrivalDistance: '',
        currentTime: '' // Consider using a date-time picker for user input
    });
    const { token } = useContext(AuthContext);

    const handleInputChange = (e) => {
        setNewLocation({ ...newLocation, [e.target.name]: e.target.value });
    };

    function formatDateToBackend(dateTimeStr) {
        const [date, time] = dateTimeStr.split('T');
        const [hours, minutes] = time.split(':');
        return `${date} ${hours}:${minutes}:00`;
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (Object.values(newLocation).some(value => value.trim() === '')) {
            alert('Please fill out all fields before submitting.');
            return;
        }

        try {
            const formattedShipEmptyTime = formatDateToBackend(newLocation.currentTime);

            const shipMissionLocationData = {
                ...newLocation,
                currentTime: formattedShipEmptyTime,
            };
            const response = await fetch(`${apiURL}/ship-mission-location/insert`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify(shipMissionLocationData)
            });

            if (response.ok) {
                setIsModalOpen(false); // Close the modal on success
                setNewLocation({ // Reset form state
                    imo: '',
                    latitude: '',
                    longitude: '',
                    postPortId: '',
                    nextPortId: '',
                    speed: '',
                    departureDistance: '',
                    arrivalDistance: '',
                    currentTime: ''
                });
                onLocationAdded(); // Trigger to refresh the parent component's list
            } else {
                const errorData = await response.json();
                alert(errorData.msg || 'Failed to add the ship mission location. Please check the information provided.');
            }
        } catch (error) {
            console.error('Error adding ship mission location:', error);
            alert('Failed to add the ship mission location. Error: ' + error.message);
        }
    };

    return (
        <>
            <Button variant="primary" onClick={() => setIsModalOpen(true)}>添加实时定位</Button>
            <Modal show={isModalOpen} onHide={() => setIsModalOpen(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>添加实时定位</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit={handleSubmit}>
                        <FloatingLabel controlId="imo" label="IMO" className="mb-3">
                            <Form.Control type="text" name="imo" value={newLocation.imo} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="latitude" label="Latitude" className="mb-3">
                            <Form.Control type="text" name="latitude" value={newLocation.latitude} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="longitude" label="Longitude" className="mb-3">
                            <Form.Control type="text" name="longitude" value={newLocation.longitude} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="postPortId" label="Post Port ID" className="mb-3">
                            <Form.Control type="text" name="postPortId" value={newLocation.postPortId} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="nextPortId" label="Next Port ID" className="mb-3">
                            <Form.Control type="text" name="nextPortId" value={newLocation.nextPortId} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="speed" label="Speed" className="mb-3">
                            <Form.Control type="number" name="speed" value={newLocation.speed} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="departureDistance" label="Departure Distance" className="mb-3">
                            <Form.Control type="number" name="departureDistance" value={newLocation.departureDistance} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="arrivalDistance" label="Arrival Distance" className="mb-3">
                            <Form.Control type="number" name="arrivalDistance" value={newLocation.arrivalDistance} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <FloatingLabel controlId="currentTime" label="Current Time" className="mb-3">
                            <Form.Control type="datetime-local" name="currentTime" value={newLocation.currentTime} onChange={handleInputChange} required />
                        </FloatingLabel>
                        <Button variant="primary" type="submit">添加</Button>
                    </Form>
                </Modal.Body>
            </Modal>
        </>
    );
}

export default ShipMissionLocationInsert;