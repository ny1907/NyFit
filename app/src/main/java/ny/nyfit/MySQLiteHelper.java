package ny.nyfit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by U820319 on 06.10.2016.
 */

public class MySQLiteHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Lebensmittel.db";

    // Food table name
    private static final String TABLE_FOOD = "Lebensmittel";

    // Food table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "Name";
    private static final String KEY_CARBONHYDRATES = "Kohlenhydrate";
    private static final String KEY_PROTEINES = "Proteine";
    private static final String KEY_FAT = "Fett";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Lebensmittel(" +
                "ID INT PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT NOT NULL, " +
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

    }

    public void getFood(int id){

    }

    public void allFoods(){

    }

    public void updateFood(Food food){

    }

    public void delete(Food food){

    }
}

// http://hmkcode.com/android-simple-sqlite-database-tutorial/