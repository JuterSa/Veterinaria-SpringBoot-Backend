package com.softcaribbean.veterinariaXYZ.config.controller.errors;

import com.softcaribbean.veterinariaXYZ.util.ResponseMessage;

import java.io.Serial;

public class ApplicationCustomException extends Exception {

	@Serial
	private static final long serialVersionUID = 2962065458808354006L;
	private final int code;
	private final String message;

	public ApplicationCustomException (int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public ResponseMessage<Object> getResponse(){
		return new ResponseMessage<>(this.code, this.message, null);
	}


}
