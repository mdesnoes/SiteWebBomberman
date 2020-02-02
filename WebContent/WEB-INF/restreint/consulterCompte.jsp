<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Consultation Compte Bomberman</title>

	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/myStyle.css">
	<link rel="stylesheet" href="icons/all.css">
	
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</head>
<body style="background-image: url(img/plateau_bomberman.png); background-size: contain;">
	
	<c:import url="/WEB-INF/inc/retourAccueil.jsp" />

<!-- 	Partie Consultation Profil -->
	<div class="container-fluid bandeauConsultationCompte">
		<span style="font-size:30px;">
			<i class="fas fa-chevron-right"></i>
			<b>Profil de <c:out value="${ sessionScope.sessionUtilisateur.pseudo }" /></b>
			<i class="fas fa-address-card"></i>
		</span>
	</div>
	
	<div class="container couleurGrisClair">
		<c:import url="/WEB-INF/inc/consulterProfil.jsp" />
	</div>

<!-- 	Partie Modification Profil -->
	<div class="container-fluid bandeauConsultationCompte">
		<span style="font-size:30px;">
			<i class="fas fa-chevron-right"></i>
			<b>Modifier le profil</b>
		</span>
		&nbsp;
		<i>Pour modifier votre profil, merci d'écrire les nouvelles informations puis cliquer sur modifier votre compte</i>
	</div>
	
	
	<div class="container couleurGrisClair">
		<c:import url="/WEB-INF/inc/formulaireModificationProfil.jsp" />
		<br />
	</div>
	
	
<!-- 	Partie Autres actions -->
	<div class="container-fluid bandeauConsultationCompte">
		<span style="font-size:30px;">
			<i class="fas fa-chevron-right"></i>
			<b>Autres actions</b>
		</span>
	</div>
	
	<div class="container couleurGrisClair">
		<div class="form-row">
		  	<div class="form-group col-md-3">
				<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#modalModificationMotDePasse" style="margin-top:20px;">Modifier mon mot de passe <i class="fas fa-lock-open"></i></button>
			</div>
			<div class="form-group col-md-3">
					<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalSuppressionCompte" style="margin-top:20px;">Supprimer mon compte <i class="fas fa-times"></i></button>
			</div>
		</div>
	</div>
	

	<c:import url="/WEB-INF/inc/piedDePage.jsp" />
	
	
	<!-- Modal Suppression Compte -->
	<div class="modal fade" id="modalSuppressionCompte" tabindex="-1" role="dialog">
		<c:import url="/WEB-INF/inc/modalSuppressionCompte.jsp" />
	</div>
	
	
	<!-- 	Modal Modif Mot de passe -->
	<div class="modal fade" id="modalModificationMotDePasse" tabindex="-1" role="dialog">
		<c:import url="/WEB-INF/inc/modalModificationMotDePasse.jsp" />
	</div>
	
</body>
</html>