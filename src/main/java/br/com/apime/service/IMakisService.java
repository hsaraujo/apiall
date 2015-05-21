package br.com.apime.service;

import br.com.apime.model.MakisReturn;

public interface IMakisService 
{
	public MakisReturn getHistorico(String[] credentials);
}
