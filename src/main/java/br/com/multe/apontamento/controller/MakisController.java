package br.com.multe.apontamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.multe.apontamento.model.Makis;
import br.com.multe.apontamento.service.IMakisService;
import br.com.multe.apontamento.utils.GeneralHelper;

import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/makis")
public class MakisController 
{
	@Autowired
	private IMakisService makisService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> allApontamentos(@RequestHeader("Authorization") String authorization)
	{
		String[] credentials = GeneralHelper.getLoginAndPasswordFromHeader(authorization);
		if(credentials == null)
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		
		List<Makis> makisList = makisService.getHistorico(credentials);
		
		return new ResponseEntity<String>(new GsonBuilder().setDateFormat("dd/MM/yyyy").create().toJson(makisList), HttpStatus.OK);
	}
}
