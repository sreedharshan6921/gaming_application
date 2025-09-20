import React, { useState, useEffect } from 'react';
import axios from 'axios';
import apiClient from './apiClient';

const Dashboard = () => {
  const [dailyCollection, setDailyCollection] = useState(0);

  useEffect(() => {
    const fetchDailyCollections = async () => {
      try {
        const today = new Date().toISOString().split('T')[0];
        const response = await apiClient.get(
          `/collections/${today}`
        );
        setDailyCollection(response.data.amount);
      } catch (error) {
        console.error('Error fetching daily collections:', error);
        setDailyCollection(0);
      }
    };
    fetchDailyCollections();
  }, []);

  return (
    <div style={{ padding: '20px', backgroundColor: '#f0f2f5', minHeight: '100vh', fontFamily: 'Arial, sans-serif' }}>
      <div style={{ backgroundColor: 'white', padding: '30px', borderRadius: '10px', boxShadow: '0 4px 8px rgba(0,0,0,0.1)', textAlign: 'center' }}>
        <h2 style={{ color: '#333' }}>Admin Dashboard</h2>
        <p style={{ color: '#333' }}>Welcome to your gaming center's dashboard!</p>
        <h3 style={{ color: '#333' }}>Today's Collection: ${dailyCollection.toFixed(2)}</h3>
      </div>
    </div>
  );
};

export default Dashboard;