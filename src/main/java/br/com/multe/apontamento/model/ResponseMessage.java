package br.com.multe.apontamento.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("response")
public class ResponseMessage {

	private int code;
	private String message;
	
	public ResponseMessage() { }
	
	public ResponseMessage (int code, String message)
	{
		this.code = code;
		this.message = message;
	}
	
	@JsonProperty("code")
	public int getCode(){
		return code;
	}
	
	public void setCode(int code){
		this.code = code;
	}
	
	@JsonProperty("message")
	public String getMessage(){
		return message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String toString(){
		return message;
	}
}
