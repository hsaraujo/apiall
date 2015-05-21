package br.com.apiall.crims.service;

import br.com.apiall.crims.model.Perfil;

public interface ICrimsService 
{
	public boolean doLogin(String[] credentials);
	
	public Perfil getPerfil(String[] credentials);
}
