package com.example.android.actionbarcompat.basic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;

public class NewActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "message to be passed";
    EditText username;
    EditText firstName;
    EditText lastName;
    String[] message;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        username = (EditText) findViewById(R.id.username);
        firstName = (EditText) findViewById(R.id.firstname);
        lastName = (EditText) findViewById(R.id.lastname);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        String[] messageName = {username.getText().toString(), firstName.getText().toString(), lastName.getText().toString()};
        intent.putExtra(EXTRA_MESSAGE, messageName);
        startActivity(intent);
    }
}
