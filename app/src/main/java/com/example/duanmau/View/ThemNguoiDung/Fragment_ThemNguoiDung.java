package com.example.duanmau.View.ThemNguoiDung;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.Activities.ContainerActivity;
import com.example.duanmau.Adapter.AdapterQLThuThu;
import com.example.duanmau.Object.ThanhVien;
import com.example.duanmau.Object.ThuThu;
import com.example.duanmau.R;
import com.example.duanmau.SQLite.DAO.ThuThuDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class Fragment_ThemNguoiDung extends Fragment {
    FloatingActionButton fab_themnguoidung;
    ListView lv_themnguoidung;
    ThuThuDAO thuThuDAO;
    List<ThuThu> thuThuList = new ArrayList<>();
    TextInputLayout themnguoidung_ed_matt,themnguoidung_ed_hoten,themnguoidung_ed_matkhau,themnguoidung_ed_nhaplaimatkhau;
    Button themnguoidung_btn_xacnhan,themnguoidung_btn_huy;
    TextView themnguoidung_tv_title;
    Dialog dialog ;
    public Fragment_ThemNguoiDung() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__them_nguoi_dung, container, false);

        lv_themnguoidung = view.findViewById(R.id.lv_themnguodung);

        thuThuDAO= new ThuThuDAO(getActivity());
        thuThuList = thuThuDAO.getAllThuThu();
        AdapterQLThuThu adapterQLThuThu = new AdapterQLThuThu(thuThuList,getActivity());
        lv_themnguoidung.setAdapter(adapterQLThuThu);

        fab_themnguoidung = view.findViewById(R.id.fab_themnguoidung);
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.fab_themnguoidung);
        fab_themnguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themnguoidung_ed_matt = dialog.findViewById(R.id.themnguoidung_ed_matt);
                themnguoidung_ed_hoten = dialog.findViewById(R.id.themnguoidung_ed_hoten);
                themnguoidung_ed_matkhau = dialog.findViewById(R.id.themnguoidung_ed_matkhau);
                themnguoidung_ed_nhaplaimatkhau = dialog.findViewById(R.id.themnguoidung_ed_nhaplaimatkhau);
                themnguoidung_btn_xacnhan = dialog.findViewById(R.id.themnguoidung_btn_xacnhan);
                themnguoidung_btn_huy = dialog.findViewById(R.id.themnguoidung_btn_huy);



                themnguoidung_ed_matt.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        themnguoidung_ed_matt.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                themnguoidung_ed_hoten.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        themnguoidung_ed_hoten.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                themnguoidung_ed_matkhau.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        themnguoidung_ed_matkhau.setError("");
                        themnguoidung_ed_nhaplaimatkhau.setError("");
                        themnguoidung_ed_nhaplaimatkhau.getEditText().setText("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                themnguoidung_ed_nhaplaimatkhau.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        themnguoidung_ed_nhaplaimatkhau.setError("");
                        themnguoidung_ed_matkhau.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                themnguoidung_btn_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int err=0;
                        if(themnguoidung_ed_matt.getEditText().getText().toString().trim().matches("")){
                            themnguoidung_ed_matt.setError("Tên đăng nhập không được để trống");
                            err+=1;
                        }
                        if(themnguoidung_ed_hoten.getEditText().getText().toString().trim().matches("")){
                            themnguoidung_ed_hoten.setError("Tên người dùng không được để trống");
                            err+=1;
                        }
                        if(themnguoidung_ed_matkhau.getEditText().getText().toString().trim().matches("")){
                            themnguoidung_ed_matkhau.setError("Mật khẩu không được để trống");
                            err+=1;
                        }else{
                            if(!themnguoidung_ed_nhaplaimatkhau.getEditText().getText().toString().matches(themnguoidung_ed_matkhau.getEditText().getText().toString())){
                                themnguoidung_ed_nhaplaimatkhau.setError("Mật khẩu xác nhận khác mật khẩu đã nhập");
                                themnguoidung_ed_matkhau.setError("Mật khẩu xác nhận khác mật khẩu đã nhập");
                                err+=1;
                            }
                        }
                        if(themnguoidung_ed_nhaplaimatkhau.getEditText().getText().toString().trim().matches("")){
                            themnguoidung_ed_nhaplaimatkhau.setError("Nhập lại mật khẩu không được để trống");
                            err+=1;
                        }else{
                            if(!themnguoidung_ed_nhaplaimatkhau.getEditText().getText().toString().matches(themnguoidung_ed_matkhau.getEditText().getText().toString())){
                                themnguoidung_ed_nhaplaimatkhau.setError("Mật khẩu xác nhận khác mật khẩu đã nhập");
                                themnguoidung_ed_matkhau.setError("Mật khẩu xác nhận khác mật khẩu đã nhập");
                                err+=1;
                            }
                        }


                        if(err>0){
                            Toast.makeText(getActivity(), "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            ThuThu thuThu = new ThuThu(
                                    themnguoidung_ed_matt.getEditText().getText().toString(),
                                    themnguoidung_ed_hoten.getEditText().getText().toString(),
                                    themnguoidung_ed_matkhau.getEditText().getText().toString()
                            );

                            if(thuThuDAO.insert(thuThu)>0){
                                dialog.dismiss();
                                ((ContainerActivity)getActivity()).recreateThemNguoiDung();
                            }
                            else{
                                themnguoidung_ed_matt.setError("Tên đăng nhập đã bị trùng");
                            }
                        }

                    }
                });

                themnguoidung_btn_huy.setOnClickListener(new View.OnClickListener() {
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