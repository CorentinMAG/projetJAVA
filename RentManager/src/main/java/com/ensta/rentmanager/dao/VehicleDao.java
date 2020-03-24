package com.ensta.rentmanager.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.persistence.ConnectionManager;

public class VehicleDao {
	
	private static VehicleDao instance = null;
	private static VehicleDao instanceTest = null;
	
	private VehicleDao() {}
	private boolean test;
	private VehicleDao(boolean test) {
		this.test=test;
	}
	public static VehicleDao getInstance(boolean test) {
		if(test) {
			if(instanceTest==null) {
				instanceTest=new VehicleDao(true);
			}
			
		}else {
			if(instance==null) {
				instance=new VehicleDao(false);
			}
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele,nb_places) VALUES(?,?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur,modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur,modele, nb_places FROM Vehicle;";
	private static final String UPDATE_VEHICLE_MODELE_QUERY="UPDATE Vehicle SET modele=? WHERE id=?;";
	private static final String UPDATE_VEHICLE_CONSTRUCTEUR_QUERY="UPDATE Vehicle SET constructeur=? WHERE id=?;";
	private static final String UPDATE_VEHICLE_NBPLACE_QUERY="UPDATE Vehicle SET nb_places=? WHERE id=?;";
	
	public long create(Vehicle vehicle) throws DaoException {
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(CREATE_VEHICLE_QUERY);)
			{
			statement.setString(1,vehicle.getConstructeur());
			statement.setString(2, vehicle.getModele());
			statement.setInt(3,vehicle.getNb_place());
			
			long result = statement.executeUpdate();
			return result;
			
			}catch (SQLException e) {
				throw new DaoException("Erreur lors de la création :"+e.getMessage());
			}
	}
	public Vehicle show_vehicle(int num) throws DaoException{
		Vehicle vehicle=new Vehicle();
		try(Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(FIND_VEHICLE_QUERY);)
		{
			statement.setInt(1, num);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				vehicle.setId(num);
				vehicle.setModele(resultSet.getString(3));
				vehicle.setConstructeur(resultSet.getString(2));
				vehicle.setNb_place(resultSet.getInt(4));
			}else {
				System.out.println("Le véhicule n'existe pas");
			}
			
			
		}catch(SQLException e) {
			throw new DaoException("Impossible de récupérer le véhicule"+e.getMessage());
		}
		return vehicle;
	}

	public long delete(int id) throws DaoException {
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(DELETE_VEHICLE_QUERY);)
			{
			statement.setInt(1,id);
			
			long result = statement.executeUpdate();
			return result;
			
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Erreur lors de la suppression :"+e.getMessage());
			}
	}

	public Vehicle findById(int id) throws DaoException {
		Vehicle vehicle=new Vehicle();
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(FIND_VEHICLE_QUERY);)
			{
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				vehicle.setId(resultSet.getInt(1));
				vehicle.setConstructeur(resultSet.getString(2));
				vehicle.setModele(resultSet.getString(3));
				vehicle.setNb_place(resultSet.getInt(4));
			}else {
				System.out.println("Le véhicule n'existe pas");
			}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Erreur lors de la suppression :"+e.getMessage());
			}
		//regarder si optional.empty() ou non
		return vehicle;
	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> resultList = new ArrayList<Vehicle>();
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(FIND_VEHICLES_QUERY);)
			{
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Vehicle vehicle=new Vehicle(resultSet.getInt(1),
										resultSet.getString(3),
										resultSet.getString(2),
										resultSet.getInt(4));
				resultList.add(vehicle);
			}
			return resultList;
			
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Erreur lors de l'affichage des véhicules :"+e.getMessage());
			}
	}
	public void printChoix() {
		System.out.println("1 : Ajouter un véhicule");
		System.out.println("2 : Supprimer un véhicule");
		System.out.println("3 : Lister les véhicules");
		System.out.println("4 : Modifier les données d'un véhicule");
		System.out.println("5 : Quitter le programme");
	}
	public void printUpdate(int id) {
		System.out.println("-----Véhicule n°"+id);
		System.out.println("1 : Modifier le constructeur de véhicule");
		System.out.println("2 : Modifier le modèle du véhicule");
		System.out.println("3 : Modifier le nombre de place du véhicule");
		System.out.println("4 : revenir au menu principal");
		System.out.print("Entrer le(s) numéro(s) : ");
		
	}
public long updateNb_place(int new_nbplace, int id_modif) throws DaoException {
	// TODO Auto-generated method stub
	try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
			PreparedStatement statement=conn.prepareStatement(UPDATE_VEHICLE_NBPLACE_QUERY);)
		{
		statement.setInt(1, new_nbplace);
		statement.setInt(2, id_modif);
		
		long result=statement.executeUpdate();
		return result;
		
		}catch(SQLException e) {
			throw new DaoException("Erreur lors de l'update :"+e.getMessage());
		}
}
public long updateConstructeur(String new_constructeur, int id_modif) throws DaoException {
	// TODO Auto-generated method stub
	try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
			PreparedStatement statement=conn.prepareStatement(UPDATE_VEHICLE_CONSTRUCTEUR_QUERY);)
		{
		statement.setString(1, new_constructeur);
		statement.setInt(2, id_modif);
		
		long result=statement.executeUpdate();
		return result;
		
		}catch(SQLException e) {
			throw new DaoException("Erreur lors de l'update :"+e.getMessage());
		}
	
}
public long updateModele(String modele, int id_modif) throws DaoException {
	// TODO Auto-generated method stub
	try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
			PreparedStatement statement=conn.prepareStatement(UPDATE_VEHICLE_MODELE_QUERY);)
		{
		statement.setString(1, modele);
		statement.setInt(2, id_modif);
		
		long result=statement.executeUpdate();
		return result;
		
		}catch(SQLException e) {
			throw new DaoException("Erreur lors de l'update :"+e.getMessage());
		}
	
}
	

}
