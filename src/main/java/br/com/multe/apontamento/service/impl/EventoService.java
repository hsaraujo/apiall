package br.com.multe.apontamento.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.multe.apontamento.model.Evento;
import br.com.multe.apontamento.service.IEventoService;
import br.com.multe.apontamento.utils.Constants;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
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
		doLogin("hsaraujo", "12345678");
		goToListPage();
		doFilterInListPage();
		
		List<Evento> eventos = new ArrayList<Evento>();
		
		eventos = getAllEvents();
		
		return eventos;
	}
	
	@Override
	public List<String> getOs() 
	{
		doLogin("hsaraujo", "12345678");
		goToListPage();
		goToNewPage();
		
		return getTextsFromCombo(Constants.NOVO_OS);
	}
	
	@Override
	public List<String> getCategorias() 
	{			
		return getTextsFromCombo(Constants.NOVO_CATEGORIA);
	}
	
	private List<String> getTextsFromCombo(String idCombo)
	{
		List<String> result = new ArrayList<String>();
		
		HtmlSelect select = page.getHtmlElementById(idCombo);
		for(HtmlOption option : select.getOptions())
		{
			if(!option.getValueAttribute().equalsIgnoreCase(""))
			{
				result.add(option.getText());
			}
		}
		
		return result;
	}
	
	private int getValueFromComboByText(String idCombo, String textToSearch)
	{
		HtmlSelect select = page.getHtmlElementById(idCombo);
		for(HtmlOption option : select.getOptions())
		{
			if(option.getText().equalsIgnoreCase(textToSearch))
				return Integer.parseInt(option.getValueAttribute());
		}
		
		return 0;
	}
	
	@Override
	public void insert(Evento evento) 
	{
		try
		{
//			doLogin("hsaraujo", "12345678");
//			goToListPage();
//			goToNewPage();
			
			int valOs = getValueFromComboByText(Constants.NOVO_OS, evento.getOs());
			int valCategoria = getValueFromComboByText(Constants.NOVO_CATEGORIA, evento.getCategoria());
			
			SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
			
			String data = sdfDate.format(evento.getInicio());
			String inicio = sdfTime.format(evento.getInicio());
			String fim = sdfTime.format(evento.getFim());
			
			page.getElementById(Constants.NOVO_OS).setNodeValue(String.valueOf(valOs));
			page.getElementById(Constants.NOVO_CATEGORIA).setNodeValue(String.valueOf(valCategoria));
			page.getElementById(Constants.NOVO_DATA).setNodeValue(String.valueOf(data));
			page.getElementById(Constants.NOVO_INICIO).setNodeValue(String.valueOf(inicio));
			page.getElementById(Constants.NOVO_FIM).setNodeValue(String.valueOf(fim));
			
			HtmlInput btnNovo = (HtmlInput) page.getElementById(Constants.NOVO_BOTAO);
			page = btnNovo.click();
			
			System.out.println(page);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
			
//			HtmlInput ateInput = page.getHtmlElementById(Constants.LISTA_ATE);
//			ateInput.remove();
//			page.appendChild(ateInput);
//			ateInput.setValueAttribute("23/04/2015");
			
			HtmlInput allInput = page.getHtmlElementById(Constants.LISTA_ALL);
			page = allInput.click();
			
			HtmlInput filtroInput = page.getHtmlElementById(Constants.LISTA_FILTRO);
			page = filtroInput.click();
			
			System.out.println(page.asText());
			
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
	
	private synchronized void goToNewPage()
	{
		try
		{
			HtmlInput novoInput = page.getHtmlElementById(Constants.LISTA_NOVO);
			page = novoInput.click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private synchronized void doLogin(String login, String senha)
	{
		try
		{
			page = webClient.getPage(Constants.LOGIN_URL);
			HtmlInput usuarioInput = page.getHtmlElementById(Constants.LOGIN_USUARIO);
			usuarioInput.setValueAttribute(login);
			HtmlInput senhaInput = page.getHtmlElementById(Constants.LOGIN_SENHA);
			senhaInput.setValueAttribute(senha);
			HtmlInput botaoInput = page.getHtmlElementById(Constants.LOGIN_BOTAO);
			page = botaoInput.click();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
