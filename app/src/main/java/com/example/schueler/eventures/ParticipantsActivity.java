package com.example.schueler.eventures;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.schueler.eventures.adapter.AdapterListViewEvent;
import com.example.schueler.eventures.adapter.AdapterListViewParticipators;
import com.example.schueler.eventures.asynctask.TaskGetParticipants;
import com.example.schueler.eventures.classes.pojo.SlimEvent;
import com.example.schueler.eventures.classes.pojo.SlimUser;
import com.example.schueler.eventures.classes.pojo.UserType;
import com.example.schueler.eventures.interfaces.InterfaceTaskDefault;

import java.util.ArrayList;
import java.util.Date;

public class ParticipantsActivity extends AppCompatActivity implements InterfaceTaskDefault, SwipeRefreshLayout.OnRefreshListener {

	private ListView listview_users;
	private SwipeRefreshLayout swipeRefreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_participants);


		try {
			this.setViews();
			this.getParticipations();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//custom

	private void setViews() throws Exception {
		this.listview_users = (ListView) findViewById(R.id.listview_participants);
		this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.list_view_participators_swipe_to_refresh);
	}

	private void setListeners() throws Exception{
		this.swipeRefreshLayout.setOnRefreshListener(this);
	}

	private void getParticipations() throws Exception {
		String eID;
		try{
			eID = getIntent().getStringExtra("eID");
			TaskGetParticipants task = new TaskGetParticipants(getString(R.string.webservice_get_Events_url) + eID + "/participators",this);
			task.execute();
		}catch (Exception error){
			error.printStackTrace();
		}

	}

	private void setContent(ArrayList<SlimUser> users) throws Exception {
		if(users == null ) {
			throw new Exception("no Content found");
		}else {
			System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF " + users);
			AdapterListViewParticipators adapter = new AdapterListViewParticipators(this, R.layout.listview_item_stranger, users);
			this.listview_users.setAdapter(adapter);
		}
	}

	//asynctask

	@Override
	public void onPreExecute(Class resource) {
		this.swipeRefreshLayout.setRefreshing(true);
	}

	@Override
	public void onPostExecute(Object result, Class resource) {
		try{
			this.swipeRefreshLayout.setRefreshing(false);
			ArrayList<SlimUser> users = (ArrayList<SlimUser>) result;
			this.setContent(users);
		}catch(Exception error){
			error.printStackTrace();
		}
	}

	@Override
	public void onRefresh() {
		try {
			this.getParticipations();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
