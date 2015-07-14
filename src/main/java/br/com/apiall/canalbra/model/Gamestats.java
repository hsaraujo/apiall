package br.com.apiall.canalbra.model;

import java.util.List;

public class Gamestats 
{
	private List<MemberGamestats> 	memberGamestatsList;
	private int 					sentinelPoints;
	private int 					scourgePoints;
	private String 					winner;
	private String					bestKiller;
	private String					bestFarmer;
	private String					bestTowerRaxKiller;
	
	public Gamestats() { }
	
	public Gamestats(List<MemberGamestats> memberGamestatsList, int sentinelPoints, int scourgePoints, String winner,
					String bestKiller, String bestFarmer, String bestTowerRaxKiller)
	{
		this.memberGamestatsList	= memberGamestatsList;
		this.sentinelPoints			= sentinelPoints;
		this.scourgePoints			= scourgePoints;
		this.winner					= winner;
		this.bestKiller				= bestKiller;
		this.bestFarmer				= bestFarmer;
		this.bestTowerRaxKiller		= bestTowerRaxKiller;
	}

	public List<MemberGamestats> getMemberGamestatsList() {
		return memberGamestatsList;
	}

	public void setMemberGamestatsList(List<MemberGamestats> memberGamestatsList) {
		this.memberGamestatsList = memberGamestatsList;
	}

	public int getSentinelPoints() {
		return sentinelPoints;
	}

	public void setSentinelPoints(int sentinelPoints) {
		this.sentinelPoints = sentinelPoints;
	}

	public int getScourgePoints() {
		return scourgePoints;
	}

	public void setScourgePoints(int scourgePoints) {
		this.scourgePoints = scourgePoints;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getBestKiller() {
		return bestKiller;
	}

	public void setBestKiller(String bestKiller) {
		this.bestKiller = bestKiller;
	}

	public String getBestFarmer() {
		return bestFarmer;
	}

	public void setBestFarmer(String bestFarmer) {
		this.bestFarmer = bestFarmer;
	}

	public String getBestTowerRaxKiller() {
		return bestTowerRaxKiller;
	}

	public void setBestTowerRaxKiller(String bestTowerRaxKiller) {
		this.bestTowerRaxKiller = bestTowerRaxKiller;
	}
	
	
}
