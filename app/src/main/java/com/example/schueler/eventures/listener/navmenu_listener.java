package com.example.schueler.eventures.listener;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.schueler.eventures.EventListActivity;
import com.example.schueler.eventures.MapActivity;
import com.example.schueler.eventures.MapsActivity;
import com.example.schueler.eventures.R;

public class navmenu_listener implements NavigationView.OnNavigationItemSelectedListener {

	private AppCompatActivity resource;


	//constructors
	public navmenu_listener (AppCompatActivity obj){
		this.resource = obj;
	}


	//super
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		if(item.getItemId() == R.id.mntm_all_events){
			startActivity(EventListActivity.class);
		}else if(item.getItemId() == R.id.mntm_favourites){
			startActivity(EventListActivity.class);
		}else if(item.getItemId() == R.id.mntm_fixed_events){
			startActivity(EventListActivity.class);
		}else if(item.getItemId() == R.id.mntm_my_events){
			startActivity(EventListActivity.class);
		}else if(item.getItemId() == R.id.mntm_map){
			startActivity(MapsActivity.class);
		}
		return true;
	}


	//custom
	private void startActivity(Class classname){
		Intent activity = new Intent(this.resource,classname);

		activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this.resource);
		this.resource.startActivity(activity,options.toBundle());
		this.resource.finish();
		//((AppCompatActivity)obj).overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
	}

}


