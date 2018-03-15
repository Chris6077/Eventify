package com.example.schueler.eventures.pkg_data.classes_test;

public  class mEvent{
	private String name;
	private String time;
	private int participants;
	private Database.category cat;
	private String place;
	private String information;

	public mEvent(String name, String time, int participants, Database.category cat, String place, String information) {
		this.name = name;
		this.time = time;
		this.participants = participants;
		this.cat = cat;
		this.place = place;
		this.information = information;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}

	public Database.category getCat() {
		return cat;
	}

	public void setCat(Database.category cat) {
		this.cat = cat;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
}
