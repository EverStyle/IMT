package com.example.imt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    }

    public void checkResult(View view) {
        Intent intent = new Intent(MainActivity.this, result.class);
        boolean flag;
        boolean flag2;
        try {
            flag = Integer.parseInt(String.valueOf(rost.getText()))==0 || Integer.parseInt(String.valueOf(ves.getText()))==0;
            flag2 = String.valueOf(rost.getText()).equals("") || String.valueOf(ves.getText()).equals("");
        }
        catch (Exception e) {
            flag = true;
            flag2 = true;
        }
        if(flag || flag2) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Пожалуйста, корректно заполняйте поля!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            intent.putExtra("rost", rost.getText());
            intent.putExtra("ves", ves.getText());
            startActivity(intent);
        }
    }
    public void runHistory(View view) {
        Intent intent = new Intent(MainActivity.this, scroll.class);
        startActivity(intent);
    }
}