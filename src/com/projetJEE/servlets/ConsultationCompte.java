package com.projetJEE.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.projetJEE.beans.Utilisateur;
import com.projetJEE.metier.ModificationCompteForm;


/**
 * Servlet implementation class ConsultationCompte
 */
@WebServlet("/consultationCompte")
public class ConsultationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";


    public ConsultationCompte() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/restreint/consulterCompte.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Le methode doPost est appelé quand l'utilisateur modifie son profil ou son mot de passe
        HttpSession session = request.getSession();
		System.out.println("Actuel : " + session.getAttribute(ATT_SESSION_USER).toString());

		ModificationCompteForm form = new ModificationCompteForm();
		Utilisateur newUtilisateur = form.modificationCompteUtilisateur((Utilisateur) session.getAttribute(ATT_SESSION_USER), request);
		
        session.setAttribute( ATT_SESSION_USER, newUtilisateur );
		request.setAttribute(ATT_FORM, form);
		
		System.out.println("New : " + newUtilisateur.toString());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/restreint/consulterCompte.jsp").forward(request, response);
	}

}
