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

public class ModificationCompteForm {
	
	final static Logger logger = Logger.getLogger(ModificationCompteForm.class);
	
	private static final String CHAMP_PSEUDO = "pseudo";
    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_PRENOM = "prenom";
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_DATE_NAISSANCE = "datenaissance";
    private static final String CHAMP_ADRESSE = "adresse";
    private static final String CHAMP_CP = "cp";
    private static final String CHAMP_VILLE = "ville";
    
    private static final String CHAMP_MDP_ACTUEL = "mdpActuel";
    private static final String CHAMP_NEW_MDP = "newMdp";
    private static final String CHAMP_CONFIRMER_NEW_MDP = "confirmerNewMdp";

    
	private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    
    
    private static final String SQL_SELECT_PAR_PSEUDO = "SELECT * FROM Utilisateur WHERE pseudo = ?";
    private static final String SQL_SELECT_PAR_EMAIL = "SELECT * FROM Utilisateur WHERE email = ?";
    
    private UtilisateurDao utilisateurDao;
    private static final MotDePasseEncryptor mdpEncryptor = MotDePasseEncryptor.getInstance();

    
    public ModificationCompteForm( UtilisateurDao utilisateurDao ) {
        this.utilisateurDao = utilisateurDao;
    }
    
    
	public Utilisateur modificationCompteUtilisateur(Utilisateur utilisateur, HttpServletRequest request) {
		String pseudo = request.getParameter( CHAMP_PSEUDO );
        String nom = request.getParameter( CHAMP_NOM );
        String prenom = request.getParameter( CHAMP_PRENOM );
        String email = request.getParameter( CHAMP_EMAIL );
        String dateNaissance = request.getParameter( CHAMP_DATE_NAISSANCE );
        String adresse = request.getParameter( CHAMP_ADRESSE );
        String cp = request.getParameter( CHAMP_CP );
        String ville = request.getParameter( CHAMP_VILLE );
        
        String mdpActuel = request.getParameter( CHAMP_MDP_ACTUEL );
        String newMdp = request.getParameter( CHAMP_NEW_MDP );
        String confirmerNewMdp = request.getParameter( CHAMP_CONFIRMER_NEW_MDP );

		Utilisateur newUtilisateur = new Utilisateur();
		newUtilisateur.setId(utilisateur.getId());
		newUtilisateur.setPseudo(utilisateur.getPseudo());
		newUtilisateur.setPassword(utilisateur.getPassword());
		newUtilisateur.setPrenom(utilisateur.getPrenom());
		newUtilisateur.setNom(utilisateur.getNom());
		newUtilisateur.setEmail(utilisateur.getEmail());
		newUtilisateur.setDateNaissance(utilisateur.getDateNaissance());
		newUtilisateur.setAdresse(utilisateur.getAdresse());
		newUtilisateur.setCodePostal(utilisateur.getCodePostal());
		newUtilisateur.setVille(utilisateur.getVille());
		newUtilisateur.setDateInscription(utilisateur.getDateInscription());

		
		if(pseudo != null && pseudo != "") {
			try {
				validationPseudo(pseudo);
			}
			catch(FormValidationException e) {
				erreurs.put(CHAMP_PSEUDO, e.getMessage());
			}
			newUtilisateur.setPseudo(pseudo);
		}

		if(mdpActuel != null && newMdp != null && confirmerNewMdp != null) {
            String passwordDechiffre = mdpEncryptor.decrypter( utilisateur.getPassword() );

			try {
				validationMdpActuel(mdpActuel, passwordDechiffre);
			}
			catch(FormValidationException e) {
				erreurs.put(CHAMP_MDP_ACTUEL, e.getMessage());
			}
			
			try {
				validationNewMdp(newMdp);
			}
			catch(FormValidationException e) {
				erreurs.put(CHAMP_NEW_MDP, e.getMessage());
			}
			
			try {
				validationConfirmerNewMdp(confirmerNewMdp, newMdp);
			}
			catch(FormValidationException e) {
				erreurs.put(CHAMP_CONFIRMER_NEW_MDP, e.getMessage());
			}
			
			//Cryptage et enregistrement du mot de passe
	        String newMdpChiffre = mdpEncryptor.crypter( newMdp );
			newUtilisateur.setPassword(newMdpChiffre);
		}
		
		if(nom != null && nom != "") {
			try {
				validationNom(nom);
			}
			catch(FormValidationException e) {
				erreurs.put(CHAMP_NOM, e.getMessage());
			}
			newUtilisateur.setNom(nom);
		}
		
		if(prenom != null && prenom != "") {
			try {
				validationPrenom(prenom);
			}
			catch(FormValidationException e) {
				erreurs.put(CHAMP_PRENOM, e.getMessage());
			}
			newUtilisateur.setPrenom(prenom);
		}
		
		if(dateNaissance != null && dateNaissance != "") {
			 try {
					//Conversion de la date en timestamp
			        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			        Date date = format.parse( request.getParameter( CHAMP_DATE_NAISSANCE ) );
			        java.sql.Date dateSql = new java.sql.Date(date.getTime());
			        newUtilisateur.setDateNaissance( dateSql );
			} catch (ParseException e) {
				erreurs.put(CHAMP_DATE_NAISSANCE, "Format de la date de naissance incorrecte");
			}
		}
		
		if(email != null && email != "") {
			try {
				validationEmail(email);
			}
			catch(FormValidationException e) {
				erreurs.put(CHAMP_EMAIL, e.getMessage());
			}
			newUtilisateur.setEmail(email);
		}
		
		if(adresse != null && adresse != "") {
			try {
				validationAdresse(adresse);
			}
			catch(FormValidationException e) {
				erreurs.put(CHAMP_ADRESSE, e.getMessage());
			}
			newUtilisateur.setAdresse(adresse);
		}
		
		if(cp != null && cp != "") {
			try {
				validationCodePostal(cp);
			}
			catch(FormValidationException e) {
				erreurs.put(CHAMP_CP, e.getMessage());
			}
			newUtilisateur.setCodePostal(cp);
		}
		
		if(ville != null && ville != "") {
			newUtilisateur.setVille(ville);
		}
		
		
		if(erreurs.isEmpty()) {
            utilisateurDao.modifier( newUtilisateur );
			
			logger.info("Succes de la modification du compte : \n Anciennes informations : " + utilisateur + "\n Nouvelles informations : " + newUtilisateur);
			resultat = "Succes de la modification";
			return newUtilisateur;
		}
		
		resultat = "Echec de la modification";
		logger.info("Echec de la modification du compte : \n Anciennes informations : " + utilisateur + "\n Nouvelles informations : " + newUtilisateur);
		return utilisateur;
	}
	
	
	
