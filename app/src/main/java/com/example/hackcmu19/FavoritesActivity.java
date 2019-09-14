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
    public static final String myPreference = "myPref";
    public SharedPreferences sharedPreferences;
    public Button ABPbutton;
    public Button BackBarGrillButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        ABPbutton = findViewById(R.id.ABP);
        BackBarGrillButton = findViewById(R.id.BackBarGrill);
        sharedPreferences = getSharedPreferences(myPreference,
                Context.MODE_PRIVATE);


        //if the button is saved as pushed, then push it here
        if (sharedPreferences.contains(ABP) && sharedPreferences.getBoolean(ABP, false)){
            ABPbutton.setBackgroundColor(Color.parseColor("#b8b8b8"));
        }
        if (sharedPreferences.contains(BACK_BAR_GRILL) &&
                sharedPreferences.getBoolean(BACK_BAR_GRILL, false)){
            BackBarGrillButton.setBackgroundColor(Color.parseColor("#b8b8b8"));
        }
    }

    public void ABPButtonHit(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //if it's not already grayed out, make it grayed out
        if (((ColorDrawable) ABPbutton.getBackground()).getColor() !=
                Color.parseColor("#b8b8b8")) {
            ABPbutton.setBackgroundColor(Color.parseColor("#b8b8b8"));
            editor.putBoolean(ABP, true);
        }
        else {
            //if it is already grayed out, make it white
            ABPbutton.setBackgroundColor(Color.parseColor("#ffffff"));
            editor.putBoolean(ABP,false);
        }
        editor.commit();
    }

    public void BackBarGrillButtonHit(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //if it's not already grayed out, make it grayed out
        if (((ColorDrawable) BackBarGrillButton.getBackground()).getColor() !=
                Color.parseColor("#b8b8b8")) {
            BackBarGrillButton.setBackgroundColor(Color.parseColor("#b8b8b8"));
            editor.putBoolean(BACK_BAR_GRILL, true);
        }
        else {
            //if it is already grayed out, make it white
            BackBarGrillButton.setBackgroundColor(Color.parseColor("#ffffff"));
            editor.putBoolean(BACK_BAR_GRILL,false);
        }
        editor.commit();
    }
}
