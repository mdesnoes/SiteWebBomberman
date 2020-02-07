<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

<!-- 	<script type="text/javascript" src="js/myJavaScript.js"></script> -->

</head>
<body>

	<div class="modal-dialog modal-lg">
    	<div class="modal-content">
    		<div class="modal-header">
        		<h5 class="modal-title">Merci de confirmer les modifications </h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          				<span aria-hidden="true">&times;</span>
        			</button>
      		</div>
      		<div class="modal-body">
	       		<div class="form-group" id="divModification">
          			
        		</div>
	      	</div>
      		<div class="modal-footer">
        		<button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
   				<button type="submit" class="btn btn-primary">Confirmer</button>
      		</div>
		</div>
  	</div>
  	
  	<script>
	  	$("#btnModif" ).click(function() {
	  		
	  		$("#divModification").empty();
	  		
	  		if($("form #pseudo").val() != "") {
	  			$("#divModification").append("<p><i>Ancien pseudo :</i> <span style='color:#900'><c:out value='${ sessionScope.sessionUtilisateur.pseudo }'/> </span>" 
	  				+ " &nbsp;<i class='fas fa-arrows-alt-h'></i>&nbsp; <i>Nouveau pseudo :</i> <span style='color:#090'>" + $("form #pseudo").val() + "</span></p>");
	  		}
	  		
	  		if($("form #nom").val() != "") {
	  			$("#divModification").append("<p><i>Ancien nom :</i> <span style='color:#900'><c:out value='${ sessionScope.sessionUtilisateur.nom }'/> </span>" 
	  				+ " &nbsp;<i class='fas fa-arrows-alt-h'></i>&nbsp; <i>Nouveau nom :</i> <span style='color:#090'>" + $("form #nom").val() + "</span></p>");
	  		}
	  		
	  		if($("form #prenom").val() != "") {
	  			$("#divModification").append("<p><i>Ancien prenom :</i> <span style='color:#900'><c:out value='${ sessionScope.sessionUtilisateur.prenom }'/> </span>" 
	  				+ " &nbsp;<i class='fas fa-arrows-alt-h'></i>&nbsp; <i>Nouveau prenom :</i> <span style='color:#090'>" + $("form #prenom").val() + "</span></p>");
	  		}
	  		
	  		if($("form #datenaissance").val() != "") {
	  			$("#divModification").append("<p><i>Ancienne date de naissance :</i> <span style='color:#900'><c:out value='${ sessionScope.sessionUtilisateur.dateNaissance }'/> </span>" 
	  				+ " &nbsp;<i class='fas fa-arrows-alt-h'></i>&nbsp; <i>Nouvelle date de naissance :</i> <span style='color:#090'>" + $("form #datenaissance").val() + "</span></p>");
	  		}
	  		
	  		if($("form #email").val() != "") {
	  			$("#divModification").append("<p><i>Ancienne adresse e-mail :</i> <span style='color:#900'><c:out value='${ sessionScope.sessionUtilisateur.email }'/> </span>" 
	  				+ " &nbsp;<i class='fas fa-arrows-alt-h'></i>&nbsp; <i>Nouvelle adresse e-mail :</i> <span style='color:#090'>" + $("form #email").val() + "</span></p>");
	  		}
	  		
	  		if($("form #adresse").val() != "" || $("form #cp").val() != "" || $("form #ville").val() != "") {
	  			$("#divModification").append("<p><i>Ancienne adresse :</i> <span style='color:#900'> "
	  				+ "<c:out value='${ sessionScope.sessionUtilisateur.adresse }'/> <c:out value='${ sessionScope.sessionUtilisateur.codePostal }'/> <c:out value='${ sessionScope.sessionUtilisateur.ville }'/> </span>" 
	  				+ " &nbsp;<i class='fas fa-arrows-alt-h'></i>&nbsp; <i>Nouvelle adresse :</i> <span style='color:#090'>"
	  				+ $("form #adresse").val() + " " + $("form #cp").val() + " " + $("form #ville").val() + "</span></p>");
	  		}
	  	});
  	</script>

</body>
</html>