package com.example.duanmau.View.QLLoaiSach;

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
import com.example.duanmau.Adapter.AdapterQLLoaiSach;
import com.example.duanmau.Adapter.AdapterQLPhieuMuon;
import com.example.duanmau.Object.LoaiSach;
import com.example.duanmau.Object.PhieuMuon;
import com.example.duanmau.Object.ThanhVien;
import com.example.duanmau.R;
import com.example.duanmau.SQLite.DAO.LoaiSachDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class Fragment_QLLoaiSach extends Fragment {
    ListView qlls_lv;
    List<LoaiSach> loaiSachList;
    LoaiSachDAO loaiSachDAO;
    FloatingActionButton fab_thanhvien;
    TextInputLayout loaisach_ed_tenloai;
    Button loaisach_btn_xacnhan,loaisach_btn_huy;
    TextView loaisach_tv_title;
    Dialog dialog ;
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
        loaiSachDAO = new LoaiSachDAO(getActivity());
        loaiSachList = loaiSachDAO.getAllLoaiSach();
        AdapterQLLoaiSach qllsAdapter = new AdapterQLLoaiSach(getActivity(),loaiSachList);
        qlls_lv.setAdapter(qllsAdapter);

        fab_thanhvien = view.findViewById(R.id.fab_loaisach);
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.fab_loaisach);
        fab_thanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaisach_ed_tenloai = dialog.findViewById(R.id.loaisach_ed_tenloai);
                loaisach_btn_xacnhan = dialog.findViewById(R.id.loaisach_btn_xacnhan);
                loaisach_btn_huy = dialog.findViewById(R.id.loaisach_btn_huy);



                loaisach_ed_tenloai.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        loaisach_ed_tenloai.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                loaisach_btn_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int err=0;
                        if(loaisach_ed_tenloai.getEditText().getText().toString().trim().matches("")){
                            loaisach_ed_tenloai.setError("Tên loại sách không được để trống");
                            err+=1;
                        }

                        if(err>0){
                            Toast.makeText(getActivity(), "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            LoaiSach loaiSach = new LoaiSach(
                                    1,
                                    loaisach_ed_tenloai.getEditText().getText().toString()
                            );

                            if(loaiSachDAO.insert(loaiSach)>0){
                                dialog.dismiss();
                                ((ContainerActivity)getActivity()).recreateLoaiSach();
                            }
                            else{
                                Toast.makeText(getActivity(), "Hệ thống đang lỗi vui lòng kiểm tra lại sau", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

                loaisach_btn_huy.setOnClickListener(new View.OnClickListener() {
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