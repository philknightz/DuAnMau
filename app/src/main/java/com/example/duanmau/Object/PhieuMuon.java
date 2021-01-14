package com.example.duanmau.Object;

public class PhieuMuon {
    private int MaPM;
    private int MaTT;
    private int MaTV;
    private int MaSach;
    private String NgayMuon;
    private int TraSach;
    private int TienThue;

    public PhieuMuon(int maPM, int maTT, int maTV, int maSach, String ngayMuon, int traSach, int tienThue) {
        MaPM = maPM;
        MaTT = maTT;
        MaTV = maTV;
        MaSach = maSach;
        NgayMuon = ngayMuon;
        TraSach = traSach;
        TienThue = tienThue;
    }

    public PhieuMuon() {
    }

    public int getMaPM() {
        return MaPM;
    }

    public void setMaPM(int maPM) {
        MaPM = maPM;
    }

    public int getMaTT() {
        return MaTT;
    }

    public void setMaTT(int maTT) {
        MaTT = maTT;
    }

    public int getMaTV() {
        return MaTV;
    }

    public void setMaTV(int maTV) {
        MaTV = maTV;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public String getNgayMuon() {
        return NgayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        NgayMuon = ngayMuon;
    }

    public int getTraSach() {
        return TraSach;
    }

    public void setTraSach(int traSach) {
        TraSach = traSach;
    }

    public int getTienThue() {
        return TienThue;
    }

    public void setTienThue(int tienThue) {
        TienThue = tienThue;
    }
}
