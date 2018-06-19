package com.example.schueler.eventures.listener;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.schueler.eventures.Event_Activity;
import com.example.schueler.eventures.StrangerActivity;
import com.example.schueler.eventures.UserActivity;
import com.example.schueler.eventures.classes.pojo.local.LocalDatabase;

/**
 * Created by schueler on 4/23/18.
 */

public class ListenerNavigationMenuHeader implements View.OnClickListener {

	//fields
	private AppCompatActivity resource;

	//constructors
	public ListenerNavigationMenuHeader(AppCompatActivity resource) {
		this.resource = resource;
	}

	//super
	@Override
	public void onClick(View v) {
		Intent intent= new Intent(resource,StrangerActivity.class);
		intent.putExtra("uID", LocalDatabase.getuID());
		resource.startActivity(intent);
	}


}