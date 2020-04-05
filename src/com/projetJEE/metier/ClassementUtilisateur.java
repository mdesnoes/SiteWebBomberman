package com.projetJEE.metier;

import java.util.Map;

import org.apache.log4j.Logger;

import com.projetJEE.dao.partie.PartieDao;


public class ClassementUtilisateur {
	
	final static Logger logger = Logger.getLogger(ClassementUtilisateur.class);

	private static final String VICTOIRE = "victoire";
    private static final String DEFAITE = "defaite";
    private static final String RATIO = "ratio";
 
	private PartieDao partieDao;
    
	public ClassementUtilisateur( PartieDao partieDao ) {
        this.partieDao = partieDao;
    }
	
	public Map<String, Float> classerUtilisateur( String periode, String triePar ) {
		
        logger.info("Filtre classement : periode = " + periode + " - triePar = " + triePar);
        
        switch(triePar) {
		    case VICTOIRE: return this.partieDao.classerParVictoire(periode);
		    case DEFAITE: return this.partieDao.classerParDefaite(periode);
		    case RATIO: return this.partieDao.classerParRatio(periode);
        }
        return null;
	}

}
