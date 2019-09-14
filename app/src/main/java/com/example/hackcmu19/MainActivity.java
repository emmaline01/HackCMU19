package com.example.hackcmu19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.graphics.Color.*;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.hackcmu19.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.hackcmu19.MESSAGE2";
    public static final String EXTRA_MESSAGE3 = "com.example.hackcmu19.MESSAGE3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView error = findViewById(R.id.errorMsg);
        error.setVisibility(View.INVISIBLE);
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
        System.out.println(secondQMsg + " " + firstQMsg);
        if (secondQMsg.equals("") || firstQMsg.equals("")) {
            error.setVisibility(View.VISIBLE);
            return;
        }

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

        extras.putString(EXTRA_MESSAGE, firstQMsg);
        extras.putString(EXTRA_MESSAGE2, secondQMsg);
        extras.putString(EXTRA_MESSAGE3, thirdQMsg);

        intent.putExtras(extras);

        startActivity(intent);
    }

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
    }

        //this is NOT called when the button is pushed but it changes the label text
    public void inputText(View v) {
        EditText textIn = (EditText) findViewById(R.id.waitingTime);

        TextView textOut = (TextView) findViewById(R.id.question);
        textOut.setText(textIn.getText());
    }

}
