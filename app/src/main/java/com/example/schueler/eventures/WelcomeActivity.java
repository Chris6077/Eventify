package com.example.schueler.eventures;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

	private ImageView image_logo;
	private TextView textview_app_name;
	private LinearLayout content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		this.setViews();
		this.animate();
		this.registrateeventhandlers();

	}


	private void setViews(){
		this.image_logo = (ImageView) this.findViewById(R.id.image_welcome_logo);
		this.textview_app_name = (TextView) this.findViewById(R.id.textview_welcome_app_name_lowercase);
		this.content = (LinearLayout) this.findViewById(R.id.content_welcome);

	}

	private void animate(){
		Animation anim_splash = AnimationUtils.loadAnimation(this,R.anim.anim_splash);
		this.image_logo.startAnimation(anim_splash);
		this.textview_app_name.startAnimation(anim_splash);
	}

	private void registrateeventhandlers(){
		this.image_logo.setOnClickListener(this);
		this.content.setOnClickListener(this);
	}

	private void showNewIntent(){
		final Intent login_activity = new Intent(this,LoginActivity.class);
		try{
			ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<View, String>(image_logo,"logo"));
			startActivity(login_activity);
		}catch(Exception error){
			//Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public void onClick(View v) {
		Intent login_activity = new Intent(this,LoginActivity.class);
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<View, String>(image_logo,"logo"),new Pair<View, String>(textview_app_name,"content"));
		startActivity(login_activity,options.toBundle());
	}
}
