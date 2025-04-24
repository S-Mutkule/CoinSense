import React from 'react';
import './Login.css';
import Signup from './Signup';
import { useNavigate } from 'react-router-dom';
import Dashboard from './Dashboard';
import PasswordResetter from './ResetPasswordLinkSender';
import { useState } from 'react';


const LoginPage = ({ onLogin }) => {
  const navigate = useNavigate();
  const [name, setName] = useState('');
  const [formData, setFormData] = React.useState({
    username: '',
    password: ''
  });

  const alertForUnregisteredUser = () => {
    alert('Unregistered User! Check the Username Entered')
  }

  const alertForWrongPassword = () => {
      alert('Wrong Password! Check the Password Entered')
    }

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

   const goToDashboard = ({name}) => {
      setName({name})
      onLogin(name);
      navigate('/Dashboard');
    };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(formData);
    try{
        const response = await fetch("/loginPage/login", {
                method: 'POST',
                headers: { 'Content-Type' : 'application/json' },
                body: JSON.stringify({
                 username : formData.username,
                 password : formData.password
                 })
            });
            const data = await response.json();
            console.log('Response:', data);
            if(data.status == 200){
                console.log('HUrray:', data);
                goToDashboard(data.name);
            } else{
                data.body.toLowerCase() === "wrong username" ? alertForUnregisteredUser() : alertForWrongPassword();
            }
      } catch (error) {
        console.error('Error:', error);
      }
  };



  return (
    <div className="login-page">
      <form className="login-container" onSubmit={handleSubmit}>
        <h2>Login to CoinSense</h2>
        <div className="form-row">
          <div className="form-group">
            <label htmlFor="username">Username</label>
            <input
              type="text"
              id="username"
              name="username"
              value={formData.username}
              onChange={handleChange}
              placeholder="Username"
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
              placeholder="Password"
              required
            />
          </div>
        </div>
        <div className="form-footer">
          <button className="login-btn" type="submit">Login</button>
          <div>
          <a
            className="forgot-link"
            onClick={() => navigate('/signup')}>
            New User?
          </a>
          </div>
          <div>
            <a className="forgot-link"
            onClick={() => navigate('/passwordResetterLinkSender')}>
            Forgot password?</a>
          </div>
        </div>
      </form>
    </div>
  );
};

export default LoginPage;
