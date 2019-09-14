package com.example.hackcmu19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity {
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
                " min and waiting for " + msgWaitingMinutes + " min");

        int distMin = Integer.parseInt(msgDistMinutes);
        int waitMin = Integer.parseInt(msgWaitingMinutes);

        //au bon pain, back bar grill, bowl life, carnegie mellon cafe, el gallo de oro, inoodle, nakama, rohr cafe (gates), taste of india, the underground
        int[] waitArray = {40, 20, 40, 20, 20, 5, 5, 10, 5, 15};
        int[] distArray = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

        String[] places = {"Au Bon Pain", "Back Bar Grill", "Bowl Life", "Carnegie Mellon Cafe", "El Gallo de Oro", "iNoodle", "Nakama", "Rohr Cafe",
                "Taste of India", "The Underground"};

        ArrayList<Integer> available = new ArrayList<Integer>();
        ArrayList<Integer> times = new ArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            if (distArray[i] <= distMin && waitArray[i] < distMin) {
                available.add(i);
                times.add(waitArray[i] + distArray[i]);
            }
        }

        int n = times.size();
        for (int i = 1; i < n; ++i) {
            int key = times.get(i);
            int index = available.get(i);
            int j = i - 1;
            while (j >= 0 && times.get(j) > key) {
                available.set(j+1, available.get(j));
                times.set(j + 1, times.get(j));
                j = j - 1;
            }
            times.set(j + 1, key);
            available.set(j+1, index);
        }

        //Button button = (Button) findViewById(R.id.eatery1);
        //button.setText(places[available.get(0)]);

        //button = (Button) findViewById(R.id.eatery2);
        //button.setText(places[available.get(1)]);

        //button =  (Button)findViewById(R.id.eatery3);
        //button.setText(places[available.get(2)]);

        //button = (Button)findViewById(R.id.eatery4);
        //button.setText(places[available.get(3)]);

        //button = (Button)findViewById(R.id.eatery5);
        //button.setText(places[available.get(4)]);

    }

    public void goToPage(View view) {
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

        }
        //b = (Button) findViewById(view.getId());
        eateryName = b.getText().toString();

        intentButton.putExtra(EXTRA_MESSAGE3, eateryName);
        startActivity(intentButton);
    }
}