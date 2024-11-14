// ShipEconUpdate.jsx
import React, {useState, useContext} from 'react';
import AuthContext from '../context/AuthContext';
import {Button, FloatingLabel, Modal} from "react-bootstrap";
import Form from "react-bootstrap/Form";

function ShipEconUpdate({imo, details, onUpdate}) {
    const [editedDetails, setEditedDetails] = useState(details);
    const {token} = useContext(AuthContext);
    const [isModalOpen, setIsModalOpen] = useState(false);

    const apiURL = import.meta.env.VITE_API_URL;

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        setEditedDetails(prevDetails => ({...prevDetails, [name]: parseFloat(value) || value}));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch(`${apiURL}/ship-econ-indicator/update?imo=${imo}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify(editedDetails)
            });

            if (response.ok) {
                alert('ship updated successfully');
                onUpdate();
            } else {
                alert('Failed to update ship. Please try again.');
            }
        } catch (error) {
            console.error('Error updating ship:', error);
            alert('Error updating ship. Please try again.');
        }
    };

    return (

        <Form onSubmit={handleSubmit}>
            {Object.entries(editedDetails).map(([key, value]) => (
                <FloatingLabel controlId={key} label={key} className="mb-3" key={key}>
                    <Form.Control type="text" name={key} value={value} onChange={handleInputChange}/>
                </FloatingLabel>
            ))}
            <Button variant="primary" type="submit">保存</Button>
        </Form>
    );
}

export default ShipEconUpdate;
