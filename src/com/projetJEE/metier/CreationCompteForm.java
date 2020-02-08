package com.projetJEE.metier;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.projetJEE.beans.Utilisateur;
import com.projetJEE.dao.UtilisateurDao;
import com.sun.istack.internal.logging.Logger;

public class CreationCompteForm {
	
	final static Logger logger = Logger.getLogger(CreationCompteForm.class);

	private static final String CHAMP_PSEUDO = "pseudo";
	private static final String CHAMP_PASSWORD = "password";
    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_PRENOM = "prenom";
    private static final String CHAMP_DATE = "datenaissance";
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_DATE_NAISSANCE = "datenaissance";
    private static final String CHAMP_ADRESSE = "adresse";
    private static final String CHAMP_CP = "cp";
    private static final String CHAMP_VILLE = "ville";

	private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
        
    
    private static final String SQL_SELECT_PAR_PSEUDO = "SELECT * FROM Utilisateur WHERE pseudo = ?";
    private static final String SQL_SELECT_PAR_EMAIL = "SELECT * FROM Utilisateur WHERE email = ?";

    private UtilisateurDao utilisateurDao;
    private static final MotDePasseEncryptor mdpEncryptor = MotDePasseEncryptor.getInstance();

    
    public CreationCompteForm( UtilisateurDao utilisateurDao ) {
        this.utilisateurDao = utilisateurDao;
    }
    
    
	public Utilisateur creationCompteUtilisateur(HttpServletRequest request) {
		
		Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo( request.getParameter( CHAMP_PSEUDO ) );
        utilisateur.setPrenom( request.getParameter( CHAMP_PRENOM ) );
        utilisateur.setNom( request.getParameter( CHAMP_NOM ) );
        utilisateur.setEmail( request.getParameter( CHAMP_EMAIL ) );
        utilisateur.setAdresse( request.getParameter( CHAMP_ADRESSE ) );
        utilisateur.setCodePostal( request.getParameter( CHAMP_CP ) );
        utilisateur.setVille( request.getParameter( CHAMP_VILLE ) );
		
        
        try {
			validationPseudo(utilisateur.getPseudo());
		}
		catch(FormValidationException e) {
			erreurs.put(CHAMP_PSEUDO, e.getMessage());
		}
        
        String password = request.getParameter( CHAMP_PASSWORD );
        try {
			validationPassword( password );
		}
		catch(FormValidationException e) {
			erreurs.put(CHAMP_PASSWORD, e.getMessage());
		}
        String passwordChiffre = mdpEncryptor.crypter(password);
        utilisateur.setPassword( passwordChiffre );
        
        try {
			validationNom(utilisateur.getNom());
		}
		catch(FormValidationException e) {
			erreurs.put(CHAMP_NOM, e.getMessage());
		}
        
        try {
			validationPrenom(utilisateur.getPrenom());
		}
		catch(FormValidationException e) {
			erreurs.put(CHAMP_PRENOM, e.getMessage());
		}
        
        try {
			validationEmail(utilisateur.getEmail());
		}
		catch(FormValidationException e) {
			erreurs.put(CHAMP_EMAIL, e.getMessage());
		}
        
        
        try {
			//Conversion de la date en sql.date
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	        Date date = format.parse( request.getParameter( CHAMP_DATE ) );
	        java.sql.Date dateSql = new java.sql.Date(date.getTime());
	        utilisateur.setDateNaissance( dateSql );
		} catch (ParseException e) {
			erreurs.put(CHAMP_DATE_NAISSANCE, "Format de la date de naissance incorrecte");
		}
        
        try {
			validationAdresse(utilisateur.getAdresse());
		}
		catch(FormValidationException e) {
			erreurs.put(CHAMP_ADRESSE, e.getMessage());
		}
        
        try {
			validationCodePostal(utilisateur.getCodePostal());
		}
		catch(FormValidationException e) {
			erreurs.put(CHAMP_CP, e.getMessage());
		}
        
        try {
			validationVille(utilisateur.getVille());
		}
		catch(FormValidationException e) {
			erreurs.put(CHAMP_VILLE, e.getMessage());
		}
        
		
		if(erreurs.isEmpty()) {
            utilisateurDao.creer( utilisateur );
			
			logger.info("Succès de la crétion du compte : " + utilisateur);
			resultat = "Succès de la création du compte";
		} else {
			
			logger.info("Echec de la crétion du compte : " + utilisateur);
			resultat = "Echec de la création du compte";
		}
		
		return utilisateur;
	}
	
	
	private void validationPseudo( String pseudo ) throws FormValidationException {
		if(!pseudo.isEmpty()) {
	    	if ( pseudo.length() < 2 ) {
	            throw new FormValidationException( "Le nom d'utilisateur doit contenir au moins 2 caractères" );
	        } else if ( utilisateurDao.trouver( SQL_SELECT_PAR_PSEUDO, pseudo ) != null ) {
                throw new FormValidationException( "Ce pseudo est déjà utilisée, merci d'en choisir un autre." );
            }
		} else {
            throw new FormValidationException( "Merci de saisir un nom d'utilisateur" );
		}
    }
	
	private void validationPassword( String password ) throws FormValidationException {
		if(!password.isEmpty()) {
	    	if ( password.length() < 5 ) {
	            throw new FormValidationException( "Le mot de passe doit contenir au moins 5 caractères" );
	        }
		} else {
            throw new FormValidationException( "Merci de saisir un mot de passe" );
		}
    }
	
	private void validationNom( String nom ) throws FormValidationException {
		if(!nom.isEmpty()) {
	    	if ( nom.length() < 2 ) {
	            throw new FormValidationException( "Le nom doit contenir au moins 2 caractères" );
	        }
		} else {
            throw new FormValidationException( "Merci de saisir un nom" );
		}
    }
	
	private void validationPrenom( String prenom ) throws FormValidationException {
		if(!prenom.isEmpty()) {
	    	if ( prenom.length() < 2 ) {
	            throw new FormValidationException( "Le prenom doit contenir au moins 2 caractères" );
	        }
		}else {
            throw new FormValidationException( "Merci de saisir un prénom" );
		}
    }
	
	private void validationEmail( String email ) throws FormValidationException {
		if(!email.isEmpty()) {
	    	if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new FormValidationException( "Merci de saisir une adresse e-mail valide" );
	        } else if ( utilisateurDao.trouver(SQL_SELECT_PAR_EMAIL, email ) != null ) {
                throw new FormValidationException( "Cette adresse email est déjà utilisée, merci d'en choisir une autre." );
            }
		} else {
            throw new FormValidationException( "Merci de saisir une adresse e-mail" );
		}
    }
	
	private void validationAdresse( String adresse ) throws FormValidationException {
    	if ( !adresse.isEmpty() && adresse.length() < 3 ) {
            throw new FormValidationException( "L'adresse doit contenir au moins 3 caractères" );
        }
    }
    
    private void validationCodePostal( String cp ) throws FormValidationException {
    	if ( !cp.isEmpty() && !cp.matches("^(([0-8][0-9])|(9[0-5])|(2[ab]))[0-9]{3}$") ) {
            throw new FormValidationException( "Merci de saisir un code postal valide" );
        }
    }
    
    private void validationVille( String ville ) throws FormValidationException {
    	if ( !ville.isEmpty() && ville.length() < 3 ) {
            throw new FormValidationException( "La ville doit contenir au moins 3 caractères" );
        }
    }
    
    
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

}
