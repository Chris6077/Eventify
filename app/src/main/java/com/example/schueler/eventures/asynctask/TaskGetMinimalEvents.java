package com.example.schueler.eventures.asynctask;

import android.os.AsyncTask;

import com.example.schueler.eventures.classes.pojo.MinimalEvent;
import com.example.schueler.eventures.interfaces.InterfaceTaskDefault;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by schueler on 5/7/18.
 */

public class TaskGetMinimalEvents extends AsyncTask<Object, Object, ArrayList<MinimalEvent>> {

	//fields
	private String url;
	private InterfaceTaskDefault listener;

	//constructors
	public TaskGetMinimalEvents(String url, InterfaceTaskDefault listener) {
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
	protected ArrayList<MinimalEvent> doInBackground(Object... params) {
		try {
			Gson gson = new Gson();
			HttpURLConnection conn = (HttpURLConnection) new URL(this.getUrl()).openConnection();
			Type collectionType = new TypeToken<Collection<MinimalEvent>>(){}.getType();
			String result = GetData(conn);
			ArrayList<MinimalEvent> events = gson.fromJson(result, collectionType);
			return events;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<MinimalEvent> events){
		this.getListener().onPostExecute(events,this.getClass());
		super.onPostExecute(events);
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
			error.printStackTrace();
		}
		System.out.println("CONTENT AAAAAAAAAAAAAAAAAAAAAAAAAA" + content);
		return content;
	}

}
