package br.com.apiall.canalbra.model;

public class Game 
{
	private int 	id;
	private String 	name;
	private String 	owner;
	private String 	duration;
	private String 	date;
	
	public Game() { }
	
	public Game(int id, String name, String owner, String duration, String date)
	{
		this.id			= id;
		this.name		= name;
		this.owner		= owner;
		this.duration	= duration;
		this.date		= date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
