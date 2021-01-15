package com.example.duanmau.View.QLSach;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duanmau.Adapter.AdapterQLPhieuMuon;
import com.example.duanmau.Adapter.AdapterQLSach;
import com.example.duanmau.Object.PhieuMuon;
import com.example.duanmau.Object.Sach;
import com.example.duanmau.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_QLSach extends Fragment {

    ListView qlsach_lv;
    List<Sach> sachList;
    public Fragment_QLSach() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__q_l_sach, container, false);
        qlsach_lv = view.findViewById(R.id.qlsach_lv);
        sachList = new ArrayList<>();
        sachList.add(new Sach(1,"Java",30000,1));
        sachList.add(new Sach(2,"Python",50000,1));
        sachList.add(new Sach(3,"Kotlyn",40000,2));
        sachList.add(new Sach(4,"Java",30000,1));
        sachList.add(new Sach(5,"Python",50000,1));
        sachList.add(new Sach(6,"Kotlyn",40000,2));
        AdapterQLSach qlsachAdapter = new AdapterQLSach(getActivity(),sachList);
        qlsach_lv.setAdapter(qlsachAdapter);
        return view;
    }
}