package com.projetJEE.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Deconnexion
 */
@WebServlet("/deconnexion")
public class Deconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String ACCUEIL= "/SiteWebBomberman/accueil";

    private static final String ATT_TRIE_PAR = "triePar";
    private static final String ATT_PERIODE = "periode";
    
    public Deconnexion() {
        super();
    }


    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String periode = (String) session.getAttribute( ATT_PERIODE );
		String triePar = (String) session.getAttribute( ATT_TRIE_PAR );
		
        session.invalidate();
        
        response.sendRedirect( ACCUEIL + "?periode=" + periode + "&triePar=" + triePar );
    }

}
