package com.example.guessmaster;

public abstract class Entity {
	private String name;
	private Date born;
	private double difficulty; //between 0 and 1
	
	public Entity(String name, Date birthDate, double difficulty) {
		this.name = name;
		this.born = new Date(birthDate); //no privacy leak
		this.difficulty = difficulty;
	}
	
	public Entity(Entity entity) {
		this.name = entity.name;
		this.born = new Date(entity.born); //no privacy leak
		this.difficulty = entity.difficulty;
	}

	public String getName() {
		return name;
	}

	public Date getBorn() {
		return new Date(born);
	}
	
	public String toString() {
		return "Name: "+name+"\n"+"Born at: "+born.toString()+"\n"+"Difficulty: " + difficulty +"\n";
	}
	
	/**
	 * @return the number of tickets awarded when the birth date is guessed correctly
	 */
	public int getAwardedTicketNumber() {
		return (int)(difficulty*100);
	}
	
	/**
	 * make a welcome message that states the entity type
	 * @return welcome message
	 */
	public String welcomeMessage() {
		return "Welcome! Let's start the game! " + entityType();
	}
	
	/**
	 * make a closing message that states the entity properties
	 * @return closing message
	 */
	public String closingMessage() {
		return "Congratudations! The detailed information of the entity you guessed is: \n" + toString();
	}
	
	/**
	 * state the type of entity. Must be declared by subclasses
	 * @return type of entity
	 */
	public abstract String entityType();
	
	/**
	 * create a deep clone of entity or subclass
	 * @return deep clone
	 */
	public abstract Entity clone();
}