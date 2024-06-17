/**
 * Ce programme nous demande des caractères et les stocks dans un tableaux. Qu'il comparent avec d'autres
 * caractères dans un autre tableau. Il renvoie aussi le nombre de caractères dans le tableau. Il permet aussi
 * de vérifier la présence d'un caractère dans le tableau de base. Il peut aussi comparer 2 tableau pour vérifier
 * si les 2 tableaux sont identiques ou non.
*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>




const char ETOILE = '*';   /** permet d'écrire '*', qui permet de mettre fin a la saisie. */



#define TAILLE_MAX 15   /** correspond a la taille maximale des tableaux dans le programme. */



typedef char tMot[TAILLE_MAX + 1];  /** type tableau de taille TAILLE_MAX+1, Tout les tableaux du programme partent de ce type, et sont des caractères. */


/**
 * \detail deux paramètres char sont pris en compte,
 * le fonction utilise un scanf dans une boucle qui
 * s'arrête uniquement si on rentre une étoile au lieu 
 * d'un caractère
 * sinon la boucle peut s'arrêter si le mot est trop grand
 * notez qu'on ne peut pas revenir en arrière, une faute
 * de frappe et c'est tant pis, plus qu'a relancer.
 * \param m c'est le tableau utilisé
 */

void  lireMot(tMot m)
{
    char car; 
    char entree;
    int i; 
    i = 0; // i est encore notre compteur pour connaitre la case
    while ((car != ETOILE) && (i < TAILLE_MAX)) // tant que le caractère dans la case n'est pas égale à '*' le programme va continuer à s'executer 
    {
        printf("rentrez un caractère : ");
        scanf("%c%c", &car, &entree);  // 2 %c pour "absober" la touche entrée
        m[i] = car;
        i=i+1; // on rajoute 1 à i pour pourvoir avancer dans les cases et sortir de la boucle
    }
}

/**
 * \brief Elle permet de d'afficher les caractères du tableau
 * \detail Elle affiche les mots représenter par les tableaux, puisqu'elle affiche a la suite sans utiliser de backslash n (sauf a la fin).
 * \param tMot m c'est le tableau qu'on affiche.
 */

void affiche(tMot m)
{
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
 * \brief Elle permet de renvoyer la longueur du tableau
 * \param m c'est le tableau utilisé
 * \return renvoie la longueur du tableau 
 * \detail Elle affiche le nombre de caractères présent dans le tableau, 15 étant son max,
 * la fonction arrête de lire quand elle croise une étoile , puisqu'aucun caractère ne figure après.
 *  
 */

int longueur(tMot m)
{
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
 * \brief Teste la fonction longueur avec plusieurs tableaux. Elle sert uniquement a tester.
 */

void testelongueur() // va nous permettre de tester la fonction longueur
{ 
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
 * \brief Permet de vérifier la présence d'un caractère dans le tableau.
 * \detail possède 3 variable (dont 2 de boucles)
 * la boucle s'arrête quand l'un des variants de boucle
 * atteint la taille maximum, ou qu'on trouve le mot.
 * Auquel cas, la dernière variable res deviendra true. 
 * \param m C'est le tableau utilisé.
 * \param c C'est le caractère que l'on recherche dans le tableau.
 * \return true si le caractère est présent, sinon false.
 */

bool estDans(tMot m, char c)
{
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
 * \brief Compare deux tableaux afin de savoir s'ils sont identiques.
 * \detail possède 2 variants de boucle, fonctionne un peu
 * comme estDans, mais prends 2 paramètres, n'utilise
 * pas estDans et compare 2 tableaux.
 * res reverra true si les 2 tableaux sont identiques,
 * Sinon, renvoie false.
 * \param m1 Premier tableau.
 * \param m2 Deuxième tableau.
 * \return true si les tableaux sont identiques, sinon false.
 */

int compare(tMot m1, tMot m2)
{
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
 * \brief Fonction principale du programme.
 * \detail le programme vérifie en sois toutes les fonctions.
 * Des tableaux sont fournis avec des mots pour vérifier
 * La fonction compare, mais on remplis aussi un tableau
 * grâce a la fonction LireMot, qui s'affiche avec 
 * affiche, et nous renvoie la longueur du mot, et la 
 * présence d'un caractère ou non dans le tableau
 * 
 */

int main()
{
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
