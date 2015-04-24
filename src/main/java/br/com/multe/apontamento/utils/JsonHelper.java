package br.com.multe.apontamento.utils;

import java.text.SimpleDateFormat;

import br.com.multe.apontamento.model.Evento;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonHelper 
{

	public static Evento getEventoFromJson(String json)
	{
		try
		{
			JsonObject jObj = (JsonObject) new JsonParser().parse(json);
	        JsonObject obj = jObj.getAsJsonObject("evento");
	
	        Evento evento = getEventoFromObject(obj);
	        
	        return evento;
		}catch(Exception e)
		{
			return null;
		}
	}
	
	private static Evento getEventoFromObject(JsonObject jsonObject)
    {
		try
		{
	        Evento evento = new Evento();
	        
	        evento.setOs(jsonObject.get("os").getAsString());
			evento.setCategoria(jsonObject.get("categoria").getAsString());
			evento.setDescricao(jsonObject.get("descricao").getAsString());
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			evento.setInicio(sdf.parse(jsonObject.get("inicio").getAsString()));
			evento.setFim(sdf.parse(jsonObject.get("fim").getAsString()));
	        
	        return evento;
		}
		catch(Exception e)
		{
			return null;
		}
    }
}
