package br.com.apiall.canalbra.model;

public class Member 
{
	private int rank;
	private int points;
	private int lvl;
	private String joined;
	private int games;
	private int wins;
	private int looses;
	private int heroKill;
	private int heroDeath;
	private int assist;
	private int creepKill;
	private int creepDeny;
	private int neutral;
	private int towerKill;
	private int rk;
	private int ck;
	
	public Member() { }
	
	public Member(int rank, int points, int lvl, String joined, int games, int wins, int looses, int heroKill,
				int heroDeath, int assist, int creepKill, int creepDeny, int neutral, int towerKill, int rk, int ck)
	{
		this.rank		= rank;
		this.points		= points;
		this.lvl		= lvl;
		this.joined		= joined;
		this.games		= games;
		this.wins		= wins;
		this.looses		= looses;
		this.heroKill	= heroKill;
		this.heroDeath	= heroDeath;
		this.assist		= assist;
		this.creepKill	= creepKill;
		this.creepDeny	= creepDeny;
		this.neutral	= neutral;
		this.towerKill	= towerKill;
		this.rk			= rk;
		this.ck			= ck;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public String getJoined() {
		return joined;
	}

	public void setJoined(String joined) {
		this.joined = joined;
	}

	public int getGames() {
		return games;
	}

	public void setGames(int games) {
		this.games = games;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLooses() {
		return looses;
	}

	public void setLooses(int looses) {
		this.looses = looses;
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
