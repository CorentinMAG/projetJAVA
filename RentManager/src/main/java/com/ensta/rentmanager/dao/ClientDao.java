package com.ensta.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.persistence.ConnectionManager;

public class ClientDao {
	
	private static ClientDao instance=null;
	private static ClientDao instanceTest=null;
	
	private ClientDao() {}
	private boolean test;
	private ClientDao(boolean test) {
		this.test=test;
	}
	public static ClientDao getInstance(boolean test) {
		if(test) {
			if(instanceTest == null) {
				instanceTest = new ClientDao(true);
			}
			}else {
				if(instance==null) {
					instance=new ClientDao(false);
				}
			}
			return instance;
		}
		
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String UPDATE_CLIENT_NAME_QUERY = "UPDATE Client SET nom=? WHERE id=?;";
	private static final String UPDATE_CLIENT_PRENOM_QUERY = "UPDATE Client SET prenom=? WHERE id=?;";
	private static final String UPDATE_CLIENT_EMAIL_QUERY = "UPDATE Client SET email=? WHERE id=?;";
	private static final String UPDATE_CLIENT_BIRTH_QUERY = "UPDATE Client SET naissance=? WHERE id=?;";
	
	public long create(Client client) throws DaoException {
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
			PreparedStatement statement=conn.prepareStatement(CREATE_CLIENT_QUERY);)
		{
			statement.setString(1,client.getNom());
			statement.setString(2, client.getPrenom());
			statement.setString(3, client.getEmail());
			statement.setDate(4, client.getNaissance());
			
			long result = statement.executeUpdate();
			return result;
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la création :"+e.getMessage());
		}
		
	}
	public long updateName(String val,int id) throws DaoException{
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(UPDATE_CLIENT_NAME_QUERY);)
			{
			statement.setString(1, val);
			statement.setInt(2, id);
			
			long result=statement.executeUpdate();
			return result;
			
			}catch(SQLException e) {
				throw new DaoException("Erreur lors de l'update :"+e.getMessage());
			}
	}
	public long updatePrenom(String val,int id) throws DaoException{
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(UPDATE_CLIENT_PRENOM_QUERY);)
			{
			statement.setString(1, val);
			statement.setInt(2, id);
			
			long result=statement.executeUpdate();
			return result;
			
			}catch(SQLException e) {
				throw new DaoException("Erreur lors de l'update :"+e.getMessage());
			}
	}
	public long updateMail(String val,int id) throws DaoException{
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(UPDATE_CLIENT_EMAIL_QUERY);)
			{
			statement.setString(1, val);
			statement.setInt(2, id);
			
			long result=statement.executeUpdate();
			return result;
			
			}catch(SQLException e) {
				throw new DaoException("Erreur lors de l'update :"+e.getMessage());
			}
	}
	public long updateBirth(String val,int id) throws DaoException{
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(UPDATE_CLIENT_BIRTH_QUERY);)
			{
			statement.setString(1, val);
			statement.setInt(2, id);
			
			long result=statement.executeUpdate();
			return result;
			
			}catch(SQLException e) {
				throw new DaoException("Erreur lors de l'update :"+e.getMessage());
			}
	}
	public Client show_client(int num) throws DaoException{
		Client client=new Client();
		try(Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(FIND_CLIENT_QUERY);)
		{
			statement.setInt(1, num);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				client.setId(num);
				client.setNom(resultSet.getString(1));
				client.setPrenom(resultSet.getString(2));
				client.setEmail(resultSet.getString(3));
				client.setNaissance(resultSet.getDate(4));
			}else {
				System.out.println("Le client n'existe pas");
			}
			
			
		}catch(SQLException e) {
			throw new DaoException("Impossible de récupérer le client"+e.getMessage());
		}
		return client;
	}
	
	public long delete(int num) throws DaoException {
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(DELETE_CLIENT_QUERY);)
			{
				statement.setInt(1,num);
				
				long result = statement.executeUpdate();
				return result;
			} catch (SQLException e) {
				throw new DaoException("Erreur lors de la création :"+e.getMessage());
			}
	}

	public Optional<Client> findById(long id) {
		Client c=new Client();
		Optional<Client> optClient = Optional.of(c);
		try (Connection conn=ConnectionManager.getConnection();){
			
		}catch(SQLException e) {
			return Optional.empty();
		}
		return optClient;
	}

	public List<Client> findAll() throws DaoException {
		List<Client> resultList =new ArrayList<Client>();
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(FIND_CLIENTS_QUERY);)
			{
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Client client=new Client(resultSet.getInt(1),
										resultSet.getString(2),
										resultSet.getString(3),
										resultSet.getString(4),
										resultSet.getDate(5));
				resultList.add(client);
			}
			return resultList;
				
			}catch(SQLException e) {
				throw new DaoException("Erreur lors de la création: "+e.getMessage());			
			}
	}
}

