package com.example.schueler.eventures.listener;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.schueler.eventures.UserActivity;

/**
 * Created by schueler on 4/23/18.
 */

public class NavMenuHeader_listener  implements View.OnClickListener {

	//fields
	private AppCompatActivity resource;

	//constructors
	public NavMenuHeader_listener(AppCompatActivity resource) {
		this.resource = resource;
	}

	//super
	@Override
	public void onClick(View v) {
		this.startActivity(UserActivity.class);
	}

	//custom
	private void startActivity(Class classname){
		Intent activity = new Intent(this.resource,classname);


		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this.resource);
		this.resource.startActivity(activity,options.toBundle());
		//((AppCompatActivity)obj).overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
	}

}