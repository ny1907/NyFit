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
    // Aktuelles Datum
    private static final String CURRENT_DATE = "date('now')";

    // Food table name
    private static final String TABLE_FOOD = "Lebensmittel";
    // Plan table name
    private static final String TABLE_PLAN = "Plan";
    // Food-Plan table name
    private static final String TABLE_FOODPLAN = "Foodplan";
    // Statistiken table name
    private static final String TABLE_STATISTICS = "Statistiken";

    // Food table Columns names
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "Name";
    private static final String KEY_KCAL = "Kcal";
    private static final String KEY_CARBONHYDRATES = "Kohlenhydrate";
    private static final String KEY_PROTEINS = "Proteine";
    private static final String KEY_FAT = "Fett";

    // Plan table Columns names
    private static final String PLAN_ID = "id";
    private static final String PLAN_NAME = "Name";
    private static final String PLAN_CREATE_DATE = "Erstelldatum";

    // Foodplan table Columns names
    private static final String FP_PLAN_ID = "planID";
    private static final String FP_FOOD_ID = "Food_ID";
    private static final String FP_AMOUNT = "Menge";

    // Statistiken table Columns names
    private static final String STATISTICS_ID = "ID";
    private static final String STATISTICS_DATE = "Datum";
    private static final String STATISTICS_WEIGHT = "Gewicht";
    private static final String STATISTICS_FAT = "Fett";
    private static final String STATISTICS_MUSCLE = "Muskel";
    private static final String STATISTICS_WATER = "Wasser";

    // Table Create Statments
    // Food Table Create Statement
    private static final String CREATE_TABLE_FOOD = "CREATE TABLE IF NOT EXISTS " + TABLE_FOOD + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_NAME + " TEXT NOT NULL, " +
            KEY_KCAL + " REAL NOT NULL," +
            KEY_CARBONHYDRATES + " REAL NOT NULL, " +
            KEY_PROTEINS +  " REAL NOT NULL, " +
            KEY_FAT + " REAL NOT NULL" +
            ");";

    // Plan Table Create Statement
    private static final String CREATE_TABLE_PLAN = "CREATE TABLE IF NOT EXISTS " + TABLE_PLAN + "(" +
            PLAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PLAN_NAME + " TEXT NOT NULL, " +
            PLAN_CREATE_DATE + " INTEGER NOT NULL" +
            ");";


    // Foodplan Table Create Statement
    private static final String CREATE_TABLE_FOODPLAN = "CREATE TABLE IF NOT EXISTS " + TABLE_FOODPLAN + "(" +
            FP_PLAN_ID + " INTEGER NOT NULL, " +
            FP_FOOD_ID + " INTEGER NOT NULL, " +
            FP_AMOUNT + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + FP_PLAN_ID + ") REFERENCES " + TABLE_PLAN + "(" + PLAN_ID + "), " +
            "FOREIGN KEY(" + FP_FOOD_ID + ") REFERENCES " + TABLE_FOOD + "(" + KEY_ID + ")" +
            ");";

    // Statistics Table Create Statement
    private static final String CREATE_TABLE_STATISTICS = "CREATE TABLE IF NOT EXISTS " + TABLE_STATISTICS + "(" +
            STATISTICS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            STATISTICS_WEIGHT + " REAL NOT NULL, " +
            STATISTICS_FAT + " REAL NOT NULL, " +
            STATISTICS_MUSCLE + " REAL NOT NULL, " +
            STATISTICS_WATER + " REAL NOT NULL, " +
            STATISTICS_DATE + " TEXT NOT NULL" +
            ");";

    private static final String[] FOOD_COLUMNS = {KEY_ID, KEY_NAME, KEY_KCAL, KEY_CARBONHYDRATES, KEY_PROTEINS, KEY_FAT};
    private static final String[] PLAN_COLUMNS = {PLAN_ID, PLAN_NAME};
    private static final String[] FOODPLAN_COLUMNS = {FP_PLAN_ID, FP_FOOD_ID, FP_AMOUNT};
    private static final String[] STATISTICS_COLUMNS = {STATISTICS_ID, STATISTICS_WEIGHT, STATISTICS_FAT, STATISTICS_MUSCLE, STATISTICS_WATER, STATISTICS_DATE};

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_FOOD);
        db.execSQL(CREATE_TABLE_PLAN);
        db.execSQL(CREATE_TABLE_FOODPLAN);
        db.execSQL(CREATE_TABLE_STATISTICS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODPLAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTICS);
        onUpgrade(db, oldVersion, newVersion);
        this.onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // FOOD
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
                        FOOD_COLUMNS, // b. column names
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

        db.close();

        return food;
    }
    public Food getFood(String name){

        // Lesbare Referenz auf Datenbank
        SQLiteDatabase db = this.getReadableDatabase();

        // SQL Statement
        Cursor cursor =
                db.query(TABLE_FOOD, // a. table
                        FOOD_COLUMNS, // b. column names
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
        db.close();

        return food;
    }
    public List<Food> allFoods(){
        List<Food> foods = new ArrayList<Food>();

        String query = "SELECT * FROM " + TABLE_FOOD + " ORDER BY " + KEY_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Food food = null;
        if (cursor.moveToFirst()){
            do {
                food = new Food(cursor.getString(1), cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getFloat(5));
                food.setId(Integer.valueOf(cursor.getString(0)));
                foods.add(food);
            } while (cursor.moveToNext());
        }

        Log.d("getAllFoods()", foods.toString());
        db.close();

        return foods;
    }
    public int updateFood(Food food){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName());
        values.put(KEY_KCAL, food.getKcal());
        values.put(KEY_CARBONHYDRATES, food.getKohlenhydrate());
        values.put(KEY_PROTEINS, food.getProteine());
        values.put(KEY_FAT, food.getFett());

        int i = db.update(TABLE_FOOD,
                values,
                KEY_ID+" = ?",
                new String[] { String.valueOf(food.getId())}
        );

        db.close();

        return i;
    }
    public void deleteFood(Food food){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_FOOD,
                KEY_ID+" = ?",
                new String[] { String.valueOf(food.getId()) }
                );

        db.close();

        Log.d("deleteFood", food.toString());
    }

    // PLAN
    public void addPlan(Plan plan){
        Log.d("addPlan", String.valueOf(plan.getId()));

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PLAN_NAME, plan.getName());
        values.put(PLAN_CREATE_DATE, CURRENT_DATE);

        db.insert(TABLE_PLAN, null, values);
        db.close();
    }
    public Plan getPlan(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_PLAN,
                        PLAN_COLUMNS,
                        PLAN_ID + " = ?",
                        new String[] { String.valueOf(id) },
                        null,
                        null,
                        null,
                        null);

        // Erste Zeile wählen
        if (cursor != null){
            cursor.moveToFirst();
        }

        // Plan Objekt erstellen
        Plan plan = new Plan(cursor.getString(1));
        plan.setId(Integer.valueOf(cursor.getString(0)));

        //log
        Log.d("getPlan(" + id + ")", plan.toString());
        db.close();
        return plan;
    }
    public Plan getPlan(String name) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_PLAN,
                        PLAN_COLUMNS,
                        PLAN_NAME + " = ?",
                        new String[] { name },
                        null,
                        null,
                        null,
                        null);

        // Erste Zeile wählen
        if (cursor != null){
            cursor.moveToFirst();
        }

        // Plan Objekt erstellen
        Plan plan = new Plan(cursor.getString(1));
        plan.setId(Integer.valueOf(cursor.getString(0)));

        //log
        Log.d("getPlan(" + name + ")", plan.toString());
        db.close();
        return plan;
    }
    public List<Plan> getAllPlans() {
        List<Plan> listPlans = new ArrayList<Plan>();

        String query = "SELECT * FROM " + TABLE_PLAN + " ORDER BY " + PLAN_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Plan plan = null;
        if (cursor.moveToFirst()){
            do {
                plan = new Plan(cursor.getString(1));
                plan.setId(Integer.valueOf(cursor.getString(0)));
                listPlans.add(plan);
            } while (cursor.moveToNext());
        }

        Log.d("getAllPlans", listPlans.toString());

        return listPlans;
    }
    public int updateFood(Plan plan){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PLAN_NAME, plan.getName());

        int i = db.update(TABLE_PLAN,
                values,
                PLAN_ID + " = ?",
                new String[] { String.valueOf(plan.getId())}
        );

        db.close();

        return i;
    }
    public void deletePlan(Plan plan){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PLAN,
                PLAN_ID + " = ?",
                new String[] { String.valueOf(plan.getId())}
        );

        db.close();

        Log.d("deletePlan", plan.toString());
    }

    // FOODPLAN
    public void addFoodToPlan(Foodplan foodplan){
        Log.d("addFoodToPlan", String.valueOf(foodplan.getPlanID() + " : " + foodplan.getFoodID()));

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FP_PLAN_ID, foodplan.getPlanID());
        values.put(FP_FOOD_ID, foodplan.getFoodID());
        values.put(FP_AMOUNT, foodplan.getAmount());

        db.insert(TABLE_FOODPLAN, null, values);
        db.close();
    }

    // STATISTICS
    public void addStat(Statistics stat){
        Log.d("addStat", stat.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STATISTICS_WEIGHT, stat.getGewicht());
        values.put(STATISTICS_DATE, stat.getDatum());
        values.put(STATISTICS_FAT, stat.getFett());
        values.put(STATISTICS_MUSCLE, stat.getMuskel());
        values.put(STATISTICS_WATER, stat.getWasser());

        db.insert(TABLE_STATISTICS, null, values);
        db.close();
    }
    public Statistics getStatistic (int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_STATISTICS, // a.table
                        STATISTICS_COLUMNS, // b.columns
                        " id = ?", // c.selection
                        new String[] { String.valueOf(id) }, // d.selections args
                        null, // e.group by
                        null, // f.having
                        null, // g.order by
                        null); // h.limit

        // Erste Zeile wählen
        if (cursor != null) {
            cursor.moveToFirst();
        }

        // Statistics Objekt erstellen
        Statistics stats = new Statistics(cursor.getFloat(1), cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4));
        stats.setId(Integer.parseInt(cursor.getString(0)));

        // log
        Log.d("getStatistics(" + id + ")", stats.toString());
        db.close();

        return stats;
    }
    public List<Statistics> getAllStatistics () {
        List<Statistics> listStats = new ArrayList<Statistics>();

        String query = "SELECT * FROM " + TABLE_STATISTICS + " ORDER BY " + STATISTICS_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Statistics stats = null;
        if (cursor.moveToFirst()) {
            do {
                stats = new Statistics(cursor.getFloat(1), cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4));
                stats.setId(Integer.valueOf(cursor.getString(0)));
                stats.setDatum(cursor.getString(5));
                listStats.add(stats);
            } while (cursor.moveToNext());
        }

        Log.d("getAllStatistics()", stats.toString());
        db.close();

        return listStats;
    }

}

// http://hmkcode.com/android-simple-sqlite-database-tutorial/