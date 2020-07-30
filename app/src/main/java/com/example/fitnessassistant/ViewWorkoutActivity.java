// INSPIRED BY: https://medium.com/@bhawanthagunawardana/android-sqlite-database-crud-s-with-example-application-4f5a841da8f6

package com.example.fitnessassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
// view to handle food list
public class ViewWorkoutActivity extends AppCompatActivity {
    private Cursor cr;
    // workout list
    ListView workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);

        displayWorkouts();
    }

    // gets the info from database and display it
    public void displayWorkouts(){

        cr = DatabaseHandler.readDatabaseWorkouts();
        workout = findViewById(R.id.workout);


        SimpleCursorAdapter adapter;

        if(!cr.moveToFirst()){
            Toast.makeText(this, "Workouts not found", Toast.LENGTH_SHORT).show();
            return;
        }
        // layouts for simple cr adapter
        final int[]    layouts = { android.R.id.text1, android.R.id.text2 };
        // columns for simple cr adapter
            final String[] columns = { "weekDay", "workoutCSV" };
        // simple cursor adapter
        adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cr,columns, layouts);
        // setting the adapter for food to simple cr adapter
        workout.setAdapter(adapter);



    }
}