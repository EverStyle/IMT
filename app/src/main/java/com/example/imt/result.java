package com.example.imt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.sqliteapp.DatabaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class result extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    EditText result;
    TextView opi1;
    String body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        result = (EditText) findViewById(R.id.result);
        opi1 = (TextView) findViewById(R.id.opi1);
        Bundle arguments = getIntent().getExtras();
        float rost = Float.parseFloat(arguments.get("rost").toString());
        float ves = Float.parseFloat(arguments.get("ves").toString());
        float rost1 = rost /100;
        float res = ves/(rost1*rost1);
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        db = databaseHelper.getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_VALUE, res);
        cv.put(DatabaseHelper.COLUMN_DATE, dateText);
        db.insert(DatabaseHelper.TABLE, null, cv);
        db.close();

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