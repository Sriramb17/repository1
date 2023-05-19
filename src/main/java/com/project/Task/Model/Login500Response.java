package com.project.Task.Model;

public class Login500Response{
	private String message;
	private boolean errors;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setErrors(boolean errors){
		this.errors = errors;
	}

	public boolean getErrors(){
		return errors;
	}

	public Login500Response(String message, boolean errors) {
		this.message = message;
		this.errors = errors;
	}

	@Override
 	public String toString(){
		return 
			"Login500Response{" + 
			"message = '" + message + '\'' + 
			",errors = '" + errors + '\'' + 
			"}";
		}
}
