Eclipse Version: 2019-12 (4.14.0)

***

1. ouvrir eclipse
2. installer les plugins suivants si ce n'est pas fait (help -> Eclipse Marketplace):
3. Buildship Gradle Integration 3.0
4. Eclipse JST Server Adapters (Apache Tomcat,JOnAS,J2EE) Luna
5. Maven (Java EE) Integration for Eclipse WTP (Luna/Mars) 1.2.0
6. File -> Open projects From File System puis select archive
7. Sélectionner l'archive RentManager.zip
8. click droit sur le pom.xml -> maven -> Update project
9. run -> run configuration -> Maven Build click droit -> new configuration
10. dans la section Goals -> clean install tomcat7:run
11. cliquer sur run
12. allez à l'url http://localhost:8080/RentManager
13. Enjoy ! 


## CONTROLLER

dans cette partie 1 classe = 1 servlet donc 1 fonctionnalité (ajout,supression...)
CliController permet de faire la même chose que l'interface graphique mais via la cli

## MODEL 

Les différents modeles utilisés. Certains ont été créés pour rendre plus simple l'affichage des données dans les tableaux (interface graphique).

## DAO 

Opération et requêtes avec/vers la bdd

## PERSISTENCE 

définition de la bdd, création des tables et des données de base.
Exécuter FillDatabase permet de réinitialiser la bdd (on utilise pas la bdd de test par défaut)

## SERVICE

fonctions logique des modèles principaux.

## COMPTE RENDU

Toutes les fonctionnalités demandés sont implémentés à savoir:
*  lister, ajouter, modifier et supprimer les clients, vehicules et réservations
* un client n'ayant pas 18ans ne peut pas être créé
* un client ayant une adresse mail déjà prise ne peut pas être créé
* le nom et le prénom d'un client doivent faire au moins 3 caractères
* une voiture ne peux pas être réservé 2 fois le même jour
* une voiture ne peux pas être réservé plus de 7 jours de suite par le même utilisateur
* une voiture ne peux pas être réservé 30 jours de suite sans pause 
* si un client ou un véhicule est supprimé, alors il faut supprimer les réservations associées
* une voiture doit avoir un modèle et un constructeur, son nombre de place doit être compris entre 2 et 9

Pour afficher des messages d'erreurs (quand l'utilisateur à le même email qu'un autre par exemple) j'utilise des variables de sessions ainsi que du javascript (pour donner des indications à l'utilisateur avant qu'il soumette le formulaire).
Le mieux aurait été de pouvoir également implémenter des requêtes ajax pour contrôler par exemple si un mail n'existe pas, ou bien effectuer des controles lors de la création de formulaires mais sa demande d'installer des modules. Donc j'ai utilisé des messages via des variables de sessions.

Dans certain cas les messages d'erreurs qui transitent via les variables de sessions sont donc "obsolètes" (car le javascript s'en occupe). Mais j'ai jugé bon de les laisser, car le comportement du javascript peux être modifié par un utilisateur (vu que c'est un langage interprété par le navigateur).







