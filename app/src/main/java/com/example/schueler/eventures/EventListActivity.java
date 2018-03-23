package com.example.schueler.eventures;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.schueler.eventures.adapter.adapter_list_view_event;
import com.example.schueler.eventures.listener.navmenu_listener;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

	private DrawerLayout mdl;
	private ActionBarDrawerToggle toggle;
	private ListView listView_events;
	private NavigationView navigation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_list);

		this.setViews();
		this.registrateeventhandlers();
		this.setupActionBarToggle();
		this.fillList();
	}

	//super

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(this.toggle.onOptionsItemSelected(item)){
			return true;
		}

		return super.onOptionsItemSelected(item);
	}


	//custom

	private void setViews(){
		this.mdl = (DrawerLayout) findViewById(R.id.content_event_list);
		this.listView_events = (ListView) findViewById(R.id.listview_events);
		this.navigation = (NavigationView) findViewById(R.id.navigation_drawer);
	}

	private void registrateeventhandlers(){
		this.navigation.setNavigationItemSelectedListener(new navmenu_listener(this));
	}

	private void setupActionBarToggle(){
		this.toggle = new ActionBarDrawerToggle(this,mdl,R.string.actionbar_open,R.string.actionbar_close);
		this.mdl.addDrawerListener(this.toggle);
		toggle.syncState();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void fillList(){

		ArrayList<String> temp = new ArrayList<>();

		temp.add("Event1");
		temp.add("Event2");
		temp.add("Event3");
		temp.add("Event4");

		this.listView_events.setAdapter(new adapter_list_view_event(this,R.layout.listview_item_event,temp));
	}


}
