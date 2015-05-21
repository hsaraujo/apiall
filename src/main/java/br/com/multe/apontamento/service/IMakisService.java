package br.com.multe.apontamento.service;

import br.com.multe.apontamento.model.MakisReturn;

public interface IMakisService 
{
	public MakisReturn getHistorico(String[] credentials);
}
