package com.example.hackcmu19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.net.Uri;

import org.w3c.dom.Text;

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
        String latString = extras.getString(MainActivity.EXTRA_LAT_MESSAGE4);
        String longString = extras.getString(MainActivity.EXTRA_LON_MESSAGE4);

        TextView errorView = (TextView) findViewById(R.id.error1);
        errorView.setVisibility(View.INVISIBLE);
        errorView = (TextView) findViewById(R.id.error2);
        errorView.setVisibility(View.INVISIBLE);
        errorView = (TextView) findViewById(R.id.error3);
        errorView.setVisibility(View.INVISIBLE);
        errorView = (TextView) findViewById(R.id.error4);
        errorView.setVisibility(View.INVISIBLE);
        errorView = (TextView) findViewById(R.id.error5);
        errorView.setVisibility(View.INVISIBLE);

        // Capture the layout's TextView and set the string as its text
        TextView topLabel = findViewById(R.id.secondScreenMessage);
        topLabel.setText("Results for: walking for " + msgDistMinutes +
                " min and waiting for " + msgWaitingMinutes + " min");

        int distMin = Integer.parseInt(msgDistMinutes);
        int waitMin = Integer.parseInt(msgWaitingMinutes);

        //au bon pain, back bar grill, bowl life, carnegie mellon cafe, el gallo de oro, inoodle, nakama, rohr cafe (gates), taste of india, the underground
        int[] waitArray = {40, 20, 40, 20, 20, 5, 5, 10, 5, 15};

        String[] places = {"Au Bon Pain", "Back Bar Grill", "Bowl Life", "Carnegie Mellon Cafe", "El Gallo de Oro", "iNoodle", "Nakama", "Rohr Cafe",
                "Taste of India", "The Underground"};
        String[] locations ={"Cohon University Center","Cohon University Center","Cohon University Center","Resnik", "Cohon University Center",
                "Newell Simon Hall", "Resnik", "Gates-Hillman Center", "Resnik", "Morewood E-Tower"};

        double[] latitude = {40.443414, 40.443414, 40.443414, 40.442466, 40.443414, 40.443435,40.442466, 40.443437, 40.442466, 40.445464};
        double[] longitude = { -79.942058,  -79.942058,  -79.942058, -79.939765, -79.942058,-79.945671, -79.939765, -79.944530, -79.939765, -79.943241};
        //NSH: 40.443435, -79.945671
        //UC: 40.443414, -79.942058
        //underground: 40.445464, -79.943241
        //Resnik: 40.442466, -79.939765
        //Gates: 40.443437, -79.944530

        double lat = Double.parseDouble(latString);
        double lon = Double.parseDouble(longString);

        //double lat = 40.443602;
        //double lon = -79.944484;

        int[] distArray = new int [10];

        for(int i = 0; i < 10; i++){
            distArray[i] = Math.abs((int)((lat-latitude[i])*1500)) + Math.abs( (int) ((lon-longitude[i])*1500) );
        }

        ArrayList<Integer> available = new ArrayList<Integer>();
        ArrayList<Integer> times = new ArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            if (distArray[i] <= distMin && waitArray[i] <= waitMin) {
                available.add(i);
                times.add(waitArray[i] + distArray[i]);
            }
        }
        int origSize = available.size();
        int x = 0;

        if(origSize < 5){
            switch (origSize) {
                case 0:
                    errorView = (TextView) findViewById(R.id.error1);
                    errorView.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    errorView = (TextView) findViewById(R.id.error2);
                    errorView.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    errorView = (TextView) findViewById(R.id.error3);
                    errorView.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    errorView = (TextView) findViewById(R.id.error4);
                    errorView.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    errorView = (TextView) findViewById(R.id.error5);
                    errorView.setVisibility(View.VISIBLE);
                    break;
            }

        }

        while(available.size()<5){
            if(!available.contains(x)&& waitArray[x]< waitMin+15) {
                available.add(x);
                times.add(waitArray[x] + distArray[x]);
            }
            x++;
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

        TextView button = (TextView) findViewById(R.id.eatery1_name);
        button.setText(places[available.get(0)]);
        button = (TextView) findViewById(R.id.eatery1_dist);
        button.setText("Distance in minutes: "+ distArray[available.get(0)]);
        button = (TextView) findViewById(R.id.eatery1_time);
        button.setText("Wait time in minutes: "+ waitArray[available.get(0)]);
        button = (TextView) findViewById(R.id.eatery1_location);
        button.setText("Location: "+ locations[available.get(0)]);

        button = (TextView) findViewById(R.id.eatery2_name);
        button.setText(places[available.get(1)]);
        button = (TextView) findViewById(R.id.eatery2_dist);
        button.setText("Distance in minutes: "+ distArray[available.get(1)]);
        button = (TextView) findViewById(R.id.eatery2_time);
        button.setText("Wait time in minutes: "+ waitArray[available.get(1)]);
        button = (TextView) findViewById(R.id.eatery2_location);
        button.setText("Location: "+ locations[available.get(1)]);

        button =  (TextView)findViewById(R.id.eatery3_name);
        button.setText(places[available.get(2)]);
        button = (TextView) findViewById(R.id.eatery3_dist);
        button.setText("Distance in minutes: "+ distArray[available.get(2)]);
        button = (TextView) findViewById(R.id.eatery3_time);
        button.setText("Wait time in minutes: "+ waitArray[available.get(2)]);
        button = (TextView) findViewById(R.id.eatery3_location);
        button.setText("Location: "+ locations[available.get(2)]);

        button = (TextView)findViewById(R.id.eatery4_name);
        button.setText(places[available.get(3)]);
        button = (TextView) findViewById(R.id.eatery4_dist);
        button.setText("Distance in minutes: "+ distArray[available.get(3)]);
        button = (TextView) findViewById(R.id.eatery4_time);
        button.setText("Wait time in minutes: "+ waitArray[available.get(3)]);
        button = (TextView) findViewById(R.id.eatery4_location);
        button.setText("Location: "+ locations[available.get(3)]);

        button = (TextView)findViewById(R.id.eatery5_name);
        button.setText(places[available.get(4)]);
        button = (TextView) findViewById(R.id.eatery5_dist);
        button.setText("Distance in minutes: "+ distArray[available.get(4)]);
        button = (TextView) findViewById(R.id.eatery5_time);
        button.setText("Wait time in minutes: "+ waitArray[available.get(4)]);
        button = (TextView) findViewById(R.id.eatery5_location);
        button.setText("Location: "+ locations[available.get(4)]);
    }

    public void goToPage(View view) {
        Intent intent = new Intent(this, DisplayButtonActivity.class);
        TextView t = (TextView) findViewById(R.id.eatery1_name);
        String eateryName = "";

        switch (view.getId()) {
            case R.id.eatery1:
                t = (TextView) findViewById(R.id.eatery1_name);
                break;
            case R.id.eatery2:
                t = (TextView) findViewById(R.id.eatery2_name);
                break;
            case R.id.eatery3:
                t = (TextView) findViewById(R.id.eatery3_name);
                break;
            case R.id.eatery4:
                t = (TextView) findViewById(R.id.eatery4_name);
                break;
            case R.id.eatery5:
                t = (TextView) findViewById(R.id.eatery5_name);
                break;

        }
        //b = (Button) findViewById(view.getId());
        eateryName = t.getText().toString();

        String[] names = {"Au Bon Pain", "Back Bar Grill", "Bowl Life", "Carnegie Mellon Cafe", "El Gallo de Oro", "iNoodle", "Nakama", "Rohr Cafe",
                "Taste of India", "The Underground"};

        String[] urls = {"https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=113",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=138",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=142",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=88",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=91",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=110",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=97",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=115",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=114",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=112"};

        for(int i = 0; i < 10; i++){
            if(eateryName.equals(names[i]))
            {
                //goToUrl(urls[i]);
                intent.putExtra(EXTRA_MESSAGE3, urls[i]);
                break;
            }
        }
        startActivity(intent);
    }
    /*public void goToUrl(String url){
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);

    }*/
}