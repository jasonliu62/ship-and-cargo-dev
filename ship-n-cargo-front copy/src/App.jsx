import {BrowserRouter as Router, Routes, Route, NavLink} from 'react-router-dom';
import AuthContext, {AuthProvider} from './context/AuthContext';
import Login from './user/Login';
import Register from './user/Register';
import LoginAndLogoutBtn from "./user/LoginAndLogoutBtn.jsx";
import UserDisplay from "./user/UserDisplay.jsx";
import CargoList from "./cargo/CargoList.jsx";
import ShipList from "./ship/ShipList.jsx";
import ShipManagementList from "./ship/ShipManagementList.jsx";
import ShipMissionLocationList from "./ship/ShipMissionLocationList.jsx";
import ShipIdleLocationList from "./ship/ShipIdleLoactionList.jsx";
import PortList from "./port/PortList.jsx";
import PortActivityList from "./port/PortActivityList.jsx";
import PortOtherList from "./port/PortOtherList.jsx";
import CargoNeedSortList from "./cargo-need/CargoNeedSortList.jsx";
import ShipEconSortList from "./ship-econ-indicator/ShipEconSortList.jsx";
import SubmitSimulation from "./simulation/SubmitSimulation.jsx";
import SimulationHistoryList from "./simulation/SimulationHistoryList.jsx";
import './style/App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import React, {useContext, useState} from "react";
import WelcomeScreen from "./home/WelcomeScreen.jsx";
import CreateSimulation from "./simulation/CreateSimulation.jsx";
import DefaultEmptyScreen from "./home/DefaultEmptyScreen.jsx";
import {Button} from "react-bootstrap";
import logo from './assets/logo.png';

function App() {
    const [collapsed, setCollapsed] = useState(false); // State to manage collapse

    const toggleNavbar = () => setCollapsed(!collapsed);

    return (<>
        <AuthProvider>
            <Router>
                <Routes>
                    <Route path="/home" element={<WelcomeScreen/>}/>
                    <Route path="/*" element={<div style={{display: 'flex'}}>
                        <Navbar className={`sidebar ${collapsed ? 'collapsed' : ''}`} bg="light" expand="lg" >
                            <Button onClick={toggleNavbar} className="toggle">{collapsed ? '>' : '<'}</Button>
                            {!collapsed && <Container>
                                <Nav className="flex-column">
                                    <NavLink to="/home">
                                        <img src={logo} className="logo" alt="Logo"/>
                                    </NavLink>
                                    <UserDisplay/>
                                    <LoginAndLogoutBtn/>
                                    <br/>
                                    <Nav.Item className="navbar-title">货物</Nav.Item>
                                    <NavLink to="/cargo/list" className="nav-link indented">货物列表</NavLink>
                                    <br/>
                                    <Nav.Item className="navbar-title">船舶</Nav.Item>
                                    <NavLink to="/ship/list" className="nav-link indented">船舶列表</NavLink>
                                    <NavLink to="/ship-management/list"
                                             className="nav-link indented">船舶经营信息</NavLink>
                                    <NavLink to="/ship-mission-location/list" className="nav-link indented">船舶实时定位信息</NavLink>
                                    <NavLink to="/ship-idle-location/list" className="nav-link indented">船舶在港位置</NavLink>
                                    <br/>
                                    <Nav.Item className="navbar-title">港口</Nav.Item>
                                    <NavLink to="/port/list" className="nav-link indented">港口属性信息</NavLink>
                                    <NavLink to="/port-activity/list" className="nav-link indented">港口动态信息</NavLink>
                                    <NavLink to="/port-other/list" className="nav-link indented">港口其他信息</NavLink>
                                    <br/>
                                    <Nav.Item className="navbar-title">模拟</Nav.Item>
                                    <NavLink to="/simulation/list-simulation-history"
                                             className="nav-link indented">模拟结果历史</NavLink>
                                    <NavLink to="/create-simulation" className="nav-link indented">新建模拟</NavLink>

                                    {/*<Nav.Item>test</Nav.Item>*/}
                                    {/*<NavLink to="/cargo-need/sort-list" className="nav-link indented">Cargo Need Sort List</NavLink>*/}
                                    {/*<NavLink to="/ship-econ-indicator/sort-list" className="nav-link indented">Ship Econ Indicator Sort List</NavLink>*/}
                                    {/*<NavLink to="/simulation" className="nav-link indented">新建模拟(pt3)</NavLink>*/}

                                </Nav>
                            </Container>}

                        </Navbar>
                        <div>
                            <Routes class="right-half">
                                <Route path="/" element={<DefaultEmptyScreen/>}/>
                                <Route path="/home" element={<WelcomeScreen/>}/>
                                <Route path="/user/login" element={<Login/>}/>
                                <Route path="/user/register" element={<Register/>}/>
                                <Route path="/cargo/list" element={<CargoList/>}/>
                                <Route path="/ship/list" element={<ShipList/>}/>
                                <Route path="/ship-management/list" element={<ShipManagementList/>}/>
                                <Route path="/ship-mission-location/list" element={<ShipMissionLocationList/>}/>
                                <Route path="/ship-idle-location/list" element={<ShipIdleLocationList/>}/>
                                <Route path="/port/list" element={<PortList/>}/>
                                <Route path="/port-activity/list" element={<PortActivityList/>}/>
                                <Route path="/port-other/list" element={<PortOtherList/>}/>
                                <Route path="/cargo-need/sort-list" element={<CargoNeedSortList/>}/>
                                <Route path="/ship-econ-indicator/sort-list" element={<ShipEconSortList/>}/>
                                <Route path="/simulation" element={<SubmitSimulation/>}/>
                                <Route path="/simulation/list-simulation-history" element={<SimulationHistoryList/>}/>
                                <Route path="/create-simulation" element={<CreateSimulation/>}/>
                            </Routes>
                        </div>
                    </div>}/>
                </Routes>
            </Router>
        </AuthProvider>
    </>)
}

export default App;
