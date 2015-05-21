package br.com.apiall.crims.service.impl;

import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import br.com.apiall.crims.service.ICrimsService;
import br.com.apiall.makis.utils.MakisConstants;

@Service
public class CrimsService implements ICrimsService 
{
	private WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
	private HtmlPage page;
	
	public boolean doLogin(String[] credentials)
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

			if(page.getUrl().toString().contains("start.php"))
				return true;
			else 
				return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
