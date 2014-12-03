Bonjour, ce document contient les instructions afin de déployer cette application Web sur votre serveur tomcat.

- Maven doit être installé
- La base de données PostGreSQL doit être installée (Il est également possible de proposer l'installation de PGAdmin)

1) Si votre projet ne contient pas de fichier WAR :
 a) Aller sous la racine de votre projet
 b) Ouvrir une fenêtre de commande
 c) Lancer la commande mvn package
 d) Le WAR se trouve sous la racine du répertoire 'target' de votre projet

2) Afin que TOMCAT puisse utiliser votre application Web
 a) Aller sous le répertoire TOMCAT qui utilise vos applications
 b) Déposer le WAR sous répertoire webapp
 c) Le serveur déploiera pour vous l'application

3) Environnement de travail
 a) Préparer les variables d'environnement de votre poste de cette facon
  a.1) CATALINA_BASE : Répertoire racine de tomcat (souvent sous "C:/apache/tomcat")
  a.2) CATALINE_HOME : Répertoire de vos applications web (Par exemple : "C:/users/MON_NOM_D'USAGER/tomcat")

4) Conseil ! : Il est conseiller de renommer le fichier WAR si vous jugez son nom trop long

5) Lancer un navigateur
 a) Allez sous : http://localhost:8000/VOTRE_NOM_DE_FICHIER.war (sans le point war)
 
