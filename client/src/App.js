import React from 'react';
import Footer from './components/Footer';
import HomePage from './components/Logo';
import Login from "./components/Login";
import './App.css';

function App() {
  return (
      <div className="App">
        <HomePage />
          <Login />
        <Footer />
      </div>
  );
}

export default App;
