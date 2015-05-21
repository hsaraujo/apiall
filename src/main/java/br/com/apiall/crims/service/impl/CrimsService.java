package br.com.apiall.crims.service.impl;

import org.springframework.stereotype.Service;

import br.com.apiall.crims.service.ICrimsService;
import br.com.apiall.utils.CrimsConstants;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

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
			
			page = webClient.getPage(CrimsConstants.LOGIN_URL);
			HtmlInput usuarioInput = (HtmlInput) page.getElementsByName(CrimsConstants.LOGIN_USUARIO).get(0);
			usuarioInput.setValueAttribute(login);
			HtmlInput senhaInput = (HtmlInput) page.getElementsByName(CrimsConstants.LOGIN_SENHA).get(0);
			senhaInput.setValueAttribute(senha);
			HtmlForm formLogin = (HtmlForm) page.getElementById(CrimsConstants.LOGIN_FORM);
			page = ((HtmlButton) formLogin.getElementsByTagName("button").get(0)).click();

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
