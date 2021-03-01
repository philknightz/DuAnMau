package com.example.duanmau.View.DoanhThu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.Object.PhieuMuon;
import com.example.duanmau.R;
import com.example.duanmau.SQLite.DAO.DoanhThuDao;

import java.util.ArrayList;
import java.util.List;


public class Fragment_DoanhThu extends Fragment {
    TextView doanhthu_tv_ngay,doanhthu_tv_tuan,doanhthu_tv_thang;

    public Fragment_DoanhThu() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment__doanh_thu, container, false);

        doanhthu_tv_ngay = view.findViewById(R.id.doanhthu_tv_ngay);
        doanhthu_tv_tuan = view.findViewById(R.id.doanhthu_tv_tuan);
        doanhthu_tv_thang = view.findViewById(R.id.doanhthu_tv_thang);

        DoanhThuDao doanhThuDao = new DoanhThuDao(getContext());
        int doanhThuNgay = doanhThuDao.getDoanhThu("01-02-2020","01-02-2020");
        int doanhThuTuan = doanhThuDao.getDoanhThu("01-02-2020","08-02-2020");
        int doanhThuThang = doanhThuDao.getDoanhThu("01-02-2020","28-02-2020");

        doanhthu_tv_ngay.setText(String.valueOf(doanhThuNgay));
        doanhthu_tv_tuan.setText(String.valueOf(doanhThuTuan));
        doanhthu_tv_thang.setText(String.valueOf(doanhThuThang));

        return view;
    }
}