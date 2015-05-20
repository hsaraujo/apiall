package br.com.multe.apontamento.controller;

import java.nio.charset.Charset;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.misc.BASE64Decoder;

@Controller
public class MakisController 
{

	private String[] getLoginAndPasswordFromHeader(String authorization)
	{
		try
		{
			if (authorization != null && authorization.startsWith("Basic")) {
		        String base64Credentials = authorization.substring("Basic".length()).trim();
		        BASE64Decoder decoder = new BASE64Decoder();
		        String credentials = new String(decoder.decodeBuffer(base64Credentials), Charset.forName("UTF-8"));
		        final String[] values = credentials.split(":", 2);
		        return values;
			}
	    else
	    	return null;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}