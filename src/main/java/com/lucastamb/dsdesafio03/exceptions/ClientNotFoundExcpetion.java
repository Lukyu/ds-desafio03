package com.lucastamb.dsdesafio03.exceptions;

public class ClientNotFoundExcpetion extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ClientNotFoundExcpetion(String msg) {
		super(msg);
	}

}
