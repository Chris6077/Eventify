package com.example.schueler.eventures.classes.pojo.local;

/**
 * Created by schueler on 4/24/18.
 */

public class RegistrationUserObject {
	public String firstName;
	public String lastName;
	public String bithdate;
	public String email;
	public String password;

	public RegistrationUserObject(String firstname, String lastname, String bithdate, String email, String password) {
		this.firstName = firstname;
		this.lastName = lastname;
		this.bithdate = bithdate;
		this.email = email;
		this.password = password;
	}
}
