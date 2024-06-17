/**
 * @file sudoku.c
 * @brief Programme permettant de résoudre un Sudoku.
 * @author ROUE Victor
 * @date 28/11/2023
 * @version 1.0
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

// taille du tableau
#define TAILLE 9

typedef int tGrille[TAILLE][TAILLE];

/**
 * @brief Charge une grille depuis un fichier.
 *
 * Demande à l'utilisateur le nom du fichier, puis ouvre le fichier et lit les données dans la grille.
 *
 * @param[in,out] g La grille à remplir.
 */
void chargerGrille(tGrille g);

/**
 * @brief Affiche la grille de Sudoku.
 *
 * Affiche la grille de manière lisible avec des indices de lignes et colonnes.
 *
 * @param[in] grille1 La grille à afficher.
 */
void afficherGrille(tGrille grille1);

/**
 * @brief Compte le nombre de zéros dans la grille.
 *
 * @param[in] grille1 La grille à analyser.
 * @return Le nombre de zéros dans la grille.
 */
int compteZero(tGrille grille1);

/**
 * @brief Saisit une valeur entre 1 et 9.
 *
 * Saisit une valeur depuis l'utilisateur et vérifie si elle est valide (entier entre 1 et 9).
 *
 * @param[out] valeur Pointeur vers la variable où stocker la valeur saisie.
 */
void saisir(int *valeur);

/**
 * @brief Vérifie si une valeur peut être placée dans une certaine position de la grille.
 *
 * Vérifie si la valeur respecte les règles du Sudoku pour une position donnée.
 *
 * @param[in] grille1 La grille à vérifier.
 * @param[in] numligne L'indice de ligne.
 * @param[in] numcol L'indice de colonne.
 * @param[in] valeur La valeur à placer.
 * @return true si la valeur peut être placée, false sinon.
 */
bool possible(tGrille grille1, int numligne, int numcol, int valeur);

/**
 * @brief Fonction principale du programme.
 *
 * Charge une grille depuis un fichier, permet à l'utilisateur de remplir la grille, vérifie la validité des valeurs
 * saisies et affiche la grille résultante.
 *
 * @return EXIT_SUCCESS si le programme s'exécute correctement.
 */
int main();
