package br.com.apiall.apontamento.model;

import java.util.Date;

public class Apontamento 
{
	private long id;
	private String funcionario;
	private String os;
	private String categoria;
	private String descricao;
	private Date inicio, fim;
	
	public Apontamento() { }
	
	public Apontamento(long id, String funcionario, String os, String categoria, String descricao, Date inicio, Date fim)
	{
		this.id = id;
		this.funcionario = funcionario;
		this.os = os;
		this.categoria = categoria;
		this.descricao = descricao;
		this.inicio = inicio;
		this.fim = fim;		
	}
	
	public Apontamento(long id, String funcionario, String os, Date inicio, Date fim)
	{
		this.id = id;
		this.funcionario = funcionario;
		this.os = os;
		this.inicio = inicio;
		this.fim = fim;		
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}
	
	
}
