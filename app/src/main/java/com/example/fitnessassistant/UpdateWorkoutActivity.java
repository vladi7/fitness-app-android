package com.example.fitnessassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateWorkoutActivity extends AppCompatActivity {
    private String record = "Monday";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_workout);
        Spinner weekDays = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adpt = new ArrayAdapter<String>(UpdateWorkoutActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.weekDays));

        adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekDays.setAdapter(adpt);

        weekDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                switch(position){
                    case 0:
                        record="Monday";
                        break;
                    case 1:
                        record = "Tuesday";
                        break;
                    case 2:
                        record="Wednesday";
                        break;
                    case 3:
                        record="Thursday";
                        break;
                    case 4:
                        record="Friday";
                        break;
                    case 5:
                        record="Saturday";
                        break;
                    case 6:
                        record="Sunday";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    public void updateButtonPressed(View view) {

        EditText workoutInfo = findViewById(R.id.teRecipe);


        String workoutInfoNew = workoutInfo.getText().toString();

        DatabaseHandler.updateWorkoutUsingName(record, workoutInfoNew);
        Toast.makeText(this, "Workout updated", Toast.LENGTH_SHORT).show();

        workoutInfo.setText("");
    }
}