import time

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

    print("+" + "-" * (4 * n - 1) + "+")
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


def resoudre(taille_echiquier):
    solutions = []  
    combinaisons = brut_force(taille_echiquier)  
    for element in combinaisons:
        if est_valide(element):  
            solutions.append(element)  
    nb_total_solutions = len(solutions)  
    return nb_total_solutions, solutions

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
            affichage(solution)
main()
