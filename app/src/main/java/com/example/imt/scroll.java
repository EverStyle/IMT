package com.example.imt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.sqliteapp.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class scroll extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    ListView userList;
    Cursor userCursor;
    Button GraphButton;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        userList = findViewById(R.id.list);
        GraphButton = findViewById(R.id.graph);
        SeeData();
    }

    public void clear(View view) {
        databaseHelper = new DatabaseHelper(getApplicationContext());
        db = databaseHelper.getReadableDatabase();
        db.delete(DatabaseHelper.TABLE, null, null);
        db.close();
        SeeData();
    }
    public void graph(View view) {
        Intent intent = new Intent(scroll.this, Graph.class);
        startActivity(intent);
    }

    @SuppressLint("Range")
    public void SeeData() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        databaseHelper = new DatabaseHelper(getApplicationContext());
        db = databaseHelper.getReadableDatabase();
        userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);
        while (userCursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("date",userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_DATE)));
            user.put("value",userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_VALUE)));
            list.add(user);
        }
        userCursor.close();
        db.close();
        if (list.size()==0) {
            GraphButton.setVisibility(View.GONE);
        }
        ListAdapter adapter = new SimpleAdapter(scroll.this, list, R.layout.list_row,new String[]{"value","date"}, new int[]{R.id.value, R.id.date});
        userList.setAdapter(adapter);
    }
}