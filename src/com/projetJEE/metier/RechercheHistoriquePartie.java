package com.projetJEE.metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.projetJEE.beans.Partie;
import com.projetJEE.dao.partie.PartieDao;


public class RechercheHistoriquePartie {
	
	final static Logger logger = Logger.getLogger(RechercheHistoriquePartie.class);
	
	private static final String WHERE = "WHERE";
	
	private static final String CHAMP_RECHERCHE_ID = "recherche_id";
	private static final String CHAMP_RECHERCHE_DATE_DEBUT = "recherche_date_debut";
	private static final String CHAMP_RECHERCHE_VAINQUEUR = "recherche_vainqueur";

	
    private PartieDao partieDao;
    
	public RechercheHistoriquePartie( PartieDao partieDao ) {
        this.partieDao = partieDao;
    }
	
	public List<Partie> rechercherPartie( HttpServletRequest request ) {
		
        String id = request.getParameter( CHAMP_RECHERCHE_ID );
        String dateDebut = request.getParameter( CHAMP_RECHERCHE_DATE_DEBUT );
        String vainqueur = request.getParameter( CHAMP_RECHERCHE_VAINQUEUR );


        if( (id == null || id.isEmpty()) &&
        	(dateDebut == null || dateDebut.isEmpty()) && 
        	(vainqueur == null || vainqueur.isEmpty()) ) {
        	
        	return this.partieDao.lister();
        }

        /* Requete sql de recherche */
        String sql = "SELECT * FROM Partie ";

        if(id != null) {
        	sql = sql.concat( "WHERE id LIKE '" + id + "%' " );
        }
        
        if(dateDebut != null && !dateDebut.isEmpty()) {
        	if(!sql.contains(WHERE)) {
        		sql = sql.concat( "WHERE date_debut LIKE '" + dateDebut + "%' " );
        	} else {
        		sql = sql.concat( "AND date_debut LIKE '" + dateDebut + "%' " );
        	}
        }
        
        if(vainqueur != null && !vainqueur.isEmpty()) {
        	if(!sql.contains(WHERE)) {
        		sql = sql.concat( "WHERE vainqueur LIKE '" + vainqueur + "%' " );
        	} else {
        		sql = sql.concat( "AND vainqueur LIKE '" + vainqueur + "%' " );
        	}
        }
        
        sql = sql.concat("ORDER BY id DESC");

        logger.info("Requete SQL de recherche de partie : " + sql);
        return this.partieDao.rechercher(sql);
	}
	
}
