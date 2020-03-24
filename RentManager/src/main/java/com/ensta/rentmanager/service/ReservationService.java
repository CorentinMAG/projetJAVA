package com.ensta.rentmanager.service;
import java.sql.Date;
import java.util.List;

import org.h2.api.Interval;

import com.ensta.rentmanager.dao.ReservationDao;
import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.model.Vehicle;

public class ReservationService {
	private static ReservationService instance=null;
	private ReservationService() {}
	
	public static ReservationService getInstance() {
		if (instance == null) {
			instance = new ReservationService();
		}
		
		return instance;
	}
	//use test base or not
	ReservationDao reservationdao=ReservationDao.getInstance(false);
	
	public List<Reservation> FindAll() throws ServiceException{
		try {
			return reservationdao.findAll();
			
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public long create(Reservation reservation) throws ServiceException{
		try {
			return reservationdao.create(reservation);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	public long deleteReservation(int id) throws ServiceException{
		try {
			return reservationdao.delete(id);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void checkDate(Date debut,Date fin,int id_vehicle) throws ServiceException{
		try {
			List<Reservation> allreservation =reservationdao.findResaByVehicleId(id_vehicle);
			for(Reservation r:allreservation) {
				if(debut.before(r.getDebut()) && fin.after(r.getDebut()) ||
						debut.after(r.getDebut()) && fin.before(r.getFin()) ||
								debut.after(r.getDebut()) && fin.before(r.getFin()) ||
										debut.before(r.getDebut()) && fin.after(r.getFin())){
											throw new ServiceException("Dates invalides");
											
										}
			}
			if(fin.getYear()==debut.getYear() && fin.getMonth()==debut.getMonth()) {
				int nbjours = fin.getDate() -debut.getDate();
				if(nbjours>7) {
					throw new ServiceException("Impossible de louer un véhicule plus de 7 jours");
				}
			}else {
				throw new ServiceException("Impossible de louer un véhicule plus de 7 jours");
			}
			
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public List<Reservation> findClientReservations(int idclient)throws ServiceException{
		try {
			return reservationdao.findResaByClientId(idclient);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public List<Reservation> findVehicleReservations(int idvehicle)throws ServiceException{
		try {
			return reservationdao.findResaByVehicleId(idvehicle);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public long updatedebut(String datedebut,int id) throws ServiceException {
		try {
			return reservationdao.updateD(datedebut, id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public Reservation findById(int id) throws ServiceException {
		try {
		 return reservationdao.getresabyid(id);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public long updatefin(String datefin,int id) throws ServiceException {
		try {
			return reservationdao.updateF(datefin,id);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	public void printReservationChoices() {
		System.out.println("1 - créer une réservation");
		System.out.println("2 - supprimer une réservation");
		System.out.println("3 - réservations relatives à un client");
		System.out.println("4 - reservations relatives à un véhicule");
		System.out.println("5 - toutes les réservations");
		System.out.println("6 - menu principal");
	}
}
