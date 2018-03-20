package com.example.schueler.eventures;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.schueler.eventures.pkg_data.classes_test.Database;
import com.example.schueler.eventures.pkg_data.classes_test.mEvent;
import com.example.schueler.eventures.pkg_data.classes_test.navmenu_listener;

import java.sql.Time;
import java.util.List;

public class EventListActivity extends AppCompatActivity {

	private DrawerLayout mdl;
	private ActionBarDrawerToggle toggle;
	private Database database;
	private ListView listView_events;
	private NavigationView navigation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_list);

		this.setViews();
		this.registrateeventhandlers();
		this.setupActionBarToggle();
		this.loadData();
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

		final Context context = this;

		this.navigation.setNavigationItemSelectedListener(new navmenu_listener(context,this));
	}

	private void setupActionBarToggle(){
		this.toggle = new ActionBarDrawerToggle(this,mdl,R.string.actionbar_open,R.string.actionbar_close);
		this.mdl.addDrawerListener(this.toggle);
		toggle.syncState();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void loadData(){
		try{
			database = new Database();

			database.add(new mEvent("name","20:30",12,Database.category.LADIES_NIGHT,"Klagenfurt","Information"));
			database.add(new mEvent("name","20:30",12,Database.category.LADIES_NIGHT,"Klagenfurt","Information"));
			database.add(new mEvent("name","20:30",12,Database.category.LADIES_NIGHT,"Klagenfurt","Information"));
			database.add(new mEvent("name","20:30",12,Database.category.LADIES_NIGHT,"Klagenfurt","Information"));
			database.add(new mEvent("name","20:30",12,Database.category.LADIES_NIGHT,"Klagenfurt","Information"));
			database.add(new mEvent("name","20:30",12,Database.category.LADIES_NIGHT,"Klagenfurt","Information"));
			database.add(new mEvent("name","20:30",12,Database.category.LADIES_NIGHT,"Klagenfurt","Information"));
			database.add(new mEvent("name","20:30",12,Database.category.LADIES_NIGHT,"Klagenfurt","Information"));
			database.add(new mEvent("name","20:30",12,Database.category.LADIES_NIGHT,"Klagenfurt","Information"));
			database.add(new mEvent("name","20:30",12,Database.category.LADIES_NIGHT,"Klagenfurt","Information"));
			database.add(new mEvent("name","20:30",12,Database.category.LADIES_NIGHT,"Klagenfurt","Information"));
			database.add(new mEvent("name","20:30",12,Database.category.LADIES_NIGHT,"Klagenfurt","Information"));
			this.fillList();
		}catch (Exception error){
			Toast.makeText(this,"error ... " + error.getMessage(), Toast.LENGTH_LONG).show();
		}

	}

	private void fillList(){
		ArrayAdapter<mEvent> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,database.getEvents());
		this.listView_events.setAdapter(adapter);
	}
}
