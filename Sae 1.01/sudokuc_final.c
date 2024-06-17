#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define TAILLE 9

typedef int tGrille[TAILLE][TAILLE];

void chargerGrille(tGrille g)
{
    char nomFichier[30];
    FILE *f;
    printf("Nom du fichier ? ");
    scanf("%s", nomFichier);
    f = fopen(nomFichier, "rb");
    if (f == NULL)
    {
        printf("\n ERREUR sur le fichier %s\n", nomFichier);
    }
    else
    {
        fread(g, sizeof(int), TAILLE * TAILLE, f);
    }
    fclose(f);
}

void afficherGrille(tGrille grille1)
{
    int col, ligne;
    int nb = 1;

    printf("      1  2  3   4  5  6   7  8  9\n");
    printf("    +---------+---------+---------+\n");

    for (ligne = 0; ligne < TAILLE; ligne++)
    {
        if (ligne == 3 || ligne == 6)
        {
            printf("    +---------+---------+---------+\n");
        }

        printf("%d   |", nb);

        for (col = 0; col < TAILLE; col++)
        {
            if (col == 3 || col == 6)
            {
                printf("|");
            }

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
    printf("    +---------+---------+---------+\n");
}

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

void saisir(int *valeur)
{
    char val[2];
    scanf("%s", val);

    if (sscanf(val, "%d", valeur) != 0 && *valeur >= 1 && *valeur <= 9)
    {
    }

    else
    {
        while (sscanf(val, "%d", valeur) == 0 || *valeur < 1 || *valeur > 9)
        {
            printf("La valeur saisie doit etre un entier entre 1 et 9\n");
            printf("valeur ?\n");
            scanf("%s", val);
        }
    }
}

bool possible(tGrille grille1, int numligne, int numcol, int valeur)
{
    int numcolbloc, numlignebloc;
    for (int col = 0; col < TAILLE; col++)
    {
        if (grille1[numligne][col] == valeur)
        {
            return false;
        }
    }

    for (int ligne = 0; ligne < TAILLE; ligne++)
    {
        if (grille1[ligne][numcol] == valeur)
        {
            return false;
        }
    }
    // check carre
    numcolbloc = numcol / 3;
    numlignebloc = numligne / 3;

    for (int ligne = numlignebloc * 3; ligne < (numlignebloc * 3) + 3; ligne++)
    {
        for (int col = numcolbloc * 3; col < (numcolbloc * 3) + 3; col++)
        {
            if (grille1[ligne][col] == valeur)
            {
                return false;
            }
        }
    }
    return true;
}

int main()
{
    tGrille grille1;
    int numLigne, numColonne, valeur, nbZero;
    bool verif;

    chargerGrille(grille1);

    nbZero = compteZero(grille1);

    while (nbZero != 0)
    {
        afficherGrille(grille1);
        printf("Coordonees ? (ligne puis colonne)\n");
        saisir(&numLigne);
        saisir(&numColonne);
        numLigne = numLigne - 1;
        numColonne = numColonne - 1;
        if (grille1[numLigne][numColonne] != 0)
        {
            printf("Impossible, la case n'est pas libre.\n");
        }
        else
        {
            printf("valeur ?\n");
            saisir(&valeur);
            verif = possible(grille1, numLigne, numColonne, valeur);
            if (verif == true)
            {
                grille1[numLigne][numColonne] = valeur;
                nbZero = compteZero(grille1);
            }
            else
            {
                printf("Valeur deja presente dans la grille\n");
            }
        }
    }
    printf("Grille pleine, fin de partie");

    return EXIT_SUCCESS;
}