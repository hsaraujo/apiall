package br.com.multe.apontamento.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.multe.apontamento.model.Makis;
import br.com.multe.apontamento.model.MakisReturn;
import br.com.multe.apontamento.model.Total;
import br.com.multe.apontamento.service.IMakisService;
import br.com.multe.apontamento.utils.MakisConstants;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

@Service
public class MakisService implements IMakisService
{
	private WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
	private HtmlPage page;

	@Override
	public MakisReturn getHistorico(String[] credentials)
	{
		doLogin(credentials);
		
		List<Makis> makis = new ArrayList<Makis>();
		Total total = new Total();
		
		makis = getAllHistoricos();
		total = getTotal();
		
		return new MakisReturn(makis, total);
	}
	
	private Total getTotal()
	{
		Total total = new Total();
		
		try
		{
			HtmlTable table = (HtmlTable) page.getElementsByTagName("table").get(4);
			List<HtmlTableRow> rows = table.getBodies().get(0).getRows();
			
			total.setCompras(Double.parseDouble(rows.get(rows.size() - 4).getCell(4).asText()));
			total.setPontos(Double.parseDouble(rows.get(rows.size() - 3).getCell(4).asText()));
			total.setResgates(Double.parseDouble(rows.get(rows.size() - 2).getCell(4).asText()));
			total.setAtual(Double.parseDouble(rows.get(rows.size() -1).getCell(4).asText()));
		}
		catch(Exception e){ }
		
		return total;
	}
	
	private synchronized List<Makis> getAllHistoricos()
	{
		List<Makis> makisList = new ArrayList<Makis>();
		try
		{
			HtmlTable table = (HtmlTable) page.getElementsByTagName("table").get(4);
			List<HtmlTableRow> rows = table.getBodies().get(0).getRows();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
	
			for(int i = 1; i < rows.size() - 4; i++)
			{
				HtmlTableRow row = rows.get(i);
				Makis makis = new Makis();
				
				String data = row.getCell(0).asText();
				String custo = row.getCell(1).asText();
				String pontos = row.getCell(2).asText();
				String historico = row.getCell(3).asText();
				String unidade = row.getCell(5).asText();
	
				try
				{
					makis.setData(sdf.parse(data));					
				}
				catch (Exception e) 
				{
					makis.setData(sdf.parse("01-01-01"));
				}
				
				try
				{
					makis.setCusto(Double.parseDouble(custo));
				}
				catch(Exception e)
				{
					makis.setCusto(0);
				}
				
				try
				{
					makis.setPontos(Double.parseDouble(pontos));
				}
				catch(Exception e)
				{
					makis.setPontos(0);
				}
				
				makis.setHistorico(historico);
				makis.setUnidade(unidade);
				
				makisList.add(makis);
			}
		}catch(Exception e){ }
		
		return makisList;
	}
	
	private synchronized void doLogin(String[] credentials)
	{
		try
		{
			String login = credentials[0];
			String senha = credentials[1];
			
			page = webClient.getPage(MakisConstants.LOGIN_URL);
			HtmlInput usuarioInput = (HtmlInput) page.getElementsByName(MakisConstants.LOGIN_USUARIO).get(0);
			usuarioInput.setValueAttribute(login);
			HtmlInput senhaInput = (HtmlInput) page.getElementsByName(MakisConstants.LOGIN_SENHA).get(0);
			senhaInput.setValueAttribute(senha);
			HtmlInput botaoInput = (HtmlInput) page.getElementsByName(MakisConstants.LOGIN_BOTAO).get(0);
			page = botaoInput.click();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
