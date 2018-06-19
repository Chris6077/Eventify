package com.example.schueler.eventures;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.schueler.eventures.asynctask.TaskGetImage;
import com.example.schueler.eventures.asynctask.TaskGetUser;
import com.example.schueler.eventures.classes.pojo.User;
import com.example.schueler.eventures.interfaces.InterfaceTaskDefault;

public class StrangerActivity extends AppCompatActivity implements InterfaceTaskDefault {

	private CollapsingToolbarLayout collapsingToolbar;
	private LinearLayout content_user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stranger);

		this.setViews();
		this.registrateeventhandlers();
		this.getUser();
	}

	@Override
	public void onPreExecute(Class resource) {
		content_user.addView(getLayoutInflater().inflate(R.layout.header_progressbar,null));
	}

	@Override
	public void onPostExecute(Object result, Class resource) {
		try{
			content_user.removeAllViews();
			User user = (User) result;
			this.setContent(user);
		}catch (Exception error){
			error.printStackTrace();
		}
	}

	//custom

	private void setViews(){
		this.collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar_user);
		this.content_user = (LinearLayout) findViewById(R.id.content_user_info_items);
	}

	private void setListener(){
	}

	private void registrateeventhandlers(){

	}

	private void getUser(){
		TaskGetUser task = new TaskGetUser(getString(R.string.webservice_get_Users_url) + getIntent().getStringExtra("uID"),this);
		task.execute();
	}

	private void setContent(User user){
		try{

			this.collapsingToolbar.setTitle(user.getFirstName());
			this.setInfoItem(user.getFirstName() + " " + user.getLastName(),getString(R.string.user_full_name),R.drawable.ic_account_circle_black_24dp,null);
			this.setInfoItem("sample@mail.com",getString(R.string.prompt_email),R.drawable.ic_email_black_24dp,null);
			this.setInfoItem(user.getNumberOfCreatedEvents()+"","Veranstaltete Events",R.drawable.rocket,null);


		}catch (Exception error){
			error.printStackTrace();
		}
	}

	private void setInfoItem(String header, String description, int pictureResource, View.OnClickListener listener){
		LayoutInflater inflater = getLayoutInflater();
		View item = inflater.inflate(R.layout.basic_cardview_info_item,null);

		TextView head = (TextView) item.findViewById(R.id.cardiview_info_item_header_text);
		TextView desc = (TextView) item.findViewById(R.id.cardiview_info_item_description);
		ImageView image = (ImageView) item.findViewById(R.id.cardiview_info_item_image);

		head.setText(header);
		desc.setText(description);
		image.setImageResource(pictureResource);

		item.setOnClickListener(listener);

		this.content_user.addView(item);
	}


	private void LoadImageFromURL(){
		new TaskGetImage((ImageView) findViewById(R.id.User_header_img))
				.execute("https://previews.123rf.com/images/alexutemov/alexutemov1702/alexutemov170200440/71260689-man-portrait-face-icon-web-avatar-flat-style-vector-male-blocked-or-unknown-anonymous-silhouette-bus.jpg");
	}


}
