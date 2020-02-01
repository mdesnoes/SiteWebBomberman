<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<div class="row">
		<div class="col-md-4">
			<div class="alert alert-dark">
				<i>Nom d'utilisateur :</i> <c:out value="${ sessionScope.sessionUtilisateur.pseudo }"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="alert alert-dark">
				<i>Nom :</i> <c:out value="${ sessionScope.sessionUtilisateur.nom }"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="alert alert-dark">
				<i>Prénom :</i> <c:out value="${ sessionScope.sessionUtilisateur.prenom }"/>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="alert alert-dark">
				<i>Adresse e-mail &nbsp;<i class="fas fa-at"></i> :</i> <c:out value="${ sessionScope.sessionUtilisateur.email }"/>
			</div>
		</div>
		<div class="col-md-6">
			<div class="alert alert-dark">
				<i>Date de naissance &nbsp;<i class="fas fa-calendar-alt"></i> :</i> <c:out value="${ sessionScope.sessionUtilisateur.dateNaissance }"/>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="alert alert-dark">
					<i>Adresse <i class="fas fa-map-marker-alt"></i> :</i> <c:out value="${ sessionScope.sessionUtilisateur.adresse }"/>&nbsp;<c:out value="${ sessionScope.sessionUtilisateur.codePostal }"/>&nbsp;<c:out value="${ sessionScope.sessionUtilisateur.ville }"/>
			</div>
		</div>
	</div>

</body>
</html>