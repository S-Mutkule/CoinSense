import React, { useState } from 'react';
import './Login.css'; // Reuse the same CSS file
import { useNavigate } from 'react-router-dom';

const Signup = () => {

  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    name: '',
    username: '',
    password: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log('Signup Data:', formData);
    const apiUrl = process.env.REACT_APP_API_URL;
    console.log("URL : ", apiUrl);
    // Add your signup API call here
    try{
        const response = await fetch(`${apiUrl}/signup`, {
        method: 'POST',
        headers: { 'Content-Type' : 'application/json' },
        body: JSON.stringify({
        name : formData.name,
        username : formData.username,
        password : formData.password
            })
        });
        const data = await response.json();
        console.log('Response:', data);
        if(data.status){
            console.log('hurray!');
            console.log(data.message);
            alert('Signup successful, please proceed to login page');
        }
    } catch (error) {
        console.log('Response:');
        console.error('Error:', error);
    }
  };

  return (
    <div className="login-page">
      <form className="login-container" onSubmit={handleSubmit}>
        <h2>Create Account</h2>
        <div className="form-row">
          <div className="form-group">
            <label htmlFor="name">Full Name</label>
            <input
              type="text"
              id="name"
              name="name"
              value={formData.name}
              onChange={handleChange}
              placeholder="Enter full name"
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="username">Username</label>
            <input
              type="text"
              id="username"
              name="username"
              value={formData.username}
              onChange={handleChange}
              placeholder="Choose username"
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              placeholder="Create password"
              required
            />
          </div>
        </div>

        <div className="form-footer">
          <button className="login-btn" type="submit">Sign Up</button>
          <button
            type="button"
            className="forgot-link"
            onClick={() => navigate('/')}
            style={{ background: 'none', border: 'none', color: '#9a6bff', cursor: 'pointer', padding: 0 }}
          >
            Login Page
          </button>
        </div>
      </form>
    </div>
  );
};

export default Signup;
