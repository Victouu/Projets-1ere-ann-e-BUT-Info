# Comment avoir un joli terminal linux ?

## Etape 1 : Installer une Nerd font.

Pour ce tuto, il est imperatif de telecharger une [Nerd Fonts](https://www.nerdfonts.com/font-downloads).
(choisissez une police qui vous plait !)

Il faut ensuite mettre cette police en police par défaut dans le terminal.

## Etape 2 : Installer [starship](https://starship.rs/)

Pour installer vous pouvez utilisez cette commande

- `curl -sS https://starship.rs/install.sh | sh`

## Etape 3 : creer le fichier starship.toml

Dans un premier temps il faut créer le dossier `.config` et le fichier `starship.toml`

Exécuter cette commande dans votre terminal :

- `mkdir -p ~/.config && touch ~/.config/starship.toml`

Quand cela est fait, revenez dans votre `home` si ce n'est pas deja le cas.

Il faut en ensuite dire au terminal d'utiliser le fichier `starship.toml` comme affichage de base, il faut donc modifier le fichier `.bashrc`

- `nano .bashrc`

Si cela ne fonctionne pas c'est que `.bashrc` ne se trouve pas dans ce dossier.

Quand vous etes dans `.bashrc` il faut aller a la toute fin du fichier et ajouter cette ligne :

- `eval "$(starship init bash)"`

Maintenant votre terminal devrait avoir un nouvel affichage. (si ce n'est pas le cas relancer un nouveau terminal).

## Etape 4 : configurer le ficher starship.toml

C'est maintenant que <b>starship</b> devient intéressant, car maintenant il est possible de personaliser votre terminal comme bon vous semble, ou vous pouvez utiliser des templates disponible sur internet.

Pour cela on vous allez devoir modifier le fichier `starship.toml` qui ce trouve dans le dossier `.config`.

Voici un exemple de configuration :

![exemple confg starship](https://i.postimg.cc/pds2P1tx/image-1.png)

[lien de cette config (gitlab)](https://gitlabiut.iutlan.univ-rennes1.fr/vroue/starship)

Maintenant à vous de trouver une configuration qui vous plaît !

<i>Merci d'avoir suivi ce tutoriel !<i>

---

ROUE Victor

Equipe FEUR
