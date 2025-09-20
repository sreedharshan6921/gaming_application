import React, { useState, useEffect } from 'react';
import apiClient from './apiClient';

const MembersPage = () => {
  const [members, setMembers] = useState([]);
  const [newMember, setNewMember] = useState({
    name: '',
    phone: '',
    balance: 0,
  });

  const fetchMembers = async () => {
    try {
      const response = await apiClient.get('/members');
      setMembers(response.data);
    } catch (error) {
      console.error('Error fetching members:', error);
      alert('Failed to fetch members. Please log in again.');
    }
  };

  useEffect(() => {
    fetchMembers();
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewMember({ ...newMember, [name]: value });
  };

  const handleRegisterMember = async (e) => {
    e.preventDefault();
    try {
      await apiClient.post('/members/register', newMember);
      alert('Member registered successfully!');
      setNewMember({ name: '', phone: '', balance: 0 });
      fetchMembers(); // Refresh the list
    } catch (error) {
      console.error('Error registering member:', error);
      alert('Failed to register member.');
    }
  };

  return (
    <div style={{ padding: '20px', backgroundColor: '#f0f2f5', minHeight: '100vh', fontFamily: 'Arial, sans-serif' }}>
      <div style={{ backgroundColor: 'white', padding: '30px', borderRadius: '10px', boxShadow: '0 4px 8px rgba(0,0,0,0.1)' }}>
        <h2 style={{ textAlign: 'center', color: '#333' }}>Members Management</h2>

        <div style={{ marginBottom: '40px' }}>
          <h3>Register New Member</h3>
          <form onSubmit={handleRegisterMember} style={{ display: 'flex', flexDirection: 'column', gap: '15px' }}>
            <input
              type="text"
              name="name"
              placeholder="Full Name"
              value={newMember.name}
              onChange={handleInputChange}
              required
              style={{ padding: '10px', fontSize: '16px', borderRadius: '5px', border: '1px solid #ccc' }}
            />
            <input
              type="text"
              name="phone"
              placeholder="Phone Number"
              value={newMember.phone}
              onChange={handleInputChange}
              required
              style={{ padding: '10px', fontSize: '16px', borderRadius: '5px', border: '1px solid #ccc' }}
            />
            <input
              type="number"
              name="balance"
              placeholder="Initial Balance"
              value={newMember.balance}
              onChange={handleInputChange}
              required
              style={{ padding: '10px', fontSize: '16px', borderRadius: '5px', border: '1px solid #ccc' }}
            />
            <button type="submit" style={{ padding: '10px 20px', fontSize: '16px', backgroundColor: '#28a745', color: 'white', border: 'none', borderRadius: '5px', cursor: 'pointer' }}>
              Register Member
            </button>
          </form>
        </div>

        <div>
          <h3>All Members</h3>
          <ul style={{ listStyleType: 'none', padding: 0 }}>
            {members.map((member) => (
              <li key={member.id} style={{ padding: '10px', borderBottom: '1px solid #eee' }}>
                <strong>{member.name}</strong> - {member.phone} (Balance: ${member.balance})
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default MembersPage;