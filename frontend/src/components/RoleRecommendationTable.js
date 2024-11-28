import React, { useEffect, useState } from 'react';
import { fetchRoleRecommendations, approveRecommendation } from '../services/roleService';

const RoleRecommendationTable = () => {
    const [recommendations, setRecommendations] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetchRoleRecommendations()
            .then((data) => setRecommendations(data))
            .catch(() => setError('Failed to load role recommendations'))
            .finally(() => setLoading(false));
    }, []);

    const handleApprove = (id) => {
        approveRecommendation(id)
            .then(() => alert('Recommendation approved!'))
            .catch(() => alert('Failed to approve recommendation'));
    };

    if (loading) return <div>Loading recommendations...</div>;
    if (error) return <div>{error}</div>;

    return (
        <table>
            <thead>
                <tr>
                    <th>User</th>
                    <th>Current Roles</th>
                    <th>Recommended Roles</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {recommendations.map((rec) => (
                    <tr key={rec.id}>
                        <td>{rec.user}</td>
                        <td>{rec.currentRoles.join(', ')}</td>
                        <td>{rec.recommendedRoles.join(', ')}</td>
                        <td>
                            <button onClick={() => handleApprove(rec.id)}>Approve</button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
};

export default RoleRecommendationTable;
