<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/myStyle.css">
	<link rel="stylesheet" href="icons/all.css">
	
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body style="background-image: url(img/plateau_bomberman.png); background-size: contain;">
	<c:import url="inc/retourAccueil.jsp" />
	
	<div class="container-fluid bandeauConsultationCompte">
		<span style="font-size:30px;">
			<i class="fas fa-chevron-right"></i>
			<b>Modifier le profil de <c:out value="${ utilisateur.nom }"/></b>
		</span>
		&nbsp;
		<i>Pour modifier votre profil, merci d'écrire les nouvelles informations puis cliquer sur modifier votre compte</i>
		
	</div>
	
	<div class="container formulaireModificationProfil">
		<form method="post" action="consultationCompte">
			<div class="form-row">
			  	<div class="form-group col-md-6">
			    	<label for="pseudo">Nom d'utilisateur</label>
			      	<input type="text" class="form-control" id="pseudo" placeholder="nouveau nom d'utilisateur">
			    </div>
			    <div class="form-group col-md-6">
			      	<label for="mdp">Mot de passe</label>
			      	<input type="password" class="form-control" id="mdp" placeholder="nouveau mot de passe">
			    </div>
			</div>
			
			<div class="form-row">
			  	<div class="form-group col-md-4">
			    	<label for="nom">Nom</label>
			      	<input type="text" class="form-control" id="nom" placeholder="nouveau nom">
			    </div>
			    <div class="form-group col-md-4">
			      	<label for="prenom">Prénom</label>
			      	<input type="text" class="form-control" id="prenom" placeholder="nouveau prénom">
			    </div>
			    <div class="form-group">
			    	<label for="datenaissance">Date de naissance&nbsp;<i class="fas fa-calendar-alt"></i></label>
    				<input type="date" class="form-control" id="datenaissance"/>
            	</div>
			</div>
			  
			<div class="form-group">
			    <label for="email">Addresse e-mail&nbsp;<i class="fas fa-at"></i></label>
			    <input type="text" class="form-control" id="email" placeholder="nouvelle adresse e-mail - ex : toto@example.com">
			</div>
			
			
			
			<div class="form-row">
			    <div class="form-group col-md-6">
			      	<label for="adresse">Adresse <i class="fas fa-map-marker-alt"></i> (facultative)</label>
			      	<input type="text" class="form-control" id="adresse" placeholder="nouveau numéro et/ou nouveau nom de la rue">
				</div>
				<div class="form-group col-md-4">
					<label for="cp">Code postal</label>
			     	<input type="text" class="form-control" id="cp" placeholder="nouveau code postal"/>
				</div>
				<div class="form-group col-md-2">
					<label for="ville">Ville</label>
			      	<input type="text" class="form-control" id="ville" placeholder="nouvelle ville"/>
			    </div>
			</div>
			
			<div style="text-align:center;">
		  		<button type="submit" class="btn btn-primary">Modifier votre compte</button>
		  	</div>
		</form>
	</div>
	
	<div class="container-fluid bandeauConsultationCompte">
		<span style="font-size:30px;">
			<i class="fas fa-chevron-right"></i>
			<b>Autres actions</b>
		</span>
	</div>
	
	<div class="container bandeauAutresActions">
		<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalSuppressionCompte" style="margin-top:20px;">Supprimer mon compte <i class="fas fa-times"></i></button>
	</div>

	<c:import url="inc/piedDePage.jsp" />
	
	
	<!-- Modal -->
	<div class="modal fade" id="modalSuppressionCompte" tabindex="-1" role="dialog">
		<div class="modal-dialog modal-dialog-centered" role="document">
	    	<div class="modal-content">
	    		<div class="modal-header">
	    			<i class="fas fa-exclamation-triangle"></i>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          				<span aria-hidden="true">&times;</span>
        			</button>
	      		</div>
	      		
      			<div class="modal-body">
	      			<p>Vous êtes sur le point de supprimer votre compte !</p>
	      			<p><b>Voulez-vous continuer ?</b></p>
	      		</div>
		      		
		      	<form method="post" action="accueil">
		      		<div class="modal-footer">
		        		<button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
		        		<button type="submit" class="btn btn-primary">Supprimer mon compte</button>
		      		</div>
	      		</form>
	      		
	    	</div>
	  	</div>
	</div>
	
</body>
</html>