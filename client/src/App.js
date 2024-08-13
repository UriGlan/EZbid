import React from 'react';
import {BrowserRouter as Router, Routes, Route, Navigate} from 'react-router-dom';
import SignIn from './pages/SignIn.js';
import Items from './pages/Items.js';
import SignUp from './pages/SignUp.js';
import MyBids from "./pages/myAccount/MyBids";
import MyListedItems from "./pages/myAccount/MyListedItems";


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
                <Route path="/mylisteditems" element={<MyListedItems />} />
            </Routes>
        </Router>
    );
}

export default App;
