import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import LoginPage from './Login'; // Your existing login page
import Dashboard from './Dashboard';   // The dashboard component from the previous answer
import Signup from './Signup';
import PasswordResetLinkSender from './ResetPasswordLinkSender';
import NewPasswordPage from './GetNewPasswordPage';

function App() {

  const [username, setUsername] = useState('');
  const [emailID, setEmailID] = useState('');
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const onLoginSuccess = (name, user) => {
    console.log('NAME : ', name);
    console.log('USERNAME : ', user);
    setUsername(name);
    setEmailID(user);
    setIsLoggedIn(true);
  };


  const onLogOut = () => {
  alert('You are Logging Out!');
    setIsLoggedIn(false);
  };

  return (

    <Router>
      <Routes>
        <Route path="/" element={<LoginPage onLogin={onLoginSuccess} />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/Dashboard" element= {isLoggedIn ? <Dashboard name={username} username_={emailID} LogOut={onLogOut}/> : null }/>
        <Route path="/changePassword" element={<Dashboard />} />
        <Route path="passwordResetterLinkSender" element={<PasswordResetLinkSender />} />
      </Routes>
    </Router>

  );
}

export default App;
