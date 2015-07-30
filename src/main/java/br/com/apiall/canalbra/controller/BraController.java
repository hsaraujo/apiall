package br.com.apiall.canalbra.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.apiall.canalbra.model.Ban;
import br.com.apiall.canalbra.model.Game;
import br.com.apiall.canalbra.model.Gamestats;
import br.com.apiall.canalbra.model.Memberstats;
import br.com.apiall.canalbra.model.Rank;
import br.com.apiall.canalbra.service.IBraService;

import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/canalbra")
public class BraController 
{
	
	@Autowired
	private IBraService braService;
	
	@RequestMapping(value = "/rank", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> rank()
	{
		List<Rank> ranks	= braService.getRank();
		
		return new ResponseEntity<String>(new GsonBuilder().create().toJson(ranks), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/banlist", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> banlist()
	{
		List<Ban> banList	= braService.getBanlist();
		
		return new ResponseEntity<String>(new GsonBuilder().create().toJson(banList), HttpStatus.OK);
	}
	
//	@RequestMapping(value = "/games", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
//	public @ResponseBody ResponseEntity<String> games()
//	{
//		List<Game> games	= braService.getGames(1);
//		
//		return new ResponseEntity<String>(new GsonBuilder().create().toJson(games), HttpStatus.OK);
//	}
	
	@RequestMapping(value = "/games", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> gamesWithPage(@RequestParam(value="page") Integer page)
	{
		List<Game> games = new ArrayList<Game>();
		
		if(page == null)
			games	= braService.getGames(1);
		else
			games	= braService.getGames(page.intValue());
		
		return new ResponseEntity<String>(new GsonBuilder().create().toJson(games), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/gamestats", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> gamestats(@RequestParam(value="id") Integer id)
	{
		Gamestats gamestats	= new Gamestats();
		
		if(id == null)
		{
			return new ResponseEntity<String>(new GsonBuilder().create().toJson(gamestats), HttpStatus.BAD_REQUEST);
		}
		else
		{
			gamestats	= braService.getGamestats(id);
			
			return new ResponseEntity<String>(new GsonBuilder().create().toJson(gamestats), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/member", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> member(@RequestParam(value="user") String user, @RequestParam(value="page") Integer page)
	{
		Memberstats memberStats = new Memberstats();
		
		if(user == null)
		{
			return new ResponseEntity<String>(new GsonBuilder().create().toJson(memberStats), HttpStatus.BAD_REQUEST);
		}
		else if(page == null)
		{	
			memberStats = braService.getMember(user, 1);
			
			return new ResponseEntity<String>(new GsonBuilder().create().toJson(memberStats), HttpStatus.OK);
		}
		else
		{
			memberStats = braService.getMember(user, page);
			
			return new ResponseEntity<String>(new GsonBuilder().create().toJson(memberStats), HttpStatus.OK);
		}
	}
}
