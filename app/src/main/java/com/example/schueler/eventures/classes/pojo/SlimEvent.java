package com.example.schueler.eventures.classes.pojo;/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */


import com.google.gson.Gson;

import java.io.Serializable;

/**

 *

 * @author Chris

 */

public class SlimEvent implements Serializable{

	private String eID;

	private String name;

	private  int totalLikes;

	private int maxParticipators;

	private int totalParticipators;



	public SlimEvent(String eID, String name, int totalLikes, int maxParticipators, int totalParticipators) {

		this.eID = eID;

		this.name = name;

		this.totalLikes = totalLikes;

		this.maxParticipators = maxParticipators;

		this.totalParticipators = totalParticipators;

	}

	public String geteID() {

		return eID;

	}

	public String getName() {

		return name;

	}

	public int getTotalLikes() {

		return totalLikes;

	}

	public int getMaxParticipators() {

		return maxParticipators;

	}

	public int getTotalParticipators() {

		return totalParticipators;

	}

	@Override
	public String toString() {
		return "SlimEvent{" +
				"eID='" + eID + '\'' +
				", name='" + name + '\'' +
				", totalLikes=" + totalLikes +
				", maxParticipators=" + maxParticipators +
				", totalParticipators=" + totalParticipators +
				'}';
	}
}