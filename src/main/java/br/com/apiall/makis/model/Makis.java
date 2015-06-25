package br.com.apiall.makis.model;

import java.util.Date;

public class Makis 
{
	private String historico, unidade;
	private double custo, pontos;
	private Date data;
	
	public Makis() { }
	
	public Makis(String historico, String unidade, double custo, double pontos, Date data)
	{
		this.historico = historico;
		this.unidade = unidade;
		this.custo = custo;
		this.pontos = pontos;
		this.data = data;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public double getPontos() {
		return pontos;
	}

	public void setPontos(double pontos) {
		this.pontos = pontos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
}
