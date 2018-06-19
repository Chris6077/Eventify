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
import com.example.schueler.eventures.MapsActivity;
import com.example.schueler.eventures.R;
import com.example.schueler.eventures.WelcomeActivity;
import com.example.schueler.eventures.classes.pojo.local.LocalDatabase;

public class ListenerNavigationMenu implements NavigationView.OnNavigationItemSelectedListener {

	private Context resource;


	//constructors
	public ListenerNavigationMenu(Context obj){
		this.resource = obj;
	}


	//super
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		if(item.getItemId() == R.id.mntm_all_events){
			startActivity(EventListActivity.class);
		}else if(item.getItemId() == R.id.mntm_all_events){
			startActivity(EventListActivity.class);
		}else if(item.getItemId() == R.id.mntm_logout){
			LocalDatabase.setuID(null);
			startActivity(WelcomeActivity.class);
		}else if(item.getItemId() == R.id.mntm_map){
			startActivity(MapsActivity.class);
		}
		return true;
	}


	//custom
	private void startActivity(Class classname){
		Intent activity = new Intent(this.resource,classname);

		activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
		this.resource.startActivity(activity);
		//((AppCompatActivity)obj).overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
	}

}


