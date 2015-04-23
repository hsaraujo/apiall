package br.com.multe.apontamento.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.multe.apontamento.model.Evento;
import br.com.multe.apontamento.service.IEventoService;
import br.com.multe.apontamento.utils.Constants;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

@Service
public class EventoService implements IEventoService
{

	private WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
	private HtmlPage page;
	
	@Override
	public List<Evento> getAll() 
	{
		doLogin();
		goToListPage();
		doFilterInListPage();
		
		List<Evento> eventos = new ArrayList<Evento>();
		
		eventos = getAllEvents();
		
		return eventos;
	}
	
	private synchronized List<Evento> getAllEvents()
	{
		HtmlTable table = page.getHtmlElementById(Constants.LISTA_TABELA);
		List<HtmlTableRow> rows = table.getBodies().get(0).getRows();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		List<Evento> eventos = new ArrayList<Evento>();
		
		for(int i = 1; i < rows.size(); i++)
		{
			HtmlTableRow row = rows.get(i);
			
			String data = row.getCell(0).asText();
			String funcionario = row.getCell(1).asText();
			String inicio = row.getCell(2).asText();
			String fim = row.getCell(3).asText();
			String os = row.getCell(6).asText();
			long id = getIdFromCell(row.getCell(7));

			Date dtInicio = new Date();
			Date dtFim = new Date();
			try
			{
				dtInicio = sdf.parse(data + " " + inicio);
				dtFim = sdf.parse(data + " " + fim);
			}catch (Exception e ) { }
			
			Evento evento = new Evento(id, funcionario, os, dtInicio, dtFim);
			eventos.add(evento);
		}
		
		return eventos;
	}
	
	private long getIdFromCell(HtmlTableCell cell)
	{
		HtmlImage img = (HtmlImage) cell.getElementsByTagName("img").get(0);
		String onclick = img.getOnClickAttribute();
		onclick = onclick.substring(onclick.indexOf("ID=") + 3);
		onclick = onclick.substring(0, onclick.indexOf("&"));
		return Long.parseLong(onclick);
	}
	
	private synchronized void doFilterInListPage()
	{
		try
		{
			HtmlInput deInput = page.getHtmlElementById(Constants.LISTA_DE);
			deInput.setValueAttribute("01/04/2015");
			
			HtmlInput ateInput = page.getHtmlElementById(Constants.LISTA_ATE);
			ateInput.setValueAttribute("23/04/2015");
			
			HtmlInput allInput = page.getHtmlElementById(Constants.LISTA_ALL);
			allInput.click();
			
			HtmlInput filtroInput = page.getHtmlElementById(Constants.LISTA_FILTRO);
			filtroInput.click();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private synchronized void goToListPage()
	{
		try
		{
			for (HtmlAnchor anchor : page.getAnchors())
			{
				if(anchor.getTextContent().equalsIgnoreCase("Apontamentos") && !anchor.getHrefAttribute().equalsIgnoreCase("#"))
					page = anchor.click();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private synchronized void doLogin()
	{
		try
		{
			page = webClient.getPage(Constants.LOGIN_URL);
			HtmlInput usuarioInput = page.getHtmlElementById(Constants.LOGIN_USUARIO);
			usuarioInput.setValueAttribute("hsaraujo");
			HtmlInput senhaInput = page.getHtmlElementById(Constants.LOGIN_SENHA);
			senhaInput.setValueAttribute("12345678");
			HtmlInput botaoInput = page.getHtmlElementById(Constants.LOGIN_BOTAO);
			page = botaoInput.click();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
