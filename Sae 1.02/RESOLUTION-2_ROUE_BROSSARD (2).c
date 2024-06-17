#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

#define N 4
#define TAILLE (N * N)

// Structure représentant une case dans la grille Sudoku
typedef struct
{
    int valeur;
    bool candidats[TAILLE + 1];
    int nbCandidats;
} tCase1;

// Grille de Sudoku
typedef tCase1 tGrille[TAILLE][TAILLE];

const int TOTAL_CELLS = TAILLE * TAILLE;
const char barreVert = '|';
const char barreHori = '-';
const char point = '.';
const char coin = '+';
const char espace = ' ';

// Charge une grille à partir d'un fichier
void chargerGrille(tGrille grille)
{
    FILE *f;
    char nomFichier[30];

    printf("Nom du fichier ? \n");
    scanf("%s", nomFichier);

    // Ouvre le fichier
    f = fopen(nomFichier, "rb");
    if (f == NULL)
    {
        printf("\nERREUR sur le fichier %s\n", nomFichier);
        exit(EXIT_FAILURE);
    }
    else
    {
        // Lit les valeurs du fichier dans la grille
        for (int ligne = 0; ligne < TAILLE; ligne++)
        {
            for (int col = 0; col < TAILLE; col++)
            {
                fread(&grille[ligne][col].valeur, sizeof(int), 1, f);
                grille[ligne][col].nbCandidats = 0;
                for (int candidat = 0; candidat < TAILLE; candidat++)
                {
                    grille[ligne][col].candidats[candidat] = false;
                }
            }
        }
        fclose(f);
    }
}

// Compte le nombre de cases vides dans la grille
int NBCasevide(tGrille g)
{
    int NBcase = 0;

    for (int ligne = 0; ligne < TAILLE; ligne++)
    {
        for (int col = 0; col < TAILLE; col++)
        {
            if (g[ligne][col].valeur == 0)
            {
                NBcase++;
            }
        }
    }
    return NBcase;
}

// Vérifie si une valeur peut être placée dans une case donnée
bool possible(tGrille grille1, int numligne, int numcol, int valeur)
{
    // Vérifie la ligne
    for (int col = 0; col < TAILLE; col++)
    {
        if (grille1[numligne][col].valeur == valeur)
        {
            return false;
        }
    }

    // Vérifie la colonne
    for (int ligne = 0; ligne < TAILLE; ligne++)
    {
        if (grille1[ligne][numcol].valeur == valeur)
        {
            return false;
        }
    }

    // Vérifie le bloc
    int numcolbloc = (numcol / N) * N;
    int numlignebloc = (numligne / N) * N;

    for (int ligne = numlignebloc; ligne < numlignebloc + N; ligne++)
    {
        for (int col = numcolbloc; col < numcolbloc + N; col++)
        {
            if (grille1[ligne][col].valeur == valeur)
            {
                return false;
            }
        }
    }

    return true;
}

// Ajoute un candidat à la liste des candidats d'une case
void ajouterCandidat(tCase1 *laCase, int val)
{
    if (!laCase->candidats[val])
    {
        laCase->candidats[val] = true;
        laCase->nbCandidats++;
    }
}

// Retire un candidat de la liste des candidats d'une case
void retirerCandidat(tCase1 *laCase, int val)
{
    laCase->nbCandidats--;
    laCase->candidats[val] = false;
}

// Vérifie si une valeur est un candidat dans une case
bool estCandidat(tCase1 laCase, int val)
{
    return laCase.candidats[val];
}

// Compte les candidat
int nbCandidats(tCase1 laCase)
{
    return laCase.nbCandidats;
}

// Initialise les candidats des cases vides
void initialiserCandidats(tGrille g)
{
    for (int ligne = 0; ligne < TAILLE; ligne++)
    {
        for (int col = 0; col < TAILLE; col++)
        {
            if (g[ligne][col].valeur == 0)
            {
                g[ligne][col].nbCandidats = 0;

                for (int val = 1; val <= TAILLE; val++)
                {
                    g[ligne][col].candidats[val] = possible(g, ligne, col, val);
                    if (g[ligne][col].candidats[val])
                    {
                        g[ligne][col].nbCandidats++;
                    }
                }
            }
        }
    }
}

// Applique la stratégie du singleton nu
void singletonNu(tGrille g, bool *progression)
{
    for (int ligne = 0; ligne < TAILLE; ligne++)
    {
        for (int col = 0; col < TAILLE; col++)
        {

            if (g[ligne][col].valeur == 0)
            {

                if (nbCandidats(g[ligne][col]) == 1)
                {
                    for (int x = 1; x < TAILLE + 1; x++)
                    {
                        if (g[ligne][col].candidats[x] == true)
                        {

                            g[ligne][col].valeur = x;

                            initialiserCandidats(g);
                            *progression = true;
                        }
                    }
                }
            }
        }
    }
}

