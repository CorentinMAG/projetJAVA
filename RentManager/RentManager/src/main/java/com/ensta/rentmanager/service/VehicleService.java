package com.ensta.rentmanager.service;

import java.util.List;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.dao.ClientDao;
import com.ensta.rentmanager.dao.VehicleDao;

public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;
	
	private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance(false);
	}
	
	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}
	VehicleDao vehicledao=VehicleDao.getInstance(false);
	public List<Vehicle> FindAll() throws ServiceException{
		try {
			return vehicledao.findAll();
			
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	public long create(Vehicle vehicle) throws ServiceException {
		try {
			return vehicledao.create(vehicle);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
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

	public Vehicle findById(int id) throws ServiceException {
		try {
			return vehicledao.show_vehicle(id);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	public long delete(int id) throws ServiceException{
		try {
			return vehicledao.delete(id);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public long updateConstructeur(String new_constructeur,int id) throws ServiceException {
		try {
			return vehicledao.updateConstructeur(new_constructeur, id);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public long updateModele(String new_modele,int id) throws ServiceException {
		try {
			return vehicledao.updateModele(new_modele, id);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public long updateNb_places(int new_nbplaces,int id) throws ServiceException {
		try {
			return vehicledao.updateNb_place(new_nbplaces, id);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return vehicledao.findAll();
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	
}
