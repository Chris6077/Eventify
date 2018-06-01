package com.example.schueler.eventures;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.schueler.eventures.adapter.AdapterListViewEvent;
import com.example.schueler.eventures.adapter.AdapterListViewParticipators;
import com.example.schueler.eventures.classes.pojo.SlimUser;
import com.example.schueler.eventures.classes.pojo.UserType;

import java.util.ArrayList;
import java.util.Date;

public class ParticipantsActivity extends AppCompatActivity {

	private ListView listview_users;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_participants);

		this.setViews();
		try {
			this.getParticipations();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//custom

	private void setViews(){
		this.listview_users = (ListView) findViewById(R.id.listview_participants);
	}

	private void getParticipations() throws Exception {
		ArrayList<SlimUser> users = new ArrayList<>();
		users.add(new SlimUser("13131313","julian","blaschke","", UserType.User,20,29,23,12,new Date()));
		users.add(new SlimUser("13131313","julian","blaschke","", UserType.User,20,29,23,12,new Date()));
		users.add(new SlimUser("13131313","julian","blaschke","", UserType.User,20,29,23,12,new Date()));
		users.add(new SlimUser("13131313","julian","blaschke","", UserType.User,20,29,23,12,new Date()));

		this.setContent(users);
	}

	private void setContent(ArrayList<SlimUser> users) throws Exception {
		if(users == null || users.size() < 1) {
			throw new Exception("no Content found");
		}else {
			AdapterListViewParticipators adapter = new AdapterListViewParticipators(this, R.layout.listview_item_event, users);
			this.listview_users.setAdapter(adapter);
		}
	}

}
