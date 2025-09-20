import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

const Navbar = ({ setIsLoggedIn }) => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('jwtToken');
    setIsLoggedIn(false);
    navigate('/login');
  };

  return (
    <nav style={{ padding: '15px 30px', backgroundColor: '#2c3e50', color: 'white', display: 'flex', justifyContent: 'space-between', alignItems: 'center', boxShadow: '0 2px 5px rgba(0,0,0,0.2)' }}>
      <h1 style={{ margin: 0, fontSize: '24px' }}>Gaming Center Admin</h1>
      <div style={{ display: 'flex', gap: '20px' }}>
        <Link to="/" style={{ color: 'white', textDecoration: 'none', fontWeight: 'bold' }}>
          Dashboard
        </Link>
        <Link to="/members" style={{ color: 'white', textDecoration: 'none', fontWeight: 'bold' }}>
          Members
        </Link>
        <Link to="/games" style={{ color: 'white', textDecoration: 'none', fontWeight: 'bold' }}>
          Games
        </Link>
        <Link to="/transactions" style={{ color: 'white', textDecoration: 'none', fontWeight: 'bold' }}>
          Transactions
        </Link>
        <button onClick={handleLogout} style={{ padding: '8px 16px', fontSize: '14px', backgroundColor: '#e74c3c', color: 'white', border: 'none', borderRadius: '5px', cursor: 'pointer' }}>
          Logout
        </button>
      </div>
    </nav>
  );
};

export default Navbar;