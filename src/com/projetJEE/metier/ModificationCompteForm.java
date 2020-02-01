package com.projetJEE.metier;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.projetJEE.beans.Utilisateur;

public class ModificationCompteForm {
	
	private static final String CHAMP_PSEUDO = "pseudo";
    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_PRENOM = "prenom";
    private static final String CHAMP_DATE = "datenaissance";
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_ADRESSE = "adresse";
    private static final String CHAMP_CP = "cp";
    private static final String CHAMP_VILLE = "ville";
    
    private static final String CHAMP_MDP_ACTUEL = "mdpActuel";
    private static final String CHAMP_NEW_MDP = "newMdp";
    private static final String CHAMP_CONFIRMER_NEW_MDP = "confirmerNewMdp";

    
	private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    
	public Utilisateur modificationCompteUtilisateur(Utilisateur utilisateur, HttpServletRequest request) {
		String pseudo = request.getParameter( CHAMP_PSEUDO );
        String nom = request.getParameter( CHAMP_NOM );
        String prenom = request.getParameter( CHAMP_PRENOM );
        String datenaissance = request.getParameter( CHAMP_DATE );
        String email = request.getParameter( CHAMP_EMAIL );
        String adresse = request.getParameter( CHAMP_ADRESSE );
        String cp = request.getParameter( CHAMP_CP );
        String ville = request.getParameter( CHAMP_VILLE );
        
        String mdpActuel = request.getParameter( CHAMP_MDP_ACTUEL );
        String newMdp = request.getParameter( CHAMP_NEW_MDP );
        String confirmerNewMdp = request.getParameter( CHAMP_CONFIRMER_NEW_MDP );

		Utilisateur newUtilisateur = new Utilisateur();
		newUtilisateur.setPseudo(utilisateur.getPseudo());
		newUtilisateur.setPassword(utilisateur.getPassword());
		newUtilisateur.setPrenom(utilisateur.getPrenom());
		newUtilisateur.setNom(utilisateur.getNom());
		newUtilisateur.setEmail(utilisateur.getEmail());
		newUtilisateur.setDateNaissance(utilisateur.getDateNaissance());
		newUtilisateur.setAdresse(utilisateur.getAdresse());
		newUtilisateur.setCodePostal(utilisateur.getCodePostal());
		newUtilisateur.setVille(utilisateur.getVille());

		
		if(pseudo != null && pseudo != "") {
			try {
				validationPseudo(pseudo);
			}
			catch(Exception e) {
				System.out.println("ok");
				erreurs.put(CHAMP_PSEUDO, e.getMessage());
			}
			newUtilisateur.setPseudo(pseudo);
		}


		if(mdpActuel != null && newMdp != null && confirmerNewMdp != null) { 
			try {
				validationMdpActuel(mdpActuel, utilisateur.getPassword());
			}
			catch(Exception e) {
				erreurs.put(CHAMP_MDP_ACTUEL, e.getMessage());
			}
			
			try {
				validationNewMdp(newMdp);
			}
			catch(Exception e) {
				erreurs.put(CHAMP_NEW_MDP, e.getMessage());
			}
			
			try {
				validationConfirmerNewMdp(confirmerNewMdp, newMdp);
			}
			catch(Exception e) {
				erreurs.put(CHAMP_CONFIRMER_NEW_MDP, e.getMessage());
			}
			
			newUtilisateur.setPassword(newMdp);
		}
		
		if(nom != null && nom != "") {
			try {
				validationNom(nom);
			}
			catch(Exception e) {
				erreurs.put(CHAMP_NOM, e.getMessage());
			}
			newUtilisateur.setNom(nom);
		}
		
		if(prenom != null && prenom != "") {
			try {
				validationPrenom(prenom);
			}
			catch(Exception e) {
				erreurs.put(CHAMP_PRENOM, e.getMessage());
			}
			newUtilisateur.setPrenom(prenom);
		}
		
		if(datenaissance != null && datenaissance != "") {
			newUtilisateur.setDateNaissance(datenaissance);
		}
		
		if(email != null && email != "") {
			try {
				validationEmail(email);
			}
			catch(Exception e) {
				erreurs.put(CHAMP_EMAIL, e.getMessage());
			}
			newUtilisateur.setEmail(email);
		}
		
		if(adresse != null && adresse != "") {
			try {
				validationAdresse(adresse);
			}
			catch(Exception e) {
				erreurs.put(CHAMP_ADRESSE, e.getMessage());
			}
			newUtilisateur.setAdresse(adresse);
		}
		
		if(cp != null && cp != "") {
			try {
				validationCodePostal(cp);
			}
			catch(Exception e) {
				erreurs.put(CHAMP_CP, e.getMessage());
			}
			newUtilisateur.setCodePostal(cp);
		}
		
		if(ville != null && ville != "") {
			newUtilisateur.setVille(ville);
		}
		
		
		if(erreurs.isEmpty()) {
			
			//MODIFICATION DANS LA BASE DE DONNÉ
			
			
			resultat = "Succes de la modification";
			return newUtilisateur;
		}
		
		resultat = "Echec de la modification";
		System.out.println("Il y a eu des erreurs");
		return utilisateur;
	}
	
	
	
	private void validationPseudo( String pseudo ) throws Exception {
    	if ( pseudo.length() < 2 ) {
            throw new Exception( "Le nouveau pseudo doit contenir au moins 2 caractères" );
        }
    }
	
	private void validationNom( String nom ) throws Exception {
    	if ( nom.length() < 2 ) {
            throw new Exception( "Le nouveau nom doit contenir au moins 2 caractères" );
        }
    }
	
	private void validationPrenom( String prenom ) throws Exception {
    	if ( prenom.length() < 2 ) {
            throw new Exception( "Le nouveau prenom doit contenir au moins 2 caractères" );
        }
    }
	
	private void validationAdresse( String adresse ) throws Exception {
    	if ( adresse.length() < 3 ) {
            throw new Exception( "La nouvelle adresse doit contenir au moins 3 caractères" );
        }
    }

    private void validationMdpActuel( String mdpActuel, String mdp ) throws Exception {
        if ( !mdpActuel.equals(mdp) ) {
        	throw new Exception( "Ce n'est pas le mot de passe actuel" );
        }
    }
    
    private void validationNewMdp( String newMdp) throws Exception {
    	if ( newMdp.length() < 3 ) {
            throw new Exception( "Le nouveau mot de passe doit contenir au moins 3 caractères" );
        }
    }
    
    private void validationConfirmerNewMdp( String confirmerNewMdp, String newMdp) throws Exception {
    	if ( !confirmerNewMdp.contentEquals(newMdp)  ) {
            throw new Exception( "La confirmation doit être identique au nouveau mot de passe" );
        }
    }
    
    private void validationEmail( String email ) throws Exception {
    	if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide" );
        }
    }
    
    private void validationCodePostal( String cp ) throws Exception {
    	if ( !cp.matches("^-?\\d+$") ) {
            throw new Exception( "Merci de saisir un code postal valide" );
        }
    }
	
	
	
	public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

}
