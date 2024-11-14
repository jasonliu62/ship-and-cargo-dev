import React, { useState, useContext } from 'react';
import AuthContext from '../context/AuthContext';
import {Alert, Button, Spinner, Table} from "react-bootstrap";
import * as PropTypes from "prop-types";
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import ExpandByGroupID from "./ExpandByGroupID.jsx";
import '../style/simulation/SubmitSimulation.css';

function SubmitSimulation() {
    const [start, setStart] = useState('');
    const [end, setEnd] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const [simulationResults, setSimulationResults] = useState([]);
    const [expandedId, setExpandedId] = useState(null); // Track expanded outputId
    const { token } = useContext(AuthContext);
    const apiURL = import.meta.env.VITE_API_URL;

    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsLoading(true);
        setSimulationResults([]); // Clear previous results

        const startDay = formatDateToBackend(start);
        const endDay = formatDateToBackend(end);

        try {
            const response = await fetch(`${apiURL}/simulation/final-simulation`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify({ startDay, endDay })
            });

            const data = await response.json();
            setIsLoading(false);

            if (data.code === 0) {
                // Aggregate and sort results by outputId
                const results = aggregateAndSortResults(data.data.data);
                setSimulationResults(results);
                console.log('Simulation results:', results);
            } else {
                alert("Simulation failed: " + data.msg);
            }
        } catch (error) {
            console.error('Error during simulation:', error);
            setIsLoading(false);
            alert('Failed to execute simulation. Please try again.');
        }
    };

    function formatDateToBackend(dateTimeStr) {
        const [date, time] = dateTimeStr.split('T');
        const [hours, minutes] = time.split(':');
        return `${date} ${hours}:${minutes}:00`;
    }

    function aggregateAndSortResults(data) {
        const outputMap = new Map();

        // Flatten and sort the data by outputId
        Object.values(data).flat().forEach(item => {
            if (outputMap.has(item.outputId)) {
                outputMap.get(item.outputId).push(item);
            } else {
                outputMap.set(item.outputId, [item]);
            }
        });

        // Convert map to sorted array
        return Array.from(outputMap.entries()).sort((a, b) => a[0] - b[0]);
    }

    function toggleExpand(id) {
        setExpandedId(expandedId === id ? null : id); // Toggle expandedId
    }

    return (
        <>
            {isLoading && (
                <div className="loading-overlay">
                    <Spinner animation="border" variant="light" className="spinner-border" />
                    <span>正在模拟...</span>
                </div>
            )}
        <Container>
            <br/>
            <h2>模拟时间</h2>
            <br/>
            <Form onSubmit={handleSubmit}>
                <Form.Group className="mb-3">
                    <Form.Label>模拟开始时间：</Form.Label>
                    <Form.Control
                        type="datetime-local"
                        value={start}
                        onChange={e => setStart(e.target.value)}
                        required
                    />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>模拟结束时间：</Form.Label>
                    <Form.Control
                        type="datetime-local"
                        value={end}
                        onChange={e => setEnd(e.target.value)}
                        required
                    />
                </Form.Group>
                <Button variant="primary" type="submit">开始模拟</Button>
                <br/>

            </Form>
            <br/>
            {simulationResults.length > 0 && (
                <div>
                    <h2>本次模拟结果</h2>
                    <br/>
                    <ExpandByGroupID groupId={simulationResults[0][1][0].groupId} />
                </div>
                )}
        </Container>
        </>
    );
}

export default SubmitSimulation;
