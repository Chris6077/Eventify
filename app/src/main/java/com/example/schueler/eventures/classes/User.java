package com.example.schueler.eventures.classes;

import android.provider.ContactsContract;

import com.example.schueler.eventures.classes.enums.State;
import com.example.schueler.eventures.classes.enums.Type;

import java.util.Date;

/**
 * Created by schueler on 4/16/18.
 */

public class User {

	//fields
	private String firstname;
	private String lastname;
	private Date birthdate;
	private String username;
	private String email;
	private String password;
	private String profilePicture;
	private Type User;
	private State state;
	private Date created;
	private Date lastEdited;
	private int numberOfCreated;
	private int numberOfParticipated;

	//constructors


	public User(String firstname, String lastname, Date birthdate, String username, String email, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.username = username;
		this.email = email;
		this.password = password;
	}
}
