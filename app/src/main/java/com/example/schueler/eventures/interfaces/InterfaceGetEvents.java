package com.example.schueler.eventures.interfaces;

import com.example.schueler.eventures.classes.pojo.Event;
import com.example.schueler.eventures.classes.pojo.SlimEvent;

import java.util.ArrayList;

/**
 * Created by schueler on 5/7/18.
 */

public interface InterfaceGetEvents {
	public void onPreExecute();
	public void onPostExecute(ArrayList<SlimEvent> events);
}
