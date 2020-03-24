package com.ensta.rentmanager.exception;

public class DaoException extends Exception {
	//TODO toujours créer un constructeur vide
	public DaoException() {
		super();
	}
	public DaoException(String msg) {
		super(msg);
	}
}