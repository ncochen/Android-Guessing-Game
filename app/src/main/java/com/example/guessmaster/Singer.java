package com.example.guessmaster;

public class Singer extends Person {
	private String debutAlbum; //singer's first album
	private Date debutAlbumReleaseDate; // date of debut album release
	
	/**
	 * Constructor defines all instance variables
	 * @param name
	 * @param birthDate
	 * @param difficulty
	 * @param gender
	 * @param debutAlbum
	 * @param debutAlbumReleaseDate
	 */
	public Singer(String name, Date birthDate, double difficulty, String gender, String debutAlbum,
			Date debutAlbumReleaseDate) {
		super(name, birthDate, difficulty, gender);
		this.debutAlbum = debutAlbum;
		this.debutAlbumReleaseDate = new Date(debutAlbumReleaseDate);
	}
	
	/**
	 * Copy Constructor creates a deep copy
	 * @param singer instance to be deep copied
	 */
	public Singer(Singer singer) {
		super(singer);
		this.debutAlbum = singer.debutAlbum;
		this.debutAlbumReleaseDate = new Date(singer.debutAlbumReleaseDate);
	}
	
	/**
	 * Create a deep copy of this instance
	 * @return deep copy
	 */
	public Singer clone() {
		return new Singer(this);
	}
	
	/**
	 * Create a string description of all instance variables
	 * @return description
	 */
	public String toString() {
		return super.toString() + "Debut album: " + debutAlbum + "\nDebut album release date: "
				+ debutAlbumReleaseDate + "\n";
	}
	
	/**
	 * Describe what type of entity this class is
	 * @return description
	 */
	public String entityType() {
		return "This is a singer!";
	}
}
