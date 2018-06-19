package com.example.schueler.eventures;

import android.animation.Animator;
import android.location.Address;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class New_EventActivity extends AppCompatActivity implements View.OnClickListener {

	DrawerLayout rootLayout;
	EditText mOrtView;
	Button createButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);
		setContentView(R.layout.activity_new__event);
		this.setViews();
		this.setUpCircularReveal();
		this.initOtherThings();
	}

    private void initOtherThings() {
        Bundle intentExtra = this.getIntent().getExtras();
        Object o = intentExtra.get("listAdress");
	    if(o != null){
            Address adress = (Address) o;
	        this.mOrtView.setText(adress.getAddressLine(0).toString());
        }

        this.createButton.setOnClickListener(this);
    }

    //custom

	private void setViews(){
		this.rootLayout = (DrawerLayout) findViewById(R.id.new_event_rootLayout);
		this.mOrtView = (EditText) findViewById(R.id.input_place);
		this.createButton = (Button) findViewById(R.id.create_event);
	}

	private void setUpCircularReveal(){


			rootLayout.setVisibility(View.INVISIBLE);

			ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
			if (viewTreeObserver.isAlive()) {
				viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						circularRevealActivity();
						if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
							rootLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
						} else {
							rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
						}
					}
				});
			}


	}

	private void circularRevealActivity() {
		int cx = rootLayout.getWidth() / 2;
		int cy = rootLayout.getHeight() / 2;

		float finalRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());

		// create the animator for this view (the start radius is zero)
		Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, cx, cy, 0, finalRadius);
		circularReveal.setDuration(1000);

		// make the view visible and start the animation
		rootLayout.setVisibility(View.VISIBLE);
		circularReveal.start();
	}

	@Override
	public void onClick(View view) {
		try{
			if(view.getId() == R.id.create_event){
				this.createEvent();
			}
		}catch(Exception ex){
			Log.d("error", ex.toString());
		}
	}

	private void createEvent() {
		int teilnehmer = Integer.parseInt(((TextView)findViewById(R.id.input_participants)).getText().toString());
		String beschreibung = ((EditText) findViewById(R.id.input_iformation)).getText().toString();

	}
}
