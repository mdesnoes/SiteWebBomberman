package com.projetJEE.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.projetJEE.beans.Utilisateur;
import com.projetJEE.dao.DAOFactory;
import com.projetJEE.dao.utilisateur.UtilisateurDao;


@WebServlet("/suppressionCompte")
public class SuppressionCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public static final String ACCUEIL= "/SiteWebBomberman/accueil";
	private static final String ATT_SESSION_USER = "sessionUtilisateur";
    
	public static final String CONF_DAO_FACTORY = "daofactory";
    private UtilisateurDao utilisateurDao;
	
    public SuppressionCompte() {
        super();
    }

    public void init() throws ServletException {
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		/* On supprime en base de donnée */
    	this.utilisateurDao.supprimer((Utilisateur) session.getAttribute( ATT_SESSION_USER ));

		session.invalidate();
		
        response.sendRedirect( ACCUEIL );
	}

}
