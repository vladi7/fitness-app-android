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

public class ViewWorkoutActivity extends AppCompatActivity {
    private Cursor cr;
    ListView workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);

        displayWorkouts();
    }


    public void displayWorkouts(){

        cr = DatabaseHandler.readDatabaseWorkouts();
        workout = findViewById(R.id.workout);


        SimpleCursorAdapter adapter;

        if(!cr.moveToFirst()){
            Toast.makeText(this, "Workouts not found", Toast.LENGTH_SHORT).show();
            return;
        }
            final int[]    layouts = { android.R.id.text1, android.R.id.text2 };

            final String[] columns = { "weekDay", "workoutCSV" };
            adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cr,columns, layouts);
            workout.setAdapter(adapter);



    }
}