package com.projetJEE.metier;

import com.projetJEE.beans.Utilisateur;
import com.projetJEE.dao.UtilisateurDao;

public class SupprimerCompteUtilisateur {
	    
    private UtilisateurDao utilisateurDao;
    
    
    public SupprimerCompteUtilisateur( UtilisateurDao utilisateurDao ) {
        this.utilisateurDao = utilisateurDao;
    }
    
    public void supprimerCompteUtilisateur(Utilisateur utilisateur) {
    	utilisateurDao.supprimer(utilisateur);
    }

}
