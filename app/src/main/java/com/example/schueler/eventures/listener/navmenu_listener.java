package com.example.schueler.eventures.listener;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.schueler.eventures.Event;
import com.example.schueler.eventures.EventListActivity;
import com.example.schueler.eventures.MapActivity;
import com.example.schueler.eventures.New_EventActivity;
import com.example.schueler.eventures.R;
import com.example.schueler.eventures.WelcomeActivity;

/**
 * Created by schueler on 3/18/18.
 */

public class navmenu_listener implements NavigationView.OnNavigationItemSelectedListener {

	final Context currentContext;
	private Object obj;


	//constructors

	public navmenu_listener (Object obj){
		this.currentContext = ((AppCompatActivity)obj);
		this.obj = obj;
	}


	//super

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		if(item.getItemId() == R.id.mntm_add_event){
			startActivity(New_EventActivity.class);
		}else if(item.getItemId() == R.id.mntm_all_events){
			startActivity(EventListActivity.class);
		}else if(item.getItemId() == R.id.mntm_favourites){
			startActivity(EventListActivity.class);
		}else if(item.getItemId() == R.id.mntm_fixed_events){
			startActivity(EventListActivity.class);
		}else if(item.getItemId() == R.id.mntm_my_events){
			startActivity(EventListActivity.class);
		}else if(item.getItemId() == R.id.mntm_map){
			startActivity(MapActivity.class);
		}
		return true;
	}


	//custom

	private void startActivity(Class classname){
		Intent activity = new Intent(this.currentContext,classname);
		activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		activity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		((AppCompatActivity)obj).finishAffinity();
		currentContext.startActivity(activity);
		((AppCompatActivity)obj).overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
	}


}
