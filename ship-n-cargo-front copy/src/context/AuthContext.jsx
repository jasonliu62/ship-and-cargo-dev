import React, {createContext, useState, useEffect} from 'react';
import Cookies from 'js-cookie';

const AuthContext = createContext(null);

export const AuthProvider = ({children}) => {
    const [auth, setAuth] = useState({
        token: null, email: null, userName: null
    });

    useEffect(() => {
        // Check for stored data in cookies on app load
        const token = Cookies.get('token');
        const email = Cookies.get('email') ? JSON.parse(Cookies.get('email')) : null;
        const userName = Cookies.get('userName') ? JSON.parse(Cookies.get('userName')) : null;
        if (token && email && userName) {
            setAuth({token, email, userName});
        }
    }, []);

    const login = (token, email, userName) => {
        Cookies.set('token', token);
        Cookies.set('email', JSON.stringify(email));
        Cookies.set('userName', JSON.stringify(userName));
        setAuth({token, email, userName});
    };

    const logout = () => {
        Cookies.remove('token');
        Cookies.remove('email');
        Cookies.remove('userName');
        setAuth({token: null, email: null, userName: null});
    };

    return (<AuthContext.Provider value={{...auth, login, logout}}>
            {children}
        </AuthContext.Provider>);
};

export default AuthContext;
