import React, { useState, useEffect } from 'react';
import apiClient from './apiClient';

const GamesPage = () => {
  const [games, setGames] = useState([]);
  const [newGame, setNewGame] = useState({
    name: '',
    price: '',
    description: '',
  });

  const fetchGames = async () => {
    try {
      const response = await apiClient.get('/games');
      setGames(response.data);
    } catch (error) {
      console.error('Error fetching games:', error);
      alert('Failed to fetch games. Please log in again.');
    }
  };

  useEffect(() => {
    fetchGames();
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewGame({ ...newGame, [name]: value });
  };

  const handleAddGame = async (e) => {
    e.preventDefault();
    try {
      await apiClient.post('/games', newGame);
      alert('Game added successfully!');
      setNewGame({ name: '', price: '', description: '' });
      fetchGames(); // Refresh the list
    } catch (error) {
      console.error('Error adding game:', error);
      alert('Failed to add game.');
    }
  };

  return (
    <div style={{ padding: '20px', backgroundColor: '#f0f2f5', minHeight: '100vh', fontFamily: 'Arial, sans-serif' }}>
      <div style={{ backgroundColor: 'white', padding: '30px', borderRadius: '10px', boxShadow: '0 4px 8px rgba(0,0,0,0.1)' }}>
        <h2 style={{ textAlign: 'center', color: '#333' }}>Games Management</h2>

        <div style={{ marginBottom: '40px' }}>
          <h3>Add New Game</h3>
          <form onSubmit={handleAddGame} style={{ display: 'flex', flexDirection: 'column', gap: '15px' }}>
            <input
              type="text"
              name="name"
              placeholder="Game Name"
              value={newGame.name}
              onChange={handleInputChange}
              required
              style={{ padding: '10px', fontSize: '16px', borderRadius: '5px', border: '1px solid #ccc' }}
            />
            <input
              type="number"
              name="price"
              placeholder="Price"
              value={newGame.price}
              onChange={handleInputChange}
              required
              style={{ padding: '10px', fontSize: '16px', borderRadius: '5px', border: '1px solid #ccc' }}
            />
            <input
              type="text"
              name="description"
              placeholder="Description"
              value={newGame.description}
              onChange={handleInputChange}
              required
              style={{ padding: '10px', fontSize: '16px', borderRadius: '5px', border: '1px solid #ccc' }}
            />
            <button type="submit" style={{ padding: '10px 20px', fontSize: '16px', backgroundColor: '#007bff', color: 'white', border: 'none', borderRadius: '5px', cursor: 'pointer' }}>
              Add Game
            </button>
          </form>
        </div>

        <div>
          <h3>All Games</h3>
          <ul style={{ listStyleType: 'none', padding: 0 }}>
            {games.map((game) => (
              <li key={game.id} style={{ padding: '10px', borderBottom: '1px solid #eee' }}>
                <strong>{game.name}</strong> - ${game.price} - *{game.description}*
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default GamesPage;