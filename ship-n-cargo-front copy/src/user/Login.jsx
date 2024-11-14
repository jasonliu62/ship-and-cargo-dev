import React, {useState, useContext} from 'react';
import '../style/user/Login.css'
import AuthContext from '../context/AuthContext.jsx';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import {FloatingLabel} from "react-bootstrap";
import {Link} from "react-router-dom";
import { useNavigate } from "react-router-dom";

function Login() {
    const {login} = useContext(AuthContext);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const apiURL = import.meta.env.VITE_API_URL;

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch(`${apiURL}/user/login`, {
                method: 'POST', headers: {
                    'Content-Type': 'application/json',
                }, body: JSON.stringify({email, password}),
            });
            const data = await response.json();
            if (data.code === 0) {
                login(data.data.token, data.data.email, data.data.name);
                navigate("/");
            } else {
                // Handle errors
            }
        } catch (error) {
            console.error('Error logging in:', error);
            // Handle error (e.g., show error message)
        }
    };

    return (
        <Form onSubmit={handleSubmit} className="loginForm">
            <h2>登录</h2>
            <FloatingLabel controlId="floatingInput" label="电子邮箱" className="mb-3">
                <Form.Control type="email" placeholder="name@example.com" value={email} onChange={(e) => setEmail(e.target.value)} required/>
            </FloatingLabel>
            <FloatingLabel controlId="floatingPassword" label="密码" className="mb-3">
                <Form.Control type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required/>
            </FloatingLabel>
            <Button variant="primary" type="submit">登录</Button>
            <br/>
            <br/>
            <Button as={Link} to="/user/register" variant="primary">没有账号？点击注册</Button>
        </Form>);
}

export default Login;
