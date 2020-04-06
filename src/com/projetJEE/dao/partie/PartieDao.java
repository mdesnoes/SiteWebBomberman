package com.projetJEE.dao.partie;

import java.util.List;
import java.util.Map;

import com.projetJEE.beans.Partie;
import com.projetJEE.dao.DAOException;

public interface PartieDao {

	void creer( Partie partie ) throws DAOException;
    List<Partie> lister() throws DAOException;
	List<Partie> rechercher( String sql, Object... objets ) throws DAOException;
    void supprimerParId( String id ) throws DAOException;
    Map<String, Float> classerParVictoire(String periode) throws DAOException;
    Map<String, Float> classerParDefaite(String periode) throws DAOException;
    Map<String, Float> classerParRatio(String periode) throws DAOException;

}
