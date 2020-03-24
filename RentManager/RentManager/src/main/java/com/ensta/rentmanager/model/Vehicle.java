package com.ensta.rentmanager.model;

public class Vehicle {
	private int id;
	private String constructeur;
	private int nb_place;
	private String modele;
	
	public Vehicle() {
		
	}
	
	public Vehicle(int id, String modele,String constructeur,int nb_place) {
		this.id=id;
		this.modele=modele;
		this.constructeur=constructeur;
		this.nb_place=nb_place;
		
	}
	public Vehicle(String modele,String constructeur,int nb_place) {
		this.modele=modele;
		this.constructeur=constructeur;
		this.nb_place=nb_place;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConstructeur() {
		return constructeur;
	}
	public void setConstructeur(String constructeur) {
		this.constructeur = constructeur;
	}
	public int getNb_place() {
		return nb_place;
	}
	public void setNb_place(int nb_place) {
		this.nb_place = nb_place;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", constructeur=" + constructeur + ", nb_place=" + nb_place + ", modele=" + modele
				+ "]";
	}
	

}
