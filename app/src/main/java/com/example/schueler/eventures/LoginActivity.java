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

import com.example.schueler.eventures.asynctask.TaskPostLogin;
import com.example.schueler.eventures.classes.pojo.User;
import com.example.schueler.eventures.classes.pojo.local.LocalDatabase;
import com.example.schueler.eventures.classes.pojo.local.LoginUserObject;
import com.example.schueler.eventures.classes.pojo.local.uIDObject;
import com.example.schueler.eventures.dialog.DialogError;
import com.example.schueler.eventures.dialog.DialogProgress;
import com.example.schueler.eventures.interfaces.InterfaceTaskDefault;
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
public class LoginActivity extends AppCompatActivity implements InterfaceTaskDefault {

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
    private ProgressDialog progressDialog;
    private LocalDatabase database;

    //super

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        this.setViews();
    }

    @Override
    public void onPreExecute(Class resource) {
        if(resource == TaskPostLogin.class){
            this.progressDialog = DialogProgress.getDialog(this);
            this.progressDialog.show();
        }
    }

    @Override
    public void onPostExecute(Object result, Class resource) {
        if(resource == TaskPostLogin.class){
            this.progressDialog.dismiss();
            String res = (String) result;
            Gson gson = new Gson();
            LocalDatabase.setuID(gson.fromJson(res,uIDObject.class).getuID());
            this.processResult(res);
        }
    }

    //custom

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
        database = LocalDatabase.getInstance();
    }

    private boolean isEmailValid() {

        if(TextUtils.isEmpty(this.mEmailView.getText().toString())){
            mEmailView.setError(this.getString(R.string.error_field_required));
            mEmailView.requestFocus();
            return false;
        }if(!this.mEmailView.getText().toString().contains("@")){
            mEmailView.setError(this.getString(R.string.error_invalid_email));
            mEmailView.requestFocus();
            return false;
        }
        return true;
    }

    private boolean isPasswordValid() {

        if(TextUtils.isEmpty(this.mPasswordView.getText().toString())){
            mPasswordView.setError(this.getString(R.string.error_field_required));
            mPasswordView.requestFocus();
            return false;
        }
        return true;
    }

    private void login() {

        Gson gson;
        TaskPostLogin task;

        //check if textfileds are empty
        if(isEmailValid() && isPasswordValid()){

            //send request to webservice
            try {
                gson = new Gson();
                String email = this.mEmailView.getText().toString();
                String password = this.mPasswordView.getText().toString();

                LoginUserObject param = new LoginUserObject(email,password);

                task = new TaskPostLogin(getString(R.string.webservice_post_Login),this);
                task.execute(gson.toJson(param).toString());

            } catch (Exception error) {
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }

    }

    public void processResult(String result){
        if(result == null){
            DialogError.getDialog("wrong credentials!",this).show();
            return;
        }
        Intent event_activity = new Intent(this,EventListActivity.class);
        this.startActivity(event_activity);
    }





}

