package br.com.apiall.model;

import java.util.List;

public class MakisReturn 
{
	private List<Makis> makis;
	private Total total;
	
	public MakisReturn() { }
	
	public MakisReturn(List<Makis> makis, Total total)
	{
		this.makis = makis;
		this.total = total;
	}
	
	public List<Makis> getMakis() {
		return makis;
	}
	public void setMakis(List<Makis> makis) {
		this.makis = makis;
	}
	public Total getTotal() {
		return total;
	}
	public void setTotal(Total total) {
		this.total = total;
	}
	
}
