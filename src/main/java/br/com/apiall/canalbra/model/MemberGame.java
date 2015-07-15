package br.com.apiall.canalbra.model;

public class MemberGame 
{
	private int 		gameId;
	private String 		date;
	private String		duration;
	private String 		hero;
	private String[]	items = new String[6];
	private String 		team;
	private boolean 	winner;
	private int 		heroKill;
	private int 		heroDeath;
	private int			assist;
	private int 		creepKill;
	private int			creepDeny;
	private int 		neutral;
	private int			gold;
	private int 		towerKill;
	private	int 		rk;
	private int			ck;
	
	public MemberGame() { }
	
	public MemberGame(int gameId, String date, String duration, String hero, String[] items, String team, boolean winner,
					int heroKill, int heroDeath, int assist, int creepKill, int creepDeny, int neutral, int gold,
					int towerKill, int rk, int ck)
	{
		this.gameId		= gameId;
		this.date		= date;
		this.duration	= duration;
		this.hero		= hero;
		this.items		= items;
		this.team		= team;
		this.winner		= winner;
		this.heroKill	= heroKill;
		this.heroDeath	= heroDeath;
		this.assist		= assist;
		this.creepKill	= creepKill;
		this.creepDeny	= creepDeny;
		this.neutral	= neutral;
		this.gold		= gold;
		this.towerKill	= towerKill;
		this.rk			= rk;
		this.ck			= ck;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getHero() {
		return hero;
	}

	public void setHero(String hero) {
		this.hero = hero;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public int getHeroKill() {
		return heroKill;
	}

	public void setHeroKill(int heroKill) {
		this.heroKill = heroKill;
	}

	public int getHeroDeath() {
		return heroDeath;
	}

	public void setHeroDeath(int heroDeath) {
		this.heroDeath = heroDeath;
	}

	public int getAssist() {
		return assist;
	}

	public void setAssist(int assist) {
		this.assist = assist;
	}

	public int getCreepKill() {
		return creepKill;
	}

	public void setCreepKill(int creepKill) {
		this.creepKill = creepKill;
	}

	public int getCreepDeny() {
		return creepDeny;
	}

	public void setCreepDeny(int creepDeny) {
		this.creepDeny = creepDeny;
	}

	public int getNeutral() {
		return neutral;
	}

	public void setNeutral(int neutral) {
		this.neutral = neutral;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getTowerKill() {
		return towerKill;
	}

	public void setTowerKill(int towerKill) {
		this.towerKill = towerKill;
	}

	public int getRk() {
		return rk;
	}

	public void setRk(int rk) {
		this.rk = rk;
	}

	public int getCk() {
		return ck;
	}

	public void setCk(int ck) {
		this.ck = ck;
	}
	
	
}
