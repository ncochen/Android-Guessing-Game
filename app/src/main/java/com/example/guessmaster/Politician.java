package com.example.guessmaster;

public class Politician extends Person {
	String party; //political party of politician

	/**
	 * Constructor defines all instance variables
	 * @param name
	 * @param birthDate
	 * @param difficulty
	 * @param gender
	 * @param party
	 */
	public Politician(String name, Date birthDate, double difficulty, String gender, String party) {
		super(name, birthDate, difficulty, gender);
		this.party = party;
	}
	
	/**
	 * Constructor creates a deep copy of other instance of this class
	 * @param politician to be deep copied
	 */
	public Politician(Politician politician) {
		super(politician);
		this.party = politician.party;
	}
	
	/**
	 * Create a deep copy of this instance of the class
	 * @return deep copy
	 */
	public Politician clone() {
		return new Politician(this);
	}
	
	/**
	 * Return a string description of the class
	 * @return string description
	 */
	public String toString() {
		return super.toString() + "Party: " + party + "\n";
	}
	
	/**
	 * Return a string description of entity type
	 * @return string description
	 */
	public String entityType() {
		return "This is a politician!";
	}
}
