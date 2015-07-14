package br.com.apiall.canalbra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.apiall.canalbra.service.IBraService;
import br.com.apiall.canalbra.utils.Rank;

import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/canalbra")
public class BraController 
{
	
	@Autowired
	private IBraService braService;
	
	@RequestMapping(value = "/rank", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> allApontamentos()
	{
		List<Rank> ranks	= braService.getRank();
		
		return new ResponseEntity<String>(new GsonBuilder().create().toJson(ranks), HttpStatus.OK);
	}

}
