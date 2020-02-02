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
	
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script type="text/javascript">
		function verifSecuPassword() {
			var mdp = $("#password").val();
			
			var expChiffre = /\d+/; // contient au moins 1 chiffre
			var expMajuscule = /[A-Z]+/; // contient caractere speciaux
			var expCarSpeciaux = /\W/; // contient caractere speciaux
			
			if(expCarSpeciaux.test(mdp)) {
				console.log("spec");
			}
			
			if(expChiffre.test(mdp) && expMajuscule.test(mdp) && expCarSpeciaux.test(mdp)) {
				$("#progressSecuPassword").attr("style","width: 100%;background-color:#25AE5F");
				$("#progressSecuPassword").text("très fort");
			} else if((expChiffre.test(mdp) && expMajuscule.test(mdp) && !expCarSpeciaux.test(mdp))
					|| expChiffre.test(mdp) && !expMajuscule.test(mdp) && expCarSpeciaux.test(mdp)
					|| !expChiffre.test(mdp) && expMajuscule.test(mdp) && expCarSpeciaux.test(mdp)) {
				$("#progressSecuPassword").attr("style","width: 75%;background-color:#E8CB23");
				$("#progressSecuPassword").text("fort");
			}else if(mdp.length > 1 && (expChiffre.test(mdp) || expMajuscule.test(mdp) || expCarSpeciaux.test(mdp))) {
				$("#progressSecuPassword").attr("style","width: 50%;background-color:orange");
				$("#progressSecuPassword").text("moyen");
			} else if(mdp.length > 0){
				$("#progressSecuPassword").attr("style","width: 25%;background-color:red");
				$("#progressSecuPassword").text("faible");
			} else {
				$("#progressSecuPassword").attr("style","width: 0%;background-color:red");
			}
		};
	</script>
</head>
<body style="background-image: url(img/plateau_bomberman.png); background-size: contain;">
	<c:import url="inc/retourAccueil.jsp" />
	
	<div class="container-fluid bandeauCreationCompte">
		<br />
		<h1>Création de votre compte Bomberman</h1>
		<p><i>Merci de remplir le formulaire suivant pour créer votre compte et jouer au jeu Bomberman</i></p>
	</div>

	<div class="container formulaireCreationCompte">
		<br />
		<form method="post" action="creationCompte">
			<div class="form-row">
			  	<div class="form-group col-md-6">
			    	<label for="pseudo">Nom d'utilisateur</label>
			      	<input type="text" class="form-control" id="pseudo" name="pseudo" value="<c:out value="${ utilisateur.pseudo }" />" placeholder="nom d'utilisateur">
			      	<span class="erreur">${form.erreurs['pseudo']}</span>
			    </div>
			    <div class="form-group col-md-6">
			      	<label for="password">Mot de passe</label>
			      	<input type="password" class="form-control" id="password" name="password" value="<c:out value="${ utilisateur.password }" />" oninput="verifSecuPassword();" placeholder="mot de passe">
			    	<span class="erreur">${form.erreurs['password']}</span>
			    	<div class="progress">
  						<div class="progress-bar" role="progressbar" id="progressSecuPassword" style="width: 0%;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
					</div>
			    </div>
			</div>
			
			<div class="form-row">
			  	<div class="form-group col-md-6">
			    	<label for="nom">Nom</label>
			      	<input type="text" class="form-control" id="nom" name="nom" value="<c:out value="${ utilisateur.nom }" />" placeholder="nom">
			      	<span class="erreur">${form.erreurs['nom']}</span>
			    </div>
			    <div class="form-group col-md-6">
			      	<label for="prenom">Prénom</label>
			      	<input type="text" class="form-control" id="prenom" name="prenom" value="<c:out value="${ utilisateur.prenom }" />" placeholder="prénom">
			      	<span class="erreur">${form.erreurs['prenom']}</span>
			    </div>
			</div>
			  
			<div class="form-group">
			    <label for="email">Addresse e-mail&nbsp;<i class="fas fa-at"></i></label>
			    <input type="text" class="form-control" id="email" name="email" value="<c:out value="${ utilisateur.email }" />" placeholder="ex : toto@example.com">
			    <span class="erreur">${form.erreurs['email']}</span>
			</div>
			
			<div class="form-group">
			    <label for="datenaissance">Date de naissance&nbsp;<i class="fas fa-calendar-alt"></i> (facultative)</label>
    			<input type="date" class="form-control" id="datenaissance" name="datenaissance" value="<c:out value="${ utilisateur.dateNaissance }" />">
    			<span class="erreur">${form.erreurs['datenaissance']}</span>
            </div>
			
			<div class="form-row">
			    <div class="form-group col-md-6">
			      	<label for="adresse">Adresse <i class="fas fa-map-marker-alt"></i> (facultative)</label>
			      	<input type="text" class="form-control" id="adresse" name="adresse" value="<c:out value="${ utilisateur.adresse }" />" placeholder="numéro et nom de la rue">
			      	<span class="erreur">${form.erreurs['adresse']}</span>
				</div>
				<div class="form-group col-md-4">
					<label for="cp">Code postal</label>
			     	<input type="text" class="form-control" id="cp" name="cp" value="<c:out value="${ utilisateur.codePostal }" />" placeholder="code postal"/>
			     	<span class="erreur">${form.erreurs['cp']}</span>
				</div>
				<div class="form-group col-md-2">
					<label for="ville">Ville</label>
			      	<input type="text" class="form-control" id="ville" name="ville" value="<c:out value="${ utilisateur.ville }" />" placeholder="ville"/>
			      	<span class="erreur">${form.erreurs['ville']}</span>
			    </div>
			</div>
			
			<div style="text-align:center;">
		  		<button type="submit" class="btn btn-primary">Créer mon compte &nbsp; <i class="fas fa-plus"></i></button>
		  	</div>
		</form>
		<br />
	</div>
	
	<c:import url="inc/piedDePage.jsp" />
	
</body>
</html>