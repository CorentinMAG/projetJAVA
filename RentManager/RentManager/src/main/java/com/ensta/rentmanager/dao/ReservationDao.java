package com.ensta.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.persistence.ConnectionManager;

public class ReservationDao {

	private static ReservationDao instance=null;
	private static ReservationDao instanceTest=null;
	
	private ReservationDao() {}
	private boolean test;
	private ReservationDao(boolean test) {
		this.test=test;
	}
	public static ReservationDao getInstance(boolean test) {
		if(test) {
			if(instanceTest == null) {
				instanceTest = new ReservationDao(true);
			}
			}else {
				if(instance==null) {
					instance=new ReservationDao(false);
				}
			}
			return instance;
		}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id ,vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id,debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String UPDATE_DEBUT_QUERY="UPDATE Reservation SET debut=? WHERE id=?;";
	private static final String UPDATE_FIN_QUERY="UPDATE Reservation SET fin=? WHERE id=?;";
	private static final String FIND_BY_ID="SELECT * FROM Reservation WHERE id=?;";
	public long create(Reservation reservation) throws DaoException {
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(CREATE_RESERVATION_QUERY);)
			{
			statement.setInt(1,reservation.getClient_id());
			statement.setInt(2, reservation.getVehicle_id());
			statement.setDate(3,reservation.getDebut());
			statement.setDate(4,reservation.getFin());
			
			long result = statement.executeUpdate();
			return result;
			
			}catch (SQLException e) {
				throw new DaoException("Erreur lors de la création :"+e.getMessage());
			}
	}
	
	public Reservation getresabyid(int id) throws DaoException {
		Reservation reservation=new Reservation();
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(FIND_BY_ID);)
			{
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {;
				reservation.setId(id);
				reservation.setClient_id(resultSet.getInt(1));
				reservation.setVehicle_id(resultSet.getInt(2));
				reservation.setDebut(resultSet.getDate(4));
				reservation.setFin(resultSet.getDate(5));
			}else {
				System.out.println("la réservation n'existe pas");
			}
			
			}catch(SQLException e) {
				throw new DaoException("Erreur lors de la création :"+e.getMessage());
			}
		return reservation;
		
	}
	public long updateD(String debut,int id) throws DaoException {
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(UPDATE_DEBUT_QUERY);)
			{
			Date _debut=Date.valueOf(debut);
			statement.setDate(1,_debut);
			statement.setInt(2, id);
			long result=statement.executeUpdate();
			return result;
			}catch (SQLException e) {
				throw new DaoException("Erreur lors de la création :"+e.getMessage());
			}
	}
	
	public long updateF(String fin,int id) throws DaoException {
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(UPDATE_FIN_QUERY);)
			{
			Date _fin=Date.valueOf(fin);
			statement.setDate(1,_fin);
			statement.setInt(2, id);
			long result=statement.executeUpdate();
			return result;
			}catch (SQLException e) {
				throw new DaoException("Erreur lors de la création :"+e.getMessage());
			}
	}
	
	public long delete(int id) throws DaoException {
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(DELETE_RESERVATION_QUERY);)
			{
			statement.setInt(1,id);
			
			long result = statement.executeUpdate();
			return result;
			
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Erreur lors de la suppression :"+e.getMessage());
			}
		
	}

	
	public List<Reservation> findResaByClientId(int clientId) throws DaoException {
		List<Reservation> resultList = new ArrayList<Reservation>();
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);)
			{
			statement.setInt(1,clientId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Reservation reservation=new Reservation(resultSet.getInt(1),
										clientId,
										resultSet.getInt(2),
										resultSet.getDate(3),
										resultSet.getDate(4));
				resultList.add(reservation);
				
			}
			return resultList;
			
			}catch (SQLException e) {
				throw new DaoException("Erreur lors de l'affichage des véhicules :"+e.getMessage());
			}
	}
	
	public List<Reservation> findResaByVehicleId(int vehicleId) throws DaoException {
		List<Reservation> resultList = new ArrayList<Reservation>();
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);)
			{
			statement.setInt(1,vehicleId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Reservation reservation=new Reservation(resultSet.getInt(1),
										resultSet.getInt(2),
										vehicleId,
										resultSet.getDate(3),
										resultSet.getDate(4));
				resultList.add(reservation);
				
			}
			return resultList;
			
			}catch (SQLException e) {
				throw new DaoException("Erreur lors de l'affichage des véhicules :"+e.getMessage());
			}
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> resultList = new ArrayList<Reservation>();
		try (Connection conn= test ? ConnectionManager.getConnectionForTest():ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(FIND_RESERVATIONS_QUERY);)
			{
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Reservation reservation=new Reservation(resultSet.getInt(1),
										resultSet.getInt(2),
										resultSet.getInt(3),
										resultSet.getDate(4),
										resultSet.getDate(5));
				resultList.add(reservation);
				
			}
			return resultList;
			
			}catch (SQLException e) {
				throw new DaoException("Erreur lors de l'affichage des véhicules :"+e.getMessage());
			}
	}
}
