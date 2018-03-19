package com.example.schueler.eventures.pkg_data.classes_test;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.schueler.eventures.Event;
import com.example.schueler.eventures.New_EventActivity;
import com.example.schueler.eventures.R;
import com.example.schueler.eventures.Registration_Activity;

/**
 * Created by schueler on 3/18/18.
 */

public class navmenu_listener extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	private Object obj;

	public navmenu_listener(Event event) {
		obj = event;
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		if(item == this.findViewById(R.id.mntm_add_event)){
			Intent new_event_activity = new Intent((Context) obj,New_EventActivity.class);
			startActivity(new_event_activity);
			//overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
		}
		return true;
	}
}
