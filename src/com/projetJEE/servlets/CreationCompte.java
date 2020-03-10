package com.projetJEE.servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.projetJEE.beans.Utilisateur;
import com.projetJEE.dao.DAOFactory;
import com.projetJEE.dao.utilisateur.UtilisateurDao;
import com.projetJEE.metier.CreationCompteForm;

/**
 * Servlet implementation class creationCompte
 */
@WebServlet("/creationCompte")
public class CreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private static final String VUE = "/WEB-INF/creerCompte.jsp";
	private static final String ATT_FORM = "form";
    public static final String ATT_UTILISATEUR = "utilisateur";
    
    public static final String CONF_DAO_FACTORY = "daofactory";
    private UtilisateurDao utilisateurDao;
    
    public CreationCompte() {
        super();
    }
    
    public void init() throws ServletException {
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher( VUE ).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CreationCompteForm form = new CreationCompteForm(this.utilisateurDao);
		Utilisateur utilisateur = form.creationCompteUtilisateur(request);
		
        request.setAttribute(ATT_UTILISATEUR, utilisateur );
		request.setAttribute(ATT_FORM, form);
		
		this.getServletContext().getRequestDispatcher( VUE ).forward(request, response);
	}

}
