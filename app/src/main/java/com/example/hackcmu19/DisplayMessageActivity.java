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

        TextView fav = (TextView) findViewById(R.id.fav1);
        fav.setVisibility(View.INVISIBLE);
        fav = (TextView) findViewById(R.id.fav2);
        fav.setVisibility(View.INVISIBLE);
        fav = (TextView) findViewById(R.id.fav3);
        fav.setVisibility(View.INVISIBLE);
        fav = (TextView) findViewById(R.id.fav4);
        fav.setVisibility(View.INVISIBLE);
        fav = (TextView) findViewById(R.id.fav5);
        fav.setVisibility(View.INVISIBLE);

        // Capture the layout's TextView and set the string as its text
        TextView topLabel = findViewById(R.id.secondScreenMessage);
        topLabel.setText("Results for: walking for " + msgDistMinutes +
                " min and waiting for " + msgWaitingMinutes + " min");

        int distMin = Integer.parseInt(msgDistMinutes);
        int waitMin = Integer.parseInt(msgWaitingMinutes);

        //au bon pain, back bar grill, bowl life, carnegie mellon cafe, el gallo de oro, inoodle, nakama, rohr cafe (gates), taste of india, the underground
        int[] waitArray = {40, 20, 40, 30, 5, 5, 20, 30, 5, 5, 5, 5, 15, 10,5,5, 5, 15,5};

        String[] places = {"Au Bon Pain", "Back Bar Grill", "Bowl Life", "Carnegie Mellon Cafe", "Create," "Cucina," "El Gallo de Oro", "Inovation Kitchen", "iNoodle", "Maggie Murph Cafe", "Nakama", "Nourish", "Realwich", "Rohr Cafe", "Rooted",
                "Tartan Express", "Taste of India", "The Underground", "Urban Revolution"};
        String[] locations = {"Cohon University Center", "Cohon University Center", "Cohon University Center", "Resnik",  "Cohon University Center","Resnik" "Cohon University Center", "Resnik",
                "Newell Simon Hall", "Hunt Library", "Resnik", "Cohon University Center", "Cohon University Center", "Gates-Hillman Center",  "Cohon University Center", "Cohon University Center","Resnik", "Morewood E-Tower"};

        double[] latitude = {40.443414, 40.443414, 40.443414, 40.442466, 40.443414, 40.442466, 40.443414, 40.442466, 40.443435, 40.442466, 40.442466, 40.443414, 40.443414, 40.443437,40.443414, 40.443414, 40.442466, 40.445464,40.442466};
        double[] longitude = {-79.942058, -79.942058, -79.942058, -79.939765, -79.942058,-79.939765,-79.942058, -79.939765, -79.945671,-79.939765, -79.939765, -79.942058, -79.942058, -79.944530, -79.942058, -79.939765, -79.943241, -79.939765};
        //NSH: 40.443435, -79.945671
        //UC: 40.443414, -79.942058
        //underground: 40.445464, -79.943241
        //Resnik: 40.442466, -79.939765
        //Gates: 40.443437, -79.944530

        double lat = Double.parseDouble(latString);
        double lon = Double.parseDouble(longString);

        String[] favorites = new String[19];
        favorites[0] = extras.getString(MainActivity.EXTRA_ABP_MESSAGE5);
        //System.out.println(favorites[0]);
        favorites[1] = extras.getString(MainActivity.EXTRA_BACK_BAR_MESSAGE5);
        //System.out.println(favorites[1]);

        int[] distArray = new int[19];

        for (int i = 0; i < 19; i++) {
            distArray[i] = Math.abs((int) ((lat - latitude[i]) * 1500)) + Math.abs((int) ((lon - longitude[i]) * 1500));
        }

        ArrayList<Integer> available = new ArrayList<Integer>();
        ArrayList<Integer> times = new ArrayList<Integer>();

        int numFavorites = 0;
        for (int i = 0; i < 19; i++) {
            if (favorites[i] != null && !favorites[i].equals("") && distArray[i] <= distMin && waitArray[i] <= waitMin) {
                numFavorites++;
            }
        }

        for (int i = 0; i < 19; i++) {
            if ((favorites[i] == null || favorites[i].equals("")) && distArray[i] <= distMin && waitArray[i] <= waitMin) {
                available.add(i);
                times.add(waitArray[i] + distArray[i]);
            }
        }
        int origSize = available.size() + numFavorites;
        int x = 0;

        if (origSize < 5) {
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
        int increment = 5;
        while (available.size() < 5) {
            if (!available.contains(x) && waitArray[x] < waitMin + increment) {
                available.add(x);
                times.add(waitArray[x] + distArray[x]);
            }
            x++;
            if(x == 10) {
                x = 0;
                increment+=5;
            }
        }

        int n = times.size();
        //if(numFavorites==0)
        //   numFavorites=1;

        for (int i = 1; i < n; ++i) {
            int key = times.get(i);
            int index = available.get(i);
            int j = i - 1;
            while (j >= 0 && times.get(j) > key) {
                available.set(j + 1, available.get(j));
                times.set(j + 1, times.get(j));
                j = j - 1;
            }
            times.set(j + 1, key);
            available.set(j + 1, index);
        }
        //int num = 1;

        for (int i = 0; i < 19; i++) {
            if (favorites[i] != null && !favorites[i].equals("") && distArray[i] <= distMin && waitArray[i] <= waitMin) {
                available.add(0, i);
                /*switch (num) {
                    case 1:
                        fav = (TextView) findViewById(R.id.fav1);
                        fav.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        fav = (TextView) findViewById(R.id.fav2);
                        fav.setVisibility(View.VISIBLE);
                        break;
                }
                num++;*/
            }
        }

        TextView button = (TextView) findViewById(R.id.eatery1_name);
        button.setText(places[available.get(0)]);
        button = (TextView) findViewById(R.id.eatery1_dist);
        button.setText("Distance in minutes: " + distArray[available.get(0)]);
        button = (TextView) findViewById(R.id.eatery1_time);
        button.setText("Wait time in minutes: " + waitArray[available.get(0)]);
        button = (TextView) findViewById(R.id.eatery1_location);
        button.setText("Location: " + locations[available.get(0)]);
        if (favorites[available.get(0)] != null && !favorites[available.get(0)].equals("")){
            fav = (TextView) findViewById(R.id.fav1);
            fav.setVisibility(View.VISIBLE);

        }
        button = (TextView) findViewById(R.id.eatery2_name);
        button.setText(places[available.get(1)]);
        button = (TextView) findViewById(R.id.eatery2_dist);
        button.setText("Distance in minutes: "+ distArray[available.get(1)]);
        button = (TextView) findViewById(R.id.eatery2_time);
        button.setText("Wait time in minutes: "+ waitArray[available.get(1)]);
        button = (TextView) findViewById(R.id.eatery2_location);
        button.setText("Location: "+ locations[available.get(1)]);
        if (favorites[available.get(1)] != null && !favorites[available.get(1)].equals("")){
            fav = (TextView) findViewById(R.id.fav2);
            fav.setVisibility(View.VISIBLE);

        }

        button =  (TextView)findViewById(R.id.eatery3_name);
        button.setText(places[available.get(2)]);
        button = (TextView) findViewById(R.id.eatery3_dist);
        button.setText("Distance in minutes: "+ distArray[available.get(2)]);
        button = (TextView) findViewById(R.id.eatery3_time);
        button.setText("Wait time in minutes: "+ waitArray[available.get(2)]);
        button = (TextView) findViewById(R.id.eatery3_location);
        button.setText("Location: "+ locations[available.get(2)]);
        if (favorites[available.get(2)] != null && !favorites[available.get(2)].equals("")){
            fav = (TextView) findViewById(R.id.fav3);
            fav.setVisibility(View.VISIBLE);

        }

        button = (TextView)findViewById(R.id.eatery4_name);
        button.setText(places[available.get(3)]);
        button = (TextView) findViewById(R.id.eatery4_dist);
        button.setText("Distance in minutes: "+ distArray[available.get(3)]);
        button = (TextView) findViewById(R.id.eatery4_time);
        button.setText("Wait time in minutes: "+ waitArray[available.get(3)]);
        button = (TextView) findViewById(R.id.eatery4_location);
        button.setText("Location: "+ locations[available.get(3)]);
        if (favorites[available.get(3)] != null && !favorites[available.get(3)].equals("")){
            fav = (TextView) findViewById(R.id.fav4);
            fav.setVisibility(View.VISIBLE);

        }

        button = (TextView)findViewById(R.id.eatery5_name);
        button.setText(places[available.get(4)]);
        button = (TextView) findViewById(R.id.eatery5_dist);
        button.setText("Distance in minutes: "+ distArray[available.get(4)]);
        button = (TextView) findViewById(R.id.eatery5_time);
        button.setText("Wait time in minutes: "+ waitArray[available.get(4)]);
        button = (TextView) findViewById(R.id.eatery5_location);
        button.setText("Location: "+ locations[available.get(4)]);
        if (favorites[available.get(4)] != null && !favorites[available.get(4)].equals("")){
            fav = (TextView) findViewById(R.id.fav4);
            fav.setVisibility(View.VISIBLE);

        }
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

        String[] names = {"Au Bon Pain", "Back Bar Grill", "Bowl Life", "Carnegie Mellon Cafe", "Create," "Cucina," "El Gallo de Oro", "Inovation Kitchen", "iNoodle", "Maggie Murph Cafe", "Nakama", "Nourish", "Realwich", "Rohr Cafe", "Rooted",
                "Tartan Express", "Taste of India", "The Underground", "Urban Revolution"};

        String[] urls = {"https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=113",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=138",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=142",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=88",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=140",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=144",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=91",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=147",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=110",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=95",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=97",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=127",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=143",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=115",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=141",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=82",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=114",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=112",
                "https://apps.studentaffairs.cmu.edu/dining/conceptinfo/?page=conceptDetails&conceptId=146"};

        for(int i = 0; i < 19; i++){
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
