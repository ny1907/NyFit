package ny.nyfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U820319 on 06.10.2016.
 */

public class MySQLiteHelper extends SQLiteOpenHelper{

    // Datenbank Version
    public static final int DATABASE_VERSION = 1;

    // Datenbank Name
    public static final String DATABASE_NAME = "Lebensmittel";

    // Food table name
    private static final String TABLE_FOOD = "Lebensmittel";

    // Food table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "Name";
    private static final String KEY_KCAL = "Kcal";
    private static final String KEY_CARBONHYDRATES = "Kohlenhydrate";
    private static final String KEY_PROTEINS = "Proteine";
    private static final String KEY_FAT = "Fett";

    private static final String[] COLUMNS = {KEY_ID, KEY_NAME, KEY_KCAL, KEY_CARBONHYDRATES, KEY_PROTEINS, KEY_FAT};

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Lebensmittel(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT NOT NULL, " +
                "Kcal REAL NOT NULL," +
                "Kohlenhydrate REAL NOT NULL, " +
                "Proteine REAL NOT NULL, " +
                "Fett REAL NOT NULL" +
                ");";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DELETE_TABLE = "DROP TABLE IF EXISTS Lebensmittel";

        db.execSQL(DELETE_TABLE);
        onUpgrade(db, oldVersion, newVersion);
        this.onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addFood(Food food){
        Log.d("addFood", food.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName());
        values.put(KEY_KCAL, food.getKcal());
        values.put(KEY_CARBONHYDRATES, food.getKohlenhydrate());
        values.put(KEY_PROTEINS, food.getProteine());
        values.put(KEY_FAT, food.getFett());

        db.insert(TABLE_FOOD, null, values);
        db.close();
    }

    public Food getFood(int id){

        // Lesbare Referenz auf Datenbank
        SQLiteDatabase db = this.getReadableDatabase();

        // SQL Statement
        Cursor cursor =
                db.query(TABLE_FOOD, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // Erste Zeil wählen
        if (cursor != null) {
            cursor.moveToFirst();
        }

        // Food Objekt erstellen
        Food food = new Food(cursor.getString(1), cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getFloat(5));
        food.setId(Integer.parseInt(cursor.getString(0)));

        //log
        Log.d("getFood(" + id + ")", food.toString());

        return food;
    }

    public Food getFood(String name){

        // Lesbare Referenz auf Datenbank
        SQLiteDatabase db = this.getReadableDatabase();

        // SQL Statement
        Cursor cursor =
                db.query(TABLE_FOOD, // a. table
                        COLUMNS, // b. column names
                        " name = ?", // c. selections
                        new String[] { name }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // Erste Zeil wählen
        if (cursor != null) {
            cursor.moveToFirst();
        }

        // Food Objekt erstellen
        Food food = new Food(cursor.getString(1), cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getFloat(5));
        food.setId(Integer.parseInt(cursor.getString(0)));

        //log
        Log.d("getFood(" + name + ")", food.toString());

        return food;
    }

    public List<Food> allFoods(){
        List<Food> foods = new ArrayList<Food>();

        String query = "SELECT * FROM " + TABLE_FOOD + " ORDER BY NAME";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Food food = null;
        if (cursor.moveToFirst()){
            do {
                food = new Food(cursor.getString(1), cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getFloat(5));

                foods.add(food);
            } while (cursor.moveToNext());
        }

        Log.d("getAllFoods()", foods.toString());

        return foods;
    }

    public int updateFood(Food food){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Name", food.getName());
        values.put("Kcal", food.getKcal());
        values.put("Kohlenhydrate", food.getKohlenhydrate());
        values.put("Proteine", food.getProteine());
        values.put("Fett", food.getFett());

        int i = db.update(TABLE_FOOD,
                values,
                KEY_ID+" = ?",
                new String[] { String.valueOf(food.getId())}
        );

        db.close();

        return i;
    }

    public void delete(Food food){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_FOOD,
                KEY_ID+" = ?",
                new String[] { String.valueOf(food.getId()) }
                );

        db.close();

        Log.d("deleteFood", food.toString());
    }
}

// http://hmkcode.com/android-simple-sqlite-database-tutorial/