package br.com.apiall.service;

import br.com.apiall.model.MakisReturn;

public interface IMakisService 
{
	public MakisReturn getHistorico(String[] credentials);
}
