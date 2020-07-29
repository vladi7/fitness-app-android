package com.example.fitnessassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewFoodActivity extends AppCompatActivity {
    private Cursor cr;
    ArrayList<String> workoutList;
    ListView workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food);
        displayWorkouts();

    }


    public void displayWorkouts(){
        workout = findViewById(R.id.workout);
        cr = DatabaseHandler.readDatabaseFood();
        SimpleCursorAdapter adapter;

        if(!cr.moveToFirst()){
            Toast.makeText(this, "Workouts not found", Toast.LENGTH_SHORT).show();
            return;
        }
        final int[]    layouts = { android.R.id.text1, android.R.id.text2 };

        final String[] columns = { "weekDay", "foodCSV" };
        adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cr,columns, layouts);
        workout.setAdapter(adapter);

    }
}


