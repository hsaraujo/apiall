package br.com.apiall.crims.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.apiall.crims.model.Crime;
import br.com.apiall.crims.model.Perfil;
import br.com.apiall.crims.service.ICrimsService;
import br.com.apiall.crims.utils.CrimsConstants;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
public class CrimsService implements ICrimsService 
{
	private WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
	private HtmlPage page;
	
	@Override
	public List<Crime> preparaCrime(String[] credentials)
	{
		doLogin(credentials);
		goToRobbery();
		
		String javascriptCode = "JSON.stringify(angular.element($('.ng-scope')[1]).scope().robberies)";
		
		Object object = (Object) page.executeJavaScript(javascriptCode).getJavaScriptResult();		
		
		Type listType = new TypeToken<ArrayList<Crime>>() { }.getType();
		
		List<Crime> crimes = new GsonBuilder().create().fromJson(String.valueOf(object), listType);
		
//		HtmlSelect cmbCrimes = (HtmlSelect) page.getElementsByTagName("select").get(0);
//		for(HtmlOption option : cmbCrimes.getOptions())
//		{
//			String valor = option.getValueAttribute();
//			String texto = option.getTextContent();
//			Crime crime = new Crime(Integer.parseInt(valor), texto);
//			crimes.add(crime);
//		}
//		
		return crimes;
	}
	
	public void doCrime(int id)
	{
		String setFormScript = "var formData = { action : 'single_robbery', id : "+id+" }";
		String ajax = "$.ajax({" +
							"url: '/api/index.php'," +
							"contentType: 'application/x-www-form-urlencoded'," +
							"type: 'POST'," +
							"data: formData," +
							"success: function(data) {"+
								"console.log(JSON.parse(data));" +
							"}," +
							"error: function(jqXHR, textStatus, errorThrown) {" +
								"console.log(textStatus);" +
	        				"}"+
    					"});";
		
		String javascriptCode = setFormScript + ajax;
		
		Object object = (Object) page.executeJavaScript(javascriptCode).getJavaScriptResult();
	}
	
	private synchronized void goToRobbery()
	{
		try{
			page = webClient.getPage(CrimsConstants.ROUBO_URL);
		}
		catch (Exception e) { }
	}
	
	@Override
	public Perfil getPerfil(String[] credentials)
	{
		if(!doLogin(credentials))
		{
			webClient.closeAllWindows();
			doLogin(credentials);
		}
		
		return getPerfil();
	}
	
	public Perfil getPerfil()
	{
		Perfil perfil = new Perfil();

		String javascriptCode = "JSON.stringify(angular.element($('.ng-scope')[0]).scope().user)";
		
		Object object = (Object) page.executeJavaScript(javascriptCode).getJavaScriptResult();
		perfil = new GsonBuilder().create().fromJson(String.valueOf(object), Perfil.class);
//		perfil = ObjectHelper.getProfileFromNativeObject(object);
		return perfil;
		
//		String moral = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(0).getTextContent();
//		String respeito = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(1).getTextContent();
//		String estamina = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(2).getTextContent();
//		String vicio = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(3).getTextContent();
//		String inteligencia = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(4).getTextContent();
//		String carisma = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(5).getTextContent();
//		String forca = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(6).getTextContent();
//		String resistencia = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(7).getTextContent();
//		String grana = page.getElementById(CrimsConstants.GERAL_ID_PERFIL).getElementsByTagName("span").get(8).getTextContent();
//		grana = grana.substring(1);
//		
//		perfil.setMoral(moral);
//		perfil.setRespeito(Double.parseDouble(respeito));
//		perfil.setEstamina(Double.parseDouble(estamina));
//		perfil.setVicio(Double.parseDouble(vicio));
//		perfil.setInteligencia(Double.parseDouble(inteligencia));
//		perfil.setCarisma(Double.parseDouble(carisma));
//		perfil.setForca(Double.parseDouble(forca));
//		perfil.setResistencia(Double.parseDouble(resistencia));
//		perfil.setGrana(Double.parseDouble(grana));
		
		
//		return perfil;
	}
	
	@Override
	public synchronized boolean doLogin(String[] credentials)
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

			System.out.println(page.asText());
			
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
