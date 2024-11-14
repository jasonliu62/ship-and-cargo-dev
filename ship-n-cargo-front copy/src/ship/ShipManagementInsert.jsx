// ShipManagementInsert.jsx
import React, {useContext, useState} from 'react';
import AuthContext from '../context/AuthContext'; // Adjust the import path as needed
import ModalTemplate from '../util/ModalTemplate';
import {Button, FloatingLabel, Modal} from "react-bootstrap";
import Form from "react-bootstrap/Form"; // This is your modal component

const apiURL = import.meta.env.VITE_API_URL;

function ShipManagementInsert({onShipManagementAdded}) {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [newShipManagement, setNewShipManagement] = useState({
        imo: '',
        shipOperatingDay: '',
        shipTotalTime: '',
        dailyCapitalCost: '',
        dailyOperatingCost: '',
        acceptableCargoType: '',
        owner: '',
        operator: '',
        navigationArea: '',
        shipDepartment: '',
        shipEmptyPort: '',
        shipEmptyTime: '' // This should ideally be handled by a date-time picker
    });
    const {token} = useContext(AuthContext);

    const handleInputChange = (e) => {
        setNewShipManagement({...newShipManagement, [e.target.name]: e.target.value});
    };

    const isFormValid = () => {
        return Object.values(newShipManagement).every(value => value.trim() !== '');
    };

    function formatDateToBackend(dateTimeStr) {
        const [date, time] = dateTimeStr.split('T');
        const [hours, minutes] = time.split(':');
        return `${date} ${hours}:${minutes}:00`;
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!isFormValid()) {
            alert('All fields must be filled out.');
            return;
        }
        const formattedShipEmptyTime = formatDateToBackend(newShipManagement.shipEmptyTime);

        const shipManagementData = {
            ...newShipManagement,
            shipEmptyTime: formattedShipEmptyTime,
        };
        try {
            const response = await fetch(`${apiURL}/ship-management/insert`, {
                method: 'POST', headers: {
                    'Content-Type': 'application/json', 'Authorization': `Bearer ${token}`,
                }, body: JSON.stringify(shipManagementData)
            });

            if (response.ok) {
                setIsModalOpen(false);
                setNewShipManagement({
                    imo: '',
                    shipOperatingDay: '',
                    shipTotalTime: '',
                    dailyCapitalCost: '',
                    dailyOperatingCost: '',
                    acceptableCargoType: '',
                    owner: '',
                    operator: '',
                    navigationArea: '',
                    shipDepartment: '',
                    shipEmptyPort: '',
                    shipEmptyTime: ''
                });
                onShipManagementAdded();
            } else {
                const errorData = await response.json();
                alert(errorData.msg || 'An error occurred while adding the ship management record.');
            }
        } catch (error) {
            console.error('Error adding ship management record:', error);
            alert('Error adding ship management record: ' + error.message);
        }
    };

    return ( <>
        <Button variant="primary" onClick={() => setIsModalOpen(true)}>添加经营信息</Button>
        <Modal show={isModalOpen} onHide={() => setIsModalOpen(false)}>
            <Modal.Header closeButton>
                <Modal.Title>添加经营信息</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit}>
                    <FloatingLabel controlId="imo" label="IMO" className="mb-3">
                        <Form.Control type="number" name="imo" value={newShipManagement.imo} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="shipOperatingDay" label="Ship Operating Day" className="mb-3">
                        <Form.Control type="number" step="0.1" name="shipOperatingDay" value={newShipManagement.shipOperatingDay} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="shipTotalTime" label="Ship Total Time" className="mb-3">
                        <Form.Control type="number" step="0.001" name="shipTotalTime" value={newShipManagement.shipTotalTime} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="dailyCapitalCost" label="Daily Capital Cost" className="mb-3">
                        <Form.Control type="number" name="dailyCapitalCost" value={newShipManagement.dailyCapitalCost} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="dailyOperatingCost" label="Daily Operating Cost" className="mb-3">
                        <Form.Control type="number" name="dailyOperatingCost" value={newShipManagement.dailyOperatingCost} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="acceptableCargoType" label="Acceptable Cargo Type" className="mb-3">
                        <Form.Control type="text" name="acceptableCargoType" value={newShipManagement.acceptableCargoType} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="owner" label="Owner" className="mb-3">
                        <Form.Control type="text" name="owner" value={newShipManagement.owner} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="operator" label="Operator" className="mb-3">
                        <Form.Control type="text" name="operator" value={newShipManagement.operator} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="navigationArea" label="Navigation Area" className="mb-3">
                        <Form.Control type="text" name="navigationArea" value={newShipManagement.navigationArea} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="shipDepartment" label="Ship Department" className="mb-3">
                        <Form.Control type="text" name="shipDepartment" value={newShipManagement.shipDepartment} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="shipEmptyPort" label="Ship Empty Port" className="mb-3">
                        <Form.Control type="text" name="shipEmptyPort" value={newShipManagement.shipEmptyPort} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <FloatingLabel controlId="shipEmptyTime" label="Ship Empty Time" className="mb-3">
                        <Form.Control type="datetime-local" name="shipEmptyTime" value={newShipManagement.shipEmptyTime} onChange={handleInputChange} required />
                    </FloatingLabel>
                    <Button variant="primary" type="submit">添加</Button>
                </Form>
            </Modal.Body>
        </Modal>
    </>);
}

export default ShipManagementInsert;
