import React, { useEffect, useState } from 'react';
import { fetchEntitlements } from '../services/entitlementService';

const EntitlementHeatmap = () => {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        fetchEntitlements()
            .then((entitlements) => setData(entitlements))
            .catch((err) => setError('Failed to load entitlements'))
            .finally(() => setLoading(false));
    }, []);

    if (loading) return <div>Loading heatmap...</div>;
    if (error) return <div>{error}</div>;

    return <div>{/* Render heatmap using data */}</div>;
};

export default EntitlementHeatmap;
