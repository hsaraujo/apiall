package br.com.multe.apontamento.controller;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Decoder;
import br.com.multe.apontamento.model.Evento;
import br.com.multe.apontamento.service.IEventoService;
import br.com.multe.apontamento.utils.JsonHelper;

import com.google.gson.GsonBuilder;

@SuppressWarnings("restriction")
@Controller
@RequestMapping("/api")
public class EventoController 
{
	@Autowired
	IEventoService eventoService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> allApontamentos(@RequestHeader("Authorization") String authorization)
	{
		String[] credentials = getLoginAndPasswordFromHeader(authorization);
		if(credentials == null)
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		
		List<Evento> eventos = eventoService.getEvents(credentials);
		return new ResponseEntity<String>(new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm").create().toJson(eventos), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<String> searchWithFilter(@RequestHeader("Authorization") String authorization,
												@RequestBody String body)
	{
		String[] credentials = getLoginAndPasswordFromHeader(authorization);
		if(credentials == null)
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		
		Date[] dates = JsonHelper.getDatesFilterFromJson(body);
		if(dates == null)
			return new ResponseEntity<String>("JSON formatado errado", HttpStatus.BAD_REQUEST);
		
		List<Evento> eventos = eventoService.getEventsWithFilter(credentials, dates[0], dates[1]);
		return new ResponseEntity<String>(new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm").create().toJson(eventos), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> prepare(@RequestHeader("Authorization") String authorization)
	{
		String[] credentials = getLoginAndPasswordFromHeader(authorization);
		if(credentials == null)
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		
		Map<String, List<String>> mapa = new HashMap<String, List<String>>();
		
		mapa = eventoService.getOsECategorias(credentials);
		
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
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<String> create(@RequestHeader("Authorization") String authorization,
															@RequestBody String body)
	{
		String[] credentials = getLoginAndPasswordFromHeader(authorization);
		if(credentials == null)
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		
		Evento evento = JsonHelper.getEventoFromJson(body);
		
		if(evento == null)
			return new ResponseEntity<String>("JSON formatado errado", HttpStatus.BAD_REQUEST);
		else
			return eventoService.insert(credentials, evento);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = "application/json")
	public @ResponseBody ResponseEntity<String> edit(@RequestHeader("Authorization") String authorization,
													@RequestBody String body)
	{
		String[] credentials = getLoginAndPasswordFromHeader(authorization);
		if(credentials == null)
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		
		return null;
	}
	
	private static String[] getLoginAndPasswordFromHeader(String authorization)
	{
		try
		{
			if (authorization != null && authorization.startsWith("Basic")) {
		        String base64Credentials = authorization.substring("Basic".length()).trim();
		        BASE64Decoder decoder = new BASE64Decoder();
		        String credentials = new String(decoder.decodeBuffer(base64Credentials), Charset.forName("UTF-8"));
		        final String[] values = credentials.split(":", 2);
		        return values;
			}
	    else
	    	return null;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
