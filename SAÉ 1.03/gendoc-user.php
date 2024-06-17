<?php
$titre = '#';
$titre2 = '##';
$tiret = '-';
$ex = '!';
$cro = "[";
$lignevide = ' ';


?>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Comment avoir un joli terminal Linux ?</title>
</head>
<style>
    body {
        font-family: BlinkMacSystemFont, "Segoe UI", Roboto, "Noto Sans", Ubuntu,
            Cantarell, "Helvetica Neue", sans-serif, "Apple Color Emoji",
            "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
    }

    code {
        background-color: rgb(240, 240, 237);
    }

    a {
        color: rgb(52, 140, 255);
    }
</style>
<?php
$fichier = fopen('DOC_UTILISATEUR.md', 'rb');
$contenu = fread($fichier, filesize('DOC_UTILISATEUR.md'));
fclose($fichier);
?>

<body>
    <header>
        <h1> Comment avoir un joli terminal Linux ? </h1>
    </header>
    <main>

        <?php
        $lignes = explode("\n", $contenu);
        $mots = explode(" ", $lignes[0]);

        foreach ($lignes as $ligne) {

            if (((substr($ligne, 0, strlen($titre2)) === $titre2)) || (substr($ligne, 0, strlen($tiret)) === $tiret) || (substr($ligne, 0, strlen($ex)) === $ex) || (substr($ligne, 0, strlen($cro)) === $cro)) {

                if ((substr($ligne, 0, strlen($titre2)) === $titre2)) {
                    preg_match("/\[(.*?)\]/", $ligne, $matches_cro);
                    $linkText = isset($matches_cro[1]) ? trim($matches_cro[1]) : ''; //starship

                    if (!empty($linkText)) {
                        $beforeLink = trim(substr($ligne, strlen($titre2), strpos($ligne, '[') - 2)); // Etape 2 : Installer
                        $link = trim(substr($ligne, strpos($ligne, ']') + 1)); // (https://starship.rs/)

                        echo "<h2>" . $beforeLink . "  " . " <a href='" . $link . "'>" . $linkText . " </a></h2>\n\n";
                    }
                    else{
                        echo '<h2>' . trim(substr($ligne, strlen($titre2))) . "</h2>\n\n";
                    }
                }
                if (substr($ligne, 0, strlen($tiret)) === $tiret) {
                    echo "<blockquote>\n <pre><li><code>" . trim(substr($ligne, 3, -1)) . "</pre></li></code>\n</blockquote>\n\n";
                }

                if (substr($ligne, 0, strlen($ex)) === $ex) {
                    echo "<img\n src='" . trim(substr($ligne, 26, -1)) . "'\n alt ='" . trim(substr($ligne, 2, 22)) . "'\n title ='" . trim(substr($ligne, 2, 22)) . "'\n/>\n\n";
                }
                if (substr($ligne, 0, strlen($cro)) === $cro) {
                    echo "<p>\n <a href='" . trim(substr($ligne, 32, -1)) . "'\n >" . trim(substr($ligne, 1, 29)) . "</a>\n</p>\n";
                }

            } else if ((substr($ligne, 0, strlen($titre)) != $titre)) {
                $ligne = preg_replace_callback("/\`(.*?)\`/", function($matches1) {
                    return "<code>" . $matches1[1] . "</code>";
                }, $ligne);
                preg_match("/\[(.*?)\]/", $ligne, $matches_cro);
                $linkText = isset($matches_cro[1]) ? trim($matches_cro[1]) : '';
                

                if (!empty($linkText)) {
                    $beforeLink = trim(substr($ligne, 0, strpos($ligne, '[')));
                    $afterLink = trim(substr($ligne,-1,1)) ; 
                    $link = trim(substr($ligne, strpos($ligne, ']')));
                        
                    echo "<p>" . $beforeLink . " <a href='" . $link . "'>" . $linkText . $afterLink ." </a></p>\n\n";
                    }
                else{
                    echo "<p>\n " . trim($ligne) . "\n</p>\n";
                    }
            }

        }
        ?>
    </main>

</body>

</html>