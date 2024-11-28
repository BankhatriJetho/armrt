import axios from 'axios';

export const fetchReport = async () => {
    try {
        const response = await axios.get('/api/reports/comprehensive', { responseType: 'blob' });
        return response.data;
    } catch (error) {
        console.error('Error fetching report:', error);
        throw error;
    }
};
