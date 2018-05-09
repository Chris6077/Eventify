package com.example.schueler.eventures;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.schueler.eventures.adapter.AdapterListViewEvent;
import com.example.schueler.eventures.asynctask.TaskGetImage;
import com.example.schueler.eventures.asynctask.TaskGetEvents;
import com.example.schueler.eventures.classes.pojo.Event;
import com.example.schueler.eventures.handler.HandlerState;
import com.example.schueler.eventures.interfaces.InterfaceGetEvents;
import com.example.schueler.eventures.listener.ListenerCreateEvent;
import com.example.schueler.eventures.listener.ListenerNavigationMenuHeader;
import com.example.schueler.eventures.listener.ListenerNavigationMenu;

import java.util.ArrayList;
import java.util.logging.Handler;

public class EventListActivity extends AppCompatActivity {

	private DrawerLayout mdl;
	private ActionBarDrawerToggle toggle;
	private ListView listView_events;
	private NavigationView navigation;
	private View progressView;
	public FloatingActionButton fab_add_event;

	//super

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_list);

		this.setViews();
		this.setListener();
		this.getEvents();


	}

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
		this.fab_add_event = (FloatingActionButton) findViewById(R.id.event_list_add_event_fab);

		//progress bar
		LayoutInflater layoutInflater = (LayoutInflater) this.getLayoutInflater();
		progressView = layoutInflater.inflate(R.layout.header_progressbar,null,false);
	}

	private void setListener(){
		this.navigation.setNavigationItemSelectedListener(new ListenerNavigationMenu(this));
		this.fab_add_event.setOnClickListener(new ListenerCreateEvent(this));
		this.setListenerNavigationHeader();
		this.setActionBarToggle();
	}

	private void setListenerNavigationHeader(){
		View navHeader;
		navHeader = navigation.getHeaderView(0);
		navHeader.setOnClickListener(new ListenerNavigationMenuHeader(this));
		new TaskGetImage((ImageView) navigation.getHeaderView(0).findViewById(R.id.navHeader_image)    )
				.execute("https://previews.123rf.com/images/alexutemov/alexutemov1702/alexutemov170200440/71260689-man-portrait-face-icon-web-avatar-flat-style-vector-male-blocked-or-unknown-anonymous-silhouette-bus.jpg");

	}

	private void setActionBarToggle(){
		this.toggle = new ActionBarDrawerToggle(this,mdl,R.string.actionbar_open,R.string.actionbar_close);
		this.mdl.addDrawerListener(this.toggle);
		toggle.syncState();
		toggle.setDrawerSlideAnimationEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}


	private void fillList(ArrayList<Event> events) throws Exception {
		if(events == null || events.size() < 1) {
			throw new Exception("no Content found");
		}else {
			AdapterListViewEvent adapter = new AdapterListViewEvent(this, R.layout.listview_item_event, events);
			this.listView_events.setAdapter(adapter);
		}
	}

	private void getEvents(){
		try{
			TaskGetEvents get_events = new TaskGetEvents(getString(R.string.webservice_base_url), new GetEvents_listener());
			get_events.execute();
		}catch(Exception error){
			HandlerState.handle(error,this);
		}
	}

	//listener

	private class GetEvents_listener implements InterfaceGetEvents {

		@Override
		public void onPreExecute() {
			AdapterListViewEvent adapter = new AdapterListViewEvent(EventListActivity.this, R.layout.listview_item_event, new ArrayList<Event>());
			listView_events.setAdapter(adapter);
			listView_events.addHeaderView(progressView);
		}

		@Override
		public void onPostExecute(ArrayList<Event> events) {
			try{
				fillList(events);
				listView_events.removeHeaderView(progressView);
			}catch(Exception error){
				HandlerState.handle(error,getApplicationContext());
			}
		}
	}


}
