package com.example.schueler.eventures.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.schueler.eventures.Event_Activity;
import com.example.schueler.eventures.R;
import com.example.schueler.eventures.StrangerActivity;
import com.example.schueler.eventures.classes.pojo.EventCategory;
import com.example.schueler.eventures.classes.pojo.SlimUser;
import com.example.schueler.eventures.listener.ListenerDoubleTap;

import java.util.ArrayList;

/**
 * Created by schueler on 3/23/18.
 */

public class AdapterListViewParticipators extends ArrayAdapter<SlimUser> {

	AppCompatActivity appCompatActivityResource;
	ArrayList<SlimUser> data;



	//constructors

	public AdapterListViewParticipators(AppCompatActivity res, @LayoutRes int resource, ArrayList<SlimUser> data) {
		super(res, resource, data);
		this.setAppCompatActivityResource(res);
		this.data = data;
	}


	//getters & stters

	public AppCompatActivity getAppCompatActivityResource() {
		return appCompatActivityResource;
	}

	public void setAppCompatActivityResource(AppCompatActivity appCompatActivityResource) {
		this.appCompatActivityResource = appCompatActivityResource;
	}


	//super

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		SlimUser stranger = this.data.get(position);
			LayoutInflater inflater = (LayoutInflater) this.getAppCompatActivityResource()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.listview_item_stranger, parent, false);
		TextView textView_username = (TextView) rowView.findViewById(R.id.list_item_user_name);
		TextView textView_email = (TextView) rowView.findViewById(R.id.list_item_user_email);
		textView_email.setText(stranger.getLastName());
		textView_username.setText(stranger.getFirstName());
		setListener(rowView,stranger);
		return rowView;
	}

	//custom

	private void setListener(View view, final SlimUser user){
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getAppCompatActivityResource(),StrangerActivity.class);
				intent.putExtra("uID", user.getuID());
				getAppCompatActivityResource().startActivity(intent);
			}
		});
	}

}
