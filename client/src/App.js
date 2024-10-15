import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import SignIn from './pages/SignIn.js';
import Home from './pages/Home.js';
import SignUp from './pages/SignUp.js';
import MyBids from "./pages/myAccount/MyBids";
import MyItems from "./pages/myAccount/MyItems";
import Profile from "./pages/myAccount/Profile";
import EditProfile from "./pages/myAccount/EditProfile";
import ResetPassword from "./pages/ResetPassword";
import VerificationResetCode from "./pages/VerificationResetCode";
import VerificationCode from "./pages/VerificationCode";

// This function checks if the user is authenticated
const isAuthenticated = () => {
    return localStorage.getItem("token") !== null;
};

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Navigate to="/signIn" />} />
                <Route path="/signIn" element={<SignIn />} />
                <Route path="/signUp" element={<SignUp />} />

                {/* Protected Routes */}
                <Route
                    path="/home"
                    element={isAuthenticated() ? <Home /> : <Navigate to="/signIn" />}
                />
                <Route
                    path="/mybids"
                    element={isAuthenticated() ? <MyBids /> : <Navigate to="/signIn" />}
                />
                <Route
                    path="/myitems"
                    element={isAuthenticated() ? <MyItems /> : <Navigate to="/signIn" />}
                />
                <Route
                    path="/profile"
                    element={isAuthenticated() ? <Profile /> : <Navigate to="/signIn" />}
                />
                <Route
                    path="/editprofile"
                    element={isAuthenticated() ? <EditProfile /> : <Navigate to="/signIn" />}
                />

                {/* Public Routes */}
                <Route path="/resetpassword" element={<ResetPassword />} />
                <Route path="/verificationresetcode" element={<VerificationResetCode />} />
                <Route path="/verificationcode" element={<VerificationCode />} />
            </Routes>
        </Router>
    );
}

export default App;
