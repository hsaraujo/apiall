package br.com.multe.apontamento.service;

import java.util.List;

import br.com.multe.apontamento.model.Makis;

public interface IMakisService 
{

	public List<Makis> getHistorico(String[] credentials);
}
