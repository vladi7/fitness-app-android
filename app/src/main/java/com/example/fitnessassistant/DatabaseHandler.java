package com.example.fitnessassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.Scanner;

import static android.content.Context.MODE_PRIVATE;

public class DatabaseHandler {
    private static SQLiteDatabase db;
    private static Cursor cr;
    private static int count = 0; //primary key of database

    public static void loadDatabase(Context context) {
        db = context.openOrCreateDatabase("database", MODE_PRIVATE, null);
        Scanner scan = new Scanner(context.getResources().openRawResource(R.raw.database));
        String query = "";
        while (scan.hasNextLine()) {
            query += scan.nextLine() + "\n";
            if (query.trim().endsWith(";")) {
                db.execSQL(query);
                query = "";
            }
        }

        count = (int) DatabaseUtils.queryNumEntries(db, "workouts");
    }

    public static Cursor readDatabase() {
        String query1 = "SELECT * FROM workouts";
        cr = db.rawQuery(query1, null);
        return cr;
    }

    public static void updateUsingName(String oldName,  String newWorkout){
        String sql = "UPDATE workouts SET  workoutCSV = '"+newWorkout+"' WHERE weekDay = '"+oldName+"';";
        db.execSQL(sql);
    }
}