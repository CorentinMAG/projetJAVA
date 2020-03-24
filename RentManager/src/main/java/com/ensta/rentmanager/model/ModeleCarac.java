package com.ensta.rentmanager.model;

public class ModeleCarac {
	private int id;
	private String modele;
	private String myconstructeur;
	private int nbplaces;
	
	public ModeleCarac(int id,String modele,String myconstructeur,int nbplaces) {
		this.id=id;
		this.modele=modele;
		this.myconstructeur=myconstructeur;
		this.nbplaces=nbplaces;
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

	public void setModele(String modele) {
		this.modele = modele;
	}

	public int getNbplaces() {
		return nbplaces;
	}

	public void setNbplaces(int nbplaces) {
		this.nbplaces = nbplaces;
	}

	public String getConstructeur() {
		return myconstructeur;
	}

	public void setConstructeur(String myconstructeur) {
		this.myconstructeur = myconstructeur;
	}
	

}
