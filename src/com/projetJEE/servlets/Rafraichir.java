package com.projetJEE.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/rafraichir")
public class Rafraichir extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	public static final String ACCUEIL= "/SiteWebBomberman/accueil";

	private static final String ATT_LISTE_PARTIES = "listeParties";
    private static final String ATT_MAP_CLASSEMENT = "mapClassement";
    
    
    public Rafraichir() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* On supprime les sessions de l'historique des parties et du classement */
		HttpSession session = request.getSession();
		session.removeAttribute( ATT_LISTE_PARTIES );
		session.removeAttribute( ATT_MAP_CLASSEMENT );
		
		/* On redirige vers la page d'acceuil qui va re-saisir les sessions */ 
        response.sendRedirect( ACCUEIL );
	}

}
