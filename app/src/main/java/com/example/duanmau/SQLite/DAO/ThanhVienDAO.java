package com.example.duanmau.SQLite.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Object.ThanhVien;
import com.example.duanmau.Object.ThuThu;
import com.example.duanmau.SQLite.SQLiteOpenHelper.ThuVienOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {

    private SQLiteDatabase db;
    private ThuVienOpenHelper thuVienOpenHelper;

    public ThanhVienDAO(Context context) {
        thuVienOpenHelper = new ThuVienOpenHelper(context);
        db = thuVienOpenHelper.getWritableDatabase();
    }

    public long insert(ThanhVien thanhVien){
        ContentValues values = new ContentValues();
        values.put(thuVienOpenHelper.THANHVIEN_COLUMN_HOTEN,thanhVien.getHoTen());
        values.put(thuVienOpenHelper.THANHVIEN_COLUMN_NAMSINH,thanhVien.getNamSinh());
        return db.insert(thuVienOpenHelper.THANHVIEN_TABLE_NAME,null,values);
    }

    public int update(ThanhVien thanhVien){
        ContentValues values = new ContentValues();
        values.put(thuVienOpenHelper.THANHVIEN_COLUMN_HOTEN,thanhVien.getHoTen());
        values.put(thuVienOpenHelper.THANHVIEN_COLUMN_NAMSINH,thanhVien.getNamSinh());
        return db.update(thuVienOpenHelper.THANHVIEN_TABLE_NAME,values,
                thuVienOpenHelper.THANHVIEN_COLUMN_MATV+"=?",
                new String[]{String.valueOf(thanhVien.getMaTV())});
    }

    public int delete(int MaTV){
        return db.delete(thuVienOpenHelper.THANHVIEN_TABLE_NAME,
                thuVienOpenHelper.THANHVIEN_COLUMN_MATV+"=?",
                new String[]{String.valueOf(MaTV)});
    }

    public List<ThanhVien> getAllThanhVien(){
        String sql = "SELECT * FROM "+thuVienOpenHelper.THANHVIEN_TABLE_NAME;
        return getData(sql);
    }

    public ThanhVien getOneThanhVien(String MaTT){
        String sql = "SELECT * FROM "
                +thuVienOpenHelper.THANHVIEN_TABLE_NAME+" WHERE "
                +thuVienOpenHelper.THANHVIEN_COLUMN_MATV+"=?";
        List<ThanhVien> thanhVienList = getData(sql,MaTT);
        return  thanhVienList.get(0);
    }


    private List<ThanhVien> getData(String sql,String...selectionArgs){
        List<ThanhVien> thanhVienList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ThanhVien thanhVien = new ThanhVien();
            thanhVien.setMaTV(Integer.parseInt(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.THANHVIEN_COLUMN_MATV))));
            thanhVien.setHoTen(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.THANHVIEN_COLUMN_HOTEN)));
            thanhVien.setNamSinh(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.THANHVIEN_COLUMN_NAMSINH)));
            thanhVienList.add(thanhVien);
            cursor.moveToNext();
        }
        return thanhVienList;
    }

    public void dropThanhVienTable(){
        db.execSQL(thuVienOpenHelper.THANHVIEN_DROP_TABLE);
        db.execSQL(thuVienOpenHelper.THANHVIEN_CREATE_TABLE);
    }

}
