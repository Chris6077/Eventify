package com.example.schueler.eventures;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.schueler.eventures.asynctask.TaskGetImage;

import java.io.IOException;

public class UserActivity extends AppCompatActivity {

	private CollapsingToolbarLayout collapsingToolbar;
	private View logoutView;
	private FloatingActionButton fab_choose_pic;
	private int PICK_IMAGE_REQUEST = 1;


	//super
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);

		this.setViews();
		this.registrateeventhandlers();
		this.setContent();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

			Uri uri = data.getData();

			try {
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
				// Log.d(TAG, String.valueOf(bitmap));

				ImageView imageView = (ImageView) findViewById(R.id.User_header_img);
				imageView.setImageBitmap(bitmap);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//custom

	private void setViews(){
		this.collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar_user);
		//this.logoutView = findViewById(R.id.list_item_user_logout);
		this.fab_choose_pic = (FloatingActionButton) findViewById(R.id.fab_choose_picture_user);
	}

	private void registrateeventhandlers(){
		this.logoutView.setOnClickListener(new logout_listener());
		this.fab_choose_pic.setOnClickListener(new choose_picture_listener());
	}

	private void setContent(){
		this.collapsingToolbar.setTitle("Marcel Judth");
		this.LoadImageFromURL();

	}

	private void LoadImageFromURL(){
		new TaskGetImage((ImageView) findViewById(R.id.User_header_img))
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

	private class choose_picture_listener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			// Show only images, no videos or anything else
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			// Always show the chooser (if there are multiple options available)
			startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
		}
	}

}
