package com.example.schueler.eventures.asynctask;

import android.os.AsyncTask;

import com.example.schueler.eventures.interfaces.InterfaceTaskDefault;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by schueler on 5/29/18.
 */

public class TaskPostRegistrate extends AsyncTask<String, Void, String> {

	//fields
	private String url;
	private InterfaceTaskDefault listener;

	//constructors

	public TaskPostRegistrate(String url, InterfaceTaskDefault listener) {
		this.url = url;
		this.listener = listener;
	}

	//super

	@Override
	protected String doInBackground(String... params) {
		try {
			Gson gson = new Gson();
			HttpURLConnection conn = (HttpURLConnection) new URL(this.url).openConnection();
			this.PostData(conn, params);
			return this.GetData(conn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(final String result) {
		this.listener.onPostExecute(result,this.getClass());
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		this.listener.onPreExecute(this.getClass());
		super.onPreExecute();
	}

	//custom

	private void PostData(HttpURLConnection conn, String... params) {
		BufferedWriter writer;

		try {

			//posting the data
			conn.setRequestMethod("POST");
			conn.setRequestProperty("API_KEY", "dmFsaTEyMzRpMjMwOGhnaW9zZ2Rqb2lqY3hvaTgwN");
			conn.setRequestProperty("Content-Type", "application/json");
			writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			writer.write(params[0]); //product - object in json-format
			writer.flush();
			writer.close();
			conn.getResponseCode();
		} catch (Exception error) {
			System.out.println("ERROR --- " + error);
		}

	}

	private String GetData(HttpURLConnection conn) {
		BufferedReader reader;
		String content = null;

		try {
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

		} catch (Exception error) {
			System.out.println("ERROR --- " + error);
		}
		return content;
	}

}