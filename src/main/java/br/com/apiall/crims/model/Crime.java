package br.com.apiall.crims.model;

public class Crime 
{
	private long id;
	private double difficulty, energy, successprobability, type, user_power;
	private String long_name, max_reward, min_reward, name, requiredstats, spirit, spiritname, successprobability_image, translatedname;

	public Crime() { }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public double getSuccessprobability() {
		return successprobability;
	}

	public void setSuccessprobability(double successprobability) {
		this.successprobability = successprobability;
	}

	public double getType() {
		return type;
	}

	public void setType(double type) {
		this.type = type;
	}

	public double getUser_power() {
		return user_power;
	}

	public void setUser_power(double user_power) {
		this.user_power = user_power;
	}

	public String getLong_name() {
		return long_name;
	}

	public void setLong_name(String long_name) {
		this.long_name = long_name;
	}

	public String getMax_reward() {
		return max_reward;
	}

	public void setMax_reward(String max_reward) {
		this.max_reward = max_reward;
	}

	public String getMin_reward() {
		return min_reward;
	}

	public void setMin_reward(String min_reward) {
		this.min_reward = min_reward;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRequiredstats() {
		return requiredstats;
	}

	public void setRequiredstats(String requiredstats) {
		this.requiredstats = requiredstats;
	}

	public String getSpirit() {
		return spirit;
	}

	public void setSpirit(String spirit) {
		this.spirit = spirit;
	}

	public String getSpiritname() {
		return spiritname;
	}

	public void setSpiritname(String spiritname) {
		this.spiritname = spiritname;
	}

	public String getSuccessprobability_image() {
		return successprobability_image;
	}

	public void setSuccessprobability_image(String successprobability_image) {
		this.successprobability_image = successprobability_image;
	}

	public String getTranslatedname() {
		return translatedname;
	}

	public void setTranslatedname(String translatedname) {
		this.translatedname = translatedname;
	}
	
	
	
}
