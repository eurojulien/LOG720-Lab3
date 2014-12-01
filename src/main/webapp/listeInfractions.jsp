<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des infractions</title>
</head>
<body>

	<s:if test="%{message != null}">
		&#8226; <s:property value="message"/>
	</s:if>

	<h1>Liste des infractions:</h1>
	
	<table border="solid 1px">
	<tr>
		<td> 
			Description
		</td> 
		<td> 
			Gravité
		</td> 
	</tr>
	<tr></tr>

		<s:iterator value="infractions" var="infraction">
			<tr>
				<td><s:property value="#infraction.getDescription()" /></td>
				<td><s:property value="#infraction.getGravite()" /></td>
			</tr>
		</s:iterator>
	</table>
	<br>
	<s:if test="%{role=='admin'}">
		Ajouter une infraction:
		<form id="ajouterInfraction" action="ajouterInfraction" method="POST">
		<table>
			<tr>
				<td>Description:</td>
				<td><input type="text" name="description"/><br></td>
			</tr>
			<tr>
				<td>Gravité:</td>
				<td><input type="text" name="gravite"/><br></td>
			</tr>
		</table>
		<input type="submit" value="Ajouter l'infraction" /><br>
		</form>

	</s:if>
	<a href="menu">Retour au menu</a>
</body>
</html>