package com.example.schueler.eventures.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.schueler.eventures.EventListActivity;
import com.example.schueler.eventures.R;
import com.example.schueler.eventures.listener.ListenerDatePicker;

/**
 * Created by schueler on 5/9/18.
 */

public abstract class DialogCreateEvent {


	public static Dialog getDialog(final Context context, Address adress){

		final View dialogView = View.inflate(context,R.layout.activity_create_event_dialog_activty,null);
		final Dialog dialog = new Dialog(context,R.style.MyAlertDialogStyle);
		final LinearLayout content = (LinearLayout) dialogView.findViewById(R.id.content_create_event);

		content.setFocusable(false);

		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(dialogView);

		ImageView image_close = (ImageView) dialog.findViewById(R.id.create_event_close);
		image_close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				revealShow(dialogView, false, dialog);
			}
		});

		dialog.setOnShowListener(new DialogInterface.OnShowListener() {
			@Override
			public void onShow(DialogInterface dialogInterface) {
				revealShow(dialogView, true, null);
			}
		});

		dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
				if (i == KeyEvent.KEYCODE_BACK){
					revealShow(dialogView, false, dialog);
					return true;
				}
				return false;
			}
		});
		final Button input_begin_date = (Button) dialog.findViewById(R.id.create_event_input_begin_date);
		final Button input_end_date = (Button) dialog.findViewById(R.id.create_event_input_end_date);
		final EditText input_place = (EditText) dialog.findViewById(R.id.input_place);

		input_begin_date.setOnClickListener(new ListenerDatePicker(input_begin_date,context));
		input_end_date.setOnClickListener(new ListenerDatePicker(input_end_date,context));
		if(adress != null)
			input_place.setText(adress.toString());

		final Button button_submit = (Button) dialog.findViewById(R.id.create_event_button_submit);

		button_submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				revealShow(dialogView,false, dialog);
			}
		});


		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

		return dialog;
	}

	public static Dialog getDialog(final Context context){
		return getDialog(context,null);
	}

	private static void revealShow(View dialogView, boolean b, final Dialog dialog) {

		final View view = dialogView.findViewById(R.id.new_event_rootLayout);

		int w = view.getWidth();
		int h = view.getHeight();

		int endRadius = (int) Math.hypot(w, h);

/*		int cx = (int) (fab_add_event.getX() + (fab_add_event.getWidth()/2));
		int cy = (int) (fab_add_event.getY())+ fab_add_event.getHeight() + 56;*/

		int cx = 100;
		int cy = 100;


		if(b){
			Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx,cy, 0, endRadius);

			view.setVisibility(View.VISIBLE);
			revealAnimator.setDuration(400);
			revealAnimator.start();

		} else {

			Animator anim =
					ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius, 0);

			anim.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					super.onAnimationEnd(animation);
					dialog.dismiss();
					view.setVisibility(View.INVISIBLE);

				}
			});
			anim.setDuration(400);
			anim.start();
		}

	}

}
