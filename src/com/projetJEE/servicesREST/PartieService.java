package com.projetJEE.servicesREST;

import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.projetJEE.beans.Partie;
import com.projetJEE.dao.DAOFactory;
import com.projetJEE.dao.partie.PartieDao;

@Path("/parties")
public class PartieService {
	
	private final static Logger logger = Logger.getLogger(UtilisateurService.class);

	private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
    private PartieDao partieDao = DAO_FACTORY.getPartieDao();

   
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Partie> get() {
        List<Partie> parties = this.partieDao.lister();
        logger.info("Appel de l'API REST par la methode GET /parties");
		return parties;
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	public Partie post(Partie partie) {
		DateTime dateFin = DateTime.now();
		partie.setDateFin(new Timestamp(dateFin.getMillis()));
		
		this.partieDao.creer(partie);
        logger.info("Appel de l'API REST de partie par la methode POST /parties");
		return partie;
	}


}
