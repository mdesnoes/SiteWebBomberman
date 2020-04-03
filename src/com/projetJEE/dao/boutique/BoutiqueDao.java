package com.projetJEE.dao.boutique;

import java.util.List;

import com.projetJEE.beans.Objet;
import com.projetJEE.dao.DAOException;

public interface BoutiqueDao {
	
	void creer( Objet objet ) throws DAOException;
    List<Objet> lister() throws DAOException;
    Objet trouver( String sql, Object... objets ) throws DAOException;
    void modifier( Objet objet ) throws DAOException;
    void supprimer( Objet objet ) throws DAOException;

}
