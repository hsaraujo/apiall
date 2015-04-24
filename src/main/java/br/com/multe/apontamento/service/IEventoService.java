package br.com.multe.apontamento.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import br.com.multe.apontamento.model.Evento;

public interface IEventoService 
{
	public List<Evento> getAll(String[] credentials);

	public Map<String, List<String>> getOsECategorias(String[] credentials);
	
	public ResponseEntity<String> insert(String[] credentials, Evento evento);
}
