package com.projetJEE.dao.utilisateur;

import java.util.List;

import com.projetJEE.beans.Utilisateur;
import com.projetJEE.dao.DAOException;

public interface UtilisateurDao {
	
    void creer( Utilisateur utilisateur ) throws DAOException;
    List<Utilisateur> lister() throws DAOException;
    Utilisateur trouver( String sql, Object... objets ) throws DAOException;
    void modifier( Utilisateur utilisateur ) throws DAOException;
    void supprimer( Utilisateur utilisateur ) throws DAOException;

}
