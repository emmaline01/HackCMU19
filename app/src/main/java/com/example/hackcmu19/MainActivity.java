package com.example.hackcmu19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.graphics.Color.*;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.hackcmu19.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.hackcmu19.MESSAGE2";
    public static final String EXTRA_MESSAGE3 = "com.example.hackcmu19.MESSAGE3";
    public static final String EXTRA_LAT_MESSAGE4 = "com.example.hackcmu19.LAT_MESSAGE4";
    public static final String EXTRA_LON_MESSAGE4 = "com.example.hackcmu19.LON_MESSAGE4";
    public static final int REQUEST_LOCATION_PERMISSION = 5;
    public Location lastLocation;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView error = findViewById(R.id.errorMsg);
        error.setVisibility(View.INVISIBLE);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    //get location permission on runtime
    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            fusedLocationClient.getLastLocation().addOnSuccessListener(
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            TextView error = findViewById(R.id.errorMsg);
                            error.setVisibility(View.VISIBLE);

                            if (location != null) {
                                lastLocation = location;
                            } else {
                                error.setText("location not available");
                            }
                        }
                    });
        }
    }

    //get runtime permissions?
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                // If the permission is granted, get the location,
                // otherwise, write a message to the log
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    Log.d("HackCMU19", "location permission denied");
                }
                break;
        }
    }

    /** Called when the user taps the ENTER button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        Bundle extras = new Bundle();

        TextView error = findViewById(R.id.errorMsg);

        EditText firstQ = (EditText) findViewById(R.id.distanceMinutes);
        String firstQMsg = firstQ.getText().toString();

        EditText secondQ = (EditText) findViewById(R.id.waitingTime);
        String secondQMsg = secondQ.getText().toString();

        getLocation();

        if (secondQMsg.equals("") || firstQMsg.equals("")) {
            error.setVisibility(View.VISIBLE);
            return;
        }

        /**
        Button uc = findViewById(R.id.UCLocationButton);
        Button gates = findViewById(R.id.GatesLocationButton);
        String thirdQMsg = "";
        if (((ColorDrawable) uc.getBackground()).getColor() ==
                Color.parseColor("#34c3eb")){
            //if the uc was clicked
            thirdQMsg = "UC";
        }
        else if (((ColorDrawable) gates.getBackground()).getColor() ==
                Color.parseColor("#34c3eb")) {
            //if gates was clicked
            thirdQMsg = "Gates";
        }
        else {
            //fail
            error.setVisibility(View.VISIBLE);
            return;
        }
        **/

        extras.putString(EXTRA_MESSAGE, firstQMsg);
        extras.putString(EXTRA_MESSAGE2, secondQMsg);
        extras.putString(EXTRA_LAT_MESSAGE4, Double.toString(lastLocation.getLatitude()));
        extras.putString(EXTRA_LON_MESSAGE4, Double.toString(lastLocation.getLongitude()));
        //extras.putString(EXTRA_MESSAGE3, thirdQMsg);

        intent.putExtras(extras);

        startActivity(intent);
    }
    /**
    public void locationButtonHit(View view, Button b) {
        Button uc = findViewById(R.id.UCLocationButton);
        Button gates = findViewById(R.id.GatesLocationButton);
        Button cfa = findViewById(R.id.CFALocationButton);

        Button[] locations = {uc, gates, cfa};

        if (((ColorDrawable) b.getBackground()).getColor() ==
                Color.parseColor("#34c3eb")) {
            b.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        }
        else{
            for (int i = 0; i < locations.length; i++){
                if (b.getId() != locations[i].getId() &&
                        ((ColorDrawable) locations[i].getBackground()).getColor() ==
                        Color.parseColor("#34c3eb")) {
                    return;
                }
            }
            b.setBackgroundColor(Color.parseColor("#34c3eb"));
        }
    }

    public void UCButtonHit(View view){
        Button uc = findViewById(R.id.UCLocationButton);
        locationButtonHit(view, uc);
    }
    public void gatesButtonHit(View view) {
        Button gates = findViewById(R.id.GatesLocationButton);
        locationButtonHit(view, gates);
    }
    public void CFAButtonHit(View view) {
        Button cfa = findViewById(R.id.CFALocationButton);
        locationButtonHit(view, cfa);
    }**/

        //this is NOT called when the button is pushed but it changes the label text
    public void inputText(View v) {
        EditText textIn = (EditText) findViewById(R.id.waitingTime);

        TextView textOut = (TextView) findViewById(R.id.question);
        textOut.setText(textIn.getText());
    }

}
