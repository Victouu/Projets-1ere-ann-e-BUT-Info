/**
 * \BRIEF
 * \AUTHOR = Mateo Morvan
 * \CLASSE 1D1
 * \DATE = 23 11 2023
 * Ce programme nous demande des caractères et les stocks dans un tableaux. Qu'il comparent avec d'autres
 caractères dans un autre tableau. Il renvoie aussi le nombre de caractères dans le tableau. Il permet aussi
 de vérifier la présence d'un caractère dans le tableau de base. Il peut aussi comparer 2 tableau pour vérifier si les 2 tableaux sont identiques ou non.
 * 
**/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>


/**
 * \def ETOILE
 * \brief permet d'écrire '*', qui permet de mettre fin a la saisie.
*/

const char ETOILE = '*';    

/**
 * \def TAILLE_MAX
 * \brief correspond a la taille maximale des tableaux dans le programme.
*/

#define TAILLE_MAX 15

/**
 * \typedef tMot
 * \brief type tableau de taille TAILLE_MAX+1, Tout les tableaux du programme partent de ce type, et sont
 des caractères.
*/

typedef char tMot[TAILLE_MAX + 1];  // un octet en plus pour '*'


/**
 * \fn void init(tMot m)
 * \brief Initialise le tableau avec des étoiles.
 * \param m c'est le tableau utilisé.
 */


void init(tMot m){
    int i;  // i va être notre compteur pour connaitre la case
    for (i = 0; i < (TAILLE_MAX + 1); i++) // toujours un octet en plus pour '*'
    {
        m[i] = ETOILE; // fait que la case soit égal à ETOILE
    }
}

/**
 * \fn void lireMot(tMot m)
 * \brief Elle permet de lire les mots entrées
 * \param m c'est le tableau utilisé
 * C'est la fonction qui permet de comparer les caractères entre eux, et les tableaux de caractères.
 *  
 */

void  lireMot(tMot m){
    char car;
    char entree;
    int i; 
    i = 0;// i est encore notre compteur pour connaitre la case
    while ((car != ETOILE) && (i < TAILLE_MAX)) // tant que le caractère dans la case n'est pas égale à '*' le programme va continuer à s'executer 
    {
        printf("rentrez un caractère : ");
        scanf("%c%c", &car, &entree);  // 2 %c pour "absober" la touche entrée
        m[i] = car;
        i=i+1; // on rajoute 1 à i pour pourvoir avancer dans les cases et sortir de la boucle
    }
}

/**
 * \fn void affiche(tMot m)
 * \brief Elle permet de d'afficher les caractères du tableau
 * \param m c'est le tableau utilisé
 * Elle affiche les mots représenter par les tableaux. 
 *  
 */

void affiche(tMot m){
    int i;
    i=0; // i va nous servir de compteur
    while (m[i] != ETOILE) // de même que précedemment, que le caractère dans la case n'est pas égale à '*' le programme va continuer à s'executer 
    {
        printf("%c", m[i]); // on écrit le caractère de la case
        i=i+1; // on rajoute 1 à i pour pourvoir avancer dans les cases et sortir de la boucle
    }
    printf("\n");
}

/**
 * \fn void longueur(tMot m)
 * \brief Elle permet de renvoyer la longueur du tableau
 * \param m c'est le tableau utilisé
 * \return renvoie la longueur du tableau 
 * Elle affiche le nombre de caractères présent dans le tableau, 15 étant son max,
 la fonction arrête de lire quand elle croise une étoile ( \see TAILLE_MAX), puisqu'aucun
 caractère ne figure après.
 *  
 */

int longueur(tMot m){
    int taille, i; // i va nous servir de compteur et taille va nous indiquer la taille du mot
    i = 0;
    taille = 0;
    while (m[i] != ETOILE)
    {
        taille = taille + 1; // on  ajoute 1 à la taille du mot
        i = i + 1;
    }
    return taille; // on renvoie la taille du mot
}

/**
 * \fn void testelongueur()
 * \brief Teste la fonction longueur avec plusieurs tableaux. Elle sert uniquement a tester.
 */

void testelongueur(){ // va nous permettre de tester la fonction longueur
    int taille;
    tMot m1 = {'*'};
    tMot m2 = {'C', 'H', 'I', 'E', 'N', '*'};
    tMot m3 = {'B', 'O', 'A', '*'};
    tMot m4 = {'B', 'O', 'A', ' ', 'C', 'O', 'N', 'S', 'T', 'R', 'T', 'C', 'T', 'O', 'R', '*'}; // on crée différent tableau avec déjà des caractères à l'intérieur
    taille = longueur(m1);
    printf("%d \n", taille);
    taille = longueur(m2);
    printf("%d\n", taille);
    taille = longueur(m3);
    printf("%d\n", taille);
    taille = longueur(m4);
    printf("%d\n", taille); // on test notre fonction longueur et on écrit son résultat
}

/**
 * \fn bool estDans(tMot m, char c)
 * \brief Permet de vérifier la présence d'un caractère dans le tableau.
 * \param m C'est le tableau utilisé
 *.
 * \param c C'est le caractère que l'on recherche dans le tableau.
 * \return true si le caractère est présent, sinon false.
 */

bool estDans(tMot m, char c){
    bool res; // va enregistrer true ou false et le renvoyer 
    int stop; // va nous permettre de s'arrêter quand on aura res = true / m[i] == c
    int i; // i est notre compteur
    stop = 1;
    for (i = 0; i < TAILLE_MAX; i++)
    {
        if(stop != 0) // arrête tout si stop = 0
        {
            if(m[i] != c)
            {
                res = false;
            }
            if(m[i] == c)
            {
                res = true;
                stop = 0;
            }
        }
    }
    return res;
}

/**
 * \fn int compare(tMot m1, tMot m2)
 * \brief Compare deux tableaux afin de savoir s'ils sont identiques.
 * \param m1 Premier tableau.
 * \param m2 Deuxième tableau.
 * \return true si les tableaux sont identiques, sinon false.
 */

int compare(tMot m1, tMot m2){
    bool res;
    int i; // i est notre compteur
    int stop;
    stop = 1;
    for (i = 0; i < TAILLE_MAX; i++)
    {
        if(stop != 0) // arrête tout si stop = 0
        {
            if(m1[i] == m2[i])
            {
                res = true;
            }else if(m1[i] != m2[i])
            {
                res = false;
                stop = 0;
            }
        }
    }
    return res;
}

/**
 * \fn int main()
 * \brief Fonction principale du programme.
 */

int main(){
    char car = 'F'; // caractère 'F' pour la fonction estDans afin de la tester
    bool res; 
    tMot m; // on définie un tableau m dans le main
    tMot m1 = {'C', 'H', 'I', 'E', 'N', '*'};
    tMot m2 = {'C', 'H', 'I', 'E', 'N', '*'};
    init(m);
    lireMot(m);
    affiche(m);
    testelongueur(); // fait un test pour la longueur
    res = estDans(m, car);
    if (res == true) // va nous permettre d'afficher true ou false en fonction de la fonction estDAns
    {
        printf("true\n");
    }else{
        printf("false\n");
    }
    res = compare(m1, m2);
    if (res == true) // va nous permettre d'afficher true ou false en fonction de la fonction estDAns
    {
        printf("true\n");
    }else{
        printf("false\n");
    }
    return 0;
}
