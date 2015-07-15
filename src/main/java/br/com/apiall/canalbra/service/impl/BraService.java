package br.com.apiall.canalbra.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.apiall.canalbra.model.Ban;
import br.com.apiall.canalbra.model.Game;
import br.com.apiall.canalbra.model.Gamestats;
import br.com.apiall.canalbra.model.Member;
import br.com.apiall.canalbra.model.MemberGame;
import br.com.apiall.canalbra.model.MemberGamestats;
import br.com.apiall.canalbra.model.Memberstats;
import br.com.apiall.canalbra.model.Rank;
import br.com.apiall.canalbra.service.IBraService;
import br.com.apiall.canalbra.utils.BraConstants;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
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
	public Gamestats getGamestats(int id)
	{
		Gamestats gamestats = new Gamestats();
		
		try
		{
			page = webClient.getPage(BraConstants.GAMESTATS_URL + String.valueOf(id));
			
			HtmlTable table = (HtmlTable) page.getElementsByTagName("table").get(2);
			
			List<HtmlTableRow> rows = table.getBodies().get(0).getRows();
			
			List<MemberGamestats> memberGameList = new ArrayList<MemberGamestats>();
			
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
				int creepDenny	= Integer.parseInt(row.getCell(8).asText());
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
				
				MemberGamestats memberGame = new MemberGamestats(slot, name, hero, heroKill, heroDeath, assistence, creepKill,
												creepDenny, neutral, gold, towerKill, rk, ck, items);
				
				memberGameList.add(memberGame);
			}
			
			gamestats.setMemberGamestatsList(memberGameList);
			
			
			// GETTING POINTS FOR SENTINEL AND SCOURGE
			
			HtmlTableRow rowPoints	= rows.get(rows.size() - 2);
			int[] points			= getSentinelAndScourgePointsFromCell(rowPoints.getCell(0));
			
			int sentinelPoints		= points[0];
			int scourgePoints		= points[1];
			
			gamestats.setSentinelPoints(sentinelPoints);
			gamestats.setScourgePoints(scourgePoints);
			
			
			// GETTING WINNER
			
			HtmlTableRow winnerRow	= rows.get(rows.size() -1);
			String winner = winnerRow.getAttribute("class");
			
			gamestats.setWinner(winner);
			
			
			// GETTING 
			
			HtmlDivision content	= (HtmlDivision) page.getElementById("content");
			
			int divs  				= content.getElementsByTagName("div").size();

			HtmlDivision divKillers	= (HtmlDivision) content.getElementsByTagName("div").get(divs-3);
			
			String bestKiller 		= divKillers.getElementsByTagName("span").get(0).asText();
			String bestFarmer		= divKillers.getElementsByTagName("span").get(1).asText();
			String bestTower		= divKillers.getElementsByTagName("span").get(2).asText();
			
			gamestats.setBestKiller(bestKiller);
			gamestats.setBestFarmer(bestFarmer);
			gamestats.setBestTowerRaxKiller(bestTower);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return gamestats;
	}
	
	@Override
	public Memberstats getMember(String user) 
	{
		Memberstats memberStats = new Memberstats();
		
		try
		{
			page = webClient.getPage(BraConstants.MEMBER_URL + user);
			
			HtmlTable topTable 	= (HtmlTable) page.getElementsByTagName("table").get(2);
			HtmlTable botTable	= (HtmlTable) page.getElementsByTagName("table").get(3);
			
			HtmlTableRow topRow = topTable.getBodies().get(0).getRows().get(1);
			HtmlTableRow botRow = botTable.getBodies().get(0).getRows().get(1);
			
				
			int rank		= Integer.parseInt(	topRow.getCell(0)	.getElementsByTagName("div").get(0).asText());
			int points		= Integer.parseInt(	topRow.getCell(1)	.getElementsByTagName("div").get(0).asText());
			int lvl			= Integer.parseInt(	topRow.getCell(5)	.getElementsByTagName("div").get(0).asText());
			String joined	= 					topRow.getCell(9)	.getElementsByTagName("div").get(0).asText();
			int games		= Integer.parseInt(	botRow.getCell(0)	.getElementsByTagName("div").get(0).asText());
			int wins		= Integer.parseInt(	botRow.getCell(1)	.getElementsByTagName("div").get(0).asText());
			int looses		= Integer.parseInt(	botRow.getCell(2)	.getElementsByTagName("div").get(0).asText());
			int heroKill	= Integer.parseInt(	botRow.getCell(3)	.getElementsByTagName("div").get(0).asText());
			int heroDeath	= Integer.parseInt(	botRow.getCell(4)	.getElementsByTagName("div").get(0).asText());
			int assist		= Integer.parseInt(	botRow.getCell(5)	.getElementsByTagName("div").get(0).asText());
			int creepKill	= Integer.parseInt(	botRow.getCell(6)	.getElementsByTagName("div").get(0).asText());
			int creepDeny	= Integer.parseInt(	botRow.getCell(7)	.getElementsByTagName("div").get(0).asText());
			int neutral		= Integer.parseInt(	botRow.getCell(8)	.getElementsByTagName("div").get(0).asText());
			int towerKill	= Integer.parseInt(	botRow.getCell(9)	.getElementsByTagName("div").get(0).asText());
			int rk			= Integer.parseInt(	botRow.getCell(10)	.getElementsByTagName("div").get(0).asText());
			int ck			= Integer.parseInt(	botRow.getCell(11)	.getElementsByTagName("div").get(0).asText());
				
			Member member = new Member(rank, points, lvl, joined, games, wins, looses, heroKill, heroDeath, assist,
								creepKill, creepDeny, neutral, towerKill, rk, ck);

			
			
			List<MemberGame> memberGames = new ArrayList<MemberGame>();
			
			for(int i = 0; i < games; i++)
			{
				HtmlTable tableStats	= (HtmlTable) page.getElementsByTagName("table").get(4+i);
				
				topRow 					= tableStats.getBodies().get(0).getRows().get(1);
				botRow					= tableStats.getBodies().get(0).getRows().get(3);
				
				int 	gameId			= Integer.parseInt(getStringDivFromCell(topRow.getCell(0)));
				String 	date			= getStringDivFromCell(topRow.getCell(1));
				String	duration		= getStringDivFromCell(topRow.getCell(2));
				String 	hero			= getImageSrcFromCell(topRow.getCell(3));
				
				String[] items  		= new String[6];
				for(int j = 0; j < 6; j++)
				{
					items[j]	= getImageSrcFromCell(topRow.getCell(4+j));
				}
				
				String team				= getStringDivFromCell(topRow.getCell(10));
				boolean winner			= getStringDivFromCell(topRow.getCell(10)).equalsIgnoreCase(team);
				
				heroKill				= Integer.parseInt(getStringDivFromCell(botRow.getCell(1)));
				heroDeath				= Integer.parseInt(getStringDivFromCell(botRow.getCell(2)));
				assist					= Integer.parseInt(getStringDivFromCell(botRow.getCell(3)));
				creepKill				= Integer.parseInt(getStringDivFromCell(botRow.getCell(4)));
				creepDeny				= Integer.parseInt(getStringDivFromCell(botRow.getCell(5)));
				neutral					= Integer.parseInt(getStringDivFromCell(botRow.getCell(6)));
				int gold				= Integer.parseInt(getStringDivFromCell(botRow.getCell(7)));
				towerKill				= Integer.parseInt(getStringDivFromCell(botRow.getCell(8)));
				rk						= Integer.parseInt(getStringDivFromCell(botRow.getCell(9)));
				ck						= Integer.parseInt(getStringDivFromCell(botRow.getCell(10)));
				
				MemberGame memberGame 	= new MemberGame(gameId, date, duration, hero, items, team, winner, 
											heroKill, heroDeath, assist, creepKill, creepDeny, neutral, 
											gold, towerKill, rk, ck);
				
				memberGames.add(memberGame);
				
			}
			
			
			memberStats.setMember(member);
			memberStats.setMemberGames(memberGames);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return memberStats;
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
	
	private String getStringDivFromCell(HtmlTableCell cell)
	{
		return cell.getElementsByTagName("div").get(0).asText();
	}
	
	
	private String getAnchorValueCell(HtmlTableCell cell)
	{
		return cell.getElementsByTagName("a").get(0).asText();
	}
	
	private int[] getSentinelAndScourgePointsFromCell(HtmlTableCell cell)
	{
		// References for Substring
		String sentinel 	= "Sentinel";
//		String scourge		= "Scourge";
//		String points		= "points";
		
		String pointsStr	= cell.asText();
		
		int sentinelPoint	= Integer.parseInt(pointsStr.substring(sentinel.length() + 1, pointsStr.indexOf(',')));
		int scourgePoint 	= sentinelPoint * (-1);
//		int scourgePoint	= Integer.parseInt(pointsStr.substring(pointsStr.indexOf(scourge) + scourge.length() + 1, pointsStr.indexOf(points) - 1));
		
		
		return new int[] { sentinelPoint, scourgePoint };
	}
	
}
