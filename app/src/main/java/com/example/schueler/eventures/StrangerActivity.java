package com.example.schueler.eventures;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.schueler.eventures.asynctask.DownloadImageTask;

public class StrangerActivity extends AppCompatActivity {

	private CollapsingToolbarLayout collapsingToolbar;
	private View logoutView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stranger);

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

}
