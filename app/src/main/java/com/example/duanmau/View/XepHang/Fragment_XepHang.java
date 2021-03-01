package com.example.duanmau.View.XepHang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.Object.Top;
import com.example.duanmau.R;
import com.example.duanmau.SQLite.DAO.XepHangDao;

import java.util.ArrayList;
import java.util.List;


public class Fragment_XepHang extends Fragment {
    TextView top1,top2,top3,top4,top5,top6,top7,top8,top9,top10;
    public Fragment_XepHang() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__xep_hang, container, false);
        top1 = view.findViewById(R.id.xephang_tv_top1name);
        top2 = view.findViewById(R.id.xephang_tv_top2name);
        top3 = view.findViewById(R.id.xephang_tv_top3name);
        top4 = view.findViewById(R.id.xephang_tv_top4name);
        top5 = view.findViewById(R.id.xephang_tv_top5name);
        top6 = view.findViewById(R.id.xephang_tv_top6name);
        top7 = view.findViewById(R.id.xephang_tv_top7name);
        top8 = view.findViewById(R.id.xephang_tv_top8name);
        top9 = view.findViewById(R.id.xephang_tv_top9name);
        top10 = view.findViewById(R.id.xephang_tv_top10name);

        XepHangDao xepHangDao = new XepHangDao(getContext());

        List<Top> topList = new ArrayList<>();
        topList = xepHangDao.getTop();
        try{
            top1.setText(topList.get(0).getTenSach().toString());
        }catch (Exception e){
            top1.setText("JAVA");
        }

        try{
            top2.setText(topList.get(1).getTenSach().toString());
        }catch (Exception e){
            top2.setText("C#");
        }

        try{
            top3.setText(topList.get(2).getTenSach().toString());
        }catch (Exception e){
            top3.setText("PYTHON");
        }

        try{
            top4.setText(topList.get(3).getTenSach().toString());
        }catch (Exception e){
            top4.setText("REACT");
        }

        try{
            top5.setText(topList.get(4).getTenSach().toString());
        }catch (Exception e){
            top5.setText("JAVASCRIPTS");
        }

        try{
            top6.setText(topList.get(5).getTenSach().toString());
        }catch (Exception e){
            top6.setText("TIENG ANH");
        }

        try{
            top7.setText(topList.get(6).getTenSach().toString());
        }catch (Exception e){
            top7.setText("TOAN");
        }

        try{
            top8.setText(topList.get(7).getTenSach().toString());
        }catch (Exception e){
            top8.setText("LY");
        }

        try{
            top9.setText(topList.get(8).getTenSach().toString());
        }catch (Exception e){
            top9.setText("HOA");
        }

        try{
            top10.setText(topList.get(9).getTenSach().toString());
        }catch (Exception e){
            top10.setText("VAN");
        }

        return view;
    }
}