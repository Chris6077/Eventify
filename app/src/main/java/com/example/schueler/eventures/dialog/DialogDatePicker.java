package com.example.schueler.eventures.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by schueler on 5/9/18.
 */

public abstract class DialogDatePicker {

	public static DatePickerDialog getDialog(final TextView inputDate,Context resource){
		// calender class's instance and get current date , month and year from calender
		final Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR); // current year
		int mMonth = c.get(Calendar.MONTH); // current month
		int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
		// date picker dialog
		DatePickerDialog datePickerDialog = new DatePickerDialog(resource,
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
					                      int monthOfYear, int dayOfMonth) {
						// set day of month , month and year value in the edit text
						inputDate.setText(dayOfMonth + "/"
								+ (monthOfYear + 1) + "/" + year);

					}
				}, mYear, mMonth, mDay);
		return datePickerDialog;
	}

	public static View.OnClickListener getListener(){
		return new DatePickerListener();
	}

	private static class DatePickerListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			//getDialog().show();
		}
	}

}
