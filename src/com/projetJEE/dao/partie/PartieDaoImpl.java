package com.projetJEE.dao.partie;

import static com.projetJEE.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.projetJEE.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.projetJEE.beans.Partie;
import com.projetJEE.dao.DAOException;
import com.projetJEE.dao.DAOFactory;


public class PartieDaoImpl implements PartieDao {

	private DAOFactory daoFactory;
	
	private static final String CHAMP_ID = "id";
	private static final String CHAMP_DATE_DEBUT = "date_debut";
	private static final String CHAMP_DATE_FIN = "date_fin";
	private static final String CHAMP_VAINQUEUR = "vainqueur";
	
	private static final String JOURNALIER = "journalier";
	private static final String MENSUEL = "mensuel";
	private static final String ANNUEL = "annuel";
	
	private static final String SQL_INSERT = "INSERT INTO Partie (date_debut, date_fin, vainqueur) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ALL_PARTIE = "SELECT * FROM Partie ORDER BY id DESC";
    private static final String SQL_CLASSER_PAR_VICTOIRE_JOURNALIER = "SELECT ANY_VALUE(vainqueur), COUNT(vainqueur) as count FROM Partie WHERE DAY(date_debut) = ? GROUP BY vainqueur ORDER BY count DESC";
    private static final String SQL_CLASSER_PAR_VICTOIRE_MENSUEL = "SELECT ANY_VALUE(vainqueur), COUNT(vainqueur) as count FROM Partie WHERE MONTH(date_debut) = ? GROUP BY vainqueur ORDER BY count DESC";
    private static final String SQL_CLASSER_PAR_VICTOIRE_ANNUEL = "SELECT ANY_VALUE(vainqueur), COUNT(vainqueur) as count FROM Partie WHERE YEAR(date_debut) = ? GROUP BY vainqueur ORDER BY count DESC";
    private static final String SQL_NB_PARTIES_PAR_JOURS = "SELECT COUNT(*) FROM Partie WHERE DAY(date_debut) = ?";
    private static final String SQL_NB_PARTIES_PAR_MOIS = "SELECT COUNT(*) FROM Partie WHERE MONTH(date_debut) = ?";
    private static final String SQL_NB_PARTIES_PAR_ANS = "SELECT COUNT(*) FROM Partie WHERE YEAR(date_debut) = ?";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM Partie WHERE id = ?";

    
    /* Jour, mois et année courante */
    private String day;
    private String month;
    private String year;
    
	public PartieDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
        
        DateTime date = DateTime.now();
		this.day = Integer.toString(date.getDayOfMonth());
		this.month = Integer.toString(date.getMonthOfYear());
		this.year = Integer.toString(date.getYear());
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
	public List<Partie> rechercher( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Partie> parties = new ArrayList<Partie>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
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
	public void supprimerParId( String id ) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, id );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression de la partie, aucune ligne supprimée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
	}
	
	

	@Override
	public Map<String, Float> classerParVictoire(String periode) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Map<String, Float> mapClassement = new LinkedHashMap<String, Float>();
		
		try {
            connexion = daoFactory.getConnection();
            switch(periode) {
            	case JOURNALIER: preparedStatement = initialisationRequetePreparee( connexion, SQL_CLASSER_PAR_VICTOIRE_JOURNALIER, false, this.day); break;
            	case MENSUEL: preparedStatement = initialisationRequetePreparee( connexion, SQL_CLASSER_PAR_VICTOIRE_MENSUEL, false, this.month); break;
            	case ANNUEL: preparedStatement = initialisationRequetePreparee( connexion, SQL_CLASSER_PAR_VICTOIRE_ANNUEL, false, this.year); break;
            }
            resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
            	mapClassement.put(resultSet.getString(1), resultSet.getFloat(2));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
		
		return mapClassement;
	}

	
	@Override
	public Map<String, Float> classerParDefaite(String periode) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Map<String, Float> mapClassement = this.classerParVictoire(periode);
		
		int nbParties = 0;
		try {
            connexion = daoFactory.getConnection();
            switch(periode) {
            	case JOURNALIER: preparedStatement = initialisationRequetePreparee( connexion, SQL_NB_PARTIES_PAR_JOURS, false, this.day); break;
            	case MENSUEL: preparedStatement = initialisationRequetePreparee( connexion, SQL_NB_PARTIES_PAR_MOIS, false, this.month); break;
            	case ANNUEL: preparedStatement = initialisationRequetePreparee( connexion, SQL_NB_PARTIES_PAR_ANS, false, this.year); break;
            }
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            nbParties = resultSet.getInt(1);
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
		
		// On soustrait au nombre de victoire, le nombre de partie joué (qui sont donc des defaites)
		for(Map.Entry<String, Float> entry : mapClassement.entrySet()) {
			entry.setValue( nbParties - entry.getValue() );
		}
		
		return mapClassement;
	}

	
	@Override
	public Map<String, Float> classerParRatio(String periode) throws DAOException {
		Map<String, Float> mapClassementVictoire = this.classerParVictoire(periode);
		Map<String, Float> mapClassementDefaite = this.classerParDefaite(periode);

		Map<String, Float> mapClassement = new LinkedHashMap<String, Float>();
		
		for(Map.Entry<String, Float> entry : mapClassementVictoire.entrySet()) {
			String joueur = entry.getKey();
			float nbVictoire = entry.getValue();
			float nbDefaite = mapClassementDefaite.get(joueur);
			
			mapClassement.put(joueur, (nbVictoire / nbDefaite));
		}
		
		return mapClassement;
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
