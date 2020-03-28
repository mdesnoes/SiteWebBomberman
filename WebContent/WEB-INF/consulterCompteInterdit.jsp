<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Mon compte</title>
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link rel="stylesheet" href="css/myStyle.css">
	<link rel="stylesheet" href="icons/all.css">
	
</head>
<body style="background-image: url(img/plateau_bomberman.png); background-size: contain;">

	<c:import url="/WEB-INF/inc/retourAccueil.jsp" />

	<div class="container bandeauConsultationCompteInterdit">
		<p><b>Vous devez vous <a href="accueil">connecter</a> pour accéder à votre compte</b></p>
	</div>
	
	<c:import url="/WEB-INF/inc/piedDePage.jsp" />
	
</body>
</html>