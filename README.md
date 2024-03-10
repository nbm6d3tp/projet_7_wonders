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
  
# II. Répartie des tâches pour la livraison 3

Pour la livraison 3, nous avons défini les tâches suivantes pour chaque membre de l'équipe :

- Minh :
  - Authentification
  - Écran GameScreen
  - Contrôle des branches
  - Correction du projet

- Djamila :
  - Codage de l'interface de tous les écrans du jeu
  - Diagramme UML

- Amina :
  - Traitement des données pour le jeu (données sur les cartes et les merveilles)
  - Ajout de journaux (Loggers) dans l'application

- Christian :
  - Codage de la classe PurpleCard
  - Achèvement de la logique du jeu

- Anna :
  - Rédaction du rapport
  - Réalisation des tests
  - Préparation de la présentation

# III. Résultats

Une fois que l'utilisateur a démarré le jeu, l'écran d'authentification apparaîtra, nécessitant l'authentification de deux joueurs pour pouvoir jouer au jeu. Le formulaire renverra une erreur si l'utilisateur laisse des champs vides ou si les informations de connexion des deux utilisateurs sont erronées.
![image](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/79254818/09a11a8e-1eb0-495a-ba1c-2fe321df81cf)

Si l'utilisateur n'a pas de compte, il peut s'inscrire en cliquant sur le bouton "Inscription". Cela affichera l'écran d'inscription, où l'utilisateur pourra créer un compte. Le formulaire générera une erreur si des champs sont laissés vides, si l'e-mail n'est pas au format "a@b.c" ou si les deux champs de mot de passe ne correspondent pas.
![image](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/79254818/daf10f71-f936-450a-bc15-b7e0c9679bf8)


Une fois que 2 utilisateurs se sont authentifiés avec succès, l'écran d'accueil apparaît. Cet écran comporte 5 boutons :
![image](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/79254818/8877c7d7-7dae-4744-996d-f73828c6a19a)

- "Jouer" pour commencer une partie
![image](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/79254818/2cdf87ad-045b-4ed0-ac7c-596a0a8ac8b4)

  - Quand on tape sur une carte, les informations avec les options d'actions disponible pour cette cartes seront afficher dans un dialog dans le centre de l'écran
![image](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/79254818/6799dfd8-b02f-45a1-bd4c-6b7426a82be6)

  - Quand une carte est jouée, elle est ajoutée dans la liste des cartes jouées
![image](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/79254818/d2fb18b9-a4c7-44f2-ac97-a4a04efd5a89)

  - Tous les évènements comme âge suivant, guerre commence ou jeu termine va afficher dans le dialog dans le centre de l'écran
![image](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/79254818/041f2228-98e1-4052-881d-6e465c74adbd)

- "Profil joueur 1" pour afficher l'historique de jeu du premier utilisateur
- "Profil joueur 2" pour afficher l'historique de jeu du deuxième utilisateur
![image](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/79254818/712fc143-b0d3-4b9f-923b-b5b33134560a)

- "Tutoriel" pour afficher les règles du jeu
![image](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/79254818/704d5c80-bac7-49cd-9a94-f379375327d1)

- "Déconnecter" pour se déconnecter les 2 utilisateurs du jeu.

- Particulièrement, on a pu intégré avec succès la partie back-end dans notre jeu. C'est une fontionalité qui rend notre jeu plus riche et unique. On a utilisé Firebase qui fournit des services gratuites comme héberger BDD (dans leur Real-time Database) et aussi l'authentification. Pour connecter et communiquer avec Firebase, on a utilisé les requêtes HTTPS (en utilisant la librairie java.net)
  - On essaie de créer un compte "java@test.com"
![image](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/79254818/c4bbb53d-5b59-416b-81c2-2ca2437ae834)
![image](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/79254818/c65114cb-6035-452c-aa85-e0e147487cce)

=> Le compte est bien enregitré dans la BDD
![image](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/79254818/2d526bec-2548-4551-9141-73ceb25eedf9)

  - Quand on termine de jouer une partie, le résultat de la partie est bien enregistré dans la BDD, dans l'information des 2 utilisateurs
