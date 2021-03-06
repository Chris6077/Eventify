package com.example.schueler.eventures;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.example.schueler.eventures.classes.pojo.SlimEvent;
import com.example.schueler.eventures.handler.HandlerState;
import com.example.schueler.eventures.interfaces.InterfaceGetEvents;
import com.example.schueler.eventures.interfaces.InterfaceTaskDefault;
import com.example.schueler.eventures.listener.ListenerCreateEvent;
import com.example.schueler.eventures.listener.ListenerNavigationMenuHeader;
import com.example.schueler.eventures.listener.ListenerNavigationMenu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Handler;

public class EventListActivity extends AppCompatActivity implements InterfaceTaskDefault, SwipeRefreshLayout.OnRefreshListener {

	private DrawerLayout mdl;
	private ActionBarDrawerToggle toggle;
	private ListView listView_events;
	private NavigationView navigation;
	private View progressView;
	private SwipeRefreshLayout swipeRefreshLayout;

	//super

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_list);

		try {
			this.setViews();
			this.setListener();
			this.getEvents();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(this.toggle.onOptionsItemSelected(item)){
			return true;
		}

		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onPreExecute(Class resource) {
		swipeRefreshLayout.setRefreshing(true);
	}


	@Override
	public void onPostExecute(Object result, Class resource) {
		try{
			swipeRefreshLayout.setRefreshing(false);
			ArrayList<SlimEvent> events = (ArrayList<SlimEvent>) result;
			fillList(events);
		}catch(Exception error){
			HandlerState.handle(error,getApplicationContext());
		}
	}

	@Override
	public void onRefresh() {
		this.getEvents();
	}

	//custom

	private void setViews() throws Exception {
		this.mdl = (DrawerLayout) findViewById(R.id.content_event_list);
		this.listView_events = (ListView) findViewById(R.id.listview_events);
		this.navigation = (NavigationView) findViewById(R.id.navigation_drawer);
		this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.list_view_events_swipe_to_refresh_layout);

	}

	private void setListener(){
		this.navigation.setNavigationItemSelectedListener(new ListenerNavigationMenu(this));
		this.setListenerNavigationHeader();
		this.setActionBarToggle();
		this.swipeRefreshLayout.setOnRefreshListener(this);
	}

	private void setListenerNavigationHeader(){
		View navHeader;
		navHeader = navigation.getHeaderView(0);
		navHeader.setOnClickListener(new ListenerNavigationMenuHeader(this));
	}

	private void setActionBarToggle(){
		this.toggle = new ActionBarDrawerToggle(this,mdl,R.string.actionbar_open,R.string.actionbar_close);
		this.mdl.addDrawerListener(this.toggle);
		toggle.syncState();
		toggle.setDrawerSlideAnimationEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}


	private void fillList(ArrayList<SlimEvent> events) throws Exception {
		if(events == null) {
			throw new Exception("no Content found");
		}else {
			AdapterListViewEvent adapter = new AdapterListViewEvent(this, R.layout.listview_item_event, events);
			this.listView_events.setAdapter(adapter);
		}
	}

	private void getEvents(){
		try{
			TaskGetEvents get_events = new TaskGetEvents(getString(R.string.webservice_get_Events_url), this);
			get_events.execute();
		}catch(Exception error){
			HandlerState.handle(error,this);
		}
	}



}
