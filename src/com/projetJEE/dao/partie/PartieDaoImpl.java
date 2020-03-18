package com.projetJEE.dao.partie;

import static com.projetJEE.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.projetJEE.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import com.google.common.base.Functions;
import com.google.common.collect.Ordering;
import com.projetJEE.beans.Partie;
import com.projetJEE.dao.DAOException;
import com.projetJEE.dao.DAOFactory;


public class PartieDaoImpl implements PartieDao {

	private DAOFactory daoFactory;
	
	private static final String CHAMP_ID = "id";
	private static final String CHAMP_DATE_DEBUT = "date_debut";
	private static final String CHAMP_DATE_FIN = "date_fin";
	private static final String CHAMP_VAINQUEUR = "vainqueur";
	
	private static final String SQL_INSERT = "INSERT INTO Partie (date_debut, date_fin, vainqueur) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ALL_PARTIE = "SELECT * FROM Partie ORDER BY id";
    
    private static final String SQL_CLASSEMENT_JOURNALIER = "SELECT ANY_VALUE(vainqueur), COUNT(vainqueur) as count FROM Partie WHERE DAY(date_debut) = ? GROUP BY vainqueur";
    private static final String SQL_CLASSEMENT_MENSUEL = "SELECT ANY_VALUE(vainqueur), COUNT(vainqueur) as count FROM Partie WHERE MONTH(date_debut) = ? GROUP BY vainqueur";
    private static final String SQL_CLASSEMENT_ANNUEL = "SELECT ANY_VALUE(vainqueur), COUNT(vainqueur) as count FROM Partie WHERE YEAR(date_debut) = ? GROUP BY vainqueur";

	public PartieDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	@Override
	public void creer(Partie partie) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, partie.getDateDebut(),partie.getDateFin(), partie.getVainqueur());
            int statut = preparedStatement.executeUpdate();
            
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de la partie, aucune ligne ajoutée dans la table." );
            }
            
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                partie.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Échec de la création de la partie en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
		
	}
	
	@Override
    public List<Partie> lister() throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Partie> parties = new ArrayList<Partie>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement( SQL_SELECT_ALL_PARTIE );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
            	parties.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return parties;
    }
	
	
	@Override
	public Map<String, Integer> classer(String periode, String triePar) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		Map<String, Integer> classement = new HashMap<String, Integer>();
		
		DateTime date = DateTime.now();
		String jour = Integer.toString(date.getDayOfMonth());
		String mois = Integer.toString(date.getMonthOfYear());
		String annee = Integer.toString(date.getYear());

        try {
            connexion = daoFactory.getConnection();
            
            switch(periode) {
            case "journalier": preparedStatement = initialisationRequetePreparee( connexion, SQL_CLASSEMENT_JOURNALIER, false, jour); break;
            case "mensuel": preparedStatement = initialisationRequetePreparee( connexion, SQL_CLASSEMENT_MENSUEL, false, mois); break;
            case "annuel": preparedStatement = initialisationRequetePreparee( connexion, SQL_CLASSEMENT_ANNUEL, false, annee); break;
            }
            resultSet = preparedStatement.executeQuery();
            
            while( resultSet.next() ) {
            	classement.put(resultSet.getString(1), resultSet.getInt(2));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
        // On trie le map par valeur dans l'ordre croissant si on veut classer par défaite 
        // sinon on trie dans l'ordre decroissant
        if(triePar.equals("defaite")) {
	        classement = classement.entrySet()
	        	.stream()
	        	.sorted(Map.Entry.comparingByValue())
	        	.collect(Collectors.toMap(
	        			Map.Entry::getKey,
	        			Map.Entry::getValue,
	        			(e1,e2) -> e1,
	        			LinkedHashMap::new));
        } else {
        	 classement = classement.entrySet()
 	        	.stream()
 	        	.sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
 	        	.collect(Collectors.toMap(
 	        			Map.Entry::getKey,
 	        			Map.Entry::getValue,
 	        			(e1,e2) -> e1,
 	        			LinkedHashMap::new));
        }

		return classement;
	}
	
	
	private static Partie map( ResultSet resultSet ) throws SQLException {
        Partie partie = new Partie();
        
        partie.setId( resultSet.getLong( CHAMP_ID ));
        partie.setDateDebut( resultSet.getTimestamp( CHAMP_DATE_DEBUT ));
        partie.setDateFin( resultSet.getTimestamp( CHAMP_DATE_FIN ));
        partie.setVainqueur( resultSet.getString( CHAMP_VAINQUEUR ));
        
        return partie;
    }

}
