import React, {useState} from 'react';
import '../style/user/Register.css'
import Form from "react-bootstrap/Form";
import {Button, FloatingLabel} from "react-bootstrap";
import ExpandByOutputID from "../simulation/ExpandByOutputID.jsx";

function Register() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');
    const apiURL = import.meta.env.VITE_API_URL;
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch(`${apiURL}/user/register`, {
                method: 'POST', headers: {
                    'Content-Type': 'application/json',
                }, body: JSON.stringify({email, password, name}),
            });
            const data = await response.json();
            console.log(data);
            // Handle success (e.g., navigate to another page or show message)
        } catch (error) {
            console.error('Error registering:', error);
            // Handle error (e.g., show error message)
        }
    };

    return ( <Form onSubmit={handleSubmit} className="registerForm">
        <h2>注册</h2>
        <FloatingLabel controlId="floatingEmail" label="电子邮箱" className="mb-3">
            <Form.Control type="email" placeholder="name@example.com" value={email} onChange={(e) => setEmail(e.target.value)} required/>
        </FloatingLabel>
        <FloatingLabel controlId="floatingPassword" label="密码" className="mb-3">
            <Form.Control type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required/>
        </FloatingLabel>
        <FloatingLabel controlId="floatingName" label="用户名" className="mb-3">
            <Form.Control type="text" placeholder="Username" value={name} onChange={(e) => setName(e.target.value)} required/>
        </FloatingLabel>
        <Button variant="primary" type="submit">注册</Button>
    </Form>);
}

export default Register;
