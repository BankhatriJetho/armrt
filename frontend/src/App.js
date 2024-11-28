import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import AppHeader from './components/AppHeader';
import AppFooter from './components/AppFooter';
import EntitlementHeatmap from './components/EntitlementHeatmap';
import RoleRecommendationTable from './components/RoleRecommendationTable';
import RoleDistributionChart from './components/RoleDistributionChart';

const App = () => {
    return (
        <Router>
            <div className="app-container">
                {/* Header */}
                <AppHeader />

                {/* Main Content */}
                <main className="main-content">
                    <Routes>
                        <Route path="/" element={<Navigate to="/roles" />} />
                        <Route path="/roles" element={<RoleRecommendationTable />} />
                        <Route path="/entitlements" element={<EntitlementHeatmap />} />
                        <Route path="/distribution" element={<RoleDistributionChart data={[]} />} />
                    </Routes>
                </main>

                {/* Footer */}
                <AppFooter />
            </div>
        </Router>
    );
};
export default App;
