package com.example.schueler.eventures.handler;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by schueler on 5/9/18.
 */

public abstract class HandlerState {

	public static void handle(Exception error,Context context){
		Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
	}

}
