package com.example.schueler.eventures.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by schueler on 3/23/18.
 */

public class view_holder_event {

	private View item;

	private TextView title;
	private TextView category;
	private ImageView icon;


	//constructors

	public view_holder_event(View item) {
		this.setItem(item);
	}


	//getters & setters


	public View getItem() {
		return item;
	}

	public void setItem(View item) {
		this.item = item;
	}

	public TextView getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.getTitle().setText(title);
	}

	public TextView getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.getCategory().setText(category);
	}

	public ImageView getIcon() {
		return icon;
	}

	public void setIcon(String path) {

	}
}
