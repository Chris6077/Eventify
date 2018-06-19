package com.example.schueler.eventures.classes.pojo.local;

import com.example.schueler.eventures.classes.pojo.User;

import java.util.Date;

/**
 * Created by schueler on 4/23/18.
 */

public class LocalDatabase {



	//fields
	private static String uID;
	private static LocalDatabase database = null;


	//constructor
	private LocalDatabase() {
	}

	//getter & setter


	public static String getuID() {
		return uID;
	}

	public static void setuID(String uID) {
		LocalDatabase.uID = uID;
	}

	//custom
	public static LocalDatabase getInstance(){
		if(LocalDatabase.database == null)
			LocalDatabase.database = new LocalDatabase();
		return LocalDatabase.database;
	}

}
