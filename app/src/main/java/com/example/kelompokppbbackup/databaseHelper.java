package com.example.kelompokppbbackup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class databaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="pesanan_minuman.db";
    public static final String TABLE_NAME="table_pesanan";
    public static final String COL_1="id_plg";
    public static final String COL_2="nama";
    public static final String COL_3="alamat";
    public static final String TABLE_PRODUCT="table_produk";
    public static final String COL2_1="kode_produk";
    public static final String COL2_2="nama_produk";
    public static final String COL2_3="jml_produk";
    public static final String COL2_4="harga_produk";
    public static final String COL2_5="status";
    public static final int DATABASE_VERSION=2;

    private Context context;

    public databaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table table_pesanan(id_plg integer primary key, nama text null, alamat text null);");
        //db.execSQL("CREATE TABLE IF NOT EXISTS table_produk(kode_produk INTEGER PRIMARY KEY, nama_produk TEXT, jml_produk INTEGER, harga_produk INTEGER, status INTEGER);");
        String produk = "CREATE TABLE " + TABLE_PRODUCT + " (" + COL2_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2_2 + " TEXT, " + COL2_3 + " INTEGER, " + COL2_4 + " INTEGER, " + COL2_5 + " INTEGER );";
        db.execSQL(produk);
        //db.execSQL("create table table_produk(kode_produk text null, nama_produk text null, jml_produk integer null, harga_produk integer null, status text 0);");
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCT);
        onCreate(db);
    }

    public boolean insertData(String nama, String alamat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, alamat);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result==-1)
            return false;
        else
            return true;
    }

    public boolean insertDataProduk(String nama_produk, Integer jml_produk, Integer harga_produk){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_2, nama_produk);
        contentValues.put(COL2_3, jml_produk);
        contentValues.put(COL2_4, harga_produk);
        contentValues.put(COL2_5, 0);
        long result = db.insert(TABLE_PRODUCT, null, contentValues);

        if (result==-1)
            return false;
        else
            return true;
    }


    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from table_pesanan", null);
        return res;
    }

    public Cursor getAllDataProduk(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from table_produk where status==0", null);
        return res;
    }

    //merubah data
    public boolean updateData(String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_5, 1);

        //db.update(TABLE_NAME, contentValues, "status = 0", new Integer[]{status});
        long result = db.update(TABLE_PRODUCT, contentValues, "status = ?", new String[]{status});
        if(result == -1){
            Toast.makeText(context, "Order Failed", Toast.LENGTH_SHORT).show();
            return false;
        } else{
            Toast.makeText(context, "Order Successful", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getReadableDatabase();
        long result = db.delete(TABLE_PRODUCT, "nama_produk=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        }
    }

}

