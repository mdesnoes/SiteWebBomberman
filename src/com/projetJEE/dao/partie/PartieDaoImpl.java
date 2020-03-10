package com.projetJEE.dao.partie;

import static com.projetJEE.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.projetJEE.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Partie> parties = new ArrayList<Partie>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT_ALL_PARTIE );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
            	parties.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return parties;
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
