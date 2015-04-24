package br.com.multe.apontamento.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import br.com.multe.apontamento.model.Evento;

public interface IEventoService 
{
	public List<Evento> getAll();

	public Map<String, List<String>> getOsECategorias();
	
	public ResponseEntity<String> insert(Evento evento);
}
