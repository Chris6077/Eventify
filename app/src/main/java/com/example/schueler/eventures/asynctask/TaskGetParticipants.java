package com.example.schueler.eventures.asynctask;

import android.os.AsyncTask;

import com.example.schueler.eventures.classes.pojo.Event;
import com.example.schueler.eventures.classes.pojo.SlimEvent;
import com.example.schueler.eventures.classes.pojo.SlimUser;
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

/**
 * Created by schueler on 5/7/18.
 */

public class TaskGetParticipants extends AsyncTask<Object, Object, ArrayList<SlimUser>> {

	//fields
	private String url;
	private InterfaceTaskDefault listener;

	//constructors
	public TaskGetParticipants(String url, InterfaceTaskDefault listener) {
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
	protected ArrayList<SlimUser> doInBackground(Object... params) {
		try {
			Gson gson = new Gson();
			HttpURLConnection conn = (HttpURLConnection) new URL(this.getUrl()).openConnection();
			Type collectionType = new TypeToken<Collection<SlimUser>>(){}.getType();
			String result = GetData(conn);
			ArrayList<SlimUser> users = gson.fromJson(result, collectionType);
			return users;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<SlimUser> events){
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
			conn.setRequestProperty("uID", "5b2776116358aa0004540d92");

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
		System.out.println("CONTENT" + content);
		return content;
	}

}
