package br.com.apiall.crims.model;

public class Crime 
{
	private int valor;
	private String texto;

	public Crime() { }
	
	public Crime(int valor, String texto)
	{
		this.valor = valor;
		this.texto = texto;
	}
	
	public int getValor(){
		return valor;
	}
	
	public void setValor(int valor){
		this.valor = valor;
	}
	
	public String getText(){ 
		return texto;
	}
	
	public void setTexto(String texto){
		this.texto = texto;
	}
}
