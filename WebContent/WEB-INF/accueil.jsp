<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Accueil Site Bomberman</title>

    <!-- CSS -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/myStyle.css">
	<link rel="stylesheet" href="icons/all.css">
	
	<!-- JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container-fluid enTete">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-1">
				<img src="img/logoUA.png" id="imgLogo" alt="" />
			</div>
			<div class="col-xs-12 col-sm-12 col-md-3" style="margin-top:8px;font-size:20px;">
				<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${ form.resultat }</p>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-3" style="text-align:right;">
				<c:choose>
					<c:when test="${!empty sessionScope.sessionUtilisateur }">
						<p>Vous êtes connecté sous le pseudo <u><c:out value="${ sessionScope.sessionUtilisateur.pseudo }" /></u></p>
					</c:when>
					
					<c:when test="${empty sessionScope.sessionUtilisateur }">
						<p>Vous n'êtes pas connecté <br />
							<c:if test="${!empty requestScope.intervalleConnexions}">
	                			<i style="font-size:13px;">Dernière connexion, il y a ${requestScope.intervalleConnexions}</i>
	                		</c:if>
                		</p>
					</c:when>
				</c:choose>
				
			</div>
			<div class="col-xs-12 col-sm-12 col-md-5">
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalConnexion">Se connecter&nbsp;<i class="fas fa-user"></i></button>
				  	<a href="<c:url value="/creationCompte" />">
				  		<button type="button" class="btn btn-primary">Créer compte&nbsp;<i class="fas fa-user-plus"></i></button>
				  	</a>
				  	
				  	<a href="<c:url value="/consultationCompte" />">
				  		<button type="submit" class="btn btn-primary" id="btnMonCompte">Mon compte&nbsp;<i class="fas fa-user-cog"></i></button>
					</a>
					
<!-- 					Bouton de deconnexion -->
					<form method="post" action="<c:url value="/deconnexion" />">
						<button type="submit" class="btn btn-primary" id="btnDeconnexion" disabled="disabled" title="Vous devez être connecté !">Deconnexion&nbsp;<i class="fas fa-user-times"></i></button>
					</form>
					<c:if test="${!empty sessionUtilisateur }">
						<script> $("#btnDeconnexion").removeAttr("disabled"); </script>
					</c:if>
				</div>
			</div>
		</div>
	</div>
		
	<div class="container-fluid bandeauAccueil">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-1"></div>
			<div class="col-xs-12 col-sm-12 col-md-2" id="imageBomberman" style="background-image: url(img/bomberman.png);"></div>
			
			<div class="col-xs-12 col-sm-12 col-md-6">
			
				<div class="div_telechargement">
					<span style="font-size:50px"><b>Bienvenue !</b></span>
					<h3>Vous êtes sur le site du jeu Bomberman</h3>
					
					<br/>
					<p>
						<i class="fa fa-arrow-circle-right"></i>
						&nbsp;&nbsp;
						<a href="<c:url value="/Bomberman.zip" />" download>
							<button type="button" class="btn btn-warning btn-lg">Téléchargement du client <i class="fas fa-download"></i></button>
						</a>
						&nbsp;&nbsp;
						<i class="fa fa-arrow-circle-left"></i>
					</p>
				</div>
			</div>
			
			
			<div class="col-xs-12 col-sm-12 col-md-2" id="imageBomberman" style="background-image: url(img/bomberman.png);"></div>
			<div class="col-xs-12 col-sm-12 col-md-1"></div>
		</div>
	</div>
	
	<div class="container-fluid">
		<div class="row">
			
			<div class="col-xs-12 col-sm-12 col-md-6" id="historique">
				<h3 style="text-align:center">Historique <i class="fas fa-history"></i></h3>
				<div>
					Ici vous pouvez consultez l'historique des parties
					<a href="<c:url value="/accueil?periode=${ periode }&triePar=${ triePar }"/>" style="float:right;">
						<button type="button" class="btn btn-link">Rafraichir&nbsp;<i class="fas fa-sync-alt"></i></button>
					</a>
				</div>
				
				<c:choose>
					<c:when test="${!empty sessionUtilisateur }">
						<c:import url="inc/consulterHistorique.jsp" />
					</c:when>
					
					<c:otherwise>
						<p>Merci de vous connecter pour accéder à l'historique</p>
					</c:otherwise>
				</c:choose>
			</div>
			
			<div class="col-xs-12 col-sm-12 col-md-6" id="classement">
				<h3 style="text-align:center">Classement <i class="fas fa-poll"></i></h3>
				<div>
					Ici vous pouvez consultez le classement des joueurs
					<a href="<c:url value="/accueil?periode=${ periode }&triePar=${ triePar }"/>" style="float:right;">
						<button type="button" class="btn btn-link">Rafraichir&nbsp;<i class="fas fa-sync-alt"></i></button>
					</a>
				</div>
				
				<c:import url="inc/consulterClassement.jsp" />
			</div>
						
		</div>
	</div>
	
	<c:import url="inc/piedDePage.jsp" />
	
	<!-- Modal -->
	<div class="modal fade" id="modalConnexion" tabindex="-1" role="dialog">
		<c:import url="/WEB-INF/inc/modalConnexion.jsp" />
	</div>
	
</body>
</html>