package br.com.apiall.canalbra.service;

import java.util.List;

import br.com.apiall.canalbra.model.Ban;
import br.com.apiall.canalbra.model.Game;
import br.com.apiall.canalbra.model.Gamestats;
import br.com.apiall.canalbra.model.Memberstats;
import br.com.apiall.canalbra.model.Rank;

public interface IBraService 
{
	List<Rank> 	getRank();
	List<Ban> 	getBanlist();
	List<Game> 	getGames(int page);
	Gamestats 	getGamestats(int id);
	Memberstats	getMember(String user, int page);
}
