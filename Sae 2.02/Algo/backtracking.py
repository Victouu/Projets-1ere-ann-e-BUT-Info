import time

def possible(plateau, ligne, colonne):
    res = True
    for i in range(ligne):
        if ((plateau[i] == colonne) or (plateau[i] == colonne - (ligne - i)) or (plateau[i] == colonne + (ligne - i))):
            res = False
    return res

def backtracking(plateau, ligne, n, compteur_sol, solutions):
    if ligne == n:
        compteur_sol += 1
        solutions.append(plateau[:])
        return compteur_sol

    for colonne in range(n):
        if possible(plateau, ligne, colonne):
            plateau[ligne] = colonne
            compteur_sol = backtracking(plateau, ligne + 1, n, compteur_sol, solutions)
            plateau[ligne] = -1
    
    return compteur_sol


def resoudre(n):
    compteur_sol = 0
    solutions = []

    plateau = [-1] * n
    compteur_sol = backtracking(plateau, 0, n, compteur_sol, solutions)

    return compteur_sol, solutions

def afficher_solution(solution):
    n = len(solution)

    # Affichage de la ligne horizontale du haut du tableau
    print("+" + "-" * (4 * n - 1) + "+")

    # Affichage du tableau avec les reines
    for i in range(n):
        ligne = "| "
        for j in range(n):
            if solution[i] == j:
                ligne += "X | "
            else:
                ligne += ". | "
        print(ligne)
        print("+" + "-" * (4 * n - 1) + "+")  # Ligne horizontale entre les lignes

    print()  # Ligne vide à la fin de l'affichage


def main():
    taille_echiquier = 0
    while True:
        taille_echiquier = int(input("Veuillez entrer la taille de l'échiquier (8 pour 8 x 8) : "))
        if taille_echiquier > 1 and taille_echiquier % 2 == 0:
            break
        else:
            print("La taille de l'échiquier doit être un nombre pair et supérieur à 1. Veuillez réessayer.")
    temps1 = time.perf_counter()
    nb_total_solutions, solutions = resoudre(taille_echiquier)
    print("Nombre total de solutions pour placer", taille_echiquier, "reines sur un échiquier", taille_echiquier, "x", taille_echiquier, ":", nb_total_solutions)
    temps2 = time.perf_counter()
    restemps = temps2 - temps1
    print("Temps d'execution", restemps , "secondes")

    choix = input("Voulez-vous afficher toutes les solutions ? (O/N) : ")
    if choix == "O":
        for i, solution in enumerate(solutions):
            print("Solution", i+1, ":")
            afficher_solution(solution)

main()
