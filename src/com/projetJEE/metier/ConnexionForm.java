package com.projetJEE.metier;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.projetJEE.beans.Utilisateur;


public class ConnexionForm {
	
	private static final String CHAMP_PSEUDO = "pseudo";
    private static final String CHAMP_PASSWORD = "password";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();


    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
        String pseudo = request.getParameter( CHAMP_PSEUDO );
        String password = request.getParameter( CHAMP_PASSWORD );
        
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo( pseudo );
        utilisateur.setPassword( password );

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
        
        // AUTRES VERIFICATIONS
        // SI PSEUDO ET MOT DE PASSE SONT TROUVE DANS LA BDD :
        // ON RECUPERE TOUT LES DONNEES DE L'UTILISATEUR
        // SINON ON AJOUTE UNE ERREUR 


        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }

        return utilisateur;
    }
    

    private void validationPseudo( String pseudo ) throws Exception {
        if ( pseudo.isEmpty() ) {
            throw new Exception( "Merci de saisir votre nom d'utilisateur" );
        }
    }

    private void validationPassword( String password ) throws Exception {
        if ( password != null ) {
            if ( password.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }
    
    
    
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

}
