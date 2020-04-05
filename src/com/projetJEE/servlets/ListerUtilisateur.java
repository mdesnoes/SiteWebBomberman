package com.projetJEE.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.projetJEE.beans.Utilisateur;
import com.projetJEE.dao.DAOFactory;
import com.projetJEE.dao.utilisateur.UtilisateurDao;


@WebServlet("/listerUtilisateur")
public class ListerUtilisateur extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    private static final String VUE = "/WEB-INF/listeUtilisateur.jsp";
    public static final String ATT_SESSION_LISTE_UTILISATEUR = "listeUtilisateurs";

    public static final String CONF_DAO_FACTORY = "daofactory";
    private UtilisateurDao utilisateurDao;
    
    public ListerUtilisateur() {
        super();
    }
    
    public void init() throws ServletException {
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<Utilisateur> listeUtilisateurs = this.utilisateurDao.lister();
        Map<Long, Utilisateur> mapUtilisateurs = new LinkedHashMap<Long, Utilisateur>();
        for ( Utilisateur utilisateur : listeUtilisateurs ) {
        	mapUtilisateurs.put( utilisateur.getId(), utilisateur );
        }
        session.setAttribute( ATT_SESSION_LISTE_UTILISATEUR, mapUtilisateurs );
        
		this.getServletContext().getRequestDispatcher( VUE ).forward(request, response );
	}

}
