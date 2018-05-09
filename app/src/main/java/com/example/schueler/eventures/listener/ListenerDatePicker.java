package com.example.schueler.eventures.listener;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.schueler.eventures.dialog.DialogDatePicker;

/**
 * Created by schueler on 5/9/18.
 */

public class ListenerDatePicker implements View.OnClickListener{

	TextView input;
	Context context;

	public ListenerDatePicker(final TextView input, final Context context) {
		this.input = input;
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		DialogDatePicker.getDialog(input,context).show();
	}
}
