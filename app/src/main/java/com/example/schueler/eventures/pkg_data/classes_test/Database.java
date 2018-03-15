package com.example.schueler.eventures.pkg_data.classes_test;

import android.provider.ContactsContract;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by schueler on 3/15/18.
 */

public class Database {



	public enum category{
		SPORTS,
		PARTY,
		LADIES_NIGHT
	}

	private ArrayList<mEvent> events;

	private Database database = null;


	public Database() {
		events = new ArrayList<>();
	}



	public void add(mEvent event){
		events.add(event);
	}


	public ArrayList<mEvent> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<mEvent> events) {
		this.events = events;
	}
}

