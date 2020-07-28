package com.example.fitnessassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_food);
    }
    public void updateButtonPressed(View view) {
        EditText oldNameET = findViewById(R.id.name1ET);
        EditText workoutInfo = findViewById(R.id.teRecipe);

        String oldName = oldNameET.getText().toString();
        String workoutInfoNew = workoutInfo.getText().toString();

        DatabaseHandler.updateFoodUsingName(oldName, workoutInfoNew);
        Toast.makeText(this, "Food Schedule updated", Toast.LENGTH_SHORT).show();

        oldNameET.setText("");
        workoutInfo.setText("");
    }
}