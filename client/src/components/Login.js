import React, { useState } from 'react';
import "./css/Login.css"


// This is the login component
const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    // This function is called when the form is submitted
    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Email:', email);
        console.log('Password:', password);
        // Add login logic here
    };

    return (
        <div className={"login-container"}>
            <h2 className={"header1"}>Welcome to EZBid</h2>
            <h3 className={"header2"}>Your trusted platform for online auctions. </h3>
            <form onSubmit={handleSubmit} className={"input-container"}>
                <div>
                    <label className={"label-email"}>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={handleEmailChange}
                        required
                        className={"input-email"}
                    />
                </div>
                <div>
                    <label className={"label-password"}>Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={handlePasswordChange}
                        required
                        className={"input-password"}
                    />
                </div>
                <button type="submit" className={"button-login"}>Sign in</button>
            </form>
            <div className={"notAUser"}>
                <label>Not A User?</label>
                <a href="https://www.example.com" target="_blank" rel="noopener noreferrer" className={"link-signup"}>
                    Sing up for FREE!
                </a>
            </div>
        </div>
    );
};

export default Login;
