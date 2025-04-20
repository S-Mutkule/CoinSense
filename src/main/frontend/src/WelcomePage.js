import React from 'react';

const WelcomePage = () => {
  return (
    <div style={styles.container}>
      <h1 style={styles.heading}>Welcome to Your Dashboard</h1>
      <p style={styles.text}>You've successfully logged in!</p>
    </div>
  );
};

const styles = {
  container: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    minHeight: '100vh',
    background: 'linear-gradient(135deg, #e0f7fa 0%, #b2ebf2 100%)',
    fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
  },
  heading: {
    color: '#00796b',
    fontSize: '2.5rem',
    marginBottom: '1rem',
  },
  text: {
    color: '#00897b',
    fontSize: '1.2rem',
  }
};

export default WelcomePage;
