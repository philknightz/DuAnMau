package com.example.duanmau.Object;

public class LoaiSach {
    private int MaLoai;
    private String TenLoai;

    public LoaiSach(int maLoai, String tenLoai) {
        MaLoai = maLoai;
        TenLoai = tenLoai;
    }

    public LoaiSach() {
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }
}
