package com.projetJEE.metier;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.projetJEE.beans.Utilisateur;
import com.projetJEE.dao.utilisateur.UtilisateurDao;


public class ConnexionForm {
	
	final static Logger logger = Logger.getLogger(ConnexionForm.class);
	
	private static final String CHAMP_PSEUDO = "pseudo";
    private static final String CHAMP_PASSWORD = "password";
    private static final String CHAMP_DONNEE_INCORRECTE = "donnee_incorrecte";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    
    private static final String SQL_SELECT_PAR_PSEUDO = "SELECT * FROM Utilisateur WHERE pseudo = ? ";
    
    private UtilisateurDao utilisateurDao;
    private static final MotDePasseEncryptor mdpEncryptor = MotDePasseEncryptor.getInstance();

    
    public ConnexionForm( UtilisateurDao utilisateurDao ) {
        this.utilisateurDao = utilisateurDao;
    }


    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
        String pseudo = request.getParameter( CHAMP_PSEUDO );
        String password = request.getParameter( CHAMP_PASSWORD );
        
        try {
            validationPseudo( pseudo );
        } catch ( Exception e ) {
            this.erreurs.put(CHAMP_PSEUDO, e.getMessage());
        }

        try {
            validationPassword( password );
        } catch ( Exception e ) {
            this.erreurs.put(CHAMP_PASSWORD, e.getMessage());
        }
        
        
        Utilisateur utilisateur = utilisateurDao.trouver(SQL_SELECT_PAR_PSEUDO, pseudo);
        if(utilisateur != null) {
	        if(!utilisateur.getPseudo().equals(pseudo)) {
	        	logger.info("Le pseudo " + pseudo + " n'a pas été trouvé dans la base de donnée");
	            this.erreurs.put(CHAMP_DONNEE_INCORRECTE, "Votre pseudo est incorrect");
	            
		        utilisateur.setPseudo(pseudo); // On remet le pseudo incorrecte pour l'afficher dans le modal de connexion
	        } else {
	            String passwordDechiffre = mdpEncryptor.decrypter( utilisateur.getPassword() );
	        	
	        	if(!password.equals(passwordDechiffre)) {
	        		logger.info("Mot de passe incorrecte pour l'utilisateur " + pseudo);
	                this.erreurs.put(CHAMP_DONNEE_INCORRECTE, "Votre mot de passe est incorrect");
	        	}
	        }
	    } else {
        	logger.info("Le pseudo " + pseudo + " n'a pas été trouvé dans la base de donnée");
            this.erreurs.put(CHAMP_DONNEE_INCORRECTE, "Votre pseudo est incorrect");
        }

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }

        // On retourne l'utilisateur trouvé en base de donnée ou un utilisateur avec le mauvais pseudo (pour l'affichage dans le modal de connexion)
        Utilisateur utilisateurRetoure = new Utilisateur();
        if(utilisateur != null) {
        	utilisateurRetoure = utilisateur;
        } else {
        	utilisateurRetoure.setPseudo(pseudo);
        }
        return utilisateurRetoure;
    }
    

    private void validationPseudo( String pseudo ) throws Exception {
        if ( pseudo.isEmpty() ) {
            throw new Exception( "Merci de saisir votre nom d'utilisateur" );
        }
    }

    private void validationPassword( String password ) throws Exception {
        if ( password.isEmpty() ) {
            throw new Exception( "Merci de saisir votre mot de passe" );
        }
    }
    
    
    
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

}
