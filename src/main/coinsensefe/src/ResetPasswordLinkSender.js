import React, { useState } from 'react';

function ResetPasswordLinkSender() {
  const [email, setEmail] = useState('');
  const [error, setError] = useState('');

  // Basic email validation using regular expression


  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = fetch("/resetpassword/sendlink", {
        method: 'POST',
        headers: {'Content-Type' : 'application/json' },
        body: JSON.stringify({
            emailID : email
        })
    });
    const data = await response;
    if(data.status == 200){
        console.log("link sent, please check");
    } else{
        console.log("Please enter valid email id");
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <button type="submit">Submit</button>
      </form>
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </div>
  );
}

export default ResetPasswordLinkSender;
