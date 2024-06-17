#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

// taille du tableau
#define TAILLE 4
const char Z[2] = "Z";
const char A[2] = "A";
typedef int tGrille[TAILLE][TAILLE];

// Fonction pour charger une grille depuis un fichier
/**void chargerGrille(tGrille g)
{
    char nomFichier[30];
    FILE *f;

    // Demande le nom du fichier à l'utilisateur
    printf("Nom du fichier ? ");
    scanf("%s", nomFichier);

    // Ouvre le fichier
    f = fopen(nomFichier, "rb");

    // Tant que le fichier est incorrecte erreur et redemande
    while (f == NULL)
    {
        printf("\n ERREUR sur le fichier %s\n", nomFichier);
        printf("Nom du fichier ? ");
        scanf("%s", nomFichier);
        f = fopen(nomFichier, "rb");
    }

    // Insère les données du fichier dans la grille
    fread(g, sizeof(int), TAILLE * TAILLE, f);

    fclose(f);
}**/

// Fonction pour afficher la grille
void afficherGrille(tGrille grille1)
{
    int col, ligne;
    int nb = 1;

    // Affiche les indices de colonne
    printf("      1  2   3  4\n");
    printf("    +------+------+\n");

    for (ligne = 0; ligne < TAILLE; ligne++)
    {
        // Affiche une ligne de séparation supplémentaire après chaque 3 lignes
        if (ligne == 2)
        {
            printf("    +------+------+\n");
        }

        // Affiche le numéro de ligne et la grille
        printf("%d   |", nb);

        for (col = 0; col < TAILLE; col++)
        {
            // Ajoute une barre verticale après chaque 3 colonnes
            if (col == 2)
            {
                printf("|");
            }

            // Affiche le contenu de la case, soit '.' pour 0, soit la valeur
            if (grille1[ligne][col] == 0)
            {
                printf(" . ");
            }
            else
            {
                printf(" %d ", grille1[ligne][col]);
            }
        }
        printf("|\n");
        nb++;
    }
    // Affiche la ligne de séparation en bas de la grille
    printf("    +------+------+\n");
}

// Fonction pour compter le nombre de zéros dans la grille
int compteZero(tGrille grille1)
{
    int col, ligne, nbzero;
    nbzero = 0;
    for (ligne = 0; ligne < TAILLE; ligne++)
    {
        for (col = 0; col < TAILLE; col++)
        {
            if (grille1[ligne][col] == 0)
            {
                nbzero++;
            }
        }
    }
    return nbzero;
}

// Fonction pour saisir une valeur entre 1 et 9
void saisir(int *valeur)
{
    char val[2];
    scanf("%s", val);

    if (sscanf(val, "%d", valeur) != 0 && *valeur >= 1 && *valeur <= 9)
    {
        // La valeur est valide
    }
    else
    {
        // Tant que la valeur saisie n'est pas un entier entre 1 et 9, redemande la saisie
        while (sscanf(val, "%d", valeur) == 0 || *valeur < 1 || *valeur > 9)
        {
            printf("La valeur saisie doit etre un entier entre 1 et 9, reessayer :\n");
            scanf("%s", val);
        }
    }
}

// Fonction pour vérifier si une valeur peut être placée dans une certaine position
bool possible(tGrille grille1, int numligne, int numcol, int valeur)
{
    int numcolbloc, numlignebloc;

    // Vérifie la ligne
    for (int col = 0; col < TAILLE; col++)
    {
        if (grille1[numligne][col] == valeur)
        {
            printf("ERREUR: la valeur est deja presente dans la ligne.\n");
            return false;
        }
    }

    // Vérifie la colonne
    for (int ligne = 0; ligne < TAILLE; ligne++)
    {
        if (grille1[ligne][numcol] == valeur)
        {
            printf("ERREUR: la valeur est deja presente dans la colonne.\n");
            return false;
        }
    }

    // Vérifie le carré
    numcolbloc = (numcol / 2) * 2;
    numlignebloc = (numligne / 2) * 2;

    for (int ligne = numlignebloc; ligne < numlignebloc + 3; ligne++)
    {
        for (int col = numcolbloc; col < numcolbloc + 3; col++)
        {
            if (grille1[ligne][col] == valeur)
            {
                printf("ERREUR: la valeur est deja presente dans le carre de 3*3.\n");
                return false;
            }
        }
    }

    // Si la valeur peut être placée, retourne vrai
    return true;
}

int main()
{ // initialisation des variables
    tGrille grille1 = {{1, 2, 3, 0},
                       {3, 0, 1, 0},
                       {2, 1, 4, 3},
                       {0, 0, 2, 1}};
    int numLigne, numColonne, valeur, nbZero, colDernierCoup, ligneDernierCoup;
    char reponse[2];
    bool verif;

    // Charge la grille depuis un fichier
    // chargerGrille(grille1);

    // Compte le nombre de zéros dans la grille
    nbZero = compteZero(grille1);

    // Tant qu'il y a des zéros dans la grille
    while (nbZero != 0)
    {
        // Affiche la grille
        afficherGrille(grille1);

        // Demande les coordonnées à l'utilisateur
        printf("Action ?(A ou Z)\n");
        scanf("%s", reponse);
        if (strcmp(reponse, Z) == 0)
        {
            if (grille1[ligneDernierCoup][colDernierCoup] != 0)
            {
                grille1[ligneDernierCoup][colDernierCoup] = 0;
            }
            else
            {
                afficherGrille(grille1);
            }
        }
        else if (strcmp(reponse, A) == 0)
        {
            printf("Coordonnees ? (ligne puis colonne)\n");
            saisir(&numLigne);
            saisir(&numColonne);

            // Ajuste les indices pour correspondre aux indices du tableau
            numLigne = numLigne - 1;
            numColonne = numColonne - 1;

            // Vérifie si la case est libre
            if (grille1[numLigne][numColonne] != 0)
            {
                printf("Impossible, la case n'est pas libre.\n");
            }
            else
            {
                // Demande la valeur à l'utilisateur
                printf("Valeur ?\n");
                saisir(&valeur);

                // Vérifie si la valeur peut être placée dans la case
                verif = possible(grille1, numLigne, numColonne, valeur);

                // Si la valeur est valide, la place dans la grille et met à jour le nombre de zéros
                if (verif == true)
                {
                    grille1[numLigne][numColonne] = valeur;
                    colDernierCoup = numColonne;
                    ligneDernierCoup = numLigne;

                    nbZero = compteZero(grille1);
                }
                else
                {
                    // Sinon, ne fait rien
                }
            }
        }
    }

    // Affiche un message lorsque la grille est pleine
    afficherGrille(grille1);
    printf("Grille pleine, fin de partie");

    return EXIT_SUCCESS;
}
