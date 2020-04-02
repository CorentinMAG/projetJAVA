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
	
	private long compareDate(Date debut,Date fin) {
		final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24; 
		long delta = debut.getTime() - fin.getTime();
		return Math.abs(delta / (MILLISECONDS_PER_DAY));
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
			int nbjours = fin.getDate() -debut.getDate();
			if(compareDate(debut,fin)>7) {
					throw new ServiceException("Impossible de louer un véhicule plus de 7 jours");
				}
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public void lessThanThirtiesDays(Date debut,Date fin,int id_vehicle) throws ServiceException{
		try {
			List<Reservation> allreservation=reservationdao.findResaByVehicleId(id_vehicle);
			if(allreservation.size()>0) {
				for(Reservation rsvcr:allreservation) {
					if(fin.getMonth() == rsvcr.getDebut().getMonth() && fin.getYear() == rsvcr.getDebut().getYear() || fin.getMonth() == rsvcr.getDebut().getMonth()+1 && fin.getYear() == rsvcr.getDebut().getYear()) {
						if(compareDate(fin,rsvcr.getDebut())<=30 && compareDate(rsvcr.getFin(),debut)<=30) {
						}else {
							throw new ServiceException("Impossible de louer un même véhicule plus de 30 jours");
					}	
					}	
				}
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
