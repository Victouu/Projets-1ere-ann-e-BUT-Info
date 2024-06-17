<?php

// Pour exécuter : php gendoc-tech.php fichier1.c fichier2.c ...

if ($argc < 2) {
    die("Usage: php gendoc-tech.php <fichier1.c> <fichier2.c> ...\n");
}

// Récupérer tous les fichiers sources C passés en argument
// $fichiersC contient tous les noms des fichiers
$fichiersC = array_slice($argv, 1);

// Tableau pour stocker le contenu de chaque fichier
$contenusFichiers = [];

// Iniatlisation de la version du programme
$version = 1.0;

// Initialiser le contenu HTML
$contenuHTML = '<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">

<head>
    <meta charset="utf-8" />
    <title>Documentation technique</title>
    <meta name="SAE1.03" content="" />
    <style>
    body{
        background-color: bisque;
        margin: 4;
    }
    
    header{
        background-color: #955e42;
        color: white;
        margin: 2em;
        padding: 2em;
        height: 5em;
        text-align: center;
        font-size: 3em;
        border-radius: 0.5em;
    }
    
    main > section {
        background-color: burlywood;
        margin: 6em;
        padding: 3em;
        border-radius: 0.5em;
        font-family:Arial, Helvetica, sans-serif;
    }
    
    main > section > .titrefichier {
        background-color: #955e42;
        height: 4em;
        margin: 2em;
        margin-bottom: 4em;
        border-radius: 1em;
        text-align: center;
    }
    
    main > section > article , section > section > article{
        background-color: wheat;
        height: auto;
        border-radius: 2em;
    }
    
    main > section > article > p , section > section > article > p{
       margin: 1em;
    }
    
    main > section > article > h2{
        background-color: #955e42;
        color: white;
        padding: 0.5em;
        padding-left: 1em;
        border-top-right-radius: 1em;
        border-top-left-radius: 1em;
        font-family: Arial, Helvetica, sans-serif;
    }
    
    section > section .titrefichier{
        background-color: #955e42;
        color: white;
    }
    
    h3 {
        margin: 1em;
    }
    
    nav{
        position: fixed;
        top: 15%;
        border: 1px;
        border-style: solid;
        border-color: #955e42;
        border-radius: 2em;
        text-align: center;
        background-color: burlywood;
        height: auto;
        width: 8.5em;
    }
    
    nav > p {
        color: black;
    }
    
    footer{
        margin: 6em;
        padding: 1em;
        background-color: burlywood;
        text-align: center;
        border-radius: 0.5em;
    }
     
    footer > h2{
        background-color: #955e42;
        color: white;
        padding: 0.5em;
        padding-left: 1em;
        border-radius: 0.5em;
        font-family: Arial, Helvetica, sans-serif;
    }
</style>
</head>

<body>
    <header id="titre"> 
        <h1>DOC TECHNIQUE</h1>
    </header>
    <main>';

foreach ($fichiersC as $fichierC) {
    // Si le fichier n'existe pas, on affiche une erreur
    if (!file_exists($fichierC)) {
        die("Le fichier $fichierC n'existe pas.\n");
    }

    // Lire le contenu du fichier C
    $contenuC = file_get_contents($fichierC);

    // Stocker le contenu du fichier dans le tableau
    $contenusFichiers[$fichierC] = $contenuC;

    // Extraire les commentaires
    preg_match_all('/\/\*\*(.*?)\*\//s', $contenuC, $commentairesMatches);

    // Combinaison des commentaires avec la section correspondante
    $commentaires = $commentairesMatches[1];
    $section = $fichierC;

    // Créer un tableau associatif pour stocker les commentaires par section
    $commentairesParSection[$section][] = $commentaires;
}

// Fonction pour extraire la première ligne non vide qui suit un commentaire si il est celui d'une fonction.
function extraireLigneFonction($contenuC, $commentaire)
{
    // Trouver la position de début du commentaire dans le contenu C
    $positionDebutCommentaire = strpos($contenuC, $commentaire);

    if ($positionDebutCommentaire !== false) {
        // Extraire la partie du contenu après le bloc de commentaire
        $contenuApresCommentaire = substr($contenuC, $positionDebutCommentaire + strlen($commentaire));

        // Diviser le contenu en lignes
        $lignes = explode("\n", $contenuApresCommentaire);

        // Chercher la première ligne non vide après le bloc de commentaire
        foreach ($lignes as $ligne) {
            $ligneSuivante = trim($ligne);

            // Conditions pour considérer une ligne comme vide
            $estVide = empty($ligneSuivante) || $ligneSuivante === '*' || $ligneSuivante === '*/';

            if (!$estVide) {
                return $ligneSuivante;
            }
        }
    }

    return null;
}

