package com.projetJEE.servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.projetJEE.beans.Utilisateur;
import com.projetJEE.metier.CreationCompteForm;

/**
 * Servlet implementation class creationCompte
 */
@WebServlet("/creationCompte")
public class CreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String ATT_FORM = "form";
    public static final String ATT_UTILISATEUR = "utilisateur";
    
    
    public CreationCompte() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/creerCompte.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		CreationCompteForm form = new CreationCompteForm();
		Utilisateur utilisateur = form.creationCompteUtilisateur(request);
		
        request.setAttribute(ATT_UTILISATEUR, utilisateur );
		request.setAttribute(ATT_FORM, form);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/creerCompte.jsp").forward(request, response);
	}

}
