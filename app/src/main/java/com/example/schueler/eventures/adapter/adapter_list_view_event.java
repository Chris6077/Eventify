package com.example.schueler.eventures.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.schueler.eventures.Event;
import com.example.schueler.eventures.LoginActivity;
import com.example.schueler.eventures.R;
import com.example.schueler.eventures.listener.DoubleTapListener;
import com.example.schueler.eventures.viewholder.view_holder_event;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by schueler on 3/23/18.
 */

public class adapter_list_view_event extends ArrayAdapter<String> {


	AppCompatActivity appCompatActivityResource;
	ArrayList<String> data;


	//constructors

	public adapter_list_view_event(AppCompatActivity res, @LayoutRes int resource, ArrayList<String> data) {
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
		LayoutInflater inflater = (LayoutInflater) this.getAppCompatActivityResource()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.listview_item_event, parent, false);
		TextView header = (TextView) rowView.findViewById(R.id.list_item_header);
		header.setText(this.data.get(position));
		ImageView image = (ImageView) rowView.findViewById(R.id.list_item_imageView);

		this.setUpIconLike(rowView);
		this.setUpRowViewListener(rowView);
		this.setUpTitleListener(rowView);

		return rowView;
	}


	//custom

	private void setUpIconLike(View rowView){
		final ImageView imageView = (ImageView) rowView.findViewById(R.id.list_item_like);


		imageView.setTag(R.drawable.ic_favorite_border_black_24dp);
		imageView.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if((Integer)imageView.getTag() == R.drawable.ic_favorite_border_black_24dp){
						imageView.setImageResource(R.drawable.ic_favorite_black_24dp);
						imageView.setTag(R.drawable.ic_favorite_black_24dp);
					}
					else{
						imageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
						imageView.setTag(R.drawable.ic_favorite_border_black_24dp);
					}
				}
		});




	}

	private void setUpRowViewListener(View rowView){

		final ImageView imageView = (ImageView) rowView.findViewById(R.id.list_item_like);


		rowView.setOnClickListener(new DoubleTapListener() {

			@Override
			public void onSingleClick(View v) {

			}

			@Override
			public void onDoubleClick(View v) {
				if((Integer)imageView.getTag() == R.drawable.ic_favorite_border_black_24dp){
					imageView.setImageResource(R.drawable.ic_favorite_black_24dp);
					imageView.setTag(R.drawable.ic_favorite_black_24dp);
				}
				else{
					imageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
					imageView.setTag(R.drawable.ic_favorite_border_black_24dp);
				}
			}
		});

	}

	private void setUpTitleListener(View rowView){
		final LinearLayout content_title = (LinearLayout) rowView.findViewById(R.id.list_item_header_title);
		final ImageView header_image = (ImageView) rowView.findViewById(R.id.list_item_imageView);

		content_title.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent event_activity = new Intent(getAppCompatActivityResource(),Event.class);
					ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getAppCompatActivityResource(), new Pair<View, String>(header_image,"header_image"));
					getAppCompatActivityResource().startActivity(event_activity,options.toBundle());
				}
		});
	}



}
