package com.projetJEE.servicesREST;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.projetJEE.beans.Utilisateur;


@Path("/utilisateur")
public class UtilisateurService {
	
	@GET
	@Path("/{pseudo}")
    @Produces(MediaType.APPLICATION_JSON)
	public Utilisateur get(@PathParam("pseudo") String pseudo) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom("DESNOES");
		utilisateur.setPrenom("Mathis");
		utilisateur.setPseudo(pseudo);
		
		return utilisateur;
	}
	
	@POST
	public Utilisateur post(Utilisateur utilisateur) {
		return utilisateur;
	}
	
	@GET
	public Utilisateur get() {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom("DESNOES");
		utilisateur.setPrenom("Mathis");
		
		return utilisateur;
	}

}
