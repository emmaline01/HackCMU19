package com.example.hackcmu19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.hackcmu19.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.hackcmu19.MESSAGE2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        Bundle extras = new Bundle();

        EditText firstQ = (EditText) findViewById(R.id.distanceMinutes);
        String firstQMsg = firstQ.getText().toString();

        EditText secondQ = (EditText) findViewById(R.id.waitingTime);
        String secondQMsg = secondQ.getText().toString();

        extras.putString(EXTRA_MESSAGE, firstQMsg);
        extras.putString(EXTRA_MESSAGE2, secondQMsg);

        intent.putExtras(extras);

        startActivity(intent);
    }

    //this is NOT called when the button is pushed but it changes the label text
    public void inputText(View v) {
        EditText textIn = (EditText) findViewById(R.id.waitingTime);

        TextView textOut = (TextView) findViewById(R.id.question);
        textOut.setText(textIn.getText());
    }

}
