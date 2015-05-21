package br.com.apiall.makis.service;

import br.com.apiall.makis.model.MakisReturn;

public interface IMakisService 
{
	public MakisReturn getHistorico(String[] credentials);
}
