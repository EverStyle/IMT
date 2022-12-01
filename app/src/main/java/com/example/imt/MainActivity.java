package com.example.imt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText rost;
    EditText ves;
    EditText god;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rost = (EditText) findViewById(R.id.rost);
        ves = (EditText) findViewById(R.id.ves);
        god = (EditText) findViewById(R.id.god);
    }

    public void checkResult(View view) {
        Intent intent = new Intent(MainActivity.this, result.class);
        intent.putExtra("rost", rost.getText());
        intent.putExtra("ves", ves.getText());
        intent.putExtra("god", god.getText());
        startActivity(intent);
    }
    public void runHistory(View view) {
        Intent intent = new Intent(MainActivity.this, scroll.class);
        startActivity(intent);
    }
}