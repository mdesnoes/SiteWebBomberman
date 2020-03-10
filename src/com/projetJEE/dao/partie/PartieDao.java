package com.projetJEE.dao.partie;

import java.util.List;

import com.projetJEE.beans.Partie;
import com.projetJEE.dao.DAOException;

public interface PartieDao {

	void creer( Partie partie ) throws DAOException;
    List<Partie> lister() throws DAOException;
    
}
