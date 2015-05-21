package br.com.apime.controller;

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

import br.com.apime.model.Apontamento;
import br.com.apime.service.IApontamentoService;
import br.com.apime.utils.GeneralHelper;
import br.com.apime.utils.JsonHelper;

import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/apontamento")
public class ApontamentoController 
{
	@Autowired
	IApontamentoService apontamentoService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> allApontamentos(@RequestHeader("Authorization") String authorization)
	{
		String[] credentials = GeneralHelper.getLoginAndPasswordFromHeader(authorization);
		if(credentials == null)
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		
		List<Apontamento> apontamentos = apontamentoService.getEvents(credentials);
		return new ResponseEntity<String>(new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm").create().toJson(apontamentos), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = "application/json", produces = "application/json; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> searchWithFilter(@RequestHeader("Authorization") String authorization,
												@RequestBody String body)
	{
		String[] credentials = GeneralHelper.getLoginAndPasswordFromHeader(authorization);
		if(credentials == null)
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		
		Date[] dates = JsonHelper.getDatesFilterFromJson(body);
		if(dates == null)
			return new ResponseEntity<String>("JSON formatado errado", HttpStatus.BAD_REQUEST);
		
		List<Apontamento> apontamentos = apontamentoService.getEventsWithFilter(credentials, dates[0], dates[1]);
		return new ResponseEntity<String>(new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm").create().toJson(apontamentos), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> prepare(@RequestHeader("Authorization") String authorization)
	{
		String[] credentials = GeneralHelper.getLoginAndPasswordFromHeader(authorization);
		if(credentials == null)
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		
		Map<String, List<String>> mapa = new HashMap<String, List<String>>();
		
		mapa = apontamentoService.getOsECategorias(credentials);
		
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
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST, consumes = "application/json", produces = "application/json; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> create(@RequestHeader("Authorization") String authorization,
															@RequestBody String body)
	{
		String[] credentials = GeneralHelper.getLoginAndPasswordFromHeader(authorization);
		if(credentials == null)
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		
		Apontamento apontamento = JsonHelper.getEventoFromJson(body);
		
		if(apontamento == null)
			return new ResponseEntity<String>("JSON formatado errado", HttpStatus.BAD_REQUEST);
		else
			return apontamentoService.insert(credentials, apontamento);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = "application/json")
	public @ResponseBody ResponseEntity<String> edit(@RequestHeader("Authorization") String authorization,
													@RequestBody String body)
	{
		String[] credentials = GeneralHelper.getLoginAndPasswordFromHeader(authorization);
		if(credentials == null)
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		
		return null;
	}
	
}