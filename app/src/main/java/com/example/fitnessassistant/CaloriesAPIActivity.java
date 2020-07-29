package com.example.fitnessassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class CaloriesAPIActivity extends AppCompatActivity {
    private Button callAPIText;
    private Button callAPIVoice;
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private String cals = "250";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_a_p_i);


        Intent mainIntent = getIntent();
        callAPIText = (Button) findViewById(R.id.callAPI);
        callAPIVoice = (Button) findViewById(R.id.callAPI_voice);



        callAPIText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    thread.start();

                }
            });
        callAPIVoice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
        Spinner weekDays = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adpt = new ArrayAdapter<String>(CaloriesAPIActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.CALORIES));

        adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekDays.setAdapter(adpt);

        weekDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                switch(position){
                    case 0:
                        cals="250";
                        break;
                    case 1:
                        cals = "500";
                        break;
                    case 2:
                        cals="750";
                        break;
                    case 3:
                        cals="1000";
                        break;
                    case 4:
                        cals="1250";
                        break;
                    case 5:
                        cals="1500";
                        break;
                    case 6:
                        cals="2000";
                        break;
                }
                System.out.println(cals);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, What recipe would you like to search?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView recipeValue = (TextView) findViewById(R.id.teRecipe);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String userVoiceInput = result.get(0);
                    recipeValue.setText(userVoiceInput);
                }
                break;
            }

        }
    }

    Thread thread = new Thread(new Runnable(){
        @Override
        public void run() {

            TextView recipeValue = (TextView) findViewById(R.id.teRecipe);
            try {

                URL url = new URL("https://api.edamam.com/search?q=" + recipeValue.getText().toString() +
                        "&app_id=c8fcd435&app_key=0d914c61a22a113cab9faf1fb8a029a2&from=0&to=" + 1 + "&calories=" + 0 + "-" + cals);
                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();

                BufferedReader reader = null;
                reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                StringBuffer json = new StringBuffer(1024);
                String tmp = "";
                while (true) {
                    if (!((tmp = reader.readLine()) != null)) break;
                    json.append(tmp).append("\n");

                }

                reader.close();


                JSONObject data = new JSONObject(json.toString());
                JSONObject details = data.getJSONArray("hits").getJSONObject(0);
                JSONObject main = details.getJSONObject("recipe");
                String urlFromJSON = main.getString("url");

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlFromJSON));
                //start that activity
                startActivity(intent);            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
    }

