// INSPIRED BY: https://medium.com/@bhawanthagunawardana/android-sqlite-database-crud-s-with-example-application-4f5a841da8f6

package com.example.fitnessassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
// view to handle food list
public class ViewFoodActivity extends AppCompatActivity {
    private Cursor cr;
    ArrayList<String> workoutList;
    // food list
    ListView food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food);
        displayWorkouts();

    }

    // gets the info from database and display it
    public void displayWorkouts(){
        food = findViewById(R.id.workout);
        cr = DatabaseHandler.readDatabaseFood();
        SimpleCursorAdapter adapter;

        if(!cr.moveToFirst()){
            Toast.makeText(this, "Workouts not found", Toast.LENGTH_SHORT).show();
            return;
        }
        // layouts for simple cr adapter
        final int[]    layouts = { android.R.id.text1, android.R.id.text2 };
        // columns for simple cr adapter
        final String[] columns = { "weekDay", "foodCSV" };
        // simple cursor adapter
        adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cr,columns, layouts);
        // setting the adapter for food to simple cr adapter
        food.setAdapter(adapter);

    }
}


