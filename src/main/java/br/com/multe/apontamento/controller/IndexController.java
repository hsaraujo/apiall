package br.com.multe.apontamento.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping("/index")
	public @ResponseBody String index()
	{
		return "oi";
	}
	
	@RequestMapping(value = "/apontamento", method = RequestMethod.GET)
	public @ResponseBody String allApontamentos()
	{
		List<Evento> eventos = eventoService.getAll();
		return new GsonBuilder().create().toJson(eventos);
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public @ResponseBody String prepareNovo()
	{
		List<String> os = eventoService.getOs();
		List<String> categorias = eventoService.getCategorias();
		
		return new GsonBuilder().create().toJson(os);
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public @ResponseBody String createNovo()
	{
		List<String> os = eventoService.getOs();
		List<String> categorias = eventoService.getCategorias();
		
		Evento evento = new Evento();
		evento.setOs(os.get(1));
		evento.setCategoria(categorias.get(1));
		evento.setDescricao("testeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		evento.setInicio(new Date());
		evento.setFim(new Date());
		
		eventoService.insert(evento);
		
		return new GsonBuilder().create().toJson(evento);
	}
}
