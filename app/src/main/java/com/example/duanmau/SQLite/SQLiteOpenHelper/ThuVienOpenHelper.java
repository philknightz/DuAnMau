package com.example.duanmau.SQLite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ThuVienOpenHelper extends SQLiteOpenHelper {

    public ThuVienOpenHelper( Context context) {
        super(context, "QUANLYTHUVIEN", null, 1);
    }

    public String THUTHU_TABLE_NAME = "THUTHU";
    public String THUTHU_COLUMN_MATT = "MATT";
    public String THUTHU_COLUMN_HOTEN = "HOTEN";
    public String THUTHU_COLUMN_MATKHAU = "MATKHAU";
    public String THUTHU_DROP_TABLE = "DROP TABLE IF EXISTS " + THUTHU_TABLE_NAME;
    public String THUTHU_CREATE_TABLE = "CREATE TABLE "+THUTHU_TABLE_NAME+"("
            +THUTHU_COLUMN_MATT+ " VARCHAR PRIMARY KEY, "
            +THUTHU_COLUMN_HOTEN + " VARCHAR,"
            +THUTHU_COLUMN_MATKHAU + " VARCHAR )";

    public String THANHVIEN_TABLE_NAME = "THANHVIEN";
    public String THANHVIEN_COLUMN_MATV = "MATV";
    public String THANHVIEN_COLUMN_HOTEN = "HOTEN";
    public String THANHVIEN_COLUMN_NAMSINH = "NAMSINH";
    public String THANHVIEN_DROP_TABLE = "DROP TABLE IF EXISTS " + THANHVIEN_TABLE_NAME;
    public String THANHVIEN_CREATE_TABLE = "CREATE TABLE "+THANHVIEN_TABLE_NAME+"("
            +THANHVIEN_COLUMN_MATV+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +THANHVIEN_COLUMN_HOTEN + " VARCHAR,"
            +THANHVIEN_COLUMN_NAMSINH + " VARCHAR )";

    public String LOAISACH_TABLE_NAME = "LOAISACH";
    public String LOAISACH_COLUMN_MALOAI = "MALOAI";
    public String LOAISACH_COLUMN_TENLOAI = "TENLOAI";
    public String LOAISACH_DROP_TABLE = "DROP TABLE IF EXISTS " + LOAISACH_TABLE_NAME;
    public String LOAISACH_CREATE_TABLE = "CREATE TABLE "+LOAISACH_TABLE_NAME+"("
            +LOAISACH_COLUMN_MALOAI+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +LOAISACH_COLUMN_TENLOAI + " VARCHAR )";

    public String SACH_TABLE_NAME = "SACH";
    public String SACH_COLUMN_MASACH = "MASACH";
    public String SACH_COLUMN_TENSACH = "TENSACH";
    public String SACH_COLUMN_GIATHUE = "GIATHUE";
    public String SACH_COLUMN_MALOAI = "MALOAI";
    public String SACH_DROP_TABLE = "DROP TABLE IF EXISTS " + SACH_TABLE_NAME;
    public String SACH_CREATE_TABLE = "CREATE TABLE "+SACH_TABLE_NAME+"("
            +SACH_COLUMN_MASACH+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +SACH_COLUMN_TENSACH + " VARCHAR,"
            +SACH_COLUMN_GIATHUE + " INTEGER,"
            +SACH_COLUMN_MALOAI+" INTEGER REFERENCES "+ LOAISACH_TABLE_NAME+"("+LOAISACH_COLUMN_MALOAI+")"+")";

    public String PHIEUMUON_TABLE_NAME = "PHIEUMUON";
    public String PHIEUMUON_COLUMN_MAPM = "MAPM";
    public String PHIEUMUON_COLUMN_MATT = "MATT";
    public String PHIEUMUON_COLUMN_MATV = "MATV";
    public String PHIEUMUON_COLUMN_MASACH = "MASACH";
    public String PHIEUMUON_COLUMN_TIENTHUE = "TIENTHUE";
    public String PHIEUMUON_COLUMN_NGAYMUON = "NGAYMUON";
    public String PHIEUMUON_COLUMN_TRASACH = "TRASACH";

    public String PHIEUMUON_DROP_TABLE = "DROP TABLE IF EXISTS " + PHIEUMUON_TABLE_NAME;
    public String PHIEUMUON_CREATE_TABLE = "CREATE TABLE "+PHIEUMUON_TABLE_NAME+"("
            +PHIEUMUON_COLUMN_MAPM+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +PHIEUMUON_COLUMN_MATT + " VARCHAR REFERENCES "+THUTHU_TABLE_NAME+"("+THUTHU_COLUMN_MATT+")"+","
            + PHIEUMUON_COLUMN_MATV + " INTEGER REFERENCES "+THANHVIEN_TABLE_NAME+"("+THANHVIEN_COLUMN_MATV+")"+","
            +PHIEUMUON_COLUMN_MASACH+" INTEGER REFERENCES "+ SACH_TABLE_NAME+"("+SACH_COLUMN_MASACH+")" +","
            +PHIEUMUON_COLUMN_TIENTHUE + " INTEGER,"
            +PHIEUMUON_COLUMN_NGAYMUON + " DATE,"
            +PHIEUMUON_COLUMN_TRASACH + " INTEGER,";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(THUTHU_CREATE_TABLE);
        db.execSQL(THANHVIEN_CREATE_TABLE);
        db.execSQL(LOAISACH_CREATE_TABLE);
        db.execSQL(SACH_CREATE_TABLE);
        db.execSQL(PHIEUMUON_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(THUTHU_DROP_TABLE);
        db.execSQL(THANHVIEN_DROP_TABLE);
        db.execSQL(LOAISACH_DROP_TABLE);
        db.execSQL(SACH_DROP_TABLE);
        db.execSQL(PHIEUMUON_DROP_TABLE);
        onCreate(db);
    }
}
