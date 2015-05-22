package br.com.apiall.crims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.apiall.crims.model.Perfil;
import br.com.apiall.crims.model.Crime;
import br.com.apiall.crims.service.ICrimsService;
import br.com.apiall.utils.CrimsConstants;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

@Service
public class CrimsService implements ICrimsService 
{
	private WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
	private HtmlPage page;
	
	@Override
	public List<Crime> preparaCrime(String[] credentials)
	{
		doLogin(credentials);
		
		List<Crime> crimes = new ArrayList<Crime>();
		
		HtmlSelect cmbCrimes = (HtmlSelect) page.getElementsByTagName("select").get(0);
		for(HtmlOption option : cmbCrimes.getOptions())
		{
			String valor = option.getValueAttribute();
			String texto = option.getTextContent();
			Crime crime = new Crime(Integer.parseInt(valor), texto);
			crimes.add(crime);
		}
		
		return crimes;
	}
	
	@Override
	public Perfil getPerfil(String[] credentials)
	{
		doLogin(credentials);
		
		return getPerfil();
	}
	
	public Perfil getPerfil()
	{
		Perfil perfil = new Perfil();

		String moral = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(0).getTextContent();
		String respeito = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(1).getTextContent();
		String estamina = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(2).getTextContent();
		String vicio = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(3).getTextContent();
		String inteligencia = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(4).getTextContent();
		String carisma = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(5).getTextContent();
		String forca = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(6).getTextContent();
		String resistencia = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(7).getTextContent();
		String grana = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(8).getTextContent();
		grana = grana.substring(1);
		
		perfil.setMoral(moral);
		perfil.setRespeito(Double.parseDouble(respeito));
		perfil.setEstamina(Double.parseDouble(estamina));
		perfil.setVicio(Double.parseDouble(vicio));
		perfil.setInteligencia(Double.parseDouble(inteligencia));
		perfil.setCarisma(Double.parseDouble(carisma));
		perfil.setForca(Double.parseDouble(forca));
		perfil.setResistencia(Double.parseDouble(resistencia));
		perfil.setGrana(Double.parseDouble(grana));
		
		
		return perfil;
	}
	
	@Override
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
