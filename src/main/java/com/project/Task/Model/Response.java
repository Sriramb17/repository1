package com.project.Task.Model;

public class Response{
	private String type;
	private Object token;

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setToken(Object token){
		this.token = token;
	}

	public Object getToken(){
		return token;
	}

	public Response(String type, Object token) {
		this.type = type;
		this.token = token;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"type = '" + type + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}
