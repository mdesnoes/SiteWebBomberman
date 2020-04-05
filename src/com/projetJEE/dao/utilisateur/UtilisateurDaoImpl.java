package com.projetJEE.dao.utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projetJEE.beans.Utilisateur;
import com.projetJEE.dao.DAOException;
import com.projetJEE.dao.DAOFactory;

import static com.projetJEE.dao.DAOUtilitaire.*;



public class UtilisateurDaoImpl implements UtilisateurDao {

	private DAOFactory daoFactory;
	
	private static final String CHAMP_ID = "id";
	private static final String CHAMP_PSEUDO = "pseudo";
	private static final String CHAMP_PASSWORD = "password";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_DATE_NAISSANCE = "date_naissance";
	private static final String CHAMP_ADRESSE = "adresse";
	private static final String CHAMP_VILLE = "ville";
	private static final String CHAMP_CODE_POSTAL = "code_postal";
	private static final String CHAMP_DATE_INSCRIPTION = "date_inscription";

	
	private static final String SQL_INSERT = "INSERT INTO Utilisateur (pseudo, password, nom, prenom, email, date_naissance, adresse, ville, code_postal, date_inscription)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
	private static final String SQL_UPDATE_PAR_ID = "UPDATE Utilisateur SET pseudo = ?, password = ?, nom = ?, prenom = ?, email = ?, date_naissance = ?, adresse = ?,"
			+ " ville = ?, code_postal = ? WHERE id = ? ";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM Utilisateur WHERE id = ?";
    private static final String SQL_SELECT_ALL_UTILISATEUR = "SELECT * FROM Utilisateur ORDER BY id";


	
	
	public UtilisateurDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	
	@Override
	public void creer(Utilisateur utilisateur) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, utilisateur.getPseudo(), utilisateur.getPassword(), utilisateur.getNom(),
            		utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getDateNaissance(), utilisateur.getAdresse(), utilisateur.getVille(), utilisateur.getCodePostal());
            int statut = preparedStatement.executeUpdate();
            
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
            
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                utilisateur.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
		
	}
	
	@Override
    public List<Utilisateur> lister() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Utilisateur> clients = new ArrayList<Utilisateur>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement( SQL_SELECT_ALL_UTILISATEUR );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                clients.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return clients;
    }
	
	
	@Override
	public Utilisateur trouver( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Utilisateur utilisateur = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
            	utilisateur = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return utilisateur;
    }

	

	@Override
	public void modifier( Utilisateur utilisateur ) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
		try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_PAR_ID, true, utilisateur.getPseudo(), utilisateur.getPassword(),
            		utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getDateNaissance(), utilisateur.getAdresse(),
            		utilisateur.getVille(), utilisateur.getCodePostal(), utilisateur.getId() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la modification de l'utilisateur, aucune ligne modifiée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
	}
	

	@Override
	public void supprimer( Utilisateur utilisateur ) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, utilisateur.getId() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression de l'utilisateur, aucune ligne supprimée dans la table." );
            } else {
            	utilisateur.setId( -1 );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
	}
	
	
	
	
	private static Utilisateur map( ResultSet resultSet ) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        
        utilisateur.setId( resultSet.getLong( CHAMP_ID ) );
        utilisateur.setPseudo( resultSet.getString( CHAMP_PSEUDO ));
        utilisateur.setPassword( resultSet.getString( CHAMP_PASSWORD ));
        utilisateur.setNom( resultSet.getString( CHAMP_NOM ));
        utilisateur.setPrenom( resultSet.getString( CHAMP_PRENOM ));
        utilisateur.setEmail( resultSet.getString( CHAMP_EMAIL ) );
        utilisateur.setDateNaissance( resultSet.getDate( CHAMP_DATE_NAISSANCE ));
        utilisateur.setAdresse( resultSet.getString( CHAMP_ADRESSE ));
        utilisateur.setVille( resultSet.getString( CHAMP_VILLE ));
        utilisateur.setCodePostal( resultSet.getString( CHAMP_CODE_POSTAL ));
        utilisateur.setDateInscription( resultSet.getTimestamp( CHAMP_DATE_INSCRIPTION ) );
        
        return utilisateur;
    }
}
