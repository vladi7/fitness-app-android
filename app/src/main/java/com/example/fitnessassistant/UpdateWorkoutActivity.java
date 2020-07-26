package com.example.fitnessassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateWorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_workout);
    }

    public void updateButtonPressed(View view) {
        EditText oldNameET = findViewById(R.id.name1ET);
        EditText workoutInfo = findViewById(R.id.workoutInfo);

        String oldName = oldNameET.getText().toString();
        String workoutInfoNew = workoutInfo.getText().toString();

        DatabaseHandler.updateUsingName(oldName, workoutInfoNew);
        Toast.makeText(this, "Workout updated", Toast.LENGTH_SHORT).show();

        oldNameET.setText("");
        workoutInfo.setText("");
    }
}