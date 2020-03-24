package com.ensta.rentmanager.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.dao.ClientDao;

public class ClientService {

	private static ClientService instance=null;
	private ClientService() {}
	
	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}
	//use test base or not
	ClientDao clientdao=ClientDao.getInstance(false);
	
	public List<Client> FindAll() throws ServiceException{
		try {
			return clientdao.findAll();
			
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public long create(Client client) throws ServiceException{
		checkAge(client); 
		try {
			return clientdao.create(client);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	public void verifymail(String mail) throws ServiceException {
		List<String> allmail=new ArrayList<String>();
		try {
			List<Client> listallclients = clientdao.findAll();
			for(Client client:listallclients) {
				allmail.add(client.getEmail());
			}
			if(allmail.contains(mail)) {
				throw new ServiceException("Le mail existe déjà!");
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	public void verifynp(String nom,String prenom) throws ServiceException{
		if(nom.length()<3 || prenom.length()<3) {
			throw new ServiceException("Nom/Prénom invalides");
		}
	}
	public void printChoix() {
		System.out.println("1 : Ajouter un client");
		System.out.println("2 : Supprimer un client");
		System.out.println("3 : Lister les clients");
		System.out.println("4 : Modifier les données d'un client");
		System.out.println("5 : Quitter le programme");
	}
	public void printUpdate(int id) {
		System.out.println("-----Client n°"+id);
		System.out.println("1 : Modifier le nom du client");
		System.out.println("2 : Modifier le prénom du client");
		System.out.println("3 : Modifier l'email du client");
		System.out.println("4 : Modifier la date de naissance du client");
		System.out.println("5 : revenir au menu principal");
		System.out.print("Entrer le(s) numéro(s) : ");
		
	}

	private void checkAge(Client client) throws ServiceException {
		long age=ChronoUnit.YEARS.between(client.getNaissance().toLocalDate(),LocalDate.now());
		if(age<18) {
			throw new ServiceException("le client doit avoir 18 ans");
		}
	}

	public Client findById(int id) throws ServiceException {
		try {
			return clientdao.show_client(id);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	public long updateName(String new_name,int id) throws ServiceException{
		try {
			return clientdao.updateName(new_name, id);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public long deleteClient(int id) throws ServiceException{
		try {
			return clientdao.delete(id);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public long updatePrenom(String new_prenom,int id) throws ServiceException{
		try {
			return clientdao.updatePrenom(new_prenom, id);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public long updateBirth(String new_date,int id) throws ServiceException{
		try {
			return clientdao.updateBirth(new_date, id);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public long updateEmail(String new_email,int id) throws ServiceException{
		try {
			return clientdao.updateMail(new_email, id);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public void printBetween() {
		System.out.println("1 - client");
		System.out.println("2 - véhicule");
		System.out.println("3 - réservations");
		System.out.println("4 - quitter le programme");
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return clientdao.findAll();
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	
}
