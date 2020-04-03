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

import com.projetJEE.beans.Objet;
import com.projetJEE.beans.Utilisateur;
import com.projetJEE.dao.DAOFactory;
import com.projetJEE.dao.boutique.BoutiqueDao;


@WebServlet("/boutique")
public class Boutique extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    private static final String VUE = "/WEB-INF/boutique.jsp";
    public static final String ATT_SESSION_LISTE_OBJETS = "listeObjets";

    public static final String CONF_DAO_FACTORY = "daofactory";
    private BoutiqueDao boutiqueDao;
    
    public Boutique() {
        super();
    }
    
    public void init() throws ServletException {
        this.boutiqueDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getBoutiqueDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

        List<Objet> listeObjets = this.boutiqueDao.lister();
        Map<Long, Objet> mapObjets = new LinkedHashMap<Long, Objet>();
        for ( Objet objet : listeObjets ) {
        	mapObjets.put( objet.getId(), objet );
        }
        session.setAttribute( ATT_SESSION_LISTE_OBJETS, mapObjets );
        
		this.getServletContext().getRequestDispatcher( VUE ).forward(request, response );
	}

}
