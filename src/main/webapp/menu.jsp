<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Menu</title>
</head>
<body>
	<h1>Menu</h1>
	<a href="<s:url action="listeDossiers"/>">Liste des dossiers</a><br>
	<a href="<s:url action="listeInfractions"/>">Liste des infractions</a><br>
	<a href="<s:url action="deconnexion"/>">Quitter la session</a><br>
</body>
</html>