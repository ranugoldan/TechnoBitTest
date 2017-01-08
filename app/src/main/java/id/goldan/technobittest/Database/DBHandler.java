package id.goldan.technobittest.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import id.goldan.technobittest.Model.Data;

/**
 * Created by ranug on 08/01/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "technobit";

    private static final String TABLE_DATA = "data";

    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_KELAMIN = "kelamin";
    private static final String KEY_TGL_LAHIR = "tanggal_lahir";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DATA + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAMA + " TEXT,"
                + KEY_ALAMAT + " TEXT," + KEY_KELAMIN + " TEXT," + KEY_TGL_LAHIR + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);

        onCreate(db);
    }

    public void addData(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, data.getNama());
        values.put(KEY_ALAMAT, data.getAlamat());
        values.put(KEY_KELAMIN, data.getJenisKelamin());
        values.put(KEY_TGL_LAHIR, data.getTanggalLahir());

        // Inserting Row
        db.insert(TABLE_DATA, null, values);
        db.close(); // Closing database connection
    }

    public List<Data> getAllData() {
        List<Data> dataList = new ArrayList<Data>();

        String selectQuery = "SELECT  * FROM " + TABLE_DATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Data data = new Data();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setNama(cursor.getString(1));
                data.setAlamat(cursor.getString(2));
                data.setJenisKelamin(cursor.getString(3));
                data.setTanggalLahir(cursor.getString(4));

                dataList.add(data);
            } while (cursor.moveToNext());
        }

        return dataList;
    }
    public void deleteData(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DATA, KEY_ID + " = ?",
                new String[] { String.valueOf(data.getId()) });
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLE_DATA);
        db.close();
    }
}
