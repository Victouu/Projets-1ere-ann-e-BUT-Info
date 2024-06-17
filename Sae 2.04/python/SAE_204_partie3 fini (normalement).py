import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression

# Importation des données
collegeDF = pd.read_csv("dataCollege.csv", sep=";")

# Conversion des données en numpy array
collegeAr = collegeDF.to_numpy()
collegeAr = collegeAr[:, 2:]

# Fonction de centrage et de réduction
def Centreduire(T):
    n = len(T)
    p = len(T[0])
    T_CR = np.zeros((n, p))
    for j in range(p):
        moy = np.mean(T[:, j])
        ecart = np.std(T[:, j])
        for i in range(n):
            T_CR[i, j] = (T[i, j] - moy) / ecart
    return T_CR

collegeAr_CR = Centreduire(collegeAr)


test1 = collegeDF.groupby('taux_de_reussite_g')
plt.hist(collegeDF['taux_de_reussite_g'], rwidth=0.8)
plt.title('Taux de réussite des collèges')
plt.xlabel('taux de réussite')
plt.ylabel('nombre de collèges')
plt.show()

test1 = collegeDF.groupby('nb_mentions_tb_g')
plt.hist(collegeDF['nb_mentions_tb_g'], rwidth=0.8)
plt.title('Nombre de mentions très bien des collèges')
plt.xlabel('nombre de mentions très bien')
plt.ylabel('nombre de collèges')
plt.show()

test1 = collegeDF.groupby('ips')
plt.hist(collegeDF['ips'], rwidth=0.8)
plt.title('ips des collèges')
plt.xlabel('ips')
plt.ylabel('nombre de collèges')
plt.show()

test1 = collegeDF.groupby('nbre_eleve_segpa')
plt.hist(collegeDF['nbre_eleve_segpa'], rwidth=0.8)
plt.title('Nombre d\'élèves SEGPA des collèges')
plt.xlabel('Nombre d\'élèves SEGPA')
plt.ylabel('nombre de collèges')
plt.show()

test1 = collegeDF.groupby('nbre_eleve_ulis')
plt.hist(collegeDF['nbre_eleve_ulis'], rwidth=0.8)
plt.title('Nombre d\'élèves ULIS des collèges')
plt.xlabel('Nombre d\'élèves ULIS')
plt.ylabel('nombre de collèges')
plt.show()


# Calcul de la matrice de covariance
matrice_cov = np.cov(collegeAr_CR, rowvar=False)
print("Matrice de covariance :")
print(matrice_cov)

# Noms des variables pour les étiquettes des axes
variables = ['ips', 'nb_mentions_tb_g', 'nbre_eleve_ulis', 'nbre_eleve_segpa']

# Création de la heatmap
plt.figure(figsize=(10, 8))
cax = plt.imshow(matrice_cov, interpolation='nearest', cmap='coolwarm')
plt.colorbar(cax)

# Ajout des étiquettes
plt.xticks(np.arange(len(variables)), variables, rotation=45, ha='right')
plt.yticks(np.arange(len(variables)), variables)

plt.title('Matrice de covariance')
plt.show()


X = collegeDF[['ips', 'nb_mentions_tb_g', 'nbre_eleve_ulis', 'nbre_eleve_segpa']]
y = collegeDF['taux_de_reussite_g']

regression = LinearRegression()
regression.fit(X, y)

# Coefficients
print("Coefficients de la régression linéaire multiple :")
print("Coefficients :", regression.coef_)

# Résumé des résultats
coefficients = regression.coef_

# Interprétation des coefficients
print("\nInterprétation des coefficients :")
print(f"Coefficient pour l'IPS : {coefficients[0]}")
print(f"Coefficient pour le nombre de mentions TB : {coefficients[1]}")
print(f"Coefficient pour le nombre d'élèves ULIS : {coefficients[2]}")
print(f"Coefficient pour le nombre d'élèves SEGPA : {coefficients[3]}")

# Calcul du coefficient de corrélation multiple (R²)
r_squared = regression.score(X, y)
print(f"\nCoefficient de corrélation multiple (R²) : {r_squared}")
