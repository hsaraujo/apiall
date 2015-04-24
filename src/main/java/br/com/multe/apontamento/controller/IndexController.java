package br.com.multe.apontamento.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.multe.apontamento.model.Evento;
import br.com.multe.apontamento.service.IEventoService;

import com.google.gson.GsonBuilder;

@Controller
public class IndexController 
{
	@Autowired
	IEventoService eventoService;
	
	@RequestMapping(value = "/apontamento", method = RequestMethod.GET)
	public @ResponseBody String allApontamentos()
	{
		List<Evento> eventos = eventoService.getAll();
		return new GsonBuilder().create().toJson(eventos);
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> prepareNovo()
	{
		Map<String, List<String>> mapa = new HashMap<String, List<String>>();
		
		mapa = eventoService.getOsECategorias();
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(mapa);
			return new ResponseEntity<String>(json, HttpStatus.OK);
		}catch(Exception e) 
		{
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> createNovo()
	{
		Map<String, List<String>> mapa = new HashMap<String, List<String>>();
		
		mapa = eventoService.getOsECategorias();
		
		Evento evento = new Evento();
		evento.setOs(mapa.get("os").get(1));
		evento.setCategoria(mapa.get("categoria").get(1));
		evento.setDescricao("testeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		evento.setInicio(new Date());
		evento.setFim(new Date());
		
		return eventoService.insert(evento);
		
	}
	
}
