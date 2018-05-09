package com.example.schueler.eventures.listener;

import android.content.Context;
import android.view.View;

import com.example.schueler.eventures.dialog.DialogCreateEvent;

/**
 * Created by schueler on 5/9/18.
 */

public class ListenerCreateEvent implements View.OnClickListener{

	final Context context;

	public ListenerCreateEvent(Context context) {
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		DialogCreateEvent.getDialog(this.context).show();
	}
}
