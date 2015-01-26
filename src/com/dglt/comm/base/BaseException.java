package com.dglt.comm.base;

public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;

	public BaseException(){
		
	}
	
	public BaseException(String errorCode){
		super( errorCode );
	}

}