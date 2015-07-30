package br.com.apiall.crims.service;

import java.util.List;

import br.com.apiall.crims.model.Crime;
import br.com.apiall.crims.model.Perfil;

public interface ICrimsService 
{
	public boolean doLogin(String[] credentials);
	
	public Perfil getPerfil(String[] credentials);
	
	public List<Crime> preparaCrime(String[] credentials);
}
