package com.example.duanmau.View.QLThanhVien;

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
import com.example.duanmau.Adapter.AdapterQLThanhVien;
import com.example.duanmau.Object.PhieuMuon;
import com.example.duanmau.Object.ThanhVien;
import com.example.duanmau.Object.ThuThu;
import com.example.duanmau.R;
import com.example.duanmau.SQLite.DAO.ThanhVienDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class Fragment_QLThanhVien extends Fragment {
    ListView qltv_lv;
    List<ThanhVien> thanhVienList;
    ThanhVienDAO thanhVienDAO;
    FloatingActionButton fab_thanhvien;
    TextInputLayout thanhvien_ed_hoten,thanhvien_ed_namsinh;
    Button thanhvien_btn_xacnhan,thanhvien_btn_huy;
    TextView loaisach_tv_title;
    Dialog dialog ;
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
        thanhVienDAO = new ThanhVienDAO(getActivity());
        thanhVienList = thanhVienDAO.getAllThanhVien();

        AdapterQLThanhVien qltvAdapter = new AdapterQLThanhVien(getActivity(),thanhVienList);
        qltv_lv.setAdapter(qltvAdapter);

        fab_thanhvien = view.findViewById(R.id.fab_thanhvien);
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.fab_thanhvien);
        fab_thanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhvien_ed_hoten = dialog.findViewById(R.id.thanhvien_ed_hoten);
                thanhvien_ed_namsinh = dialog.findViewById(R.id.thanhvien_ed_namsinh);
                thanhvien_btn_xacnhan = dialog.findViewById(R.id.thanhvien_btn_xacnhan);
                thanhvien_btn_huy = dialog.findViewById(R.id.thanhvien_btn_huy);



                thanhvien_ed_hoten.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        thanhvien_ed_hoten.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                thanhvien_ed_namsinh.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        thanhvien_ed_namsinh.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                thanhvien_btn_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int err=0;
                        if(thanhvien_ed_hoten.getEditText().getText().toString().trim().matches("")){
                            thanhvien_ed_hoten.setError("Tên thành viên không được để trống");
                            err+=1;
                        }
                        if(thanhvien_ed_namsinh.getEditText().getText().toString().trim().matches("")){
                            thanhvien_ed_namsinh.setError("Năm sinh không được để trống");
                            err+=1;
                        }

                        if(err>0){
                            Toast.makeText(getActivity(), "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            ThanhVien thanhVien = new ThanhVien(
                                    1,
                                    thanhvien_ed_hoten.getEditText().getText().toString(),
                                    thanhvien_ed_namsinh.getEditText().getText().toString()
                            );

                            if(thanhVienDAO.insert(thanhVien)>0){
                                dialog.dismiss();
                                ((ContainerActivity)getActivity()).recreateThanhVien();
                            }
                            else{
                                Toast.makeText(getActivity(), "Hệ thống đang lỗi vui lòng kiểm tra lại sau", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

                thanhvien_btn_huy.setOnClickListener(new View.OnClickListener() {
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