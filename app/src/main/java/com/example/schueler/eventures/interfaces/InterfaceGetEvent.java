package com.example.schueler.eventures.interfaces;

import com.example.schueler.eventures.classes.pojo.Event;

import java.util.ArrayList;

/**
 * Created by schueler on 5/10/18.
 */

public interface InterfaceGetEvent {
	public void onPreExecute();
	public void onPostExecute(Event event);
}
