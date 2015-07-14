package br.com.apiall.canalbra.model;

public class Ban 
{
	private String 	name;
	private String 	reason;
	private String 	info;
	private int		remain;
	private String 	from;
	private String 	date;
	
	public Ban() { }
	
	public Ban(String name, String reason, String info, int remain, String from, String date)
	{
		this.name	= name;
		this.reason	= reason;
		this.info	= info;
		this.remain	= remain;
		this.from	= from;
		this.date	= date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getRemain() {
		return remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
