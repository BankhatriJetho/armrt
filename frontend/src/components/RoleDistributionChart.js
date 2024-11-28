import React from 'react';
import { Chart } from 'react-chartjs-2';

const RoleDistributionChart = ({ data }) => {
    const chartConfig = {
        labels: data.map((item) => item.role),
        datasets: [
            {
                label: 'Role Distribution',
                data: data.map((item) => item.count),
                backgroundColor: 'rgba(75,192,192,0.6)',
            },
        ],
    };

    return <Chart type="bar" data={chartConfig} />;
};

export default RoleDistributionChart;
