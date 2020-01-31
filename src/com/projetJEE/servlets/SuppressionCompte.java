package com.projetJEE.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SuppressionCompte
 */
@WebServlet("/suppressionCompte")
public class SuppressionCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public static final String ACCUEIL= "/SiteWebBomberman/accueil";
	
    public SuppressionCompte() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        session.invalidate();
        
        //SUPPRIMER DANS LA BASE DE DONNÉE

        response.sendRedirect( ACCUEIL );
	}

}
