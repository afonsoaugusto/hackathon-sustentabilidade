package com.ciandt.hackathon.sustentability.model;

import java.util.ArrayList;
import java.util.List;

public class ReturnMessage {
	private Boolean error;
	private List<String> errorFields;
	private List<String> errorMessages;
	private Object message;
	private Object data;

	public ReturnMessage() {
		errorFields = new ArrayList<String>();
		errorMessages = new ArrayList<String>();
	}

	public void addError(String field, String message){
		errorFields.add(field);
		errorMessages.add(message);
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public List<String> getErrorFields() {
		return errorFields;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
