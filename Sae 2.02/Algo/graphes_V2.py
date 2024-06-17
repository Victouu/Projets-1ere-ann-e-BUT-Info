from collections import deque
import time

class Graphe(object):

    def __init__(self, graphe_dict=None):
        if graphe_dict is None:
            graphe_dict = {}
        self._graphe_dict = graphe_dict

    def aretes(self, sommet):
        return list(self._graphe_dict[sommet])

    def all_sommets(self):
        return set(self._graphe_dict.keys())

    def add_sommet(self, sommet):
        if sommet not in self._graphe_dict:
            self._graphe_dict[sommet] = []

    def add_arete(self, sommet1, sommet2):
        self.add_sommet(sommet1)
        self._graphe_dict[sommet1].append(sommet2)


def possible(plateau, ligne, colonne):
    for i in range(ligne):
        if plateau[i] == colonne or abs(plateau[i] - colonne) == abs(i - ligne):
            return False
    return True

def generer_graphe(n):
    graphe = Graphe()
    for i in range(n):
        for j in range(n):
            for k in range(n):
                for l in range(n):
                    if (i == k or j == l or abs(i - k) == abs(j - l)) and (i, j) != (k, l):
                        graphe.add_arete((i, j), (k, l))
    return graphe

def resoudre(n):
    graphe = generer_graphe(n)
    noeud_depart = tuple([-1] * n)
    nombre_solutions = 0
    solutions = []

    file = deque([(noeud_depart, [])])

    while file:
        etat_actuel, solution = file.popleft()
        if -1 not in etat_actuel:
            nombre_solutions += 1
            solutions.append(solution)
            continue

        ligne = etat_actuel.index(-1)
        for colonne in range(n):
            if possible(etat_actuel, ligne, colonne):
                nouvel_etat = list(etat_actuel)
                nouvel_etat[ligne] = colonne
                file.append((tuple(nouvel_etat), solution + [(ligne, colonne)]))

    return nombre_solutions, solutions

def afficher_solution(solution):
    n = len(solution)

    print("+" + "-" * (4 * n - 1) + "+")
    for i in range(n):
        ligne = "| "
        for j in range(n):
            if solution[i][1] == j:
                ligne += "X | "
            else:
                ligne += ". | "
        print(ligne)
        print("+" + "-" * (4 * n - 1) + "+")  
    print()  

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
