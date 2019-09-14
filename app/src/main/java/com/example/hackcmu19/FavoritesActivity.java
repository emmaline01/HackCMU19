package com.example.hackcmu19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FavoritesActivity extends AppCompatActivity {

    public static final String ABP = "ABPKey";
    public static final String BACK_BAR_GRILL = "BackBarGrillKey";
    public static final String BOWL_LIFE = "BowlLifeKey";
    public String[] keys = {ABP, BACK_BAR_GRILL, BOWL_LIFE};

    public static final String myPreference = "myPref";
    public SharedPreferences sharedPreferences;
    public Button ABPbutton;
    public Button BackBarGrillButton;
    public Button BowlLifeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);


        ABPbutton = findViewById(R.id.ABP);
        BackBarGrillButton = findViewById(R.id.BackBarGrill);
        BowlLifeButton = findViewById(R.id.BowlLife);
        Button[] buttons = {ABPbutton, BackBarGrillButton, BowlLifeButton};

        sharedPreferences = getSharedPreferences(myPreference,
                Context.MODE_PRIVATE);

        for (int i = 0; i < keys.length; i++){
            if (sharedPreferences.contains(keys[i]) &&
                    sharedPreferences.getBoolean(keys[i], false)){
                buttons[i].setBackgroundColor(Color.parseColor("#b8b8b8"));
            }
        }
    }

    public void faveButtonHit(View v, Button b) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Button[] buttons = {ABPbutton, BackBarGrillButton, BowlLifeButton};

        for (int i = 0; i < buttons.length; i++) {
            //if button is already hit
            if (b.getId() == buttons[i].getId()) {
                if (((ColorDrawable) buttons[i].getBackground()).getColor() !=
                        Color.parseColor("#b8b8b8")) {
                    buttons[i].setBackgroundColor(Color.parseColor("#b8b8b8"));
                    editor.putBoolean(keys[i], true);
                } else {
                    //if it is already grayed out, make it white
                    buttons[i].setBackgroundColor(Color.parseColor("#ffffff"));
                    editor.putBoolean(keys[i], false);
                }
                editor.commit();
            }
        }

    }

    public void ABPButtonHit(View view){
        faveButtonHit(view, ABPbutton);
    }

    public void BackBarGrillButtonHit(View view){
        faveButtonHit(view, BackBarGrillButton);
    }

    public void BowlLifeButtonHit(View view){
        faveButtonHit(view, BowlLifeButton);
    }
}
