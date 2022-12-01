package com.example.imt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class result extends AppCompatActivity {
    EditText result;
    TextView opi1;
    String body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = (EditText) findViewById(R.id.result);
        opi1 = (TextView) findViewById(R.id.opi1);
        Bundle arguments = getIntent().getExtras();
        float rost = Float.parseFloat(arguments.get("rost").toString());
        float ves = Float.parseFloat(arguments.get("ves").toString());
        float god = Float.parseFloat(arguments.get("god").toString());
        float rost1 = rost /100;
        float res = ves/(rost1*rost1);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(result.this);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        String IMT = sharedPreferences.getString("IMT", "unknown");
        if (Objects.equals(IMT, "unknown")) {
            editor.putString("IMT", String.valueOf(res));
        }
        else {
            editor.putString("IMT", IMT+"\n" + String.valueOf(res));
        }
        editor.commit();
        result.setText(String.valueOf(res));
        if(res<16) {
            opi1.setText("Выраженный дифицит массы тела.\n ");
            body = "lox";
        }
        if (res>16.0 && res<18.5) {
            opi1.setText("Недостаточная (дифицит) масса тела.\n");
            body = "lox";
        }
        if (res<24.99 && res>18.5) {
            opi1.setText("Норма.\n ");
            body = "middle_man";
        }
        if (res<30.0 && res>25.0) {
            opi1.setText("Избыточная масса тела.\n ");
            body = "ne_middle_man";
        }
        if (res<35.0 && res>30.0) {
            opi1.setText("Ожирение первой степени.\n ");
            body = "fat";
        }
        if (res<40.0 && res>35.0) {
            opi1.setText("Ожирение второй степени.\n ");
            body = "ultra_fat";
        }
        if (res>40.0) {
            opi1.setText("Ожирение третьей степени.\n ");
            body = "ultra_fat";
        }
    }
    public void checkResult(View view) {
        Intent intent = new Intent(getApplicationContext(), recommend.class);
        intent.putExtra("body", body);
        startActivity(intent);
    }
    public void goBrowser(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://krasunia.ru/wp-content/uploads/6/0/6/606714501afd27b5f5fbdc581069fca3.jpg"));
        startActivity(browserIntent);
    }
}