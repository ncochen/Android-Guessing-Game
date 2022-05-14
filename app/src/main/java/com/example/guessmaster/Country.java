package com.example.guessmaster;

public class Country extends Entity {
	private String capital; //capital city of the country

	/**
	 * Constructor defines all properties
	 * @param name
	 * @param birthDate
	 * @param difficulty
	 * @param capital
	 */
	public Country(String name, Date birthDate, double difficulty, String capital) {
		super(name, birthDate, difficulty);
		this.capital = capital;
	}
	
	/**
	 * Copy constructor
	 * @param country is copied
	 */
	public Country(Country country) {
		super(country);
		this.capital = country.capital;
	}
	
	/**
	 * return a deep copy of this instance
	 * @return deep copy
	 */
	public Country clone() {
		return new Country(this);
	}
	
	/**
	 * return string description of properties
	 * @return string description
	 */
	public String toString() {
		return super.toString() + "Capital city: " + capital + "\n"; 
	}
	
	/**
	 * return entity type as a String
	 * @return string description of entity (country)
	 */
	public String entityType() {
		return "This is a country!";
	}
}
