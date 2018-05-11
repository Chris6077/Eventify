/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */

package com.example.schueler.eventures.classes.pojo;


import com.example.schueler.eventures.classes.pojo.EventCategory;
import com.example.schueler.eventures.classes.pojo.Location;
import com.google.gson.Gson;

/**

 *

 * @author Chris

 */

public class MinimalEvent {

	private String eID;

	private transient Location location;

	private transient EventCategory category;



	public MinimalEvent(String eID, Location location, EventCategory category) {

		this.eID = eID;

		this.location = location;

		this.category = category;

	}



	public String geteID() {

		return eID;

	}



	public Location getLocation() {

		return location;

	}



	public EventCategory getCategory() {

		return category;

	}



	@Override

	public String toString() {

		return new Gson().toJson(this);

	}

}