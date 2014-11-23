package com.ghostgame;

public class Response {

	private String string = "";
	private String message = "";


	public Response() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
	
	public Response(String string, String message) {
		this.string = string;
		this.message = message;
	}

}
