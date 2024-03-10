# I. Idée de simplification

Nous avons décidé de simplifier notre jeu en apportant quelques modifications et en éliminant certaines fonctionnalités complexes.

- Étant donné notre manque d'expérience dans ce domaine, nous avons décidé de ne pas implémenter la Socket Programmation(qui permet plusieurs joueurs sur différents hosts de jouer le jeu ensemble). À la place, nous développons une version du jeu où les joueurs partageront le même écran. De plus, nous avons pensé qu'il serait plus logique de n'avoir que 2 joueurs sur le même écran, pas plus, alors nous avons décidé de développer la version DUEL du jeu. Cependant, au final, on n’a pas pu développer la fonctionnalité de Free City (la 3ème joueur dans la version Duel. Du coup, on a fait le jeu de version normal avec seulement 2 joueurs.

- Les Merveilles ne fourniront que des points de victoire, sans autres récompenses telles que des pièces ou des effets spéciaux.

- Nous n'utiliserons pas les cartes avec des effets compliqués dans notre jeu. Vous pouvez trouver la liste des cartes que nous utiliserons dans l'annexe.

- Nous modifierons légèrement les coûts des cartes et des étapes pour équilibrer le jeu, étant donné que notre jeu est conçu pour seulement 2 joueurs.

- Nous envisageons d'utiliser une taille de fenêtre fixe pour l'interface du jeu afin d'éviter la complexité de la programmation responsive.

- Les cartes et les merveilles dans l'interface du jeu seront représentées sous forme de texte plutôt que d'images.

- Normalement les points de victoire apportées par des cartes violets sont calculés au fin du jeu, mais ici, dans notre jeu on les calcule dès au moment qu’elles sont jouées.
  
- Normalement les points de victoire  apportées par des cartes vertes sont calculées dès au moment qu’elles sont jouées, mais ici, dans notre jeu on les calcule au fin du jeu.

- Pas de chainement des cartes (par exemple si on a joué une carte => la carte après est e en meme chaine que cette carte sera gratuite)

## **Règles du jeu de la version DUEL**
**[https://cdn.1j1ju.com/medias/c8/d6/88-7-wonders-rule.pdf - Page 7](https://cdn.1j1ju.com/medias/c8/d6/88-7-wonders-rule.pdf)**




