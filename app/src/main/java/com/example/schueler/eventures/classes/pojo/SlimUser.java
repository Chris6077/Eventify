package com.example.schueler.eventures.classes.pojo;

import com.example.schueler.eventures.classes.pojo.UserType;
import com.google.gson.Gson;

import java.util.Date;
/**
 *
 * @author Chris
 */
public class SlimUser {
	private String uID;
	private transient String firstName;
	private transient String lastName;
	private transient String profilePicture;
	private transient UserType type;
	private transient int numberOfCreated;
	private transient int numberOfParticipated;
	private transient double rating;
	private transient int totalFollowers;
	private transient Date created;

	public SlimUser(String uID, String firstName, String lastName, String profilePicture, UserType type, int numberOfCreated, int numberOfParticipated, double rating, int totalFollowers, Date created) {
		this.uID = uID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profilePicture = profilePicture;
		this.type = type;
		this.numberOfCreated = numberOfCreated;
		this.numberOfParticipated = numberOfParticipated;
		this.rating = rating;
		this.totalFollowers = totalFollowers;
		this.created = created;
	}

	public String getuID() {
		return uID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public UserType getType() {
		return type;
	}

	public int getNumberOfCreated() {
		return numberOfCreated;
	}

	public int getNumberOfParticipated() {
		return numberOfParticipated;
	}

	public double getRating() {
		return rating;
	}

	public int getTotalFollowers() {
		return totalFollowers;
	}

	public Date getCreated() {
		return created;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}