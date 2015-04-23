package br.com.multe.apontamento.service;

import java.util.List;
import java.util.Map;

import br.com.multe.apontamento.model.Evento;

public interface IEventoService 
{
	public List<Evento> getAll();
	
	public Map<Integer, String> getOs();
	
	public Map<Integer, String> getCategorias();
	
	public void insert(Evento evento);
}
