package br.com.apime.utils;

import java.nio.charset.Charset;

import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
public class GeneralHelper 
{
	public static String[] getLoginAndPasswordFromHeader(String authorization)
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
