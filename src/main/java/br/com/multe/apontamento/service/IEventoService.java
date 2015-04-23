package br.com.multe.apontamento.service;

import java.util.List;

import br.com.multe.apontamento.model.Evento;

public interface IEventoService 
{
	public List<Evento> getAll();
	
	public void insert(Evento evento);
}
