package com.projetJEE.servicesREST;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.projetJEE.beans.Utilisateur;
import com.projetJEE.dao.DAOFactory;
import com.projetJEE.dao.utilisateur.UtilisateurDao;


@Path("/utilisateur")
public class UtilisateurService {
	private final static Logger logger = Logger.getLogger(UtilisateurService.class);

	
	private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
    private UtilisateurDao utilisateurDao = DAO_FACTORY.getUtilisateurDao();

    private static final String SQL_SELECT_PAR_PSEUDO = "SELECT * FROM Utilisateur WHERE pseudo = ?";

   
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Utilisateur> get() {
        List<Utilisateur> utilisateurs = this.utilisateurDao.lister();
        logger.info("Appel de l'API REST par la methode GET /utilisateur");
		return utilisateurs;
	}
	
	
	@GET
	@Path("/{pseudo}")
    @Produces(MediaType.APPLICATION_JSON)
	public Utilisateur get(@PathParam("pseudo") String pseudo) {
		Utilisateur utilisateur = this.utilisateurDao.trouver( SQL_SELECT_PAR_PSEUDO, pseudo );
        logger.info("Appel de l'API REST par la methode GET /utilisateur/" + pseudo);
		return utilisateur;
	}
	

}
