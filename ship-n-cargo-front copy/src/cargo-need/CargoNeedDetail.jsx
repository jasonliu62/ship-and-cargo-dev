import React, {useState, useEffect, useContext} from 'react';
import AuthContext from '../context/AuthContext';
import ModalTemplate from '../util/ModalTemplate';
import CargoNeedUpdate from "./CargoNeedUpdate.jsx";
import {Button, ListGroup, ListGroupItem} from "react-bootstrap";

function CargoNeedDetail({cargoId}) {
    const [cargoDetails, setCargoDetails] = useState(null);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [refresh, setRefresh] = useState(false);
    const {token} = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    useEffect(() => {
        const fetchCargoDetails = async () => {
            try {
                const response = await fetch(`${apiURL}/cargo-need/list?cargoId=${cargoId}`, {
                    method: 'POST', headers: {
                        'Authorization': `Bearer ${token}`,
                    }
                });

                const data = await response.json();
                if (data.code === 0) {
                    setCargoDetails(data.data);
                } else {
                    alert(data.msg || 'Failed to fetch cargo details.');
                }
            } catch (error) {
                console.error('Error fetching cargo details:', error);
                alert('Error fetching cargo details. Please try again.');
            }
        };

        fetchCargoDetails().then(r => console.log(r));

    }, [cargoId, refresh]);

    const refreshDetail = () => {
        setRefresh(!refresh);
    }

    return (<>
            <Button variant="primary" onClick={() => setIsModalOpen(true)}>参数设定</Button>
            <ModalTemplate
                isOpen={isModalOpen}
                onClose={() => setIsModalOpen(false)}
                title="货运需求参数"
            >
                <CargoNeedUpdate
                    cargoId={cargoId}
                    details={cargoDetails}
                    onUpdate={refreshDetail}
                />
                {/*{cargoDetails ? (*/}
                {/*    <ListGroup>*/}
                {/*        {Object.entries(cargoDetails).map(([key, value]) => (*/}
                {/*            <ListGroupItem key={key}>{`${key}: ${value}`}</ListGroupItem>*/}
                {/*        ))}*/}
                {/*    </ListGroup>*/}
                {/*) : (*/}
                {/*    <p>Loading cargo details...</p>*/}
                {/*)}*/}
            </ModalTemplate>
        </>
    );
}

export default CargoNeedDetail;
