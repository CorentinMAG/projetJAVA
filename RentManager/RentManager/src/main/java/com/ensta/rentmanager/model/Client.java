package com.ensta.rentmanager.model;

import java.sql.Date;

public class Client {
	private int id;
	private String nom;
	private String email;
	private Date naissance;
	private String prenom;
	
	public Client() {
		
	}
	public Client(int id,String nom,String prenom,String email,Date naissance) {
		this.id=id;
		this.nom=nom;
		this.email=email;
		this.naissance=naissance;
		this.prenom=prenom;
	}
	public Client(String nom,String prenom,String email,String naissanceStr) {
		Date date=Date.valueOf(naissanceStr);
		this.naissance=date;
		this.nom=nom;
		this.email=email;
		this.prenom=prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom=prenom;
	}
	public String getPrenom(){
		return this.prenom;
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getNaissance() {
		return naissance;
	}
	public void setNaissance(Date naissance) {
		this.naissance = naissance;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", email=" + email + ", naissance=" + naissance + ", prenom="
				+ prenom + "]";
	}
	
	
	
	
}
