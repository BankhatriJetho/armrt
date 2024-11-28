import axios from 'axios';

export const fetchEntitlements = async () => {
    try {
        const response = await axios.get('/api/entitlements');
        return response.data;
    } catch (error) {
        console.error('Error fetching entitlements:', error);
        throw error;
    }
};