// Applique la stratégie du singleton caché
int compterCandidatsPossibles(tGrille g, int ligne, int colonne, int valeurCandidat)
{
    int compteurCandidats = 0;
    int debutLigne = (ligne / N) * N;
    int debutColonne = (colonne / N) * N;

    for (int i = debutLigne; i < debutLigne + N; i++)
    {
        for (int j = debutColonne; j < debutColonne + N; j++)
        {
            if (g[i][j].valeur == 0 && g[i][j].candidats[valeurCandidat] == true)
            {
                compteurCandidats++;
            }
        }
    }
    return compteurCandidats;
}

void singletonCaches(tGrille g, bool *progression)
{
    for (int ligne = 0; ligne < TAILLE; ligne++)
    {
        for (int colonne = 0; colonne < TAILLE; colonne++)
        {
            if (g[ligne][colonne].valeur == 0)
            {
                for (int valeurCandidat = 1; valeurCandidat <= TAILLE; valeurCandidat++)
                {
                    if (g[ligne][colonne].candidats[valeurCandidat] == true && compterCandidatsPossibles(g, ligne, colonne, valeurCandidat) == 1)
                    {
                        g[ligne][colonne].valeur = valeurCandidat;
                        initialiserCandidats(g);
                        break;
                    }
                }
            }
        }
    }
}

// affiche la grille
void afficherGrille(tGrille grille1)
{

    int indiceL = 1;
    printf("    ");
    for (int indiceC = 1; indiceC <= (N * N); indiceC++)
    {
        if (indiceC <= 9)
        {
            printf(" %c%d ", espace, indiceC);
        }
        else
        {
            printf("%c%d%c", espace, indiceC, espace);
        }
        if ((indiceC == N) || (indiceC == 2 * N) || (indiceC == 3 * N))
        {
            printf("%c", espace);
        }
    }
    printf("\n");
    for (int i = 0; i < (N * N); i++)
    {
        if ((i == 0) || (i == N) || (i == 2 * N) || (i == 3 * N))
        {
            printf("%c%c%c%c", espace, espace, espace, coin);
            for (int ligneSep = 0; ligneSep < N; ligneSep++)
            {
                for (int ligneTiret = 0; ligneTiret < (N * N); ligneTiret++)
                {
                    printf("%c", barreHori);
                }
                printf("%c", coin);
            }
            printf("\n");
        }
        if (indiceL <= 9)
        {
            printf("%c%d%c", espace, indiceL, espace);
        }
        else
        {
            printf("%d%c", indiceL, espace);
        }
        indiceL++;
        for (int j = 0; j < (N * N); j++)
        {

            if ((j == 0) || (j == N) || (j == 2 * N) || (j == 3 * N))
            {
                printf("%c", barreVert);
            }
            if (grille1[i][j].valeur == 0)
            {
                printf("%c%c%c%c", espace, espace, point, espace);
            }
            // affiche la valeur
            else
            {
                if (grille1[i][j].valeur <= 9)
                {
                    printf("%c%c%d%c", espace, espace, grille1[i][j].valeur, espace);
                }
                else
                {
                    printf("%c%d%c", espace, grille1[i][j].valeur, espace);
                }
            }
        }
        printf("%c\n", barreVert);
    }
    printf("%c%c%c%c", espace, espace, espace, coin);
    for (int ligneSep = 0; ligneSep < N; ligneSep++)
    {
        for (int ligneTiret = 0; ligneTiret < N * N; ligneTiret++)
        {
            printf("%c", barreHori);
        }
        printf("%c", coin);
    }
    printf("\n");
}
// fonction backtracking
bool backtracking(tGrille g, int numeroCase)
{
    int ligne, col;
    bool result;
    result = false;

    if (numeroCase == TOTAL_CELLS)
    {
        result = true;
    }
    else
    {
        ligne = numeroCase / TAILLE;
        col = numeroCase % TAILLE;
        if (g[ligne][col].valeur != 0)
        {
            result = backtracking(g, numeroCase + 1);
        }
        else
        {

            for (int val = 1; val <= TAILLE; val++)
            {
                if (possible(g, ligne, col, val) == true)
                {
                    g[ligne][col].valeur = val;

                    if (backtracking(g, numeroCase + 1))
                    {
                        result = true;
                    }
                    else
                    {
                        g[ligne][col].valeur = 0;
                    }
                }
            }
        }
    }

    return result;
}

// Fonction du main
int main()
{

    bool progression;
    tGrille grille1;

    // Chargement initial de la grille
    chargerGrille(grille1);

    // Initialisation de la grille
    initialiserCandidats(grille1);

    clock_t begin = clock();
    progression = true;
    printf("Grille initiale :\n");
    // affichage de la grille
    afficherGrille(grille1);
    while (NBCasevide(grille1) != 0 && progression == true)
    {
        progression = false;
        singletonNu(grille1, &progression);
        singletonCaches(grille1, &progression);
    }

    backtracking(grille1, 0);

    clock_t end = clock();
    double tempsCPU = (end - begin) * 1.0 / CLOCKS_PER_SEC;

    printf("Grille finale :\n");
    afficherGrille(grille1);

    printf("Temps CPU = %.3f secondes\n", tempsCPU);
    return EXIT_SUCCESS;
}
