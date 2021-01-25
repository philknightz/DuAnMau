package com.example.duanmau.SQLite.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Object.Sach;
import com.example.duanmau.Object.ThanhVien;
import com.example.duanmau.SQLite.SQLiteOpenHelper.ThuVienOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {

    private SQLiteDatabase db;
    private ThuVienOpenHelper thuVienOpenHelper;

    public SachDAO(Context context) {
        thuVienOpenHelper = new ThuVienOpenHelper(context);
        db = thuVienOpenHelper.getWritableDatabase();
    }

    public long insert(Sach sach){
        ContentValues values = new ContentValues();
        values.put(thuVienOpenHelper.SACH_COLUMN_TENSACH,sach.getTenSach());
        values.put(thuVienOpenHelper.SACH_COLUMN_GIATHUE,sach.getGiaThue());
        values.put(thuVienOpenHelper.SACH_COLUMN_MALOAI,sach.getMaLoai());
        return db.insert(thuVienOpenHelper.SACH_TABLE_NAME,null,values);
    }

    public int update(Sach sach){
        ContentValues values = new ContentValues();
        values.put(thuVienOpenHelper.SACH_COLUMN_TENSACH,sach.getTenSach());
        values.put(thuVienOpenHelper.SACH_COLUMN_GIATHUE,sach.getGiaThue());
        values.put(thuVienOpenHelper.SACH_COLUMN_MALOAI,sach.getMaLoai());
        return db.update(thuVienOpenHelper.SACH_TABLE_NAME,values,
                thuVienOpenHelper.SACH_COLUMN_MASACH+"=?",
                new String[]{String.valueOf(sach.getMaSach())});
    }

    public int delete(int MaSach){
        return db.delete(thuVienOpenHelper.SACH_TABLE_NAME,
                thuVienOpenHelper.SACH_COLUMN_MASACH+"=?",
                new String[]{String.valueOf(MaSach)});
    }

    public List<Sach> getAllSach(){
        String sql = "SELECT * FROM "+thuVienOpenHelper.SACH_TABLE_NAME;
        return getData(sql);
    }

    public Sach getOneSach(String MaSach){
        String sql = "SELECT * FROM "
                +thuVienOpenHelper.SACH_TABLE_NAME+" WHERE "
                +thuVienOpenHelper.SACH_COLUMN_MASACH+"=?";
        List<Sach> sachList = getData(sql,MaSach);
        return  sachList.get(0);
    }


    private List<Sach> getData(String sql,String...selectionArgs){
        List<Sach> sachList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Sach sach = new Sach();
            sach.setMaSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.SACH_COLUMN_MASACH))));
            sach.setTenSach(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.SACH_COLUMN_TENSACH)));
            sach.setGiaThue(Integer.parseInt(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.SACH_COLUMN_GIATHUE))));
            sach.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.SACH_COLUMN_MALOAI))));
            sachList.add(sach);
            cursor.moveToNext();
        }
        return sachList;
    }

    public void dropSachTable(){
        db.execSQL(thuVienOpenHelper.SACH_DROP_TABLE);
        db.execSQL(thuVienOpenHelper.SACH_CREATE_TABLE);
    }
}
