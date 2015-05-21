package br.com.apiall.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.apiall.model.Apontamento;
import br.com.apiall.service.IApontamentoService;
import br.com.apiall.utils.ApontamentoConstants;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;

@Service
public class ApontamentoService implements IApontamentoService
{

	private WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
	private HtmlPage page;
	
	@Override
	public List<Apontamento> getEvents(String[] credentials)
	{
		doLogin(credentials);
		goToListPage();
		doFilterInListPage();
		
		List<Apontamento> apontamentos = new ArrayList<Apontamento>();
		
		apontamentos = getAllEvents();
		
		return apontamentos;
	}
	
	@Override
	public List<Apontamento> getEventsWithFilter(String[] credentials, Date inicio, Date fim)
	{
		doLogin(credentials);
		goToListPage();
		doFilterInListPage(inicio, fim);
		
		List<Apontamento> apontamentos = new ArrayList<Apontamento>();
		
		apontamentos = getAllEvents();
		
		return apontamentos;
	}
	
	@Override
	public Map<String, List<String>> getOsECategorias(String[] credentials)
	{
		doLogin(credentials);
		goToListPage();
		goToNewPage();
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("os", getOs());
		map.put("categoria", getCategorias());
		
		return map;
	}
	
	private List<String> getOs() 
	{
		return getTextsFromCombo(ApontamentoConstants.NOVO_OS);
	}
	
	private List<String> getCategorias() 
	{			
		return getTextsFromCombo(ApontamentoConstants.NOVO_CATEGORIA);
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
	public ResponseEntity<String> insert(String[] credentials, Apontamento apontamento) 
	{
		try
		{
			int valOs = 0;
			int valCategoria = 0;
			
			try
			{
				valOs = getValueFromComboByText(ApontamentoConstants.NOVO_OS, apontamento.getOs());
				valCategoria = getValueFromComboByText(ApontamentoConstants.NOVO_CATEGORIA, apontamento.getCategoria());
			}
			catch(NullPointerException npe)
			{
				doLogin(credentials);
				goToListPage();
				goToNewPage();
				insert(credentials, apontamento);
			}
			
			SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
			
			String data = sdfDate.format(apontamento.getInicio());
			String inicio = sdfTime.format(apontamento.getInicio());
			String fim = sdfTime.format(apontamento.getFim());
			
			HtmlSelect selectOs = (HtmlSelect) page.getElementById(ApontamentoConstants.NOVO_OS);
			selectOs.setSelectedAttribute(String.valueOf(valOs), true);
			
			HtmlSelect selectCategoria = (HtmlSelect) page.getElementById(ApontamentoConstants.NOVO_CATEGORIA);
			selectCategoria.setSelectedAttribute(String.valueOf(valCategoria), true);
			
			HtmlInput inputData = (HtmlInput) page.getElementById(ApontamentoConstants.NOVO_DATA);
			inputData.setValueAttribute(String.valueOf(data));
			
			HtmlInput inputInicio = (HtmlInput) page.getElementById(ApontamentoConstants.NOVO_INICIO);
			inputInicio.setValueAttribute(String.valueOf(inicio));
			
			HtmlInput inputFim = (HtmlInput) page.getElementById(ApontamentoConstants.NOVO_FIM);
			inputFim.setValueAttribute(String.valueOf(fim));
			
			HtmlTextArea inputDescricao = (HtmlTextArea) page.getElementById(ApontamentoConstants.NOVO_DESCRICAO);
			inputDescricao.setText(apontamento.getDescricao());
			
			HtmlInput inputBotao = (HtmlInput) page.getElementById(ApontamentoConstants.NOVO_BOTAO);
			page = inputBotao.click();
			
			HtmlSpan spanError = (HtmlSpan) page.getElementById(ApontamentoConstants.NOVO_ERRO);
			
			if(spanError != null)
			{
				String message = spanError.getFirstChild().getTextContent();

				return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<String>("Apontamento criado", HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return new ResponseEntity<String>("Erro não esperado", HttpStatus.BAD_REQUEST);
		}
	}
	
	private synchronized List<Apontamento> getAllEvents()
	{
		List<Apontamento> apontamentos = new ArrayList<Apontamento>();
		try
		{
			HtmlTable table = page.getHtmlElementById(ApontamentoConstants.LISTA_TABELA);
			List<HtmlTableRow> rows = table.getBodies().get(0).getRows();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
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
				
				Apontamento apontamento = new Apontamento(id, funcionario, os, dtInicio, dtFim);
				apontamentos.add(apontamento);
			}
		}catch(Exception e){ }
		return apontamentos;
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
			HtmlInput allInput = page.getHtmlElementById(ApontamentoConstants.LISTA_ALL);
			page = allInput.click();
			
			HtmlInput filtroInput = page.getHtmlElementById(ApontamentoConstants.LISTA_FILTRO);
			page = filtroInput.click();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private synchronized void doFilterInListPage(Date inicio, Date fim)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			HtmlInput deInput = page.getHtmlElementById(ApontamentoConstants.LISTA_DE);
			deInput.setValueAttribute(sdf.format(inicio));
			
			HtmlInput ateInput = page.getHtmlElementById(ApontamentoConstants.LISTA_ATE);
			ateInput.setValueAttribute(sdf.format(fim));
			
			HtmlInput allInput = page.getHtmlElementById(ApontamentoConstants.LISTA_ALL);
			page = allInput.click();
			
			HtmlInput filtroInput = page.getHtmlElementById(ApontamentoConstants.LISTA_FILTRO);
			page = filtroInput.click();
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
			HtmlInput novoInput = page.getHtmlElementById(ApontamentoConstants.LISTA_NOVO);
			page = novoInput.click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private synchronized void doLogin(String[] credentials)
	{
		try
		{
			String login = credentials[0];
			String senha = credentials[1];
			
			page = webClient.getPage(ApontamentoConstants.LOGIN_URL);
			HtmlInput usuarioInput = page.getHtmlElementById(ApontamentoConstants.LOGIN_USUARIO);
			usuarioInput.setValueAttribute(login);
			HtmlInput senhaInput = page.getHtmlElementById(ApontamentoConstants.LOGIN_SENHA);
			senhaInput.setValueAttribute(senha);
			HtmlInput botaoInput = page.getHtmlElementById(ApontamentoConstants.LOGIN_BOTAO);
			page = botaoInput.click();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public ResponseEntity<String> edit(String[] credentials, Apontamento apontamento) 
	{
		return null;
	}

	@Override
	public ResponseEntity<String> delete(String[] credentials, int id) 
	{
		return null;
	}

}
