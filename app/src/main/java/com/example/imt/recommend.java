package com.example.imt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class recommend extends AppCompatActivity {
    ImageView che1l;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        che1l = findViewById(R.id.che1l);
        text = findViewById(R.id.text);
        String body = getIntent().getStringExtra("body").toString();
        int resID = che1l.getContext().getResources().getIdentifier(body , "drawable", che1l.getContext().getPackageName());
        che1l.setImageResource(resID);
        if (body.equals("lox")) {
            text.setText("Ну надо это, покушать");
        }
        if (body.equals("middle_man")) {
            text.setText("Расслабся бро");
        }
        if (body.equals("ne_middle_man")) {
            text.setText("Ну чет ты наелся нормально так");
        }
        if (body.equals("ultra_fat")) {
            text.setText("Жесть ты, чел...");
        }

    }
}