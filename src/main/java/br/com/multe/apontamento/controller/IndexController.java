package br.com.multe.apontamento.controller;

import java.util.List;
import java.util.Map;

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
		Map<Integer, String> os = eventoService.getOs();
		Map<Integer, String> categorias = eventoService.getCategorias();
		
		for (Integer key : os.keySet()) {
            
            //Capturamos o valor a partir da chave
            String value = os.get(key);
            System.out.println(key + " = " + value);
		}
		
		System.out.println("-----------");
		
		for (Integer key : categorias.keySet()) {
            
            //Capturamos o valor a partir da chave
            String value = categorias.get(key);
            System.out.println(key + " = " + value);
		}
		
		return "";
//		return new GsonBuilder().create().toJson(eventos);
	}
}
