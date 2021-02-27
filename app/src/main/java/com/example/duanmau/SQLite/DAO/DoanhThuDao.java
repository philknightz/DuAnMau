package com.example.duanmau.SQLite.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Object.PhieuMuon;
import com.example.duanmau.SQLite.SQLiteOpenHelper.ThuVienOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DoanhThuDao {
    private SQLiteDatabase db;
    private ThuVienOpenHelper thuVienOpenHelper;

    public DoanhThuDao(Context context) {
        thuVienOpenHelper = new ThuVienOpenHelper(context);
        db = thuVienOpenHelper.getWritableDatabase();
    }

    public int getDoanhThu(String tuNgay, String denNgay){
        Cursor cursor = db.rawQuery("SELECT SUM( "+thuVienOpenHelper.PHIEUMUON_COLUMN_TIENTHUE+" ) as doanhThu FROM "
                + thuVienOpenHelper.PHIEUMUON_TABLE_NAME +" WHERE "
                + thuVienOpenHelper.PHIEUMUON_COLUMN_NGAYMUON + " BETWEEN ? AND ?"
                ,new String[]{tuNgay,denNgay});
        int doanhthu = 0;
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            try{
                doanhthu = Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu")));
            }catch (Exception e){
                doanhthu =0;
            }

            cursor.moveToNext();
        }
        return doanhthu;
    }

}
