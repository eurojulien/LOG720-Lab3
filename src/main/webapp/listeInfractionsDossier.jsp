<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des infractions de <s:property
		value="dossier.getNom()" />, <s:property
		value="dossier.getPrenom()" /></title>
</head>
<body>

	<s:if test="%{message != null}">
		&#8226; <s:property value="message"/>
	</s:if>

	<h1>
		Liste des infractions de
		<s:property value="dossier.getNom()" />,
		<s:property value="dossier.getPrenom()" />,
		<s:property value="dossier.getNumeropermis()" />,
		<s:property value="dossier.getNumeroplaque()" />:
	</h1>

	<table border="solid 1px">

		<tr>
			<td>Description</td>
			<td>Gravité</td>
		</tr>
		<tr>
		</tr>

		<s:iterator value="infractionsDossier" var="infraction">
			<tr>
				<td><s:property value="#infraction.getDescription()" /></td>
				<td><s:property value="#infraction.getNiveaugravite()" /></td>
			</tr>
		</s:iterator>
	</table>
	<br>
	<s:if test="%{role=='police'}">
		Ajouter une infraction
		<form id="ajouterInfraction" action="ajouterInfractionDossier" method="POST">
		<select name="infractionId">
			<s:iterator value="listeInfractions" var="infraction">
				<option value="<s:property value="#infraction.getId()" />"><s:property value="#infraction.getDescription()" /> - <s:property value="#infraction.getNiveaugravite()" /></option>
			</s:iterator>
		</select>
		<input type="hidden" name="dossierId" value=<s:property value="dossier.getId()" />>
		<input type="submit" value="Ajouter" />
		</form>
	</s:if>

	<br>
	<a href="listeDossiers">Retour à la liste des dossiers</a><br>
	<a href="menu">Retour au menu</a>
</body>
</html>