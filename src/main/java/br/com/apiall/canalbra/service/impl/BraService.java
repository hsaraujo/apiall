package br.com.apiall.canalbra.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.apiall.canalbra.service.IBraService;
import br.com.apiall.canalbra.utils.BraConstants;
import br.com.apiall.canalbra.utils.Rank;

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

}
