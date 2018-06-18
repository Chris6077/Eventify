package com.example.schueler.eventures.interfaces;

/**
 * Created by schueler on 5/29/18.
 */

public interface InterfaceTaskDefault {
	void onPreExecute(Class resource);
	void onPostExecute(Object result, Class resource);
}
