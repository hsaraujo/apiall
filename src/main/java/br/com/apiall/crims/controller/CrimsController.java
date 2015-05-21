package br.com.apiall.crims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.apiall.crims.service.ICrimsService;
import br.com.apiall.utils.GeneralHelper;

@Controller
@RequestMapping("/api/thecrims")
public class CrimsController 
{
	@Autowired
	ICrimsService crimsService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<String> edit(@RequestHeader("Authorization") String authorization)
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
}
