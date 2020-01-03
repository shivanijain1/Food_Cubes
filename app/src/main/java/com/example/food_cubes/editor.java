package com.example.food_cubes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.food_cubes.menuTable.data.menuContract.menuEntry;
//import com.example.android.menuTable.data.menuHelper;
import com.example.food_cubes.data.menuContract.menuEntry;
import com.example.food_cubes.data.menuHelper;

public class editor extends AppCompatActivity {
    private EditText mDayEditText;
    private EditText mBreakfastEditText;
    private EditText mLunchEditText;
    private EditText mDinnerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        mDayEditText = findViewById(R.id.day);
        mBreakfastEditText = findViewById(R.id.breakfast);
        mLunchEditText = findViewById(R.id.lunch);
        mDinnerEditText = findViewById(R.id.dinner);

    }
   private void insert (){
        String dayString = mDayEditText.getText().toString().trim();
        String breakfastString = mBreakfastEditText.getText().toString().trim();
        String lunchString = mLunchEditText.getText().toString().trim();
        String dinnerString = mDinnerEditText.getText().toString().trim();

        menuHelper mDbHelper = new menuHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(menuEntry.column_day, dayString);
        values.put(menuEntry.COLUMN_BREAKFAST, breakfastString);
        values.put(menuEntry.COLUMN_LUNCH, lunchString);
        values.put(menuEntry.COLUMN_DINNER, dinnerString);

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(menuEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving menu", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "menu saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }

    }
}
