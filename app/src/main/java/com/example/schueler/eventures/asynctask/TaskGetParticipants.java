package com.example.schueler.eventures.asynctask;

import android.os.AsyncTask;

import com.example.schueler.eventures.classes.pojo.Event;
import com.example.schueler.eventures.classes.pojo.SlimEvent;
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

/**
 * Created by schueler on 5/7/18.
 */

public class TaskGetParticipants extends AsyncTask<Object, Object, ArrayList<SlimEvent>> {

	//fields
	private String url;
	private InterfaceGetEvents listener;

	//constructors
	public TaskGetParticipants(String url, InterfaceGetEvents listener) {
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

	public InterfaceGetEvents getListener() {
		return listener;
	}

	public void setListener(InterfaceGetEvents listener) {
		this.listener = listener;
	}

	//super
	@Override
	protected ArrayList<SlimEvent> doInBackground(Object... params) {
		try {
			Gson gson = new Gson();
			HttpURLConnection conn = (HttpURLConnection) new URL(this.getUrl()).openConnection();
			Type collectionType = new TypeToken<Collection<SlimEvent>>(){}.getType();
			String result = GetData(conn);
			ArrayList<SlimEvent> events = gson.fromJson(result, collectionType);
			return events;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<SlimEvent> events){
		this.getListener().onPostExecute(events);
		super.onPostExecute(events);
	}

	@Override
	protected void onPreExecute() {
		this.getListener().onPreExecute();
		super.onPreExecute();
	}


	//custom
	private void PostData(HttpURLConnection conn, String... params){
		BufferedWriter writer;

		try{

			//posting the data
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			writer.write(params[0]); //product - object in json-format
			writer.flush();
			writer.close();
			conn.getResponseCode();

		}catch(Exception error){
			System.out.println("ERROR --- " + error);
		}

	}

	private String GetData(HttpURLConnection conn){
		BufferedReader reader;
		String content = null;

		try{
			conn.setRequestMethod("GET");
			conn.setRequestProperty("API_KEY", "dmFsaTEyMzRpMjMwOGhnaW9zZ2Rqb2lqY3hvaTgwN");
			conn.setRequestProperty("uID", "5af9599ca2d6f53ca8b702a7");

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
