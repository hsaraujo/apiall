package br.com.multe.apontamento.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import br.com.multe.apontamento.model.Evento;

public interface IEventoService 
{
	public List<Evento> getEvents(String[] credentials);
	
	public List<Evento> getEventsWithFilter(String[] credentials, Date inicio, Date fim);

	public Map<String, List<String>> getOsECategorias(String[] credentials);
	
	public ResponseEntity<String> insert(String[] credentials, Evento evento);
	
	public ResponseEntity<String> edit(String[] credentials, Evento evento);
	
	public ResponseEntity<String> delete(String[] credentials, int id);
}
