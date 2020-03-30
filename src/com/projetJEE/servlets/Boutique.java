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


@WebServlet("/boutique")
public class Boutique extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    private static final String VUE = "/WEB-INF/boutique.jsp";
    
    public Boutique() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        
		this.getServletContext().getRequestDispatcher( VUE ).forward(request, response );
	}

}
