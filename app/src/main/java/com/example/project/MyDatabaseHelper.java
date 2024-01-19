package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private   static final String DATABASE_NAME = "Listpatient.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_patient";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOM = "nom";
    private static final String COLUMN_PRENOM = "prenom";
    private static final String COLUMN_MALADIE = "maladie";
    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NOM + " TEXT, " +
                        COLUMN_PRENOM + " TEXT, " +
                        COLUMN_MALADIE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    /*public boolean updatePatientData(String id, String nom, String prenom, String maladie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // Map the updated data to the database columns
        contentValues.put("NOM_COLUMN_NAME", nom);
        contentValues.put("PRENOM_COLUMN_NAME", prenom);
        contentValues.put("MALADIE_COLUMN_NAME", maladie);

        // Perform the update operation
        int rowsAffected = db.update(TABLE_NAME, contentValues, "ID_COLUMN_NAME = ?", new String[]{id});

        // Close the database connection
        db.close();

        // Check if the update was successful
        return rowsAffected > 0;
    }*/
    void addPatient(String nom, String prenom, String maladie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOM, nom);
        cv.put(COLUMN_PRENOM, prenom);
        cv.put(COLUMN_MALADIE, maladie);
        long result = db.insert(TABLE_NAME,null, cv);
        if (result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    void updatePatientData(String row_id,String nom,String prenom,String maladie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOM,nom);
        cv.put(COLUMN_PRENOM,prenom);
        cv.put(COLUMN_MALADIE,maladie);
        long r = db.update(TABLE_NAME,cv,"_id=?",new String[]{row_id});
        if (r==-1)
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,"Updated succcess",Toast.LENGTH_SHORT).show();

    }
}
