package com.example.schueler.eventures;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schueler.eventures.adapter.adapter_list_view_event;
import com.example.schueler.eventures.asynctask.DownloadImageTask;
import com.example.schueler.eventures.classes.pojo.Event;
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

public class Event_Activity extends AppCompatActivity{

	private CoordinatorLayout mdl;
	private ActionBarDrawerToggle toggle;
	private LinearLayout content_event;
	private ImageView image_event;
	private Animation anim_horizontal_left;
	private Animation anim_vertical_down;
	private LinearLayout content_participants;
	private LinearLayout content_user;
	private ImageView img_running;
	private ImageView img_profile;
	private TextView text_view_participants;
	private TextView text_view_profile;
	private NavigationView navigation;
	private CollapsingToolbarLayout collapsingToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_event);

			this.setViews();
			this.registereventhandlers();
			this.setContent();
		}catch(Exception error){
			Toast.makeText(this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
		}

		//this.setAnimations();
	}

	//super

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(this.toggle.onOptionsItemSelected(item)){
			return true;
		}

		return super.onOptionsItemSelected(item);
	}


	//custom

	private void setViews(){
		this.mdl = (CoordinatorLayout) findViewById(R.id.content);
		this.content_event = (LinearLayout) findViewById(R.id.content_event);
		this.content_user = (LinearLayout) findViewById(R.id.content_user);
		this.navigation = (NavigationView) findViewById(R.id.navigation_drawer);
		this.collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar_event);

	}

	private void registereventhandlers(){

		final Context context = this;

		
	}

	private void Participants_sharedTransitionAnimation() throws Exception{
		Intent user_activity = new Intent(this,ParticipantsActivity.class);
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<View, String>(img_running,"img_running"),new Pair<View, String>(text_view_participants,"text_view_participants"));
		startActivity(user_activity,options.toBundle());
	}

	private void User_sharedTransitionAnimation(){
		Intent user_activity = new Intent(this,StrangerActivity.class);
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<View, String>(img_profile,"img_profile"),new Pair<View, String>(text_view_profile,"text_view_profile"));
		startActivity(user_activity,options.toBundle());
	}

	private void setContent(){
		//this.collapsingToolbar.setTitle("Rad fahren");
		//this.LoadImageFromURL();
	}

	private void LoadImageFromURL(int imageRes){
		new DownloadImageTask((ImageView) findViewById(imageRes))
				.execute("https://previews.123rf.com/images/alexutemov/alexutemov1702/alexutemov170200440/71260689-man-portrait-face-icon-web-avatar-flat-style-vector-male-blocked-or-unknown-anonymous-silhouette-bus.jpg");

	}

	private void setInfoItem(String header, String description, int pictureResource, View.OnClickListener listener){
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View item = inflater.inflate(R.layout.listview_item_event,null, false);

		TextView head = (TextView) item.findViewById(R.id.cardiview_info_item_header_text);
		TextView desc = (TextView) item.findViewById(R.id.cardiview_info_item_description);
		ImageView image = (ImageView) item.findViewById(R.id.cardiview_info_item_image);

		head.setText(header);
		desc.setText(description);
		image.setImageResource(pictureResource);

		item.setOnClickListener(listener);

		this.content_event.addView(item);
	}

	//super


	//async Task

	private class LoadEventsSync extends AsyncTask<Object, Object, ArrayList<Event>> {

		//fields
		private String url;

		//constructors
		public LoadEventsSync(String url) {
			this.url = url;
		}


		//super
		@Override
		protected ArrayList<Event> doInBackground(Object... params) {
			try {
				Gson gson = new Gson();
				HttpURLConnection conn = (HttpURLConnection) new URL(this.url + "getAllEvents").openConnection();
				Type collectionType = new TypeToken<Collection<Event>>(){}.getType();
				String result = GetData(conn);
				ArrayList<Event> events = gson.fromJson(result, collectionType);
				return events;

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Event> events){
			super.onPostExecute(events);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

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


}
