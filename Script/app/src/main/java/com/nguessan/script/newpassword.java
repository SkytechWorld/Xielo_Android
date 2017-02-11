package com.nguessan.script;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class newpassword extends AppCompatActivity {
    DatabaseHelper db;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpassword);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Android Insomnia Regular.ttf");
        TextView myTextView = (TextView) findViewById(R.id.textViewnp);
        myTextView.setTypeface(myTypeface);
        username = getIntent().getStringExtra("username");
        db = new DatabaseHelper(this);


    }
    public void onNewpasswordClick (View view) {
        if (view.getId() == R.id.buttonsubmitnp) {

            EditText b = (EditText) findViewById(R.id.editTextnp1);
            String password = b.getText().toString();
            EditText a = (EditText) findViewById(R.id.editTextnp2);
            String pass = a.getText().toString();



            if (pass.equals(password)) {
                db.updatePassword(username, password);
                Toast.makeText(newpassword.this,"Password updated", Toast.LENGTH_SHORT).show();

                final Intent i = new Intent(newpassword.this, MainActivity.class);

                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            startActivity(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();

            }
            else {
                Toast.makeText(newpassword.this, "Passwords don't match", Toast.LENGTH_SHORT).show();

            }


        }
    }
}