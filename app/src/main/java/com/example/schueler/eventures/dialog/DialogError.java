package com.example.schueler.eventures.dialog;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by schueler on 5/30/18.
 */

public class DialogError {

	public static AlertDialog getDialog(String errorMsg,Context resource){
		AlertDialog.Builder builder = new AlertDialog.Builder(resource);
		AlertDialog dialog = builder.create();
		dialog.setTitle("An Error occurred");
		dialog.setMessage(errorMsg);
		return dialog;
	}

	public static AlertDialog getDialog(Exception errorMsg,Context resource){
		return getDialog(errorMsg.getMessage(),resource);
	}
}
