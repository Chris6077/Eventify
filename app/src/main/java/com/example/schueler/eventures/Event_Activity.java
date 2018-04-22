package com.example.schueler.eventures;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schueler.eventures.listener.navmenu_listener;

public class Event_Activity extends AppCompatActivity implements View.OnClickListener {

	private DrawerLayout mdl;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_event);

			this.setViews();
			this.setupActionBarToggle();
			this.registereventhandlers();
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
		this.mdl = (DrawerLayout) findViewById(R.id.content);
		this.content_event = (LinearLayout) findViewById(R.id.content_event);
		this.content_participants = (LinearLayout) findViewById(R.id.content_participants);
		this.img_running = (ImageView) findViewById(R.id.img_running_event);
		this.text_view_participants = (TextView) findViewById(R.id.text_view_participants_event);
		this.content_user = (LinearLayout) findViewById(R.id.content_user);
		this.img_profile = (ImageView) findViewById(R.id.img_profile_event);
		this.text_view_profile = (TextView) findViewById(R.id.text_view_profile_event);
		this.navigation = (NavigationView) findViewById(R.id.navigation_drawer);

	}

	private void registereventhandlers(){

		final Context context = this;

		this.content_participants.setOnClickListener(this);
		this.content_user.setOnClickListener(this);
		this.navigation.setNavigationItemSelectedListener(new navmenu_listener(this));
	}

	private void setupActionBarToggle(){
		this.toggle = new ActionBarDrawerToggle(this,mdl,R.string.actionbar_open,R.string.actionbar_close);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			this.mdl.addDrawerListener(this.toggle);
		}
		toggle.syncState();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void Participants_sharedTransitionAnimation() throws Exception{
		Intent user_activity = new Intent(this,ParticipantsActivity.class);
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<View, String>(img_running,"img_running"),new Pair<View, String>(text_view_participants,"text_view_participants"));
		startActivity(user_activity,options.toBundle());
	}

	private void User_sharedTransitionAnimation(){
		Intent user_activity = new Intent(this,UserActivity.class);
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<View, String>(img_profile,"img_profile"),new Pair<View, String>(text_view_profile,"text_view_profile"));
		startActivity(user_activity,options.toBundle());
	}

	//super

	@Override
	public void onClick(View v) {
		try{
			if(content_participants.getId() == v.getId()){
				this.Participants_sharedTransitionAnimation();
			}else if(content_user.getId() == v.getId()){
				this.User_sharedTransitionAnimation();
			}
		}catch (Exception error){
			Toast.makeText(this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
		}
	}


}