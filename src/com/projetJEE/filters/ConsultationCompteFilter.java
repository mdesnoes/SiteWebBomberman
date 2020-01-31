package com.projetJEE.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet Filter implementation class ConsultationCompteFilter
 */
@WebFilter("/consultationCompte")
public class ConsultationCompteFilter implements Filter {

  	public static final String ACCES_MON_COMPTE_INTERDIT = "/consultationCompteInterdit";
  	public static final String ATT_SESSION_USER = "sessionUtilisateur";

  	
	public void destroy() {
		
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession();

        
        // On redirige vers "/consultationCompteINterdit" si aucun utilisateur n'est connecté
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            response.sendRedirect( request.getContextPath() + ACCES_MON_COMPTE_INTERDIT );
        } else {
            chain.doFilter( request, response );
        }
	}


}
