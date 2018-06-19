package com.example.schueler.eventures.asynctask;

import android.os.AsyncTask;

import com.example.schueler.eventures.classes.pojo.Event;
import com.example.schueler.eventures.classes.pojo.User;
import com.example.schueler.eventures.interfaces.InterfaceTaskDefault;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by schueler on 5/7/18.
 */

public class TaskGetUser extends AsyncTask<Object, Object, User> {

	//fields
	private String url;
	private InterfaceTaskDefault listener;

	//constructors
	public TaskGetUser(String url, InterfaceTaskDefault listener) {
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
	protected User doInBackground(Object... params) {
		try {
			Gson gson = new Gson();
			HttpURLConnection conn = (HttpURLConnection) new URL(this.getUrl()).openConnection();
			String result = GetData(conn);
			User user = gson.fromJson(result, User.class);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(User user){
		this.getListener().onPostExecute(user,this.getClass());
		super.onPostExecute(user);
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
