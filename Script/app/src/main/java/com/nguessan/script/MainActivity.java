package com.nguessan.script;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity {
    DatabaseHelper helper = new DatabaseHelper(this);
    LoginButton loginButton;
    CallbackManager callbackManager;



    @Override
    protected void onCreate  (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Android Insomnia Regular.ttf");
        loginButton = (LoginButton) findViewById(R.id.button5);
        TextView myTextView = (TextView) findViewById(R.id.textView);
        myTextView.setTypeface(myTypeface);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("LOGIN_SUCCESS", "Success");
                loginButton.setVisibility(View.INVISIBLE); //<- IMPORTANT
                Intent intent = new Intent(MainActivity.this,School.class);
                startActivity(intent);
                finish();//<- IMPORTANT

            }

            @Override
            public void onCancel() {
                Log.d("LOGIN_CANCEL", "Cancel");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("LOGIN_ERROR", "Error");

            }
        });

    }
    @Override
    protected void onActivityResult (int requestCode, int resultCode,Intent data){
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }


    public void onButtonClick (View view) {
        if (view.getId() == R.id.btnlogin) {

            EditText b = (EditText) findViewById(R.id.editTextp);
            String pass = b.getText().toString();
            EditText a = (EditText) findViewById(R.id.editTextu);
            String str = a.getText().toString();

            String password = helper.searchPass(str);

            if (pass.equals(password)) {
                Intent i = new Intent(MainActivity.this, School.class);
                startActivity(i);
            } else {
                Toast temp = Toast.makeText(MainActivity.this, "Username and Password don't match", Toast.LENGTH_SHORT);
                temp.show();
            }

        }
        if (view.getId() == R.id.button3) {
            Intent i = new Intent(MainActivity.this, signup.class);
            startActivity(i);
        }

    }

    public void onForgotClick (View v){
        if (v.getId() == R.id.button2){
            Intent intent = new Intent(MainActivity.this,homepage.class);
            startActivity(intent);
        }
    }
}

