import React from 'react';
import {BrowserRouter as Router, Routes, Route, Navigate} from 'react-router-dom';
import SignIn from './pages/SignIn.js';
import Home from './pages/Home.js';
import SignUp from './pages/SignUp.js';
import MyBids from "./pages/myAccount/MyBids";
import MyItems from "./pages/myAccount/MyItems";
import Profile from "./pages/myAccount/Profile";
import EditProfile from "./pages/myAccount/ProfileEdit";
import ResetPassword from "./pages/ResetPassword";
import VerificationResetCode from "./pages/VerificationResetCode";


function App() {
    return (
        <Router>
            <Routes>
                {/* Define the routes for your application */}
                <Route path="/" element={<Navigate to="/signIn" />} />
                <Route path="/signIn" element={<SignIn />} />
                <Route path="/signUp" element={<SignUp />} />
                <Route path="/home" element={<Home />} />
                <Route path="/mybids" element={<MyBids />} />
                <Route path="/myitems" element={<MyItems />} />
                <Route path="/profile" element={<Profile />} />
                <Route path="/editprofile" element={<EditProfile />} />
                <Route path="/resetpassword" element={<ResetPassword />} />
                <Route path="/verificationresetcode" element={<VerificationResetCode />} />
            </Routes>
        </Router>
    );
}

export default App;
