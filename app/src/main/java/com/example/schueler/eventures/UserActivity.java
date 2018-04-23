package com.example.schueler.eventures;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.schueler.eventures.asynctask.DownloadImageTask;

public class UserActivity extends AppCompatActivity {

	private CollapsingToolbarLayout collapsingToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);

		this.setViews();
		this.setContent();

	}

	//custom

	private void setViews(){
		this.collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar_user);
	}

	private void setContent(){
		this.collapsingToolbar.setTitle("Marcel Judth");
		this.LoadImageFromURL();
	}

	private void LoadImageFromURL(){
		new DownloadImageTask((ImageView) findViewById(R.id.User_header_img))
				.execute("https://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");

	}
}
