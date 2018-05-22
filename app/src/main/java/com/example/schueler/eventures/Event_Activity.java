package com.example.schueler.eventures;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
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

import com.example.schueler.eventures.adapter.AdapterListViewEvent;
import com.example.schueler.eventures.asynctask.TaskGetEvent;
import com.example.schueler.eventures.asynctask.TaskGetEvents;
import com.example.schueler.eventures.asynctask.TaskGetImage;
import com.example.schueler.eventures.classes.pojo.Event;
import com.example.schueler.eventures.classes.pojo.SlimEvent;
import com.example.schueler.eventures.handler.HandlerState;
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
import java.text.SimpleDateFormat;
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
			this.setListener();
			this.getEvent();
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

	private void setListener(){

		final Context context = this;

		
	}

	private void setContent(final Event event) throws Exception{
		if(event == null)
			throw new Exception("no Event found");

		this.collapsingToolbar.setTitle(event.getName());
		this.setInfoItem(event.getCategory().toString(),getString(R.string.event_category),R.drawable.biker,null);
		this.setInfoItem(event.getStartDate().toString(),getString(R.string.event_date_begin),R.drawable.clock,null);
		this.setInfoItem(event.getEndDate().toString(),getString(R.string.event_date_end),R.drawable.clock,null);
		this.setInfoItem(event.getDescription().toString(),getString(R.string.event_information),R.drawable.rocket,null);
		//this.setInfoItem(event.getCreatorID().toString(),getString(R.string.event_organizer),R.drawable.profle,null);
		this.setInfoItem("Klagenfurt",getString(R.string.event_place),R.drawable.pin2,null);
		this.setInfoItem(String.valueOf(event.getMaxParticipants()), getString(R.string.event_participants), R.drawable.running, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent event_activity = new Intent(getApplicationContext(),ParticipantsActivity.class);
				event_activity.putExtra("event", event);
				startActivity(event_activity);
			}
		});
		this.setInfoItem(String.valueOf(event.getMinAge()),getString(R.string.event_minage),R.drawable.profle,null);

	}

	private void getEvent(){
		try{
			TaskGetEvent getEvent = new TaskGetEvent(getString(R.string.webservice_get_Events_url) + ((SlimEvent)this.getIntent().getSerializableExtra("event")).geteID(),new GetEvent_listener());
			getEvent.execute();
		}catch(Exception error){
			HandlerState.handle(error,this);
		}
	}

	private void setInfoItem(String header, String description, int pictureResource, View.OnClickListener listener){
		LayoutInflater inflater = getLayoutInflater();
		View item = inflater.inflate(R.layout.basic_cardview_info_item,null);

		TextView head = (TextView) item.findViewById(R.id.cardiview_info_item_header_text);
		TextView desc = (TextView) item.findViewById(R.id.cardiview_info_item_description);
		ImageView image = (ImageView) item.findViewById(R.id.cardiview_info_item_image);

		head.setText(header);
		desc.setText(description);
		image.setImageResource(pictureResource);

		item.setOnClickListener(listener);

		this.content_event.addView(item);
	}


	private class GetEvent_listener implements InterfaceGetEvent {

		@Override
		public void onPreExecute() {
			content_event.addView(getLayoutInflater().inflate(R.layout.header_progressbar,null));
		}

		@Override
		public void onPostExecute(Event event) {
			try {
				content_event.removeAllViews();
				setContent(event);
			} catch (Exception e) {
				HandlerState.handle(e,getApplicationContext());
			}
		}
	}

}
