package com.edujoa.ssz.exception;

import java.io.Serializable;

public class Chattingexception extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8615701745817224987L;
	
	public Chattingexception(String errorMsg) {
		super(errorMsg);	
		
	}
	
}
