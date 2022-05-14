package com.example.guessmaster;

public class Person extends Entity{
	private String gender;

	/**
	 * Constructor defines all properties of class
	 * @param name
	 * @param birthDate
	 * @param difficulty
	 * @param gender
	 */
	public Person(String name, Date birthDate, double difficulty, String gender) {
		super(name, birthDate, difficulty);
		this.gender = gender;
	}
	
	/**
	 * Constructor creates an instance of person as a deep copy of another person
	 * @param person to be deep copied
	 */
	public Person(Person person) {
		super(person);
		this.gender = person.gender;
	}
	
	/**
	 * Method creates a deep copy of this person
	 * @return deep copy
	 */
	public Person clone() {
		return new Person(this);
	}
	
	/**
	 * Return description of person instance variables
	 * @return string description
	 */
	public String toString() {
		return super.toString() + "Gender: " + gender + "\n";
	}
	
	/**
	 * Return string describing entity type
	 * @return string description
	 */
	public String entityType() {
		return "This is a person!";
	}
}
