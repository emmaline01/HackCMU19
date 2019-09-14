package com.example.hackcmu19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

public class DisplayMessageActivity extends AppCompatActivity{
    public static final String EXTRA_MESSAGE3 = "com.example.hackcmu19.MESSAGE3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String msgDistMinutes = extras.getString(MainActivity.EXTRA_MESSAGE);
        String msgWaitingMinutes = extras.getString(MainActivity.EXTRA_MESSAGE2);
        String currLocation = extras.getString(MainActivity.EXTRA_MESSAGE3);

        // Capture the layout's TextView and set the string as its text
        TextView topLabel = findViewById(R.id.secondScreenMessage);
        topLabel.setText("You entered: walking for " + msgDistMinutes +
                "min and waiting for " + msgWaitingMinutes + "min");

    }
    public void goToPage(View view)
    {
        Intent intentButton = new Intent(this, DisplayButtonActivity.class);
        Button b = (Button) findViewById(R.id.eatery1);

        String eateryName;

        switch (view.getId()) {
            case R.id.eatery1:
                b = (Button) findViewById(R.id.eatery1);
                break;
            case R.id.eatery2:
                b = (Button) findViewById(R.id.eatery2);
                break;
            case R.id.eatery3:
                b = (Button) findViewById(R.id.eatery3);
                break;
            case R.id.eatery4:
                b = (Button) findViewById(R.id.eatery4);
                break;
            case R.id.eatery5:
                b = (Button) findViewById(R.id.eatery5);
                break;

        }
        //b = (Button) findViewById(view.getId());
        eateryName = b.getText().toString();

        intentButton.putExtra(EXTRA_MESSAGE3, eateryName);
        startActivity(intentButton);
    }
}
