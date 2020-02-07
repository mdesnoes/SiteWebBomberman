package com.projetJEE.metier;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.projetJEE.beans.Utilisateur;
import com.projetJEE.servlets.Accueil;
import com.sun.istack.internal.logging.Logger;

public class CreationCompteForm {
	
	final static Logger logger = Logger.getLogger(CreationCompteForm.class);

	private static final String CHAMP_PSEUDO = "pseudo";
	private static final String CHAMP_PASSWORD = "password";
    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_PRENOM = "prenom";
    private static final String CHAMP_DATE = "datenaissance";
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_ADRESSE = "adresse";
    private static final String CHAMP_CP = "cp";
    private static final String CHAMP_VILLE = "ville";

	private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    
    
	public Utilisateur creationCompteUtilisateur(HttpServletRequest request) {
		
		Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo( request.getParameter( CHAMP_PSEUDO ) );
        utilisateur.setPassword( request.getParameter( CHAMP_PASSWORD ) );
        utilisateur.setPrenom( request.getParameter( CHAMP_PRENOM ) );
        utilisateur.setNom( request.getParameter( CHAMP_NOM ) );
        utilisateur.setEmail( request.getParameter( CHAMP_EMAIL ) );
        utilisateur.setDateNaissance( request.getParameter( CHAMP_DATE ) );
        utilisateur.setAdresse( request.getParameter( CHAMP_ADRESSE ) );
        utilisateur.setCodePostal( request.getParameter( CHAMP_CP ) );
        utilisateur.setVille( request.getParameter( CHAMP_VILLE ) );
		
        System.out.println(request.getParameter("securitePassword"));
        
        try {
			validationPseudo(utilisateur.getPseudo());
		}
		catch(Exception e) {
			erreurs.put(CHAMP_PSEUDO, e.getMessage());
		}
        
        try {
			validationPassword(utilisateur.getPassword());
		}
		catch(Exception e) {
			erreurs.put(CHAMP_PASSWORD, e.getMessage());
		}
        
        try {
			validationNom(utilisateur.getNom());
		}
		catch(Exception e) {
			erreurs.put(CHAMP_NOM, e.getMessage());
		}
        
        try {
			validationPrenom(utilisateur.getPrenom());
		}
		catch(Exception e) {
			erreurs.put(CHAMP_PRENOM, e.getMessage());
		}
        
        try {
			validationEmail(utilisateur.getEmail());
		}
		catch(Exception e) {
			erreurs.put(CHAMP_EMAIL, e.getMessage());
		}
        
        try {
			validationAdresse(utilisateur.getAdresse());
		}
		catch(Exception e) {
			erreurs.put(CHAMP_ADRESSE, e.getMessage());
		}
        
        try {
			validationCodePostal(utilisateur.getCodePostal());
		}
		catch(Exception e) {
			erreurs.put(CHAMP_CP, e.getMessage());
		}
        
        try {
			validationVille(utilisateur.getVille());
		}
		catch(Exception e) {
			erreurs.put(CHAMP_VILLE, e.getMessage());
		}
        
		
		if(erreurs.isEmpty()) {
			
			//AJOUT DANS LA BDD
			
			logger.info("Succès de la crétion du compte : " + utilisateur);
			resultat = "Succès de la création du compte";
		} else {
			
			logger.info("Echec de la crétion du compte : " + utilisateur);
			resultat = "Echec de la création du compte";
		}
		
		return utilisateur;
	}
	
	
	private void validationPseudo( String pseudo ) throws Exception {
		if(!pseudo.isEmpty()) {
	    	if ( pseudo.length() < 2 ) {
	            throw new Exception( "Le nom d'utilisateur doit contenir au moins 2 caractères" );
	        }
		} else {
            throw new Exception( "Merci de saisir un nom d'utilisateur" );
		}
    }
	
	private void validationPassword( String password ) throws Exception {
		if(!password.isEmpty()) {
	    	if ( password.length() < 5 ) {
	            throw new Exception( "Le mot de passe doit contenir au moins 5 caractères" );
	        }
		} else {
            throw new Exception( "Merci de saisir un mot de passe" );
		}
    }
	
	private void validationNom( String nom ) throws Exception {
		if(!nom.isEmpty()) {
	    	if ( nom.length() < 2 ) {
	            throw new Exception( "Le nom doit contenir au moins 2 caractères" );
	        }
		} else {
            throw new Exception( "Merci de saisir un nom" );
		}
    }
	
	private void validationPrenom( String prenom ) throws Exception {
		if(!prenom.isEmpty()) {
	    	if ( prenom.length() < 2 ) {
	            throw new Exception( "Le prenom doit contenir au moins 2 caractères" );
	        }
		}else {
            throw new Exception( "Merci de saisir un prénom" );
		}
    }
	
	private void validationEmail( String email ) throws Exception {
		if(!email.isEmpty()) {
	    	if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new Exception( "Merci de saisir une adresse e-mail valide" );
	        }
		} else {
            throw new Exception( "Merci de saisir une adresse e-mail" );
		}
    }
	
	private void validationAdresse( String adresse ) throws Exception {
    	if ( !adresse.isEmpty() && adresse.length() < 3 ) {
            throw new Exception( "L'adresse doit contenir au moins 3 caractères" );
        }
    }
    
    private void validationCodePostal( String cp ) throws Exception {
    	if ( !cp.isEmpty() && !cp.matches("^(([0-8][0-9])|(9[0-5])|(2[ab]))[0-9]{3}$") ) {
            throw new Exception( "Merci de saisir un code postal valide" );
        }
    }
    
    private void validationVille( String ville ) throws Exception {
    	if ( !ville.isEmpty() && ville.length() < 3 ) {
            throw new Exception( "La ville doit contenir au moins 3 caractères" );
        }
    }
    
    
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

}
