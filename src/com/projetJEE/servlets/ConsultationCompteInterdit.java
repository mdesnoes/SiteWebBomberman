package com.projetJEE.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/consultationCompteInterdit")
public class ConsultationCompteInterdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static final String VUE = "/WEB-INF/consulterCompteInterdit.jsp";

    public ConsultationCompteInterdit() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher( VUE ).forward(request, response);
	}
	

}
