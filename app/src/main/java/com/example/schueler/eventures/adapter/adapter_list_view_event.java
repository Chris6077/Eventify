package com.example.schueler.eventures.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.schueler.eventures.viewholder.view_holder_event;

import java.util.ArrayList;

/**
 * Created by schueler on 3/23/18.
 */

public class adapter_list_view_event extends ArrayAdapter<String> {


	AppCompatActivity res;

	//constructors

	public adapter_list_view_event(AppCompatActivity res, @LayoutRes int resource, ArrayList<String> data) {
		super(res, resource, data);
		this.res = res;
	}


	//super

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		view_holder_event holder_event = null;
		LayoutInflater inflater = this.res.getLayoutInflater();
		if(convertView == null){
		}else{
			holder_event = (view_holder_event) convertView.getTag();
		}

		holder_event.setTitle("custom array Adapter title");

		return convertView;

	}



	//custom



}
