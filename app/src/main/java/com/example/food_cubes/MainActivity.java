package com.example.food_cubes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.food_cubes.data.menuContract;
import com.example.food_cubes.data.menuHelper;

import static com.example.food_cubes.data.menuContract.menuEntry.TABLE_NAME;

public class MainActivity extends AppCompatActivity {
    public menuHelper mDbHelper;
    Button bt;
    Intent intent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.add);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, editor.class);
                startActivity(intent);
            }
        });
        mDbHelper = new menuHelper(this);
        displayDatabaseInfo();

    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String[] projection = {
                menuContract.menuEntry._ID,
                menuContract.menuEntry.column_day,
                menuContract.menuEntry.COLUMN_BREAKFAST,
                menuContract.menuEntry.COLUMN_LUNCH,
                menuContract.menuEntry.COLUMN_DINNER,
        };
          /* Cursor cursor = db.query(menuContract.menuEntry.TABLE_NAME,   // The table to query
                    projection ,            // The columns to return
                    null,                  // The columns for the WHERE clause
                    null,                  // The values for the WHERE clause
                    null,                  // Don't group the rows
                    null,                  // Don't filter by row groups
                    null);                   // The sort order
*/
      Cursor cursor = db.rawQuery("SELECT * FROM " + menuContract.menuEntry.TABLE_NAME, null);
        try {
            // Create a header in the Text View that looks like this:
            //
            // The menu table contains <number of rows in Cursor> menuTable
            // _id - day - breakfast - lunch - dinner
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            TextView displayView = findViewById(R.id.text_view_menu);
            displayView.setText("The menu table contains " + cursor.getCount() + " menuTable.\n\n");
            displayView.append(menuContract.menuEntry._ID + " - " +
                    menuContract.menuEntry.column_day + " - " +
                    menuContract.menuEntry.COLUMN_BREAKFAST + " - " +
                    menuContract.menuEntry.COLUMN_LUNCH + " - " +
                    menuContract.menuEntry.COLUMN_DINNER + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(menuContract.menuEntry._ID);
            int dayColumnIndex = cursor.getColumnIndex(menuContract.menuEntry.column_day);
            int breakfastColumnIndex = cursor.getColumnIndex(menuContract.menuEntry.COLUMN_BREAKFAST);
            int lunchColumnIndex = cursor.getColumnIndex(menuContract.menuEntry.column_day);
            int dinnerColumnIndex = cursor.getColumnIndex(menuContract.menuEntry.COLUMN_DINNER);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentDay = cursor.getString(dayColumnIndex);
                String currentBreakfast = cursor.getString(breakfastColumnIndex);
                String currentLunch = cursor.getString(lunchColumnIndex);
                String currentDinner = cursor.getString(dinnerColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentDay + " - " +
                        currentBreakfast + " - " +
                        currentLunch + " - " +
                        currentDinner));
            }
        }finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }


    private void insertpet () {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,

        ContentValues values = new ContentValues();

        values.put(menuContract.menuEntry.column_day, "day");
        values.put(menuContract.menuEntry.COLUMN_BREAKFAST, "breakfast");
        values.put(menuContract.menuEntry.COLUMN_LUNCH, "lunch");
        values.put(menuContract.menuEntry.COLUMN_DINNER, "dinner");


        long newRowId = db.insert(menuContract.menuEntry.TABLE_NAME, null, values);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/
}