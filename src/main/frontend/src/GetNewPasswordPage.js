import React from 'react';
import { useLocation } from 'react-router-dom';

function GetNewPasswordPage() {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const token = searchParams.get('token');
  const username = searchParams.get('username');

  return (
    <div className="password-reset-container">
      <h2>Password Reset</h2>
      <div className="info-box">
        <p><strong>Token:</strong> {token}</p>
        <p><strong>Email:</strong> {username}</p>
      </div>

      <form className="password-form">
        <div className="form-group">
          <label htmlFor="newPassword">New Password</label>
          <input
            type="password"
            id="newPassword"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="confirmPassword">Confirm Password</label>
          <input
            type="password"
            id="confirmPassword"
            required
          />
        </div>

        <button type="submit" className="submit-button">
          Update Password
        </button>
      </form>
    </div>
  );
}

export default GetNewPasswordPage;
