package br.com.multe.apontamento.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.stereotype.Service;

import br.com.multe.apontamento.model.Evento;
import br.com.multe.apontamento.service.IEventoService;
import br.com.multe.apontamento.utils.Constants;

@Service
public class EventoService implements IEventoService
{

	@Override
	public List<Evento> getAll() 
	{
		List<Evento> eventos = new ArrayList<Evento>();
		
		return eventos;
	}

	
	private void doLogin()
	{
		String url = Constants.LOGIN_URL;
	}
	
}
