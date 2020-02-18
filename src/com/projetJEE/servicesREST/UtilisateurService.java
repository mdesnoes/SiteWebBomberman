package com.projetJEE.servicesREST;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.projetJEE.beans.Utilisateur;
import com.projetJEE.dao.DAOFactory;
import com.projetJEE.dao.UtilisateurDao;


@Path("/utilisateur")
public class UtilisateurService {
	
	private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
    private UtilisateurDao utilisateurDao = DAO_FACTORY.getUtilisateurDao();

    private static final String SQL_SELECT_PAR_PSEUDO = "SELECT * FROM Utilisateur WHERE pseudo = ?";

   
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Utilisateur> get() {
        List<Utilisateur> utilisateurs = this.utilisateurDao.lister();
		return utilisateurs;
	}
	
	
	@GET
	@Path("/{pseudo}")
    @Produces(MediaType.APPLICATION_JSON)
	public Utilisateur get(@PathParam("pseudo") String pseudo) {
		Utilisateur utilisateur = this.utilisateurDao.trouver( SQL_SELECT_PAR_PSEUDO, pseudo );
		return utilisateur;
	}
	

}
