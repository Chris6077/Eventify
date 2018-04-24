package com.example.schueler.eventures;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schueler.eventures.adapter.adapter_list_view_event;
import com.example.schueler.eventures.classes.pojo.Event;
import com.example.schueler.eventures.classes.pojo.EventCategory;
import com.example.schueler.eventures.classes.pojo.EventState;
import com.example.schueler.eventures.classes.pojo.EventType;
import com.example.schueler.eventures.listener.NavMenuHeader_listener;
import com.example.schueler.eventures.listener.navmenu_listener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class EventListActivity extends AppCompatActivity {

	private DrawerLayout mdl;
	private ActionBarDrawerToggle toggle;
	private ListView listView_events;
	private NavigationView navigation;
	private View progressView;
	public FloatingActionButton fab_add_event;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_list);

		this.setViews();
		this.registrateeventhandlers();
		this.setupActionBarToggle();
		this.loadEvents();


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
		this.fab_add_event = (FloatingActionButton) findViewById(R.id.event_list_add_event_fab);

		//progress bar
		LayoutInflater layoutInflater = (LayoutInflater) this.getLayoutInflater();
		progressView = layoutInflater.inflate(R.layout.header_progressbar,null,false);
	}

	private void registrateeventhandlers(){
		this.navigation.setNavigationItemSelectedListener(new navmenu_listener(this));
		this.fab_add_event.setOnClickListener(new fab_listener(this,fab_add_event));
		this.registrateHeaderListener();
	}

	private void setupActionBarToggle(){
		this.toggle = new ActionBarDrawerToggle(this,mdl,R.string.actionbar_open,R.string.actionbar_close);
		this.mdl.addDrawerListener(this.toggle);
		toggle.syncState();
		toggle.setDrawerSlideAnimationEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void fillList(ArrayList<Event> events){

		if(events == null || events.size() < 1) {
			Toast.makeText(this,"no resource found",Toast.LENGTH_LONG).show();
			return;
		}else {
			adapter_list_view_event adapter = new adapter_list_view_event(this, R.layout.listview_item_event, events);
			this.listView_events.setAdapter(adapter);
		}
	}

	private void showDiag() {

		final View dialogView = View.inflate(this,R.layout.activity_create_event_dialog_activty,null);

		final Dialog dialog = new Dialog(this,R.style.MyAlertDialogStyle);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(dialogView);

		ImageView image_close = (ImageView) dialog.findViewById(R.id.create_event_close);
		image_close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				revealShow(dialogView, false, dialog);
			}
		});

		dialog.setOnShowListener(new DialogInterface.OnShowListener() {
			@Override
			public void onShow(DialogInterface dialogInterface) {
				revealShow(dialogView, true, null);
			}
		});

		dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
				if (i == KeyEvent.KEYCODE_BACK){
					revealShow(dialogView, false, dialog);
					return true;
				}
				return false;
			}
		});
		final TextView input_date = (TextView) dialog.findViewById(R.id.create_event_input_date);

		input_date.setOnClickListener(new DatePickerListener());

		final Button button_submit = (Button) dialog.findViewById(R.id.create_event_button_submit);

		button_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				revealShow(dialogView,false, dialog);
			}
		});


		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

		dialog.show();
	}

	private void revealShow(View dialogView, boolean b, final Dialog dialog) {

		final View view = dialogView.findViewById(R.id.create_event_dialog_content);

		int w = view.getWidth();
		int h = view.getHeight();

		int endRadius = (int) Math.hypot(w, h);

		int cx = (int) (fab_add_event.getX() + (fab_add_event.getWidth()/2));
		int cy = (int) (fab_add_event.getY())+ fab_add_event.getHeight() + 56;


		if(b){
			Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx,cy, 0, endRadius);

			view.setVisibility(View.VISIBLE);
			revealAnimator.setDuration(400);
			revealAnimator.start();

		} else {

			Animator anim =
					ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius, 0);

			anim.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					super.onAnimationEnd(animation);
					dialog.dismiss();
					view.setVisibility(View.INVISIBLE);

				}
			});
			anim.setDuration(400);
			anim.start();
		}

	}

	private void openDatePicker(final TextView inputDate){

		// calender class's instance and get current date , month and year from calender
		final Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR); // current year
		int mMonth = c.get(Calendar.MONTH); // current month
		int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
		// date picker dialog
		DatePickerDialog datePickerDialog = new DatePickerDialog(this,
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
					                      int monthOfYear, int dayOfMonth) {
						// set day of month , month and year value in the edit text
						inputDate.setText(dayOfMonth + "/"
								+ (monthOfYear + 1) + "/" + year);

					}
				}, mYear, mMonth, mDay);
		datePickerDialog.show();
	}

	private void loadEvents(){
		LoadEventsSync loadEventsSync = new LoadEventsSync(getString(R.string.webservice_base_url));
		loadEventsSync.execute();
	}

	private void registrateHeaderListener(){
		View navHeader;
		navHeader = navigation.getHeaderView(0);
		navHeader.setOnClickListener(new NavMenuHeader_listener(this));

	}


	//listener

	private class fab_listener implements OnClickListener {

		private AppCompatActivity resource;
		private FloatingActionButton fab;

		public fab_listener(AppCompatActivity resource, FloatingActionButton fab) {
			this.resource = resource;
			this.fab = fab;
		}

		@Override
		public void onClick(View v) {
			showDiag();
		}
	}

	private class DatePickerListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			openDatePicker((TextView) v);
		}
	}

	//async Tasks

	private class LoadEventsSync extends AsyncTask<Object, Object, ArrayList<Event>> {

		//fields
		private String url;

		//constructors
		public LoadEventsSync(String url) {
			this.url = url;
		}


		//super
		@Override
		protected ArrayList<Event> doInBackground(Object... params) {
			try {
				Gson gson = new Gson();
				HttpURLConnection conn = (HttpURLConnection) new URL(this.url + "getAllEvents").openConnection();
				Type collectionType = new TypeToken<Collection<Event>>(){}.getType();
				String result = GetData(conn);
				ArrayList<Event> events = gson.fromJson(result, collectionType);
				return events;

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Event> events) {
			listView_events.removeHeaderView(progressView);
			fillList(events);
			super.onPostExecute(events);
		}

		@Override
		protected void onPreExecute() {
			listView_events.addHeaderView(progressView);
			super.onPreExecute();
		}

		private void PostData(HttpURLConnection conn, String... params){
			BufferedWriter writer;

			try{

				//posting the data
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				writer.write(params[0]); //product - object in json-format
				writer.flush();
				writer.close();
				conn.getResponseCode();

			}catch(Exception error){
				System.out.println("ERROR --- " + error);
			}

		}

		private String GetData(HttpURLConnection conn){
			BufferedReader reader;
			String content = null;

			try{
				//reading the result

				reader = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;

				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				content = sb.toString();
				reader.close();
				conn.disconnect();


			}catch(Exception error){
				System.out.println("ERROR --- " + error);
			}
			return content;
		}

	}



}
