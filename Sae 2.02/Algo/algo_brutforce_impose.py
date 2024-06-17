def brut_force(n, placement=[]):
    if n == len(placement):
        return [placement]
    else:
        combinaisons = []
        for i in range(n):
            if i not in placement:  
                for element in brut_force(n, placement + [i]):
                    combinaisons.append(element)
        return combinaisons

def est_valide(placement):
    n = len(placement)
    for i in range(n):
        for j in range(i + 1, n):
            if placement[i] == placement[j] or abs(placement[i] - placement[j]) == abs(i - j):
                return False
    return True

def affichage(solution):
    n = len(solution)

    print("+" + "-" * (4 * n - 1) + "+")  # Première ligne de tirets
    for i in range(n):
        ligne = "| "
        for j in range(n):
            if solution[i] == j:  
                ligne += "X | "
            else:
                ligne += ". | "  
        print(ligne)
        print("+" + "-" * (4 * n - 1) + "+")  
    print()


def resoudre(n, premiere_reine):
    combinaisons = brut_force(n, [premiere_reine])  
    solutions_valides = [solution for solution in combinaisons if est_valide(solution)]
    return solutions_valides

def main():
    taille_echiquier = 0
    while True:
        taille_echiquier = int(input("Veuillez entrer la taille de l'échiquier (8 pour 8 x 8) : "))
        if taille_echiquier > 1 and taille_echiquier % 2 == 0:
            break
        else:
            print("La taille de l'échiquier doit être un nombre pair et supérieur à 1. Veuillez réessayer.")

    premiere_reine = int(input(f"Veuillez entrer la position de la première reine de 1 à {taille_echiquier} : "))  
    solutions_valides = resoudre(taille_echiquier, premiere_reine)
    
    nb_total_solutions = len(solutions_valides) 
    print("Nombre total de solutions pour placer", taille_echiquier, "reines sur un échiquier", taille_echiquier, "x", taille_echiquier, ":", nb_total_solutions)

    choix = input("Voulez-vous afficher toutes les solutions ? (O/N) : ")
    if choix == "O":
        for i, solution in enumerate(solutions_valides):
            print("Solution", i+1, ":")
            affichage(solution)
main()
