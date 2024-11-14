import React, {useContext} from 'react';
import AuthContext from '../context/AuthContext';

function UserDisplay() {
    const {email, userName} = useContext(AuthContext);

    return (<div>
        {email ? `已登录为 ${userName} (${email})` : '请先登录'}
    </div>);
}


export default UserDisplay;
