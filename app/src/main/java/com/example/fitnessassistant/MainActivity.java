package com.example.fitnessassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
// this one is purely to handle the front screen of the app.
public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler.loadDatabase(this);
    }
    // checks which button is pressed and changes the view accordingly
    public void buttonPressed(View view) {
        String tag = view.getTag().toString().toLowerCase();
        switch (tag) {
            case "viewbutton": {
                Intent intent = new Intent(MainActivity.this, ViewWorkoutActivity.class);
                startActivity(intent);
                break;
            }
            case "updatebutton": {
                Intent intent = new Intent(MainActivity.this, UpdateWorkoutActivity.class);
                startActivity(intent);
                break;
            }
            case "viewbuttonfood": {
                Intent intent = new Intent(MainActivity.this, ViewFoodActivity.class);
                startActivity(intent);
                break;
            }
            case "updatefoodbutton": {
                Intent intent = new Intent(MainActivity.this, UpdateFoodActivity.class);
                startActivity(intent);
                break;
            }
            case "weatherbutton": {
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                startActivity(intent);
                break;
            }
            case "recipe": {
                Intent intent = new Intent(MainActivity.this, CaloriesAPIActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}