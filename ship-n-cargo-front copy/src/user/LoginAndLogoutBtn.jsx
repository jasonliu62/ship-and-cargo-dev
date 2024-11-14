import React, {useContext} from 'react';
import AuthContext from '../context/AuthContext';
import {Link} from "react-router-dom";
import {Button} from "react-bootstrap";

function LoginAndLogoutBtn() {
    const {email, userName} = useContext(AuthContext);
    const {logout} = useContext(AuthContext);

    const handleLogout = () => {
        logout();
    };

    return (
        email ? <Button as={Link} to="/home" variant="primary" onClick={handleLogout}>退出登录</Button> :
            <Button as={Link} to="/user/login" variant="primary">登录</Button>
    );
}

export default LoginAndLogoutBtn;
