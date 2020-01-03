package com.example.food_cubes.data;

import android.provider.BaseColumns;

public class menuContract {
   private  menuContract (){}
    public static final class menuEntry implements BaseColumns{
       public static final String TABLE_NAME ="food_cubes";
       public static final String _ID = BaseColumns._ID;
       public static final String column_day="day";
       public static final String COLUMN_BREAKFAST = "breakfast";
       public static final String COLUMN_LUNCH = "lunch";
       public static final String COLUMN_DINNER = "dinner";
    }
}
