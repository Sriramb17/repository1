package com.project.Task.Model;

public class Login404Response{
	private String message;

	public Login404Response(String message) {
		this.message = message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"Login404Response{" + 
			"message = '" + message + '\'' + 
			"}";
		}
}
