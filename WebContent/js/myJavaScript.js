

function creationCookie() {
	
	var pseudo = $("#pseudo").val();
	var password = $("#password").val();
	
	sessionStorage.setItem("pseudo", pseudo);
	sessionStorage.setItem("password", password);
	
	activerBtn();
}

function activerBouton() {
	if(sessionStorage.getItem("pseudo") && sessionStorage.getItem("password")){

		$("#btnMonCompte").removeAttr("disabled");
		$("#btnDeconnexion").removeAttr("disabled");
	}
	
}


function deconnexion() {
	
	sessionStorage.clear();
	
	$("#btnMonCompte").attr("disabled", true);
	$("#btnDeconnexion").attr("disabled", true);

}



function verificationSessionDejaOuverte() {
	if(sessionStorage.getItem("pseudo") && sessionStorage.getItem("password")){
		console.log("session ouverte");
		$("#informationsConnexion").html("Vous êtes connecté sous le pseudo <u>" + sessionStorage.getItem("pseudo") + "</u>");
	} else {
		console.log("session non ouverte");
		$("#informationsConnexion").html("Vous n'êtes pas connecté");
	}
}
