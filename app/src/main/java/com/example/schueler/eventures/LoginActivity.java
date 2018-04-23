package com.example.schueler.eventures;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schueler.eventures.classes.pojo.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };


    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private ImageView img_login;
    private LinearLayout email_login_form;
    private Animation anim_horizontal_left;
    private Animation anim_horizontal_right;
    private Button btn_sign_up;
    private Button btn_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        this.setViews();
    }

    private void setViews() {
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        img_login = (ImageView) findViewById(R.id.img_login);
        email_login_form = (LinearLayout) findViewById(R.id.email_login_form);
        btn_sign_in = (Button) findViewById(R.id.email_sign_in_button);
        btn_sign_in.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btn_sign_up = (Button) findViewById(R.id.btn_sign_up);
        btn_sign_up.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registration_activity = new Intent(getApplicationContext(),Registration_Activity.class);
                getApplicationContext().startActivity(registration_activity);
            }
        });
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private void login() {

        Gson gson;
        LoginSync loginSync;

        //check if textfileds are empty
        if (IsEmpty(mEmailView)) {
        }

        //finally make post request

        try {
            loginSync = new LoginSync(getString(R.string.webservice_post_Login));
            gson = new Gson();
            User user = new User("Julian", "Black", new Date("1999/10/25"), "julian", "email@mail.com", "1234", "");
            loginSync.execute(gson.toJson(user).toString());

        } catch (Exception error) {
            Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private boolean IsEmpty(View view) {
        View focusView;
        if (TextUtils.isEmpty(mEmailView.toString())) {
            mEmailView.setError(this.getString(R.string.error_field_required));
            focusView = mEmailView;
            focusView.requestFocus();
            return true;
        }
        return false;
    }

    private void ShowProgressDialog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


    //async Tasks


    private class LoginSync extends AsyncTask<String, Void, String> {

        //fields
        private String url;
        ProgressDialog asyncDialog = new ProgressDialog(LoginActivity.this);

        //constructors

        public LoginSync(String url) {
            this.url = url;
        }

        //super

        @Override
        protected String doInBackground(String... params) {
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(this.url).openConnection();
                this.PostData(conn, params);
                return this.GetData(conn);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final String result) {
            asyncDialog.dismiss();
            Toast.makeText(getApplication(), result, Toast.LENGTH_LONG).show();
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

        private void PostData(HttpURLConnection conn, String... params) {
            BufferedWriter writer;

            try {

                //posting the data
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
                writer.write(params[0]); //product - object in json-format
                writer.flush();
                writer.close();
                conn.getResponseCode();

            } catch (Exception error) {
                System.out.println("ERROR --- " + error);
            }

        }

        private String GetData(HttpURLConnection conn) {
            BufferedReader reader;
            String content = null;

            try {
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


            } catch (Exception error) {
                System.out.println("ERROR --- " + error);
            }
            return content;
        }

    }

}

