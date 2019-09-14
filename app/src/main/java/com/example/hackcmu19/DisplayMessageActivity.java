package com.example.hackcmu19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String msgDistMinutes = extras.getString(MainActivity.EXTRA_MESSAGE);
        String msgWaitingMinutes = extras.getString(MainActivity.EXTRA_MESSAGE2);

        // Capture the layout's TextView and set the string as its text
        TextView topLabel = findViewById(R.id.secondScreenMessage);
        topLabel.setText("You entered: walking for " + msgDistMinutes +
                "min and waiting for " + msgWaitingMinutes + "min");
    }
}
