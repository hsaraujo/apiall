package br.com.apiall.crims.model;


public class Perfil
{
	private long id;
	private String username, pusher_id, cash, spirit_name, character_name, messages_icon, friends_icon, avatar, gangcenter_icon, tasks_icon;
	private double respect, credits, tolerance, strength, charisma, intelligence, cash_numeric, stamina, level_name, assault_points, addiction;
	private boolean under_protection;
	
	public Perfil(){ }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setId(String id) {
		this.id = Long.parseLong(id);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPusher_id(){
		return pusher_id;
	}
	
	public void setPusher_id(String pusher_id){
		this.pusher_id = pusher_id;
	}
	
	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public String getSpirit_name() {
		return spirit_name;
	}

	public void setSpirit_name(String spirit_name) {
		this.spirit_name = spirit_name;
	}

	public String getCharacter_name() {
		return character_name;
	}

	public void setCharacter_name(String character_name) {
		this.character_name = character_name;
	}

	public String getMessages_icon() {
		return messages_icon;
	}

	public void setMessages_icon(String messages_icon) {
		this.messages_icon = messages_icon;
	}

	public String getFriends_icon() {
		return friends_icon;
	}

	public void setFriends_icon(String friends_icon) {
		this.friends_icon = friends_icon;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getGangcenter_icon() {
		return gangcenter_icon;
	}

	public void setGangcenter_icon(String gangcenter_icon) {
		this.gangcenter_icon = gangcenter_icon;
	}

	public String getTasks_icon() {
		return tasks_icon;
	}

	public void setTasks_icon(String tasks_icon) {
		this.tasks_icon = tasks_icon;
	}

	public double getRespect() {
		return respect;
	}

	public void setRespect(double respect) {
		this.respect = respect;
	}
	
	public void setRespect(String respect) {
		this.respect = Double.parseDouble(respect);
	}

	public double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}
	
	public void setCredits(String credits) {
		this.credits = Double.parseDouble(credits);
	}

	public double getTolerance() {
		return tolerance;
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}
	
	public void setTolerance(String tolerance) {
		this.tolerance = Double.parseDouble(tolerance);
	} 

	public double getStrength() {
		return strength;
	}

	public void setStrength(double strength) {
		this.strength = strength;
	}

	public void setStrength(String strength) {
		this.strength = Double.parseDouble(strength);;
	}
	
	public double getCharisma() {
		return charisma;
	}

	public void setCharisma(double charisma) {
		this.charisma = charisma;
	}
	
	public void setCharisma(String charisma) {
		this.charisma = Double.parseDouble(charisma);
	}

	public double getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(double intelligence) {
		this.intelligence = intelligence;
	}
	
	public void setIntelligence(String intelligence) {
		this.intelligence = Double.parseDouble(intelligence);
	}

	public double getCash_numeric() {
		return cash_numeric;
	}

	public void setCash_numeric(double cash_numeric) {
		this.cash_numeric = cash_numeric;
	}
	
	public void setCash_numeric(String cash_numeric) {
		this.cash_numeric = Double.parseDouble(cash_numeric);
	}

	public double getStamina() {
		return stamina;
	}

	public void setStamina(double stamina) {
		this.stamina = stamina;
	}
	
	public void setStamina(String stamina) {
		this.stamina = Double.parseDouble(stamina);
	}

	public double getLevel_name() {
		return level_name;
	}

	public void setLevel_name(double level_name) {
		this.level_name = level_name;
	}
	
	public void setLevel_name(String level_name) {
		this.level_name = Double.parseDouble(level_name);
	}

	public double getAssault_points() {
		return assault_points;
	}

	public void setAssault_points(double assault_points) {
		this.assault_points = assault_points;
	}
	
	public void setAssault_points(String assault_points) {
		this.assault_points = Double.parseDouble(assault_points);
	}

	public double getAddiction() {
		return addiction;
	}

	public void setAddiction(double addiction) {
		this.addiction = addiction;
	}
	
	public void setAddiction(String addiction) {
		this.addiction = Double.parseDouble(addiction);
	}

	public boolean isUnder_protection() {
		return under_protection;
	}

	public void setUnder_protection(boolean under_protection) {
		this.under_protection = under_protection;
	}
	
	public void setUnder_protection(String under_protection) {
		this.under_protection = Boolean.parseBoolean(under_protection);
	}
	
	
}
