package br.com.apiall.canalbra.model;

public class Rank 
{

	private int position;
	private String name;
	private int points;
	private int games;
	private int wins;
	private int looses;
	private int winPercentage;
	private String favouriteHero;
	private String bestKD;
	private String bestCS;
	
	public Rank() { }
	
	public Rank(int position, String name, int points, int games, int wins, int looses, int winPercentage,
				String favouriteHero, String bestKD, String bestCS)
	{
		this.position 		= position;
		this.name			= name;
		this.points			= points;
		this.games			= games;
		this.wins			= wins;
		this.looses			= looses;
		this.winPercentage	= winPercentage;
		this.favouriteHero	= favouriteHero;
		this.bestKD			= bestKD;
		this.bestCS			= bestCS;
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
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

	public int getWinPercentage() {
		return winPercentage;
	}

	public void setWinPercentage(int winPercentage) {
		this.winPercentage = winPercentage;
	}

	public String getFavouriteHero() {
		return favouriteHero;
	}

	public void setFavouriteHero(String favouriteHero) {
		this.favouriteHero = favouriteHero;
	}

	public String getBestKD() {
		return bestKD;
	}

	public void setBestKD(String bestKD) {
		this.bestKD = bestKD;
	}

	public String getBestCS() {
		return bestCS;
	}

	public void setBestCS(String bestCS) {
		this.bestCS = bestCS;
	}
	
}
