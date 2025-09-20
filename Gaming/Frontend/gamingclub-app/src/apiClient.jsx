import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8081/api', // Your backend URL
});

// Add a request interceptor to include the JWT token
apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('jwtToken');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default apiClient;