Bonjour, ce document contient les instructions afin de d�ployer cette application Web sur votre serveur tomcat.

1) Si votre projet ne contient pas de fichier WAR :
 a) Aller sous la racine de votre projet
 b) Ouvrir une fen�tre de commande
 c) Lancer la commande mvn package
 d) Le WAR se trouve sous la racine du r�pertoire 'target' de votre projet

2) Afin que TOMCAT puisse utiliser votre application Web
 a) Aller sous le r�pertoire TOMCAT qui utilise vos applications
 b) D�poser le WAR sous r�pertoire webapp
 c) Le serveur d�ploiera pour vous l'application

3) Environnement de travail
 a) Pr�parer les variables d'environnement de votre poste de cette facon
  a.1) CATALINA_BASE : R�pertoire racine de tomcat (souvent sous "C:/apache/tomcat")
  a.2) CATALINE_HOME : R�pertoire de vos applications web (Par exemple : "C:/users/MON_NOM_D'USAGER/tomcat")

4) Conseil ! : Il est conseiller de renommer le fichier WAR si vous jugez son nom trop long

5) Lancer un navigateur
 a) Allez sous : http://localhost:8000/VOTRE_NOM_DE_FICHIER.war (sans le point war)
 
