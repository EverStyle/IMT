package com.example.imt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class scroll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(scroll.this);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        String IMT = sharedPreferences.getString("IMT", "unknown");
        TextView mytext = findViewById(R.id.mytext);
        mytext.setText(IMT);
    }
}