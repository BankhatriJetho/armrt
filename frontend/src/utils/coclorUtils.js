export const getHeatmapColor = (value) => {
    if (value > 80) return 'red';
    if (value > 50) return 'orange';
    return 'green';
};
