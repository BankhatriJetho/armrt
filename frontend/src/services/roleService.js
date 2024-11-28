import axios from 'axios';

export const fetchRoleRecommendations = async () => {
    try {
        const response = await axios.get('/api/roles/recommendations');
        return response.data;
    } catch (error) {
        console.error('Error fetching recommendations:', error);
        throw error;
    }
};

export const approveRecommendation = async (id) => {
    try {
        await axios.post(`/api/roles/approve/${id}`);
    } catch (error) {
        console.error('Error approving recommendation:', error);
        throw error;
    }
};
