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
import com.projetJEE.dao.UtilisateurDao;
import com.projetJEE.metier.ModificationCompteForm;


/**
 * Servlet implementation class ConsultationCompte
 */
@WebServlet("/consultationCompte")
public class ConsultationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";

    public static final String CONF_DAO_FACTORY = "daofactory";
    private UtilisateurDao utilisateurDao;
    

    public ConsultationCompte() {
        super();
    }
    
    public void init() throws ServletException {
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/restreint/consulterCompte.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Le methode doPost est appelé quand l'utilisateur modifie son profil ou son mot de passe
        HttpSession session = request.getSession();

		ModificationCompteForm form = new ModificationCompteForm(this.utilisateurDao);
		Utilisateur newUtilisateur = form.modificationCompteUtilisateur((Utilisateur) session.getAttribute(ATT_SESSION_USER), request);
		
        session.setAttribute( ATT_SESSION_USER, newUtilisateur );
		request.setAttribute(ATT_FORM, form);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/restreint/consulterCompte.jsp").forward(request, response);
	}

}
