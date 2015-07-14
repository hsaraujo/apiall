package br.com.apiall.canalbra.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.apiall.canalbra.model.Ban;
import br.com.apiall.canalbra.model.Game;
import br.com.apiall.canalbra.model.Gamestats;
import br.com.apiall.canalbra.model.Rank;
import br.com.apiall.canalbra.service.IBraService;
import br.com.apiall.canalbra.utils.BraConstants;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

@Service
public class BraService implements IBraService 
{
	private WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
	private HtmlPage page;
	
	@Override
	public List<Rank> getRank() 
	{
		List<Rank> ranks = new ArrayList<Rank>();
		
		try
		{
			page = webClient.getPage(BraConstants.RANK_URL);
			
			HtmlTable table = (HtmlTable) page.getElementsByTagName("table").get(2);
			
			List<HtmlTableRow> rows = table.getBodies().get(0).getRows();
			
			for(int i = 1; i < rows.size(); i++)
			{
				HtmlTableRow row = rows.get(i);
				
				int position 			= Integer.parseInt(row.getCell(0).asText());
				String name				= row.getCell(2).asText();
				int points				= Integer.parseInt(row.getCell(3).asText());
				int games				= Integer.parseInt(row.getCell(6).asText());
				int wins				= Integer.parseInt(row.getCell(7).asText());
				int looses				= Integer.parseInt(row.getCell(8).asText());
				
				int winPercentage		= getPercentageFromCell(row.getCell(9));
				
				String favouriteHero	= getImageSrcFromCell(row.getCell(10));
				
				String bestKD			= row.getCell(11).asText();
				String bestCS			= row.getCell(12).asText();
				
				Rank rank = new Rank(position, name, points, games, wins, looses, winPercentage, favouriteHero, bestKD, bestCS);
				
				ranks.add(rank);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ranks;
	}
	
	private String getImageSrcFromCell(HtmlTableCell cell)
	{
		return cell.getElementsByTagName("img").get(0).getAttribute("src");
	}
	
	private int getPercentageFromCell(HtmlTableCell cell)
	{
		String cellStr = cell.asText();
		return Integer.parseInt(cellStr.substring(0, cellStr.length()-1));
	}

	@Override
	public List<Ban> getBanlist() 
	{
		List<Ban> banlist = new ArrayList<Ban>();
		
		try
		{
			page = webClient.getPage(BraConstants.BANLIST_URL);
			
			HtmlTable table = (HtmlTable) page.getElementsByTagName("table").get(2);
			
			List<HtmlTableRow> rows = table.getBodies().get(0).getRows();
			
			for(int i = 1; i < rows.size(); i++)
			{
				HtmlTableRow row = rows.get(i);
				
				String name		= row.getCell(0).asText();
				String reason	= row.getCell(3).asText();
				String info		= row.getCell(4).asText();
				int remain	= Integer.parseInt(row.getCell(5).asText());
				String from		= row.getCell(6).asText();
				String date		= row.getCell(7).asText();
				
				Ban ban = new Ban(name, reason, info, remain, from, date);
				
				banlist.add(ban);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return banlist;
	}

	@Override
	public List<Game> getGames(int pageNumber)
	{
		List<Game> games = new ArrayList<Game>();
		
		try
		{
			page = webClient.getPage(BraConstants.GAMES_URL + String.valueOf(pageNumber));
			
			HtmlTable table = (HtmlTable) page.getElementsByTagName("table").get(2);
			
			List<HtmlTableRow> rows = table.getBodies().get(0).getRows();
			
			for(int i = 2; i < rows.size(); i++)
			{
				HtmlTableRow row = rows.get(i);
				
				int id			= Integer.parseInt(row.getCell(0).asText());
				String name		= row.getCell(1).asText();
				String owner	= row.getCell(2).asText();
				String duration	= row.getCell(3).asText();
				String date		= row.getCell(4).asText();
				
				Game game = new Game(id, name, owner, duration, date);
				
				games.add(game);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return games;
	}

	@Override
	public List<Gamestats> getGamestats(int id)
	{
		List<Gamestats> gamestatsList = new ArrayList<Gamestats>();
		
		try
		{
			page = webClient.getPage(BraConstants.GAMESTATS_URL + String.valueOf(id));
			
			HtmlTable table = (HtmlTable) page.getElementsByTagName("table").get(2);
			
			List<HtmlTableRow> rows = table.getBodies().get(0).getRows();
			
			for(int i = 5; i < rows.size() - 2; i++)
			{
				HtmlTableRow row = rows.get(i);
				
				int slot		= Integer.parseInt(row.getCell(1).asText());
				String name		= getAnchorValueCell(row.getCell(2));
				String hero		= getImageSrcFromCell(row.getCell(3));
				int heroKill	= Integer.parseInt(row.getCell(4).asText());
				int heroDeath	= Integer.parseInt(row.getCell(5).asText());
				int assistence	= Integer.parseInt(row.getCell(6).asText());
				int creepKill	= Integer.parseInt(row.getCell(7).asText());
				int creepDeath	= Integer.parseInt(row.getCell(8).asText());
				int neutral		= Integer.parseInt(row.getCell(9).asText());
				int gold		= Integer.parseInt(row.getCell(10).asText());
				int towerKill	= Integer.parseInt(row.getCell(11).asText());
				int rk			= Integer.parseInt(row.getCell(12).asText());
				int ck			= Integer.parseInt(row.getCell(13).asText());
				
				String[] items  = new String[6];
				for(int j = 0; j < 6; j++)
				{
					items[j]	= getImageSrcFromCell(row.getCell(14+j));
				}
				
				Gamestats game = new Gamestats(slot, name, hero, heroKill, heroDeath, assistence, creepKill,
												creepDeath, neutral, gold, towerKill, rk, ck, items);
				
				gamestatsList.add(game);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return gamestatsList;
	}
	
	private String getAnchorValueCell(HtmlTableCell cell)
	{
		return cell.getElementsByTagName("a").get(0).asText();
	}
	
	
}
