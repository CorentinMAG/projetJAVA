package com.ensta.rentmanager.model;

import java.sql.Date;

public class VehicleReservation {
	private int id;
	private String modele;
	private Date debut;
	private Date fin;
	
	public VehicleReservation(int id,String modele,Date debut,Date fin) {
		this.id=id;
		this.modele=modele;
		this.debut=debut;
		this.fin=fin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModele() {
		return modele;
	}
	@Override
	public String toString() {
		return "VehicleReservation [id=" + id + ", modele=" + modele + ", debut=" + debut + ", fin=" + fin + "]";
	}
	public void setModele(String modele) {
		this.modele = modele;
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
