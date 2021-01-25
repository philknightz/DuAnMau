package com.example.duanmau.SQLite.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Object.PhieuMuon;
import com.example.duanmau.Object.ThuThu;
import com.example.duanmau.SQLite.SQLiteOpenHelper.ThuVienOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    private SQLiteDatabase db;
    private ThuVienOpenHelper thuVienOpenHelper;
    public PhieuMuonDAO(Context context) {
        thuVienOpenHelper = new ThuVienOpenHelper(context);
        db = thuVienOpenHelper.getWritableDatabase();
    }

    public long insert(PhieuMuon phieuMuon){
        ContentValues values = new ContentValues();
        values.put(thuVienOpenHelper.PHIEUMUON_COLUMN_MATT,phieuMuon.getMaTT());
        values.put(thuVienOpenHelper.PHIEUMUON_COLUMN_MATV,phieuMuon.getMaTV());
        values.put(thuVienOpenHelper.PHIEUMUON_COLUMN_MASACH,phieuMuon.getMaSach());
        values.put(thuVienOpenHelper.PHIEUMUON_COLUMN_NGAYMUON,phieuMuon.getNgayMuon());
        values.put(thuVienOpenHelper.PHIEUMUON_COLUMN_TRASACH,phieuMuon.getTraSach());
        values.put(thuVienOpenHelper.PHIEUMUON_COLUMN_TIENTHUE,phieuMuon.getTienThue());
        return db.insert(thuVienOpenHelper.PHIEUMUON_TABLE_NAME,null,values);
    }

    public int update(PhieuMuon phieuMuon){
        ContentValues values = new ContentValues();
        values.put(thuVienOpenHelper.PHIEUMUON_COLUMN_MATT,phieuMuon.getMaTT());
        values.put(thuVienOpenHelper.PHIEUMUON_COLUMN_MATV,phieuMuon.getMaTV());
        values.put(thuVienOpenHelper.PHIEUMUON_COLUMN_MASACH,phieuMuon.getMaSach());
        values.put(thuVienOpenHelper.PHIEUMUON_COLUMN_NGAYMUON,phieuMuon.getNgayMuon());
        values.put(thuVienOpenHelper.PHIEUMUON_COLUMN_TRASACH,phieuMuon.getTraSach());
        values.put(thuVienOpenHelper.PHIEUMUON_COLUMN_TIENTHUE,phieuMuon.getTienThue());
        return db.update(thuVienOpenHelper.PHIEUMUON_TABLE_NAME,values,
                thuVienOpenHelper.PHIEUMUON_TABLE_NAME+"=?",
                new String[]{String.valueOf(phieuMuon.getMaPM())});
    }

    public int delete(String MaPM){
        return db.delete(thuVienOpenHelper.PHIEUMUON_TABLE_NAME,
                thuVienOpenHelper.PHIEUMUON_COLUMN_MAPM+"=?",
                new String[]{String.valueOf(MaPM)});
    }

    public List<PhieuMuon> getAllPhieuMuon(){
        String sql = "SELECT * FROM "+thuVienOpenHelper.PHIEUMUON_TABLE_NAME;
        return getData(sql);
    }

    public PhieuMuon getOnePhieuMuon(String MaPM){
        String sql = "SELECT * FROM "
                +thuVienOpenHelper.PHIEUMUON_TABLE_NAME+" WHERE "
                +thuVienOpenHelper.PHIEUMUON_COLUMN_MAPM+"=?";
        List<PhieuMuon> phieuMuonList = getData(sql,MaPM);
        return  phieuMuonList.get(0);
    }


    private List<PhieuMuon> getData(String sql,String...selectionArgs){
        List<PhieuMuon> phieuMuonList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            PhieuMuon phieuMuon = new PhieuMuon();
            phieuMuon.setMaPM(Integer.parseInt(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.PHIEUMUON_COLUMN_MAPM))));
            phieuMuon.setMaTT(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.PHIEUMUON_COLUMN_MATT)));
            phieuMuon.setMaTV(Integer.parseInt(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.PHIEUMUON_COLUMN_MATV))));
            phieuMuon.setMaSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.PHIEUMUON_COLUMN_MASACH))));
            phieuMuon.setNgayMuon(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.PHIEUMUON_COLUMN_NGAYMUON)));
            phieuMuon.setTienThue(Integer.parseInt(cursor.getString(cursor.getColumnIndex(thuVienOpenHelper.PHIEUMUON_COLUMN_TIENTHUE))));
            phieuMuonList.add(phieuMuon);
            cursor.moveToNext();
        }
        return phieuMuonList;
    }

    public void dropThuThuTable(){
        db.execSQL(thuVienOpenHelper.PHIEUMUON_DROP_TABLE);
        db.execSQL(thuVienOpenHelper.PHIEUMUON_CREATE_TABLE);
    }
}
