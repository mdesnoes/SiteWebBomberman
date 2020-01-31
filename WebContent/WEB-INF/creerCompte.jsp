<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Création d'un compte</title>
	
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/myStyle.css">
	<link rel="stylesheet" href="icons/all.css">
	
</head>
<body style="background-image: url(img/plateau_bomberman.png); background-size: contain;">
	<c:import url="inc/retourAccueil.jsp" />
	
	<div class="container-fluid bandeauCreationCompte">
		<br />
		<h1>Création de votre compte Bomberman</h1>
		<p><i>Merci de remplir le formulaire suivant pour créer votre compte et jouer au jeu Bomberman</i></p>
	</div>

	<div class="container formulaireCreationCompte">
		<form method="post" action="creationCompte">
			<div class="form-row">
			  	<div class="form-group col-md-6">
			    	<label for="pseudo">Nom d'utilisateur</label>
			      	<input type="text" class="form-control" id="pseudo" placeholder="nom d'utilisateur">
			    </div>
			    <div class="form-group col-md-6">
			      	<label for="mdp">Mot de passe</label>
			      	<input type="password" class="form-control" id="mdp" placeholder="mot de passe">
			    </div>
			</div>
			
			<div class="form-row">
			  	<div class="form-group col-md-6">
			    	<label for="nom">Nom</label>
			      	<input type="text" class="form-control" id="nom" placeholder="nom">
			    </div>
			    <div class="form-group col-md-6">
			      	<label for="prenom">Prénom</label>
			      	<input type="text" class="form-control" id="prenom" placeholder="prénom">
			    </div>
			</div>
			  
			<div class="form-group">
			    <label for="email">Addresse e-mail&nbsp;<i class="fas fa-at"></i></label>
			    <input type="text" class="form-control" id="email" placeholder="ex : toto@example.com">
			</div>
			
			<div class="form-group">
			    <label for="datenaissance">Date de naissance&nbsp;<i class="fas fa-calendar-alt"></i></label>
    			<input type="date" class="form-control" id="datenaissance" />
            </div>
			
			<div class="form-row">
			    <div class="form-group col-md-6">
			      	<label for="adresse">Adresse <i class="fas fa-map-marker-alt"></i> (facultative)</label>
			      	<input type="text" class="form-control" id="adresse" placeholder="numéro et nom de la rue">
				</div>
				<div class="form-group col-md-4">
					<label for="cp">Code postal</label>
			     	<input type="text" class="form-control" id="cp" placeholder="code postal"/>
				</div>
				<div class="form-group col-md-2">
					<label for="ville">Ville</label>
			      	<input type="text" class="form-control" id="ville" placeholder="ville"/>
			    </div>
			</div>
			
			<div style="text-align:center;">
		  		<button type="submit" class="btn btn-primary">Créer mon compte</button>
		  	</div>
		</form>
	</div>
	
	<c:import url="inc/piedDePage.jsp" />
	
</body>
</html>