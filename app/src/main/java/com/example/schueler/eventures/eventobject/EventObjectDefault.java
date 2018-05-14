package com.example.schueler.eventures.eventobject;

import java.util.EventObject;

/**
 * Created by schueler on 5/14/18.
 */

public class EventObjectDefault extends EventObject{

	private String message;

	/**
	 * Constructs a prototypical Event.
	 *
	 * @param source The object on which the Event initially occurred.
	 * @throws IllegalArgumentException if source is null.
	 */

	public EventObjectDefault(Object source) {
		super(source);
	}

	public EventObjectDefault(Object source,String message) {
		super(source);
		this.setMessage(message);
	}

	//getter & setter


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
