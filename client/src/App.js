import React from 'react';
import {BrowserRouter as Router, Routes, Route, Navigate} from 'react-router-dom';
import SignIn from './pages/SignIn.js';
import Items from './pages/Items.js';
import SignUp from './pages/SignUp.js';
import MyBids from "./pages/myAccount/MyBids";
import MyItems from "./pages/myAccount/MyItems";
import Profile from "./pages/myAccount/Profile";
import EditProfile from "./pages/myAccount/ProfileEdit";


function App() {
    return (
        <Router>
            <Routes>
                {/* Define the routes for your application */}
                <Route path="/" element={<Navigate to="/signIn" />} />
                <Route path="/signIn" element={<SignIn />} />
                <Route path="/signUp" element={<SignUp />} />
                <Route path="/items" element={<Items />} />
                <Route path="/mybids" element={<MyBids />} />
                <Route path="/myitems" element={<MyItems />} />
                <Route path="/profile" element={<Profile />} />
                <Route path="/editprofile" element={<EditProfile />} />
            </Routes>
        </Router>
    );
}

export default App;
