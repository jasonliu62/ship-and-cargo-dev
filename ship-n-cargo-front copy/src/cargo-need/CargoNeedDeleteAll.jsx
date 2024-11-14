import React, {useContext} from 'react';
import AuthContext from '../context/AuthContext';
import {Button} from "react-bootstrap";

function CargoNeedDeleteAll({type, deleteUrl, identifier, onDeleted}) {
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;
    const handleDelete = async () => {
        if (!window.confirm(`确认要全部删除吗？`)) {
            return;
        }

        try {
            const response = await fetch(`${apiURL}/${deleteUrl}/delete-all`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            if (response.ok) {
                onDeleted(); // Callback to refresh the list after deletion
            } else {
                alert('Failed to delete the item.');
            }
        } catch (error) {
            console.error('Error deleting item:', error);
            alert('Error deleting item: ' + error.message);
        }
    };

    return (
        <Button variant="danger" onClick={handleDelete}>全部删除</Button>
    );
}

export default CargoNeedDeleteAll;