package br.com.apiall.crims.utils;

import net.sourceforge.htmlunit.corejs.javascript.NativeObject;
import br.com.apiall.crims.model.Perfil;

public class ObjectHelper 
{

	public static Perfil getProfileFromNativeObject(NativeObject object)
	{
		Perfil perfil = new Perfil();
		for(Object key : object.getAllIds())
		{		
			String value = "";
			try
			{
				value = String.valueOf(object.get(key));
			}catch(Exception e)
			{
				continue;
			}
			if(String.valueOf(key).equals("id"))
				perfil.setId(value);
			else if(String.valueOf(key).equals("username"))
				perfil.setUsername(value);
			else if(String.valueOf(key).equals("respect"))
				perfil.setRespect(value);
			else if(String.valueOf(key).equals("credits"))
				perfil.setCredits(value);
			else if(String.valueOf(key).equals("tolerance"))
				perfil.setTolerance(value);
			else if(String.valueOf(key).equals("strength"))
				perfil.setStrength(value);
			else if(String.valueOf(key).equals("charisma"))
				perfil.setCharisma(value);
			else if(String.valueOf(key).equals("intelligence"))
				perfil.setIntelligence(value);
			else if(String.valueOf(key).equals("cash"))
				perfil.setCash(value);
			else if(String.valueOf(key).equals("cash_numeric"))
				perfil.setCash_numeric(value);
			else if(String.valueOf(key).equals("stamina"))
				perfil.setStamina(value);
			else if(String.valueOf(key).equals("spirit_name"))
				perfil.setSpirit_name(value);
			else if(String.valueOf(key).equals("level_name"))
				perfil.setLevel_name(value);
			else if(String.valueOf(key).equals("assault_points"))
				perfil.setAssault_points(value);
			else if(String.valueOf(key).equals("character_name"))
				perfil.setCharacter_name(value);
			else if(String.valueOf(key).equals("addiction"))
				perfil.setAddiction(value);
			else if(String.valueOf(key).equals("messages_icon"))
				perfil.setMessages_icon(value);
			else if(String.valueOf(key).equals("friends_icon"))
				perfil.setFriends_icon(value);
			else if(String.valueOf(key).equals("avatar"))
				perfil.setAvatar(value);
			else if(String.valueOf(key).equals("under_protection"))
				perfil.setUnder_protection(value);
			else if(String.valueOf(key).equals("gangcenter_icon"))
				perfil.setGangcenter_icon(value);
			else if(String.valueOf(key).equals("tasks_icon"))
				perfil.setTasks_icon(value);
			
		}
		
		return perfil;
	}
}
