package com.example.schueler.eventures.dialog;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.schueler.eventures.R;

/**
 * Created by schueler on 5/29/18.
 */

public class DialogProgress {

	public static ProgressDialog getDialog(Context resource){
		ProgressDialog dialog = new ProgressDialog(resource);
		dialog.setTitle(resource.getString(R.string.progress_dialog_regestration_title));
		dialog.setMessage(resource.getString(R.string.progress_dialog_login_message));
		return dialog;
	}

}
