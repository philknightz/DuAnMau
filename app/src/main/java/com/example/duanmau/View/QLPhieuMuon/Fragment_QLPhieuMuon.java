package com.example.duanmau.View.QLPhieuMuon;

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
import com.example.duanmau.Object.PhieuMuon;
import com.example.duanmau.Object.ThanhVien;
import com.example.duanmau.R;
import com.example.duanmau.SQLite.DAO.PhieuMuonDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class Fragment_QLPhieuMuon extends Fragment {
    ListView qlpm_lv;
    List<PhieuMuon> phieuMuonList;
    PhieuMuonDAO phieuMuonDAO;
    FloatingActionButton fab_phieumuon;
    TextInputLayout phieumuon_ed_matt,phieumuon_ed_matv,phieumuon_ed_masach,phieumuon_ed_ngaymuon,phieumuon_ed_trasach,phieumuon_ed_tienthue;
    Button phieumuon_btn_xacnhan,phieumuon_btn_huy;
    TextView phieumuon_tv_title;
    Dialog dialog ;
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
        phieuMuonDAO = new PhieuMuonDAO(getActivity());
        phieuMuonList = phieuMuonDAO.getAllPhieuMuon();
        AdapterQLPhieuMuon qlpmAdapter = new AdapterQLPhieuMuon(getActivity(),phieuMuonList);
        qlpm_lv.setAdapter(qlpmAdapter);

        fab_phieumuon = view.findViewById(R.id.fab_phieumuon);
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.fab_phieumuon);
        fab_phieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phieumuon_ed_matt = dialog.findViewById(R.id.phieumuon_ed_matt);
                phieumuon_ed_matv = dialog.findViewById(R.id.phieumuon_ed_matv);
                phieumuon_ed_masach = dialog.findViewById(R.id.phieumuon_ed_masach);
                phieumuon_ed_ngaymuon = dialog.findViewById(R.id.phieumuon_ed_ngaymuon);
                phieumuon_ed_trasach = dialog.findViewById(R.id.phieumuon_ed_trasach);
                phieumuon_ed_tienthue = dialog.findViewById(R.id.phieumuon_ed_tienthue);
                phieumuon_btn_xacnhan = dialog.findViewById(R.id.phieumuon_btn_xacnhan);
                phieumuon_btn_huy = dialog.findViewById(R.id.phieumuon_btn_huy);



                phieumuon_ed_matt.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        phieumuon_ed_matt.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                phieumuon_ed_matv.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        phieumuon_ed_matv.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                phieumuon_ed_masach.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        phieumuon_ed_masach.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                phieumuon_ed_ngaymuon.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        phieumuon_ed_ngaymuon.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                phieumuon_ed_trasach.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        phieumuon_ed_trasach.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                phieumuon_ed_tienthue.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        phieumuon_ed_tienthue.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                phieumuon_btn_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int err=0;
                        if(phieumuon_ed_matt.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_matt.setError("Tên thành viên không được để trống");
                            err+=1;
                        }
                        if(phieumuon_ed_matv.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_matv.setError("Năm sinh không được để trống");
                            err+=1;
                        }
                        if(phieumuon_ed_masach.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_masach.setError("Tên thành viên không được để trống");
                            err+=1;
                        }
                        if(phieumuon_ed_ngaymuon.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_ngaymuon.setError("Năm sinh không được để trống");
                            err+=1;
                        }
                        if(phieumuon_ed_trasach.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_masach.setError("Tên thành viên không được để trống");
                            err+=1;
                        }
                        if(phieumuon_ed_trasach.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_ngaymuon.setError("Năm sinh không được để trống");
                            err+=1;
                        }

                        if(err>0){
                            Toast.makeText(getActivity(), "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            PhieuMuon phieuMuon = new PhieuMuon(
                                    1,
                                    phieumuon_ed_matt.getEditText().getText().toString(),
                                    Integer.parseInt(phieumuon_ed_matv.getEditText().getText().toString()),
                                    Integer.parseInt(phieumuon_ed_masach.getEditText().getText().toString()),
                                    phieumuon_ed_ngaymuon.getEditText().getText().toString(),
                                    Integer.parseInt(phieumuon_ed_trasach.getEditText().getText().toString()),
                                    Integer.parseInt(phieumuon_ed_tienthue.getEditText().getText().toString())
                            );

                            if(phieuMuonDAO.insert(phieuMuon)>0){
                                dialog.dismiss();
                                ((ContainerActivity)getActivity()).recreatePhieuMuon();
                            }
                            else{
                                Toast.makeText(getActivity(), "Hệ thống đang lỗi vui lòng kiểm tra lại sau", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

                phieumuon_btn_huy.setOnClickListener(new View.OnClickListener() {
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