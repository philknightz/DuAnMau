package com.example.duanmau.View.QLLoaiSach;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duanmau.Adapter.AdapterQLLoaiSach;
import com.example.duanmau.Adapter.AdapterQLPhieuMuon;
import com.example.duanmau.Object.LoaiSach;
import com.example.duanmau.Object.PhieuMuon;
import com.example.duanmau.R;

import java.util.ArrayList;
import java.util.List;


public class Fragment_QLLoaiSach extends Fragment {
    ListView qlls_lv;
    List<LoaiSach> loaiSachList;

    public Fragment_QLLoaiSach() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__q_l_loai_sach, container, false);
        qlls_lv = view.findViewById(R.id.qlls_lv);
        loaiSachList = new ArrayList<>();
        loaiSachList.add(new LoaiSach(1,"Cong nghe thong tin"));
        loaiSachList.add(new LoaiSach(2,"Tieng anh"));
        loaiSachList.add(new LoaiSach(3,"Dia ly"));
        loaiSachList.add(new LoaiSach(4,"Toan hoc"));
        loaiSachList.add(new LoaiSach(5,"ngu van"));
        AdapterQLLoaiSach qllsAdapter = new AdapterQLLoaiSach(getActivity(),loaiSachList);
        qlls_lv.setAdapter(qllsAdapter);
        return view;
    }
}