![image](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/79254818/279a2571-01a9-4fc2-b42a-631f5895642d)
![image](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/79254818/e5bd277a-9c07-4f48-8086-4473d9a3265a)
![image](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/79254818/d724d623-6e35-478d-a396-af72c43888f3)

  - Vous pouvez aller voir notre BDD via ce lien vers Firebase: https://console.firebase.google.com/u/0/project/wonders-e2863/overview. On vous a envoyé un lien d'invitation pour pouvoir accéder dans le BDD via votre mail de Dauphine: olivier.cailloux@lamsade.dauphine.fr.
  - Vous pouvez tester le jeu avec 2 comptes là:
    - java@test.com - 12345678
    - dauphine@test.com - 12345678

# IV. UML pour livraison 3
[UMLmanuel.pdf](https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/files/11860853/UMLmanuel.pdf)

# V. Annexe

## **Méthodologie de travail :**

Pour garantir la qualité de notre code, respecter les délais et assurer une bonne communication entre les membres de notre équipe, nous avons suivi une méthodologie de travail efficace. Voici les pratiques que nous avons mises en place pour le développement de notre application :

- Récupération des dernières mises à jour : Avant de commencer à coder, nous avons effectué une mise à jour du projet en récupérant les dernières modifications à partir du référentiel. Cela nous a permis de travailler sur la version la plus récente de l'application et d'éviter les conflits de version.

- Utilisation de branches de travail : Pour éviter de modifier directement la branche principale, nous avons créé des branches de travail à partir de la branche "release-1" et nous avons travaillé sur ces branches. Cela nous a permis de travailler en toute sécurité sur des versions distinctes de l'application et d'éviter les conflits avec les autres membres de l'équipe.

- Tests unitaires : Avant de soumettre une classe pour intégration dans le projet, nous l'avons testée de manière approfondie. Nous nous sommes assurés que nos tests couvraient tous les cas d'utilisation possibles de la classe.

En suivant ces pratiques, nous avons pu garantir la qualité de notre code, éviter les conflits entre les membres de l'équipe et respecter les délais de livraison.

Nous avons également documenté chaque classe et chaque méthode que nous avons développée afin de faciliter la compréhension du code par les autres membres de l'équipe. De plus, nous avons régulièrement sauvegardé notre code et l'avons partagé avec les autres membres de l'équipe pour éviter toute perte de données.

Pour la gestion de projet, nous avons utilisé Trello. Cet outil nous a permis de collaborer en temps réel, de répartir efficacement les tâches et de suivre facilement l'état de chaque tâche, ce qui a favorisé une communication fluide au sein de l'équipe.

<img width="452" alt="image" src="https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/123176276/3f5bc2f1-d540-4994-a43f-1cff3f8dc04b">

## **Lien vers le rapport de livraison 1 :**
**https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/blob/release-1/RAPPORT%20java%20Final.pdf**

## **Lien vers le rapport de livraison 2 :**
**https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/blob/release-2/README.md**

## **Liste des cartes et des merveilles dans notre jeu**

<img width="369" alt="image" src="https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/123176276/d8958fc7-1acc-454f-abdf-99c2ae87d877">

<img width="397" alt="image" src="https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/123176276/f99a6377-f920-42ba-b8bc-e9ff71c82419">

<img width="452" alt="image" src="https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/123176276/bb711d20-793b-4993-88b4-5dc506ea3a4e">

<img width="452" alt="image" src="https://github.com/oliviercailloux-org/projet-dream-team-7-wonders/assets/123176276/392bd41c-b8a1-40f5-a5eb-1e6c2eed24cf">

## **Règles du jeu de la version DUEL**
**[https://cdn.1j1ju.com/medias/c8/d6/88-7-wonders-rule.pdf - Page 7](https://cdn.1j1ju.com/medias/c8/d6/88-7-wonders-rule.pdf)**




