<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		function verifSecuPassword() {
			var mdp = $("#newMdp").val();
			
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
<body>

	<div class="modal-dialog modal-dialog-centered" role="document">
    	<div class="modal-content">
      		<div class="modal-header">
        		<h5 class="modal-title">Modifier mon mot de passe</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          				<span aria-hidden="true">&times;</span>
        			</button>
      		</div>
      		<form method="post" action="consultationCompte">
	      		<div class="modal-body">
		        		<div class="form-group">
	            			<label for="mdpActuel" class="col-form-label">Mot de passe actuel :</label>
	            			<input type="password" class="form-control" id="mdpActuel" name="mdpActuel" />
	            			<span class="erreur">${form.erreurs['mdpActuel']}</span>
	          			</div>
	         			<div class="form-group">
	            			<label for="newMdp" class="col-form-label">Nouveau mot de passe :</label>
	            			<input type="password" class="form-control" id="newMdp" name="newMdp" oninput="verifSecuPassword();" />
	            			<span class="erreur">${form.erreurs['newMdp']}</span>
	            			<div class="progress">
  								<div class="progress-bar" role="progressbar" id="progressSecuPassword" style="width: 0%;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
							</div>
	          			</div>
	          			<div class="form-group">
	            			<label for="confirmerNewMdp" class="col-form-label">Confirmer nouveau mot de passe :</label>
	            			<input type="password" class="form-control" id="confirmerNewMdp" name="confirmerNewMdp" value="" size="20" maxlength="20"/>
	            			<span class="erreur">${form.erreurs['confirmerNewMdp']}</span>
	          			</div>
	      		</div>
	      		<div class="modal-footer">
	        		<button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
	        		<button type="submit" class="btn btn-primary">Modifier</button>
	      		</div>
      		</form>
    	</div>
  	</div>

</body>
</html>