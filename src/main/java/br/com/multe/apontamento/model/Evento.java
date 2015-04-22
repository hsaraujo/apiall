package br.com.multe.apontamento.model;

import java.util.Date;

public class Evento 
{
	private long id;
	private Funcionario funcionario;
	private Os os;
	private Categoria categoria;
	private String descricao;
	private Date inicio, fim;
	
	public Evento() { }
	
	public Evento(long id, Funcionario funcionario, Os os, Categoria categoria, String descricao, Date inicio, Date fim)
	{
		this.id = id;
		this.funcionario = funcionario;
		this.os = os;
		this.categoria = categoria;
		this.descricao = descricao;
		this.inicio = inicio;
		this.fim = fim;		
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Os getOs() {
		return os;
	}

	public void setOs(Os os) {
		this.os = os;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
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
