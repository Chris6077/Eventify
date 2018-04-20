package com.example.schueler.eventures.listener;

import android.view.View;

/**
 * Created by schueler on 4/5/18.
 */
public abstract class DoubleTapListener implements View.OnClickListener {

	private static final long DOUBLE_CLICK_TIME_DELTA = 300;//milliseconds

	long lastClickTime = 0;

	@Override
	public void onClick(View v) {
		long clickTime = System.currentTimeMillis();
		if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA){
			onDoubleClick(v);
			lastClickTime = 0;
		} else {
			onSingleClick(v);
		}
		lastClickTime = clickTime;
	}

	public abstract void onSingleClick(View v);
	public abstract void onDoubleClick(View v);
}