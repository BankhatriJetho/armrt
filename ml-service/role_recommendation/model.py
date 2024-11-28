import pandas as pd
from sklearn.decomposition import PCA
from sklearn.cluster import KMeans
from sklearn.preprocessing import StandardScaler

class RoleRecommendationModel:
    def __init__(self, n_clusters=3):
        self.n_clusters = n_clusters
        self.pca = PCA(n_components=2)
        self.kmeans = KMeans(n_clusters=self.n_clusters, random_state=42)
        self.scaler = StandardScaler()

    def fit_transform(self, data):
        """
        Prepares and reduces data dimensionality with PCA.
        """
        scaled_data = self.scaler.fit_transform(data)
        reduced_data = self.pca.fit_transform(scaled_data)
        return reduced_data

    def cluster_data(self, data):
        """
        Clusters data into groups using KMeans.
        """
        return self.kmeans.fit_predict(data)

    def generate_role_recommendations(self, data):
        """
        Generates role recommendations based on clusters.
        """
        # Example logic for recommendations
        reduced_data = self.fit_transform(data)
        clusters = self.cluster_data(reduced_data)

        data['cluster'] = clusters
        recommendations = data.groupby('cluster')['role'].apply(list).to_dict()

        return recommendations
