package com.example.schueler.eventures.asynctask;

import android.os.AsyncTask;

import com.example.schueler.eventures.classes.pojo.Event;
import com.example.schueler.eventures.classes.pojo.EventCategory;
import com.example.schueler.eventures.classes.pojo.EventState;
import com.example.schueler.eventures.classes.pojo.EventType;
import com.example.schueler.eventures.classes.pojo.Location;
import com.example.schueler.eventures.interfaces.InterfaceGetEvent;
import com.example.schueler.eventures.interfaces.InterfaceGetEvents;
import com.example.schueler.eventures.interfaces.InterfaceTaskDefault;
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
	private InterfaceTaskDefault listener;

	//constructors
	public TaskGetEvent(String url, InterfaceTaskDefault listener) {
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

	public InterfaceTaskDefault getListener() {
		return listener;
	}

	public void setListener(InterfaceTaskDefault listener) {
		this.listener = listener;
	}

	//super
	@Override
	protected Event doInBackground(Object... params) {
		try {
			Gson gson = new Gson();
			HttpURLConnection conn = (HttpURLConnection) new URL(this.getUrl()).openConnection();
			String result = GetData(conn);
			Event event = gson.fromJson(result, Event.class);
			return event;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Event event){
		this.getListener().onPostExecute(event,this.getClass());
		super.onPostExecute(event);
	}

	@Override
	protected void onPreExecute() {
		this.getListener().onPreExecute(this.getClass());
		super.onPreExecute();
	}


	//custom

	private String GetData(HttpURLConnection conn){
		BufferedReader reader;
		String content = null;

		try{
			conn.setRequestMethod("GET");
			conn.setRequestProperty("API_KEY", "dmFsaTEyMzRpMjMwOGhnaW9zZ2Rqb2lqY3hvaTgwN");
			conn.setRequestProperty("uID", "5b2776686358aa0004540d94");

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
