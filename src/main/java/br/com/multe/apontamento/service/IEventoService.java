package br.com.multe.apontamento.service;

import java.util.List;

import br.com.multe.apontamento.model.Evento;

public interface IEventoService 
{
	public List<Evento> getAll();
	
	public List<String> getOs();
	
	public List<String> getCategorias();
	
	public void insert(Evento evento);
}
