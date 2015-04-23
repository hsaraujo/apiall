package br.com.multe.apontamento.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.multe.apontamento.model.Evento;
import br.com.multe.apontamento.service.IEventoService;

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
		String response = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		List<Evento> eventos = eventoService.getAll();
		for(Evento evento : eventos)
		{			
			response = response + sdf.format(evento.getInicio()) + "<br/>";
		}
		
		return response;
	}
}