	private void validationPseudo( String pseudo ) throws FormValidationException {
    	if ( pseudo.length() < 2 ) {
            throw new FormValidationException( "Le nouveau nom d'utilisateur doit contenir au moins 2 caractères" );
        } else if ( utilisateurDao.trouver( SQL_SELECT_PAR_PSEUDO, pseudo ) != null ) {
            throw new FormValidationException( "Ce pseudo est déjà utilisée, merci d'en choisir un autre." );
        }
    }
	
	private void validationNom( String nom ) throws FormValidationException {
    	if ( nom.length() < 2 ) {
            throw new FormValidationException( "Le nouveau nom doit contenir au moins 2 caractères" );
        }
    }
	
	private void validationPrenom( String prenom ) throws FormValidationException {
    	if ( prenom.length() < 2 ) {
            throw new FormValidationException( "Le nouveau prenom doit contenir au moins 2 caractères" );
        }
    }
	
	private void validationAdresse( String adresse ) throws FormValidationException {
    	if ( adresse.length() < 3 ) {
            throw new FormValidationException( "La nouvelle adresse doit contenir au moins 3 caractères" );
        }
    }

    private void validationMdpActuel( String mdpActuel, String mdp ) throws FormValidationException {
        if ( !mdpActuel.equals(mdp) ) {
        	throw new FormValidationException( "Ce n'est pas le mot de passe actuel" );
        }
    }
    
    private void validationNewMdp( String newMdp) throws FormValidationException {
    	if ( newMdp.length() < 5 ) {
            throw new FormValidationException( "Le nouveau mot de passe doit contenir au moins 5 caractères" );
        }
    }
    
    private void validationConfirmerNewMdp( String confirmerNewMdp, String newMdp) throws FormValidationException {
    	if ( !confirmerNewMdp.contentEquals(newMdp)  ) {
            throw new FormValidationException( "La confirmation doit être identique au nouveau mot de passe" );
        }
    }
    
    private void validationEmail( String email ) throws FormValidationException {
    	if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new FormValidationException( "Merci de saisir une adresse mail valide" );
        } else if ( utilisateurDao.trouver( SQL_SELECT_PAR_EMAIL, email ) != null ) {
            throw new FormValidationException( "Cette adresse email est déjà utilisée, merci d'en choisir une autre." );
        }
    }
    
    private void validationCodePostal( String cp ) throws FormValidationException {
    	if ( !cp.matches("^(([0-8][0-9])|(9[0-5])|(2[ab]))[0-9]{3}$") ) {
            throw new FormValidationException( "Merci de saisir un code postal valide" );
        }
    }
	
	
	
	public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

}
