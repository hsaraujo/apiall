package br.com.apiall.crims.service;

public interface ICrimsService 
{
	public boolean doLogin(String[] credentials);
	
	public void getPerfil(String[] credentials);
}
