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
	<script src="js/myJavaScript.js"></script>
</head>
<body>

	<div class="container-fluid enTete">
		<div class="row">
			<div class="col-md-1">
				<img alt="" src="img/logoUA.png" id="imgLogo">
			</div>
			<div class="col-md-6" style="text-align:right;">
			
				<!-- On verifie que l'utilisateur a un compte -->
				<c:choose>
					<c:when test="${ aUnCompte }">
						<p id="informationsConnexion"></p>
						<!-- On verifie que les sessions sont présente et on remplie le <p> ci dessus -->
						<script>verificationSessionDejaOuverte();</script>
					</c:when>
					
					<c:when test="${ !aUnCompte }">
						<p id="informationsConnexion">Vous n'êtes pas connecté</p>
					</c:when>
				</c:choose>
				
			</div>
			<div class="col-md-5">
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalConnexion">Se connecter&nbsp;<i class="fas fa-user"></i></button>
				  	<a href="creationCompte">
				  		<button type="button" class="btn btn-primary">Créer compte&nbsp;<i class="fas fa-user-plus"></i></button>
				  	</a>
					<a href="consultationCompte">
						<button type="button" class="btn btn-primary" id="btnMonCompte" disabled="disabled" title="Vous devez être connecté !">Mon compte&nbsp;<i class="fas fa-user-cog"></i></button>
					</a>
					<a href="accueil">
						<button type="button" class="btn btn-primary" id="btnDeconnexion" disabled="disabled" title="Vous devez être connecté !" onclick="deconnexion();">Deconnexion&nbsp;<i class="fas fa-user-times"></i></button>
					</a>
					
					<script> activerBouton(); </script>
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
					<span style="font-size:60px"><b>Bienvenue !</b></span>
					<h3>Vous êtes sur le site du jeu Bomberman</h3>
					
					<br/>
					<br/>
					<p>
						<i class="fa fa-arrow-circle-right"></i>
						&nbsp;&nbsp;
						<a href="Bomberman.zip" download>
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
	
	<div class="container-fluid" style="background-image: url(img/plateau_bomberman.png); background-size: contain;">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-5" id="historique">
					<h3 style="text-align:center">Historique <i class="fas fa-history"></i></h3>
					<p>Ici vous pouvez consultez l'historique</p>
					<br />
					<p>Merci de vous connecter pour accéder à l'historique</p>
				</div>
				
				<div class="col-xs-12 col-sm-12 col-md-2" ></div>
			
				<div class="col-xs-12 col-sm-12 col-md-5" id="classement">
					<h3 style="text-align:center">Classement <i class="fas fa-poll"></i></h3>
					<p>Ici vous pouvez consultez le classement des joueurs</p>
					
				</div>
			</div>
		</div>
	</div>
	
	<c:import url="inc/piedDePage.jsp" />
	
	<!-- Modal -->
	<div class="modal fade" id="modalConnexion" tabindex="-1" role="dialog">
		<div class="modal-dialog modal-dialog-centered" role="document">
	    	<div class="modal-content">
	      		<div class="modal-header">
	        		<h5 class="modal-title">Se connecter</h5>
	        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          				<span aria-hidden="true">&times;</span>
	        			</button>
	      		</div>
	      		<form method="post" action="accueil">
		      		<div class="modal-body">
			        		<div class="form-group">
		            			<label for="pseudo" class="col-form-label">Nom d'utilisateur :</label>
		            			<input type="text" class="form-control" id="pseudo" />
		          			</div>
		         			<div class="form-group">
		            			<label for="password" class="col-form-label">Mot de passe :</label>
		            			<input type="password" class="form-control" id="password" />
		          			</div>
		      		</div>
		      		<div class="modal-footer">
		        		<button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
		        		<button type="submit" class="btn btn-primary" onclick="creationCookie();">Connexion</button>
		      		</div>
	      		</form>
	      		
	    	</div>
	  	</div>
	</div>
	
</body>
</html>