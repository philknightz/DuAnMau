package com.example.duanmau.View.QLPhieuMuon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duanmau.Adapter.AdapterQLPhieuMuon;
import com.example.duanmau.Object.PhieuMuon;
import com.example.duanmau.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_QLPhieuMuon extends Fragment {
    ListView qlpm_lv;
    List<PhieuMuon> phieuMuonList;
    public Fragment_QLPhieuMuon() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__q_l_phieu_muon, container, false);
        qlpm_lv = view.findViewById(R.id.qlpm_lv);
        phieuMuonList = new ArrayList<>();
        phieuMuonList.add(new PhieuMuon(1,1,3,1,"2020-11-11",0,5000));
        phieuMuonList.add(new PhieuMuon(2,2,5,1,"2020-10-10",1,5000));
        phieuMuonList.add(new PhieuMuon(3,1,4,2,"2020-09-09",0,2000));
        AdapterQLPhieuMuon qlpmAdapter = new AdapterQLPhieuMuon(getActivity(),phieuMuonList);
        qlpm_lv.setAdapter(qlpmAdapter);
        return view;
    }
}