package com.ensta.rentmanager.model;

import java.sql.Date;

public class ReservationClientVehicle {
	private int id;
	private String modeleCons;
	private String clientNP;
	private Date debut;
	private Date fin;
	
	public ReservationClientVehicle(int id,String modeleCons,String clientNP,Date debut,Date fin) {
		this.id=id;
		this.modeleCons=modeleCons;
		this.clientNP=clientNP;
		this.debut=debut;
		this.fin=fin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModeleCons() {
		return modeleCons;
	}
	public void setModeleCons(String modeleCons) {
		this.modeleCons = modeleCons;
	}
	public String getClientNP() {
		return clientNP;
	}
	public void setClientNP(String clientNP) {
		this.clientNP = clientNP;
	}
	public Date getDebut() {
		return debut;
	}
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	

}
