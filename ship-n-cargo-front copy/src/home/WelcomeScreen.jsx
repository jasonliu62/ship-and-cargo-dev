import React, {useState, useEffect} from 'react';
import {useNavigate} from "react-router-dom";
import {Button, Container, Row, Col, Card, Image} from 'react-bootstrap';
import bg1 from '../assets/home-bg/1-1.png';
import bg2 from '../assets/home-bg/2-2.png';
import cardBg1 from '../assets/card-img/单证数字化.png';
import cardBg2 from '../assets/card-img/Decision making.png';
import cardBg3 from '../assets/card-img/security.png';
import imgBar from '../assets/home-bg/imgbar.jpg';
import '../style/home/WelcomeScreen.css';

function WelcomeScreen() {
    const navigate = useNavigate();
    const [currentImageIndex, setCurrentImageIndex] = useState(0);
    const imageSources = [bg1, bg2];
    const handleClick = () => {
        navigate("/");
    };

    useEffect(() => {
        const intervalId = setInterval(() => {
            setCurrentImageIndex((index) => (index + 1) % imageSources.length);
        }, 5000);
        return () => clearInterval(intervalId);
    }, [imageSources.length]);

    return (
        <Container fluid className="p-0">
            <div className="background-image" style={{backgroundImage: `url(${imageSources[currentImageIndex]})`}}>
                <Button variant="light" className="button-prev"
                        onClick={() => setCurrentImageIndex((index) => (index - 1 + imageSources.length) % imageSources.length)}>{'<'}</Button>
                <Button variant="light" className="button-next"
                        onClick={() => setCurrentImageIndex((index) => (index + 1) % imageSources.length)}>{'>'}</Button>
                <Button variant="primary" className="start-button" onClick={handleClick}>开始</Button>
            </div>
            <br/>
            <Image src={imgBar} className="imgbar"/>
            <br/>
            <br/>
            <Row className="text-center m-0">
                <Col md={4}>
                    <Card>
                        <Card.Body className="card-body-custom">
                            <Card.Img variant="top" src={cardBg1} className="card-img-top-custom"/>
                            <Card.Title>单证数字化</Card.Title>
                            <Card.Text style={{margin: '0 30px'}}>
                                电子单证的实施主要旨在减少纸质单证处理的时间成本和错误率。此外，电子单证能增强单证的规范化、安全性和准确性，从而有助于适应国际贸易竞争中不断增长的数字化和信息化需求 </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={4}>
                    <Card>
                        <Card.Body className="card-body-custom">
                            <Card.Img variant="top" src={cardBg2} className="card-img-top-custom"/>
                            <Card.Title>决策智能化</Card.Title>
                            <Card.Text style={{margin: '0 30px'}}>
                                利用人工智能技术，智能决策系统能够对船舶和货物进行智能匹配与决策分析。系统能展示所有可行的方案，使用户能够全面审视并选择最优解，以提升运输效率和降低成本 </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={4}>
                    <Card>
                        <Card.Body className="card-body-custom">
                            <Card.Img variant="top" src={cardBg3} className="card-img-top-custom"/>
                            <Card.Title>信息安全化</Card.Title>
                            <Card.Text style={{margin: '0 30px'}}>
                                信息安全化：
                                近年来，区块链技术已在金融领域得到广泛应用，展现出其去中心化、集体维护的优势，以及数据不可篡改、透明性和可追溯性的特点。这些特性保证了贸易过程中单证、文件和信息的全面加密和安全 </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <div className="footer">
                    <p>© 2024 Your Company Name. All rights reserved.</p>
                    <p>Email: info@yourcompany.com</p>
                    <p>Address: 1234 Your Street, Your City, Your Country</p>
                    <p>Phone: +1 234 567 890</p>
                </div>
            </Row>
        </Container>);
}

export default WelcomeScreen;
