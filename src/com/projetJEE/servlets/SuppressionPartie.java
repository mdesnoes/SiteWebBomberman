package com.projetJEE.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.projetJEE.dao.DAOFactory;
import com.projetJEE.dao.partie.PartieDao;
import com.projetJEE.metier.ModificationCompteForm;


@WebServlet("/suppressionPartie")
public class SuppressionPartie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static Logger logger = Logger.getLogger(SuppressionPartie.class);

 
	public static final String ACCUEIL= "/SiteWebBomberman/accueil";
	private static final String ID_PARTIE = "idPartie";
    private static final String ATT_LISTE_PARTIES = "listeParties";

	public static final String CONF_DAO_FACTORY = "daofactory";
    private PartieDao partieDao;
    

    public SuppressionPartie() {
        super();
    }
    
    public void init() throws ServletException {
        this.partieDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPartieDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idPartie = request.getParameter( ID_PARTIE );
				
		/* On supprime en base de données */
		this.partieDao.supprimerParId(idPartie);
		
		/* On supprime la liste des parties en session */
        HttpSession session = request.getSession();
        session.removeAttribute( ATT_LISTE_PARTIES );

        logger.info("La partie numero " + idPartie + " a été supprimée");
        
        /* On redirige vers la servlet acceuil qui va rechercher la nouvelle liste des parties */
        response.sendRedirect( ACCUEIL );
	}

}
