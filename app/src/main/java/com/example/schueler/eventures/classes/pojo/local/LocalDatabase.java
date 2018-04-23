package com.example.schueler.eventures.classes.pojo.local;

import com.example.schueler.eventures.classes.pojo.User;

import java.util.Date;

/**
 * Created by schueler on 4/23/18.
 */

public class LocalDatabase {

	private class currentUser extends User{

		private String UID;

		public currentUser(String firstName, String lastName, Date birthDate, String username, String email, String password, String profilePicture, String UID) {
			super(firstName, lastName, birthDate, username, email, password, profilePicture);
		}

		@Override
		public String getUID() {
			return UID;
		}

		@Override
		public void setUID(String UID) {
			this.UID = UID;
		}
	}
}
