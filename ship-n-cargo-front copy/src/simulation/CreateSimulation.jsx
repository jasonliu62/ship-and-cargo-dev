import React, { useState } from 'react';
import {Button, Image} from 'react-bootstrap';
import CargoNeedSortList from "../cargo-need/CargoNeedSortList.jsx";
import ShipEconSortList from "../ship-econ-indicator/ShipEconSortList.jsx";
import SubmitSimulation from "./SubmitSimulation.jsx";
import '../style/simulation/CreateSimulation.css';
import imgBar from '../assets/sim-img/imgbar1.jpg';
import simBg from '../assets/sim-img/sim-bg.jpg';

function CreateSimulation() {
    const [step, setStep] = useState(0);  // Set initial state to 0 for the initial screen

    const nextStep = () => {
        setStep(prev => prev + 1);
    };

    const prevStep = () => {
        setStep(prev => Math.max(1, prev - 1)); // Ensures never going below step 1
    };

    const restartSimulation = () => {
        setStep(0); // Resets to initial screen
    };

    return (
        <div className={step === 0 ? "centered-container" : ""}>
            {step === 0 && (
                <div className="initialSimulationScreen">
                    <Image src={simBg} className="simbg" alt="imgbar"/>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <h1>欢迎来到模拟参数设置</h1>
                    <Button variant="primary" onClick={nextStep}>设置参数以开始模拟</Button>
                    <br/>
                    <br/>
                    <Image src={imgBar} className="imgbar" alt="imgbar"/>
                </div>
            )}
            {step === 1 && <CargoNeedSortList />}
            {step === 2 && <ShipEconSortList />}
            {step === 3 && <SubmitSimulation />}

            {step > 0 && (
                <div className="mt-3">
                    {step > 1 && (
                        <Button variant="primary" onClick={prevStep}>
                            上一步
                        </Button>
                    )}
                    {step < 3 && (
                        <Button variant="primary" onClick={nextStep} className="ms-2">
                            下一步
                        </Button>
                    )}
                    <Button variant="secondary" onClick={restartSimulation} className="ms-2">
                        重新开始
                    </Button>
                </div>
            )}
        </div>
    );
}

export default CreateSimulation;
