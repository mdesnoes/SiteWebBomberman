package com.projetJEE.dao.boutique;

import static com.projetJEE.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.projetJEE.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projetJEE.beans.Objet;
import com.projetJEE.beans.Utilisateur;
import com.projetJEE.dao.DAOException;
import com.projetJEE.dao.DAOFactory;

public class BoutiqueDaoImpl implements BoutiqueDao{
	
	private DAOFactory daoFactory;
	
	private static final String CHAMP_ID = "id";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_TYPE = "type";
	private static final String CHAMP_PRIX = "prix";
	private static final String CHAMP_DESCRIPTION = "description";
	private static final String CHAMP_IMAGE = "image";
	
	private static final String SQL_INSERT = "INSERT INTO Boutique (nom, type, prix, description, image)"
			+ " VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_PAR_ID = "UPDATE Boutique SET nom = ?, type = ?, prix = ?, description = ?, image = ?"
			+ " WHERE id = ? ";
	private static final String SQL_DELETE_PAR_ID = "DELETE FROM Boutique WHERE id = ?";
	private static final String SQL_SELECT_ALL_BOUTIQUE = "SELECT * FROM Boutique ORDER BY id";
	
	public BoutiqueDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

	@Override
	public void creer(Objet objet) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, objet.getNom(),
            		objet.getType(), objet.getPrix(), objet.getDescription(), objet.getImage());
            int statut = preparedStatement.executeUpdate();
            
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de l'objet, aucune ligne ajoutée dans la table." );
            }
            
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                objet.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Échec de la création de l'objet en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
	}

	@Override
	public List<Objet> lister() throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Objet> objets = new ArrayList<Objet>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement( SQL_SELECT_ALL_BOUTIQUE );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                objets.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return objets;
	}

	@Override
	public Objet trouver(String sql, Object... objets) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Objet objet = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
            	objet = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return objet;
	}

	@Override
	public void modifier(Objet objet) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
		try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_PAR_ID, true, objet.getNom(),
            		objet.getType(), objet.getPrix(), objet.getDescription(), objet.getImage(), objet.getId() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la modification de l'objet, aucune ligne modifiée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
		
	}

	@Override
	public void supprimer(Objet objet) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, objet.getId() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression de l'objet, aucune ligne supprimée dans la table." );
            } else {
            	objet.setId( -1 );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
		
	}
	
	private static Objet map( ResultSet resultSet ) throws SQLException {
        Objet objet = new Objet();
        
        objet.setId( resultSet.getLong( CHAMP_ID ) );
        objet.setNom( resultSet.getString( CHAMP_NOM ) );
        objet.setType( resultSet.getString( CHAMP_TYPE ) );
        objet.setPrix( resultSet.getString( CHAMP_PRIX ) );
        objet.setDescription( resultSet.getString( CHAMP_DESCRIPTION ) );
        objet.setImage( resultSet.getString( CHAMP_IMAGE ) );
        
        return objet;
    }

}
