package br.com.apime.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.apime.model.Apontamento;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonHelper 
{

	public static Apontamento getEventoFromJson(String json)
	{
		try
		{
			JsonObject jObj = (JsonObject) new JsonParser().parse(json);
	        JsonObject obj = jObj.getAsJsonObject("evento");
	
	        Apontamento apontamento = getEventoFromObject(obj);
	        
	        return apontamento;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static Date[] getDatesFilterFromJson(String json)
	{
		try
		{
			JsonObject jObj = (JsonObject) new JsonParser().parse(json);
	        JsonObject obj = jObj.getAsJsonObject("filtro");
	
	        Date[] dates = getDatesFromObject(obj);
	        
	        return dates;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	private static Date[] getDatesFromObject(JsonObject jsonObject)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String inicio = jsonObject.get("inicio").getAsString();
			String fim = jsonObject.get("fim").getAsString();
			
			Date[] dates = new Date[2];
			dates[0] = sdf.parse(inicio);
			dates[1] = sdf.parse(fim);
			
			return dates;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	private static Apontamento getEventoFromObject(JsonObject jsonObject)
    {
		try
		{
	        Apontamento apontamento = new Apontamento();
	        
	        apontamento.setOs(jsonObject.get("os").getAsString());
			apontamento.setCategoria(jsonObject.get("categoria").getAsString());
			apontamento.setDescricao(jsonObject.get("descricao").getAsString());
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			apontamento.setInicio(sdf.parse(jsonObject.get("inicio").getAsString()));
			apontamento.setFim(sdf.parse(jsonObject.get("fim").getAsString()));
	        
	        return apontamento;
		}
		catch(Exception e)
		{
			return null;
		}
    }
}
