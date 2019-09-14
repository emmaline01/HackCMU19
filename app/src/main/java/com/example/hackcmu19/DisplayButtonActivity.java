package com.example.hackcmu19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

public class DisplayButtonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String eatery = extras.getString(DisplayMessageActivity.EXTRA_MESSAGE3);

        TextView topLabel = findViewById(R.id.food);
        topLabel.setText(eatery);
    }
}
