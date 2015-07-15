package br.com.apiall.canalbra.model;

public class MemberGamestats 
{
	private int 		slot;
	private String 		name;
	private String 		hero;
	private int 		heroKill;
	private int			heroDeath;
	private int			assistence;
	private int 		creepKill;
	private int			creepDenny;
	private int 		neutral;
	private int 		gold;
	private int			towerKill;
	private int 		rk;
	private int			ck;
	private String[]	items;
	
	public MemberGamestats() { }
	
	public MemberGamestats(int slot, String name, String hero, int heroKill, int heroDeath, int assistence, int creepKill,
					int creepDenny, int neutral, int gold, int towerKill,int rk, int ck, String[] items)
	{
		this.slot		= slot;
		this.name		= name;
		this.hero		= hero;
		this.heroKill	= heroKill;
		this.heroDeath	= heroDeath;
		this.assistence	= assistence;
		this.creepKill	= creepKill;
		this.creepDenny	= creepDenny;
		this.neutral	= neutral;
		this.gold		= gold;
		this.towerKill	= towerKill;
		this.rk			= rk;
		this.ck			= ck;
		this.items 		= items;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHero() {
		return hero;
	}

	public void setHero(String hero) {
		this.hero = hero;
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

	public int getAssistence() {
		return assistence;
	}

	public void setAssistence(int assistence) {
		this.assistence = assistence;
	}

	public int getCreepKill() {
		return creepKill;
	}

	public void setCreepKill(int creepKill) {
		this.creepKill = creepKill;
	}

	public int getCreepDenny() {
		return creepDenny;
	}

	public void setCreepDenny(int creepDenny) {
		this.creepDenny = creepDenny;
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

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
}
