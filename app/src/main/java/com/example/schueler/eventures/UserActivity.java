package com.example.schueler.eventures;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.example.schueler.eventures.asynctask.DownloadImageTask;

public class UserActivity extends AppCompatActivity {

	private CollapsingToolbarLayout collapsingToolbar;
	private View logoutView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);

		this.setViews();
		this.registrateeventhandlers();
		this.setContent();

	}

	//custom

	private void setViews(){
		this.collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar_user);
		this.logoutView = findViewById(R.id.list_item_user_logout);
	}

	private void registrateeventhandlers(){
		this.logoutView.setOnClickListener(new logout_listener());
	}

	private void setContent(){
		this.collapsingToolbar.setTitle("Marcel Judth");
		this.LoadImageFromURL();

	}

	private void LoadImageFromURL(){
		new DownloadImageTask((ImageView) findViewById(R.id.User_header_img))
				.execute("https://previews.123rf.com/images/alexutemov/alexutemov1702/alexutemov170200440/71260689-man-portrait-face-icon-web-avatar-flat-style-vector-male-blocked-or-unknown-anonymous-silhouette-bus.jpg");
	}

	//listeners

	private class logout_listener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			Intent user_activity = new Intent(getApplicationContext(),WelcomeActivity.class);
			user_activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(user_activity);

		}
	}

}
