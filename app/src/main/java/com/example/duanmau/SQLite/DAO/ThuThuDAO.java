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

public class ThuThuDAO {
    private SQLiteDatabase db;
    private ThuVienOpenHelper thuVienOpenHelper;
    public ThuThuDAO(Context context) {
        thuVienOpenHelper = new ThuVienOpenHelper(context);
        db = thuVienOpenHelper.getWritableDatabase();
    }

    public long insert(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put(thuVienOpenHelper.THUTHU_COLUMN_MATT,thuThu.getMaTT());
        values.put(thuVienOpenHelper.THUTHU_COLUMN_HOTEN,thuThu.getHoTen());
        values.put(thuVienOpenHelper.THUTHU_COLUMN_MATKHAU,thuThu.getMatKhau());
        return db.insert(thuVienOpenHelper.THUTHU_TABLE_NAME,null,values);
    }

    public int update(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put(thuVienOpenHelper.THUTHU_COLUMN_HOTEN,thuThu.getHoTen());
        values.put(thuVienOpenHelper.THUTHU_COLUMN_MATKHAU,thuThu.getMatKhau());
        return db.update(thuVienOpenHelper.THUTHU_TABLE_NAME,values,
                thuVienOpenHelper.THUTHU_COLUMN_MATT+"=?",
                new String[]{String.valueOf(thuThu.getMaTT())});
    }

    public int delete(String MaTT){
        return db.delete(thuVienOpenHelper.THUTHU_TABLE_NAME,
                thuVienOpenHelper.THUTHU_COLUMN_MATT+"=?",
                new String[]{String.valueOf(MaTT)});
    }

    public List<ThuThu> getAllThuThu(){
        String sql = "SELECT * FROM "+thuVienOpenHelper.THUTHU_TABLE_NAME;
        return getData(sql);
    }

    public ThuThu getOneThuThu(String MaTT){
        String sql = "SELECT * FROM "
                +thuVienOpenHelper.THUTHU_TABLE_NAME+" WHERE "
                +thuVienOpenHelper.THUTHU_COLUMN_MATT+"=?";
        List<ThuThu> thuThuList = getData(sql,MaTT);
        return  thuThuList.get(0);
    }


    private List<ThuThu> getData(String sql,String...selectionArgs){
        List<ThuThu> thuThuList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ThuThu thuThu = new ThuThu();
            thuThu.setMaTT(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.THUTHU_COLUMN_MATT)));
            thuThu.setHoTen(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.THUTHU_COLUMN_HOTEN)));
            thuThu.setMatKhau(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.THUTHU_COLUMN_MATKHAU)));
            thuThuList.add(thuThu);
            cursor.moveToNext();
        }
        return thuThuList;
    }

}
