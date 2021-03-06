package com.example.schueler.eventures;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schueler.eventures.asynctask.TaskPostRegistrate;
import com.example.schueler.eventures.classes.pojo.User;
import com.example.schueler.eventures.classes.pojo.local.LocalDatabase;
import com.example.schueler.eventures.classes.pojo.local.RegistrationUserObject;
import com.example.schueler.eventures.classes.pojo.local.uIDObject;
import com.example.schueler.eventures.interfaces.InterfaceTaskDefault;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

/**
 * A login screen that offers login via email/password.
 */
public class Registration_Activity extends AppCompatActivity implements InterfaceTaskDefault {

	//fields
	private EditText mPasswordView;
	private EditText mEmailView;
	private EditText mFirstNameView;
	private EditText mLastNameView;
	private Button mBirthDateView;
	private Button btn_register;
	ProgressDialog asyncDialog;


	//super


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_);

		this.setViews();
		this.setUpListener();
	}

	//custom

	private void setViews(){
		mEmailView = (EditText) findViewById(R.id.email);
		mPasswordView = (EditText) findViewById(R.id.password);
		btn_register = (Button) findViewById(R.id.email_sign_in_button);
		mFirstNameView = (EditText) findViewById(R.id.firstname);
		mLastNameView = (EditText) findViewById(R.id.lastname);
		mBirthDateView = (Button) findViewById(R.id.birthdate);
		asyncDialog = new ProgressDialog(this);

	}

	private void setUpListener(){
		this.btn_register.setOnClickListener(new OnButtonRegisterClick());
		this.mBirthDateView.setOnClickListener(new OnTextViewBirthDateClick());
	}

	private boolean isEmailValid(String email) {
		//TODO: Replace this with your own logic
		return email.contains("@");
	}

	private boolean isPasswordValid(String password) {
		//TODO: Replace this with your own logic
		return password.length() > 4;
	}

	private void openPopupBirthdate(){
		// calender class's instance and get current date , month and year from calender
		final Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR); // current year
		int mMonth = c.get(Calendar.MONTH); // current month
		int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
		// date picker dialog
		DatePickerDialog datePickerDialog = new DatePickerDialog(this,
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
					                      int monthOfYear, int dayOfMonth) {
						// set day of month , month and year value in the edit text
						mBirthDateView.setText(dayOfMonth + "/"
								+ (monthOfYear + 1) + "/" + year);
						mBirthDateView.setClickable(true);

					}
				}, mYear, mMonth, mDay);
		datePickerDialog.show();
	}

	private void register(){

		Gson gson;
		RegisterSync registerSync;

		//check if textfileds are empty


		//finally make post request

		try{
			TaskPostRegistrate task = new TaskPostRegistrate(getString(R.string.webservice_post_Register),this);
			gson = new Gson();
			String email = this.mEmailView.getText().toString();
			String firstname = this.mFirstNameView.getText().toString();
			String lastname = this.mLastNameView.getText().toString();
			String birthdate = this.mBirthDateView.getText().toString();
			String password = this.mPasswordView.getText().toString();

			RegistrationUserObject param = new RegistrationUserObject(firstname,lastname,birthdate,email,password);
			task.execute(gson.toJson(param).toString());

		}catch(Exception error){
			Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show();
		}
	}

	private boolean IsEmpty(View view) {
		View focusView;
		if(TextUtils.isEmpty(mEmailView.toString())) {
			mEmailView.setError(this.getString(R.string.error_field_required));
			focusView = mEmailView;
			focusView.requestFocus();
			return true;
		}
		return false;
	}

	private void processResult(String result){
		if(result == null){
			Toast.makeText(this, "wrong credentials! ", Toast.LENGTH_SHORT).show();
			return;
		}
		Intent event_activity = new Intent(this,EventListActivity.class);
		this.startActivity(event_activity);
	}

	@Override
	public void onPreExecute(Class resource) {
		//set message of the dialog
		asyncDialog.setTitle(getString(R.string.progress_dialog_regestration_title));
		asyncDialog.setMessage(getString(R.string.progress_dialog_registration_message));
		//show dialog
		asyncDialog.show();
	}

	@Override
	public void onPostExecute(Object result, Class resource) {
		asyncDialog.dismiss();
		String res = (String) result;
		Gson gson = new Gson();
		LocalDatabase.setuID(gson.fromJson(res,uIDObject.class).getuID());
		this.processResult(res);
	}

	//listener

	private class OnButtonRegisterClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			register();
		}
	}

	private class OnTextViewBirthDateClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			mBirthDateView.setClickable(false);
			openPopupBirthdate();
		}
	}

	//async Tasks


	private class RegisterSync extends AsyncTask<String,Void,String> {

		//fields
		private String url;
		ProgressDialog asyncDialog = new ProgressDialog(Registration_Activity.this);

		//constructors

		public RegisterSync(String url) {
			this.url = url;
		}

		//super

		@Override
		protected String doInBackground(String... params) {
			try {
				HttpURLConnection conn = (HttpURLConnection) new URL(this.url).openConnection();
				this.PostData(conn,params);
				return this.GetData(conn);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(final String result){
			asyncDialog.dismiss();
			processResult(result);
		}

		@Override
		protected void onPreExecute() {
			//set message of the dialog
			asyncDialog.setTitle(getString(R.string.progress_dialog_regestration_title));
			asyncDialog.setMessage(getString(R.string.progress_dialog_registration_message));
			//show dialog
			asyncDialog.show();
			super.onPreExecute();
		}

		//custom

		private void PostData(HttpURLConnection conn,String... params){
			BufferedWriter writer;

			try{

				//posting the data
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				writer.write(params[0]); //product - object in json-format
				writer.flush();
				writer.close();
				conn.getResponseCode();

			}catch(Exception error){
				System.out.println("ERROR --- " + error);
			}

		}

		private String GetData(HttpURLConnection conn){
			BufferedReader reader;
			String content = null;

			try{
				//reading the result

				reader = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;

				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				content = sb.toString();
				reader.close();
				conn.disconnect();


			}catch(Exception error){
				System.out.println("ERROR --- " + error);
			}
			return content;
		}

	}


}

