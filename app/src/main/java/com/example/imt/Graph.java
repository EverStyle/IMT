package com.example.imt;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.sqliteapp.DatabaseHelper;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;


public class Graph extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    @SuppressLint({"Range", "SimpleDateFormat"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        GraphView graph = findViewById(R.id.graph);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        db = databaseHelper.getReadableDatabase();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);
        while (userCursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("date",userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_DATE)));
            user.put("value",userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_VALUE)));
            list.add(user);
        }
        userCursor.close();
        db.close();
        DateFormat formatter;
        formatter = new SimpleDateFormat("dd.MM.yyyy");
        DataPoint[] datapoint = new DataPoint[list.size()];
        Date d1 = null;
        Date d3 = null;
        try {
            d1 = (Date) formatter.parse(Objects.requireNonNull(list.get(0).get("date")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(int i=0;i<list.size();i++) {
            try {
                Date date = (Date) formatter.parse(Objects.requireNonNull(list.get(i).get("date")));
                assert date != null;
                datapoint[i] = new DataPoint(date, Double.parseDouble(Objects.requireNonNull(list.get(i).get("value"))));
                d3 = date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(datapoint);

        graph.addSeries(series);

// set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

        assert d1 != null;
        graph.getViewport().setMinX(d1.getTime());
        assert d3 != null;
        graph.getViewport().setMaxX(d3.getTime());
       graph.getViewport().setXAxisBoundsManual(true);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary
        graph.getGridLabelRenderer().setHumanRounding(false);
    }
}