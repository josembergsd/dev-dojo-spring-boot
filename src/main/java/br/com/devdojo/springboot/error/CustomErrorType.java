package br.com.devdojo.springboot.error;


public class CustomErrorType {

	private String errrorMessage;
	
	public CustomErrorType(String errorMessage) {
		this.errrorMessage = errorMessage;
	}

	public String getErrrorMessage() {
		return errrorMessage;
	}

}
