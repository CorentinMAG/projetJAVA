package com.ensta.rentmanager.exception;

public class ServiceException extends Exception {
	//TODO toujours créer un constructeur vide
		public ServiceException() {
			super();
		}
		public ServiceException(String msg) {
			super(msg);
		}

}
