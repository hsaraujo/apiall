package br.com.apime.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import br.com.apime.model.Apontamento;

public interface IApontamentoService 
{
	public List<Apontamento> getEvents(String[] credentials);
	
	public List<Apontamento> getEventsWithFilter(String[] credentials, Date inicio, Date fim);

	public Map<String, List<String>> getOsECategorias(String[] credentials);
	
	public ResponseEntity<String> insert(String[] credentials, Apontamento apontamento);
	
	public ResponseEntity<String> edit(String[] credentials, Apontamento apontamento);
	
	public ResponseEntity<String> delete(String[] credentials, int id);
}
