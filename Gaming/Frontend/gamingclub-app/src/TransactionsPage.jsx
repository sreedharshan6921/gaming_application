import React, { useState } from 'react';
import apiClient from './apiClient';

const TransactionsPage = () => {
  const [memberId, setMemberId] = useState('');
  const [gameId, setGameId] = useState('');
  const [updateMemberId, setUpdateMemberId] = useState('');
  const [rechargeAmount, setRechargeAmount] = useState('');

  const handleRecordGamePlay = async (e) => {
    e.preventDefault();
    try {
      await apiClient.post('/transactions', null, {
        params: { memberId, gameId },
      });
      alert('Game play recorded successfully!');
      setMemberId('');
      setGameId('');
    } catch (error) {
      console.error('Error recording game play:', error);
      alert('Failed to record game play. Check IDs or balance.');
    }
  };

  const handleUpdateBalance = async (e) => {
    e.preventDefault();
    try {
      await apiClient.put(
        `/members/${updateMemberId}/update-balance`,
        parseFloat(rechargeAmount),
        {
          headers: { 'Content-Type': 'application/json' },
        }
      );
      alert('Balance updated successfully!');
      setUpdateMemberId('');
      setRechargeAmount('');
    } catch (error) {
      console.error('Error updating balance:', error);
      alert('Failed to update balance. Check ID.');
    }
  };

  return (
    <div style={{ padding: '20px', backgroundColor: '#f0f2f5', minHeight: '100vh', fontFamily: 'Arial, sans-serif' }}>
      <div style={{ backgroundColor: 'white', padding: '30px', borderRadius: '10px', boxShadow: '0 4px 8px rgba(0,0,0,0.1)' }}>
        <h2 style={{ textAlign: 'center', color: '#333' }}>Transactions Management</h2>

        <div style={{ marginBottom: '40px' }}>
          <h3>Record Game Play</h3>
          <form onSubmit={handleRecordGamePlay} style={{ display: 'flex', flexDirection: 'column', gap: '15px' }}>
            <input
              type="text"
              placeholder="Member ID"
              value={memberId}
              onChange={(e) => setMemberId(e.target.value)}
              required
              style={{ padding: '10px', fontSize: '16px', borderRadius: '5px', border: '1px solid #ccc' }}
            />
            <input
              type="text"
              placeholder="Game ID"
              value={gameId}
              onChange={(e) => setGameId(e.target.value)}
              required
              style={{ padding: '10px', fontSize: '16px', borderRadius: '5px', border: '1px solid #ccc' }}
            />
            <button type="submit" style={{ padding: '10px 20px', fontSize: '16px', backgroundColor: '#007bff', color: 'white', border: 'none', borderRadius: '5px', cursor: 'pointer' }}>
              Record Game Play
            </button>
          </form>
        </div>

        <div>
          <h3>Update Member Balance</h3>
          <form onSubmit={handleUpdateBalance} style={{ display: 'flex', flexDirection: 'column', gap: '15px' }}>
            <input
              type="text"
              placeholder="Member ID"
              value={updateMemberId}
              onChange={(e) => setUpdateMemberId(e.target.value)}
              required
              style={{ padding: '10px', fontSize: '16px', borderRadius: '5px', border: '1px solid #ccc' }}
            />
            <input
              type="number"
              placeholder="Recharge Amount"
              value={rechargeAmount}
              onChange={(e) => setRechargeAmount(e.target.value)}
              required
              style={{ padding: '10px', fontSize: '16px', borderRadius: '5px', border: '1px solid #ccc' }}
            />
            <button type="submit" style={{ padding: '10px 20px', fontSize: '16px', backgroundColor: '#28a745', color: 'white', border: 'none', borderRadius: '5px', cursor: 'pointer' }}>
              Update Balance
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default TransactionsPage;