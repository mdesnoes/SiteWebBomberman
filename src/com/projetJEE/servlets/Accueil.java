package com.projetJEE.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import com.projetJEE.beans.Partie;
import com.projetJEE.beans.Utilisateur;
import com.projetJEE.dao.DAOFactory;
import com.projetJEE.dao.partie.PartieDao;
import com.projetJEE.dao.utilisateur.UtilisateurDao;
import com.projetJEE.metier.ConnexionForm;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/accueil")
public class Accueil extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final static Logger logger = Logger.getLogger(Accueil.class);
	
    private static final String VUE = "/WEB-INF/accueil.jsp";
    
	private static final String ATT_USER = "utilisateur";
	private static final String ATT_FORM = "form";
	private static final String ATT_SESSION_USER = "sessionUtilisateur";
	private static final String ATT_INTERVALLE_CONNEXIONS = "intervalleConnexions";
    private static final String ATT_LISTE_PARTIES = "listeParties";
    private static final String ATT_MAP_CLASSEMENT = "mapClassement";
    private static final String ATT_TRIE_PAR = "triePar";
    private static final String ATT_PERIODE = "periode";

    private static final String VICTOIRE = "victoire";
    private static final String DEFAITE = "defaite";
    private static final String RATIO = "ratio";
    private static final String JOURNALIER = "journalier";

    private static final String COOKIE_DERNIERE_CONNEXION = "derniereConnexion";
    private static final String  CHAMP_MEMOIRE = "memoire";
    private static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 365;  // 1 an
    private static final String PATTERN_DATE_TIME = "dd/MM/yyyy HH:mm:ss";
    
    public static final String CONF_DAO_FACTORY = "daofactory";
    private UtilisateurDao utilisateurDao;
    private PartieDao partieDao;
    
    public Accueil() {
        super();
    }
    
    public void init() throws ServletException {
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
        this.partieDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPartieDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String derniereConnexion = getCookieValue( request, COOKIE_DERNIERE_CONNEXION );
        if ( derniereConnexion != null ) {
            DateTime dtCourante = new DateTime();
            DateTimeFormatter formatter = DateTimeFormat.forPattern(PATTERN_DATE_TIME);
            DateTime dtDerniereConnexion = formatter.parseDateTime( derniereConnexion );
            Period periode = new Period( dtDerniereConnexion, dtCourante );
            PeriodFormatter periodFormatter = new PeriodFormatterBuilder()
                    .appendYears().appendSuffix( " an ", " ans " )
                    .appendMonths().appendSuffix( " mois " )
                    .appendDays().appendSuffix( " jour ", " jours " )
                    .appendHours().appendSuffix( " heure ", " heures " )
                    .appendMinutes().appendSuffix( " minute ", " minutes " )
                    .appendSeparator( "et " )
                    .appendSeconds().appendSuffix( " seconde", " secondes" )
                    .toFormatter();
            String intervalleConnexions = periodFormatter.print( periode );

            request.setAttribute( ATT_INTERVALLE_CONNEXIONS, intervalleConnexions );
        }

        
        HttpSession session = request.getSession();
        gestionHistorique(session);
        
        String periode = (String) request.getParameter( ATT_PERIODE );
        String triePar = (String) request.getParameter( ATT_TRIE_PAR );
        gestionClassement(session, periode, triePar);
 
		this.getServletContext().getRequestDispatcher( VUE ).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		Verification de la connexion
		ConnexionForm form = new ConnexionForm(this.utilisateurDao);
        Utilisateur utilisateur = form.connecterUtilisateur( request );

        HttpSession session = request.getSession();
        if ( form.getErreurs().isEmpty() ) {
            session.setAttribute( ATT_SESSION_USER, utilisateur );
            
			logger.info("Connexion réussie !");
        } else {
            session.setAttribute( ATT_SESSION_USER, null );
            
            logger.info("Connexion echouée !");
        }
        
        
//        Gestion du cookie du temps de connexion
        if ( request.getParameter( CHAMP_MEMOIRE ) != null ) {
            DateTime dt = new DateTime();
            DateTimeFormatter formatter = DateTimeFormat.forPattern(PATTERN_DATE_TIME);
            String dateDerniereConnexion = dt.toString( formatter );
            setCookie( response, COOKIE_DERNIERE_CONNEXION, dateDerniereConnexion, COOKIE_MAX_AGE );
        } else {
            setCookie( response, COOKIE_DERNIERE_CONNEXION, "", 0 );
        }

        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );

		this.getServletContext().getRequestDispatcher( VUE ).forward(request, response);
	}
	
	private void gestionHistorique(HttpSession session) {
        List<Partie> listeParties = this.partieDao.lister();
        Map<Long, Partie> mapParties = new HashMap<Long, Partie>();
        for ( Partie partie : listeParties ) {
        	mapParties.put( partie.getId(), partie );
        }
        session.setAttribute( ATT_LISTE_PARTIES, mapParties );
	}
	
	private void gestionClassement(HttpSession session, String periode, String triePar) {
        Map<String, Float> mapClassement = null;
        
        if(periode == null && triePar == null) {
        	periode = JOURNALIER;
        	triePar = VICTOIRE;
        }
        
        switch(triePar) {
		    case VICTOIRE: mapClassement = this.partieDao.classerParVictoire(periode); break;
		    case DEFAITE: mapClassement = this.partieDao.classerParDefaite(periode); break;
		    case RATIO: mapClassement = this.partieDao.classerParRatio(periode); break;
        }
        session.setAttribute( ATT_MAP_CLASSEMENT, mapClassement );
        session.setAttribute( ATT_TRIE_PAR, triePar );
        session.setAttribute( ATT_PERIODE, periode);
	}
	
	
	private static void setCookie( HttpServletResponse response, String nom, String valeur, int maxAge ) {
	    Cookie cookie = new Cookie( nom, valeur );
	    cookie.setMaxAge( maxAge );
	    response.addCookie( cookie );
	}
	
	
	private static String getCookieValue( HttpServletRequest request, String nom ) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for ( Cookie cookie : cookies ) {
                if (cookie != null && nom.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
