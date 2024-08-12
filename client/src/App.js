import React from 'react';
import {BrowserRouter as Router, Routes, Route, Navigate} from 'react-router-dom';
import SignIn from './pages/SignIn.js';
import SignUp from './pages/SignUp.js';
import Items from './pages/Items.js';

// Import additional pages or components here
// import Home from './pages/Home'; (for example)

function App() {
    return (
        <Router>
            <Routes>
                {/* Define the routes for your application */}
                <Route path="/" element={<Navigate to="/signIn" />} />
                <Route path="/signIn" element={<SignIn />} />
                <Route path="/signUp" element={<SignUp />} />
                <Route path="/items" element={<Items />} />
            </Routes>
        </Router>
    );
}

export default App;
