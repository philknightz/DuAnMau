package com.example.duanmau.View.QLThanhVien;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duanmau.Adapter.AdapterQLPhieuMuon;
import com.example.duanmau.Adapter.AdapterQLThanhVien;
import com.example.duanmau.Object.PhieuMuon;
import com.example.duanmau.Object.ThanhVien;
import com.example.duanmau.R;

import java.util.ArrayList;
import java.util.List;


public class Fragment_QLThanhVien extends Fragment {
    ListView qltv_lv;
    List<ThanhVien> thanhVienList;

    public Fragment_QLThanhVien() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__q_l_thanh_vien, container, false);
        qltv_lv = view.findViewById(R.id.qltv_lv);
        thanhVienList = new ArrayList<>();
        thanhVienList.add(new ThanhVien(1,"Kien","2020-11-11"));
        thanhVienList.add(new ThanhVien(2,"Trang","2019-10-10"));
        thanhVienList.add(new ThanhVien(3,"Linh","2018-09-09"));
        AdapterQLThanhVien qlpmAdapter = new AdapterQLThanhVien(getActivity(),thanhVienList);
        qltv_lv.setAdapter(qlpmAdapter);
        return view;
    }
}