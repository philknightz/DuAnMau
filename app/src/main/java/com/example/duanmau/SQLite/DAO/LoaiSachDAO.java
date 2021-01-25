package com.example.duanmau.SQLite.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Object.LoaiSach;
import com.example.duanmau.Object.ThanhVien;
import com.example.duanmau.SQLite.SQLiteOpenHelper.ThuVienOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    private SQLiteDatabase db;
    private ThuVienOpenHelper thuVienOpenHelper;

    public LoaiSachDAO(Context context) {
        thuVienOpenHelper = new ThuVienOpenHelper(context);
        db = thuVienOpenHelper.getWritableDatabase();
    }

    public long insert(LoaiSach loaiSach){
        ContentValues values = new ContentValues();
        values.put(thuVienOpenHelper.LOAISACH_COLUMN_TENLOAI,loaiSach.getTenLoai());
        return db.insert(thuVienOpenHelper.LOAISACH_TABLE_NAME,null,values);
    }

    public int update(LoaiSach loaiSach){
        ContentValues values = new ContentValues();
        values.put(thuVienOpenHelper.LOAISACH_COLUMN_TENLOAI,loaiSach.getTenLoai());
        return db.update(thuVienOpenHelper.LOAISACH_TABLE_NAME,values,
                thuVienOpenHelper.LOAISACH_COLUMN_MALOAI+"=?",
                new String[]{String.valueOf(loaiSach.getMaLoai())});
    }

    public int delete(int MaLoai){
        return db.delete(thuVienOpenHelper.LOAISACH_TABLE_NAME,
                thuVienOpenHelper.LOAISACH_COLUMN_MALOAI+"=?",
                new String[]{String.valueOf(MaLoai)});
    }

    public List<LoaiSach> getAllLoaiSach(){
        String sql = "SELECT * FROM "+thuVienOpenHelper.LOAISACH_TABLE_NAME;
        return getData(sql);
    }

    public LoaiSach getOneLoaiSach(String MaLoai){
        String sql = "SELECT * FROM "
                +thuVienOpenHelper.LOAISACH_TABLE_NAME+" WHERE "
                +thuVienOpenHelper.LOAISACH_COLUMN_MALOAI+"=?";
        List<LoaiSach> loaiSachList = getData(sql,MaLoai);
        return  loaiSachList.get(0);
    }


    private List<LoaiSach> getData(String sql,String...selectionArgs){
        List<LoaiSach> loaiSachList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            LoaiSach loaiSach = new LoaiSach();
            loaiSach.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.LOAISACH_COLUMN_MALOAI))));
            loaiSach.setTenLoai(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.LOAISACH_COLUMN_TENLOAI)));
            loaiSachList.add(loaiSach);
            cursor.moveToNext();
        }
        return loaiSachList;
    }

    public void dropThanhVienTable(){
        db.execSQL(thuVienOpenHelper.LOAISACH_DROP_TABLE);
        db.execSQL(thuVienOpenHelper.LOAISACH_CREATE_TABLE);
    }
}
