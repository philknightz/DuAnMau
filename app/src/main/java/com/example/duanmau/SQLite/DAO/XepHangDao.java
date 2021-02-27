package com.example.duanmau.SQLite.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Object.Sach;
import com.example.duanmau.Object.Top;
import com.example.duanmau.SQLite.SQLiteOpenHelper.ThuVienOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class XepHangDao {
    private SQLiteDatabase db;
    private ThuVienOpenHelper thuVienOpenHelper;
    Context context;
    public XepHangDao(Context context) {
        thuVienOpenHelper = new ThuVienOpenHelper(context);
        db = thuVienOpenHelper.getWritableDatabase();
        this.context = context;
    }

    public List<Top> getTop(){
        List<Top> topList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT "+ thuVienOpenHelper.PHIEUMUON_COLUMN_MASACH +
                        ", count("+thuVienOpenHelper.PHIEUMUON_COLUMN_MASACH+") as soLuong FROM "
                        + thuVienOpenHelper.PHIEUMUON_TABLE_NAME
                        +" GROUP BY " + thuVienOpenHelper.PHIEUMUON_COLUMN_MASACH
                        + " ORDER BY soLuong DESC LIMIT 10"
                        ,null);
        SachDAO sachDAO = new SachDAO(context);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Top top = new Top();
            Sach sach = sachDAO.getOneSach(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.PHIEUMUON_COLUMN_MASACH)));
            top.setTenSach(sach.getTenSach());
            top.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            topList.add(top);
            cursor.moveToNext();
        }
        return  topList;
    }

}
