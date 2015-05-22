package br.com.apiall.crims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.apiall.crims.model.Crime;
import br.com.apiall.crims.model.Perfil;
import br.com.apiall.crims.service.ICrimsService;
import br.com.apiall.utils.GeneralHelper;

import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/thecrims")
public class CrimsController 
{
	@Autowired
	ICrimsService crimsService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> login(@RequestHeader("Authorization") String authorization)
	{
		String[] credentials = GeneralHelper.getLoginAndPasswordFromHeader(authorization);
		if(credentials == null)
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		else
		{
			if(crimsService.doLogin(credentials))
				return new ResponseEntity<String>(HttpStatus.OK);
			else
				return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
						
		}			
	}
	
	@RequestMapping(value = "/perfil", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> perfil(@RequestHeader("Authorization") String authorization)
	{
		String[] credentials = GeneralHelper.getLoginAndPasswordFromHeader(authorization);
		if(credentials == null)
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		else
		{
			Perfil perfil = crimsService.getPerfil(credentials);
			
			return new ResponseEntity<String>(new GsonBuilder().create().toJson(perfil), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/crime", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> preparaCrime(@RequestHeader("Authorization") String authorization)
	{
		String[] credentials = GeneralHelper.getLoginAndPasswordFromHeader(authorization);
		if(credentials == null)
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		else
		{
			List<Crime> crimes = crimsService.preparaCrime(credentials);
			
			return new ResponseEntity<String>(new GsonBuilder().create().toJson(crimes), HttpStatus.OK);
		}
	}
	
}
