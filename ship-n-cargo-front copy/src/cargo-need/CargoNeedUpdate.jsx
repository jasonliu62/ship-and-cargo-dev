import React, {useState, useContext} from 'react';
import AuthContext from '../context/AuthContext';
import Form from "react-bootstrap/Form";
import {Button, FloatingLabel} from "react-bootstrap";

function CargoNeedUpdate({cargoId, details, onUpdate}) {
    const [editedDetails, setEditedDetails] = useState(details);
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        setEditedDetails(prevDetails => ({...prevDetails, [name]: parseFloat(value) || value}));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch(`${apiURL}/cargo-need/update?cargoId=${cargoId}`, {
                method: 'POST', headers: {
                    'Content-Type': 'application/json', 'Authorization': `Bearer ${token}`,
                }, body: JSON.stringify(editedDetails)
            });

            if (response.ok) {
                alert('Cargo need updated successfully');
                onUpdate();
            } else {
                alert('Failed to update cargo need. Please try again.');
            }
        } catch (error) {
            console.error('Error updating cargo need:', error);
            alert('Error updating cargo need. Please try again.');
        }
    };

    return (<Form onSubmit={handleSubmit}>
        {Object.entries(editedDetails).map(([key, value]) => (
            <FloatingLabel key={key} controlId={key} label={key} className="mb-3">
                <Form.Control
                    type="text"
                    name={key}
                    value={value}
                    onChange={handleInputChange}
                />
            </FloatingLabel>
        ))}
        <Button variant="primary" type="submit">保存</Button>
    </Form>);
}

export default CargoNeedUpdate;
