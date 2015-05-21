package br.com.multe.apontamento.model;

public class Total 
{
	private double compras, pontos, resgates, atual;
	
	public Total() { } 
	
	public Total(double compras, double pontos, double resgates, double atual)
	{
		this.compras = compras;
		this.pontos = pontos;
		this.resgates = resgates;
		this.atual = atual;
	}

	public double getCompras() {
		return compras;
	}

	public void setCompras(double compras) {
		this.compras = compras;
	}

	public double getPontos() {
		return pontos;
	}

	public void setPontos(double pontos) {
		this.pontos = pontos;
	}

	public double getResgates() {
		return resgates;
	}

	public void setResgates(double resgates) {
		this.resgates = resgates;
	}

	public double getAtual() {
		return atual;
	}

	public void setAtual(double atual) {
		this.atual = atual;
	}
	
	
}