// Fonction pour extraire les lignes commençant par #define
function extraireDefine($contenuC)
{
    // Diviser le contenu en lignes
    $lignes = explode("\n", $contenuC);

    // Tableau pour stocker les lignes commençant par #define
    $defines = [];

    foreach ($lignes as $ligne) {
        $ligne = trim($ligne);

        // Vérifier si la ligne commence par #define
        if (strpos($ligne, '#define') === 0) {

            // Remplacer "/**" par ":" et enlever "*/"
            $ligne = str_replace('/**', ':', $ligne);
            $ligne = str_replace('*/', '', $ligne);

            // Ajouter la ligne dans le tableau des defines
            $defines[] = trim(substr($ligne, strlen('#define')));
        }
    }

    return $defines;
}

// Insérer les commentaires, les lignes #define et les lignes associées à une fonction dans les sections du fichier HTML
foreach ($commentairesParSection as $section => $commentairesSection) {
    $contenuHTML .= '<section id="' . strtolower($section) . '">';
    $contenuHTML .= '<article class="titrefichier"><h2>' . $section . '</h2></article>';

    // Tableau associatif pour stocker les commentaires par catégorie
    $commentairesParCategorie = [];

    // Flag pour indiquer si le premier bloc de commentaire a été ajouté à la catégorie "description"
    $descriptionAjoutee = false;

    foreach ($commentairesSection as $commentaireSection) {
        foreach ($commentaireSection as $commentaire) {
            // Enlever les astérisques en début de ligne
            $commentaireNettoye = preg_replace('/^\s*\*\s*/m', '', $commentaire);

            // Vérifier si le commentaire est associé à une fonction (commence par "\")
            if (strpos($commentaireNettoye, '\\') === 0) {
                $ligneFonction = extraireLigneFonction($contenusFichiers[$section], $commentaire);
                $commentairesParCategorie['FONCTIONS ET PROCEDURES'][] = '<U>' . $ligneFonction . ':</U> <br>' . $commentaireNettoye;
            } else {
                // Ajouter le premier bloc de commentaire à la catégorie "description" si ce n'est pas encore fait
                if (!$descriptionAjoutee) {
                    $commentairesParCategorie['DESCRIPTION'][] = $commentaireNettoye;
                    $descriptionAjoutee = true;
                } else {
                    $contenuHTML .= '<article><p>' . nl2br(trim($commentaireNettoye)) . '</p></article>';
                }
            }
        }
    }

    // Extraire les lignes commençant par #define
    $defines = extraireDefine($contenusFichiers[$section]);

    // Ajouter les lignes #define à la catégorie "DEFINE"
    $commentairesParCategorie['DEFINE'] = $defines;

    // Ajouter les sections par catégorie (par exemple, "FONCTIONS" et "DESCRIPTION")
    foreach ($commentairesParCategorie as $categorie => $commentairesCategorie) {
        if (!empty($commentairesCategorie)) {
            $contenuHTML .= '<section id="' . strtolower($section . '_' . strtolower($categorie)) . '">';
            $contenuHTML .= '<article class="titrefichier"><h3>' . $categorie . '</h3></article>';
            foreach ($commentairesCategorie as $commentaireCategorie) {
                $contenuHTML .= '<article><p>' . nl2br(trim($commentaireCategorie)) . '</p></article>';
            }
            $contenuHTML .= '</section>';
        }
    }

    $contenuHTML .= '</section>';
}

$contenuHTML .= '</main>
<footer>
    <h2 id="pdp">Pied de page</h2>
    <p>SAE1.03</p>
    <p>Groupe 1D1 / équipe FEUR</p>
    <p>Mateo MORVAN  Victor ROUÉ  Yann CHESNEL  Baptiste LAÎNÉ</p>
</footer>
</body>
</html>';

// Écrire le contenu dans un fichier HTML
echo $contenuHTML;
?>
