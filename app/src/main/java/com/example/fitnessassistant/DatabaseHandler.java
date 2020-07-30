// INSPIRED BY: https://medium.com/@bhawanthagunawardana/android-sqlite-database-crud-s-with-example-application-4f5a841da8f6
package com.example.fitnessassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.Scanner;

import static android.content.Context.MODE_PRIVATE;
// Class to handle the connection and update of database
public class DatabaseHandler {

    private static SQLiteDatabase db;
    private static Cursor cr;
    private static int count = 0;
    private static int countfood = 0;

    public static void loadDatabase(Context context) {
        // checking the existence of the database. If it does, just gets the existent database
         if(context.getDatabasePath("database").exists()) {
             db = context.openOrCreateDatabase("database", MODE_PRIVATE, null);
         }
        else {
            //if it does not, creates the database from the sql code in resources/raw/database.sql
             db = context.openOrCreateDatabase("database", MODE_PRIVATE, null);
             // file handling
             Scanner scan = new Scanner(context.getResources().openRawResource(R.raw.database));
             String query = "";
             while (scan.hasNextLine()) {
                 query += scan.nextLine() + "\n";
                 if (query.trim().endsWith(";")) {
                     db.execSQL(query);
                     query = "";
                 }
             }
         }
        // this is purely to check if the databse loaded correctly and to see what error is thrown
        count = (int) DatabaseUtils.queryNumEntries(db, "workouts");
        countfood = (int) DatabaseUtils.queryNumEntries(db, "food");
        System.out.println("Count food"+countfood);
    }
    // reading the database records from workouts table
    public static Cursor readDatabaseWorkouts() {
        String query1 = "SELECT * FROM workouts";
        cr = db.rawQuery(query1, null);
        return cr;
    }
    // reading the database records from food table
    public static Cursor readDatabaseFood() {
        String query1 = "SELECT * FROM food";
        cr = db.rawQuery(query1, null);
        return cr;
    }
    // updating the database records in workouts table
    public static void updateWorkoutUsingName(String oldName,  String newWorkout){
        String sql = "UPDATE workouts SET  workoutCSV = '"+newWorkout+"' WHERE weekDay = '"+oldName+"';";
        db.execSQL(sql);
    }
    // updating the database records in food table
    public static void updateFoodUsingName(String oldName,  String newWorkout){
        String sql = "UPDATE food SET  foodCSV = '"+newWorkout+"' WHERE weekDay = '"+oldName+"';";
        db.execSQL(sql);
    }
}