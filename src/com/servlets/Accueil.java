package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ATT_A_UN_COMPTE = "aUnCompte";
       

    public Accueil() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean aUnCompte = true;
		//Faux si les données mdp et pseudo ne sont pas présente dans la base
		request.setAttribute(ATT_A_UN_COMPTE, aUnCompte);
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean aUnCompte = true;
		//Faux si les données mdp et pseudo ne sont pas présente dans la base
		request.setAttribute(ATT_A_UN_COMPTE, aUnCompte);
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

}
