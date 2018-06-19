package com.example.schueler.eventures;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CreateEventDialogActivty extends AppCompatActivity {

	private TextView input_date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event_dialog_activty);

		this.setViews();
		this.registerEventHandler();
	}

	//custom

	private void setViews(){
		this.input_date = (TextView) this.findViewById(R.id.create_event_input_begin_date);

	}

	private void registerEventHandler(){
		this.input_date.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				input_date.setText("asdflkasdfö");
			}
		});
	}


	//listener



}
