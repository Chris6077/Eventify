package com.example.schueler.eventures;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Event extends AppCompatActivity {

	private DrawerLayout mdl;
	private ActionBarDrawerToggle toggle;
	private LinearLayout content_event;
	private ImageView image_event;
	private Animation anim_horizontal_left;
	private Animation anim_vertical_down;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);

		this.setViews();
		this.setupActionBarToggle();
		this.setAnimations();
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
		this.image_event = (ImageView) findViewById(R.id.image_event);
		
	}

	private void setupActionBarToggle(){
		this.toggle = new ActionBarDrawerToggle(this,mdl,R.string.actionbar_open,R.string.actionbar_close);
		this.mdl.addDrawerListener(this.toggle);
		toggle.syncState();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void setAnimations(){
		anim_horizontal_left = AnimationUtils.loadAnimation(this,R.anim.anim_horizontal_left);
		anim_vertical_down = AnimationUtils.loadAnimation(this,R.anim.anim_vertical_down);
		this.content_event.setAnimation(anim_horizontal_left);


		this.image_event.setAnimation(anim_vertical_down);
	}

}
