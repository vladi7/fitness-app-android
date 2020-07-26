package com.example.fitnessassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ViewWorkoutActivity extends AppCompatActivity {
    private Cursor cr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);

        displayStudents();
    }


    public void displayStudents(){

        cr = DatabaseHandler.readDatabase();
        LinearLayout parent = (LinearLayout) findViewById(R.id.myLinearLayout);


        int count =0;
        if(!cr.moveToFirst()){
            Toast.makeText(this, "Workouts not found", Toast.LENGTH_SHORT).show();
            return;
        }
        do{
            count++;
            //display each student here
            String nameDB = cr.getString(1); //column no 2
            String numberDB = cr.getString(2); //column no 3

            LinearLayout child = new LinearLayout(this);
            child.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            child.setOrientation(LinearLayout.HORIZONTAL);

            TextView name = new TextView(this);
            TextView number = new TextView(this);

            name.setTextColor(Color.BLACK);
            number.setTextColor(Color.BLACK);

            //set textview weight = 1
            TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f);
            name.setLayoutParams(params);
            number.setLayoutParams(params);


            name.setText(nameDB);
            number.setText("\t\t\t"+numberDB);

            parent.addView(child);
            child.addView(name);
            child.addView(number);



        }while(cr.moveToNext());

        Log.d("DEBUG", "COUNT: "+count);

    }
}