package com.example.schueler.eventures.asynctask;

import android.os.AsyncTask;

import com.example.schueler.eventures.classes.pojo.Event;
import com.example.schueler.eventures.classes.pojo.EventCategory;
import com.example.schueler.eventures.classes.pojo.EventState;
import com.example.schueler.eventures.classes.pojo.EventType;
import com.example.schueler.eventures.classes.pojo.Location;
import com.example.schueler.eventures.interfaces.InterfaceGetEvent;
import com.example.schueler.eventures.interfaces.InterfaceGetEvents;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by schueler on 5/7/18.
 */

public class TaskGetEvent extends AsyncTask<Object, Object, Event> {

	//fields
	private String url;
	private InterfaceGetEvent listener;

	//constructors
	public TaskGetEvent(String url, InterfaceGetEvent listener) {
		this.setUrl(url);
		this.setListener(listener);
	}

	//getter & setter
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public InterfaceGetEvent getListener() {
		return listener;
	}

	public void setListener(InterfaceGetEvent listener) {
		this.listener = listener;
	}

	//super
	@Override
	protected Event doInBackground(Object... params) {
		try {
			Gson gson = new Gson();
//			HttpURLConnection conn = (HttpURLConnection) new URL(this.getUrl()).openConnection();
//			String result = GetData(conn);
//			Event event = gson.fromJson(result, Event.class);

			Event event =  new Event("name","191u11041401", EventState.Confirmed,"no description",200,21, EventType.Public, EventCategory.Other, new Date(),new Date());
			event.setLocation(new Location(12,12));
			return event;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Event event){
		this.getListener().onPostExecute(event);
		super.onPostExecute(event);
	}

	@Override
	protected void onPreExecute() {
		this.getListener().onPreExecute();
		super.onPreExecute();
	}


	//custom

	private String GetData(HttpURLConnection conn){
		BufferedReader reader;
		String content = null;

		try{
			//reading the result

			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			content = sb.toString();
			reader.close();
			conn.disconnect();


		}catch(Exception error){
			System.out.println("ERROR --- " + error);
		}
		return content;
	}

}