<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des dossiers</title>
</head>
<body>

	<s:if test="%{message != null}">
		&#8226; <s:property value="message"/>
	</s:if>

	<h1>Liste des dossiers:</h1>
	
	<table border="solid 1px">
	<tr>
		<td>Nom, Prénom</td>
		<td>Plaque d'immatriculation</td>
		<td>Numéro de permis de conduire</td>
		<td></td>
	</tr>
	<tr></tr>

		<s:iterator value="dossiers" var="dossier">
			<tr>
				<td><s:property value="#dossier.getNom()" />, <s:property
						value="#dossier.getPrenom()" /></td>
				<td><s:property value="#dossier.getNumeroplaque()" /></td>
				<td><s:property value="#dossier.getNumeropermis()" /></td>
				<td>
					<form action="listeInfractionsDossier" method="GET">
						<input type="hidden" name="dossierId"
							value="<s:property value="#dossier.getId()" />"> <input
							type="submit" value="Afficher les infractions" />
					</form>
				</td>
			</tr>
		</s:iterator>
	</table>
	<br>
	
	<s:if test="%{role=='admin'}">
		Ajouter un dossier:
		<form id="ajouterDossier" action="ajouterDossier" method="POST">
			<table>
				<tr>
					<td>Nom:</td>
					<td><input type="text" name="nom" /><br></td>
				</tr>
				<tr>
					<td>Prenom:</td>
					<td><input type="text" name="prenom" /><br></td>
				</tr>
				<tr>
					<td>Numéro de plaque d'immatriculation:</td>
					<td><input type="text" name="immatriculation" /><br></td>
				</tr>
				<tr>
					<td>Numéro de permis de conduire:</td>
					<td><input type="text" name="permis" /><br></td>
				</tr>
			</table>
			<input type="submit" value="Ajouter le dossier" /><br>
		</form>
	</s:if>
	
	<a href="menu">Retour au menu</a>
</body>
</html>