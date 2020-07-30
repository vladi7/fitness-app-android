// REFERENCE: https://code.tutsplus.com/tutorials/create-a-weather-app-on-android--cms-21587

package com.example.fitnessassistant;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class RemoteFetch {

    private static final String OPEN_WEATHER_MAP_API =
            "https://api.openweathermap.org/data/2.5/weather?q=Kansas&units=metric&appid=b2f577ac93ecd8c0699706609d0ae266";

    public static JSONObject getJSON( String city){
        try {
            URL url = new URL(String.format(OPEN_WEATHER_MAP_API, city));
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();

//            connection.addRequestProperty("x-api-key",
//                    context.getString(R.string.open_weather_maps_app_id));

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not
            // successful
            String TAG = "REMOTE FETCH";
            System.out.println("TRYING TO LOG"+Integer.toString(data.getInt("cod")));
            Log.i(TAG,Integer.toString(data.getInt("cod")) );
            if(data.getInt("cod") != 200){
                return null;
            }

            return data;
        }catch(Exception e){
            return null;
        }
    }
}
