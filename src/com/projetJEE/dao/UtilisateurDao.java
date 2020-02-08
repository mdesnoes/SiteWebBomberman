package com.projetJEE.dao;

import java.util.List;

import com.projetJEE.beans.Utilisateur;

public interface UtilisateurDao {
	
    void creer( Utilisateur utilisateur ) throws DAOException;
    List<Utilisateur> lister() throws DAOException;
    Utilisateur trouver( String sql, Object... objets ) throws DAOException;
    void modifier( Utilisateur utilisateur ) throws DAOException;
    void supprimer( Utilisateur utilisateur ) throws DAOException;

}
