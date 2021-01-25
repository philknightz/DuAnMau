package com.example.duanmau.View.QLSach;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.Activities.ContainerActivity;
import com.example.duanmau.Adapter.AdapterQLPhieuMuon;
import com.example.duanmau.Adapter.AdapterQLSach;
import com.example.duanmau.Object.PhieuMuon;
import com.example.duanmau.Object.Sach;
import com.example.duanmau.Object.ThanhVien;
import com.example.duanmau.R;
import com.example.duanmau.SQLite.DAO.SachDAO;
import com.example.duanmau.SQLite.DAO.ThanhVienDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class Fragment_QLSach extends Fragment {

    ListView qlsach_lv;
    List<Sach> sachList;
    SachDAO sachDAO;
    FloatingActionButton fab_thanhvien;
    TextInputLayout sach_ed_tensach,sach_ed_giathue,sach_ed_maloai;
    Button sach_btn_xacnhan,sach_btn_huy;
    TextView sach_tv_title;
    Dialog dialog ;

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
        sachDAO = new SachDAO(getActivity());
        sachList = sachDAO.getAllSach();
        AdapterQLSach qlsachAdapter = new AdapterQLSach(getActivity(),sachList);
        qlsach_lv.setAdapter(qlsachAdapter);

        fab_thanhvien = view.findViewById(R.id.fab_sach);
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.fab_sach);
        fab_thanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sach_ed_tensach = dialog.findViewById(R.id.sach_ed_tensach);
                sach_ed_giathue = dialog.findViewById(R.id.sach_ed_giathue);
                sach_ed_maloai = dialog.findViewById(R.id.sach_ed_maloai);
                sach_btn_xacnhan = dialog.findViewById(R.id.sach_btn_xacnhan);
                sach_btn_huy = dialog.findViewById(R.id.sach_btn_huy);

                sach_ed_tensach.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        sach_ed_tensach.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                sach_ed_giathue.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        sach_ed_giathue.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                sach_ed_maloai.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        sach_ed_maloai.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                sach_btn_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int err=0;
                        if(sach_ed_tensach.getEditText().getText().toString().trim().matches("")){
                            sach_ed_tensach.setError("Tên sách không được để trống");
                            err+=1;
                        }
                        if(sach_ed_giathue.getEditText().getText().toString().trim().matches("")){
                            sach_ed_giathue.setError("Giá thuê không được để trống");
                            err+=1;
                        }
                        if(sach_ed_maloai.getEditText().getText().toString().trim().matches("")){
                            sach_ed_maloai.setError("Mã loại không được để trống");
                            err+=1;
                        }

                        if(err>0){
                            Toast.makeText(getActivity(), "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            Sach sach = new Sach(
                                    1,
                                    sach_ed_tensach.getEditText().getText().toString(),
                                    Integer.parseInt(sach_ed_giathue.getEditText().getText().toString()),
                                    Integer.parseInt(sach_ed_maloai.getEditText().getText().toString())
                            );

                            if(sachDAO.insert(sach)>0){
                                dialog.dismiss();
                                ((ContainerActivity)getActivity()).recreateSach();
                            }
                            else{
                                Toast.makeText(getActivity(), "Hệ thống đang lỗi vui lòng kiểm tra lại sau", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

                sach_btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return view;
    }
}