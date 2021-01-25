package com.example.duanmau.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.Activities.ContainerActivity;
import com.example.duanmau.Object.LoaiSach;
import com.example.duanmau.Object.ThanhVien;
import com.example.duanmau.R;
import com.example.duanmau.SQLite.DAO.LoaiSachDAO;
import com.example.duanmau.SQLite.DAO.ThanhVienDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class AdapterQLThanhVien extends BaseAdapter {
    Context context;
    List<ThanhVien> thanhVienList;
    ThanhVienDAO thanhVienDAO;
    Dialog dialog ;
    TextInputLayout thanhvien_ed_hoten,thanhvien_ed_namsinh;
    Button thanhvien_btn_xacnhan,thanhvien_btn_huy;
    TextView thanhvien_tv_title;
    public AdapterQLThanhVien(Context context, List<ThanhVien> thanhVienList) {
        this.context = context;
        this.thanhVienList = thanhVienList;
    }

    @Override
    public int getCount() {
        return thanhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return thanhVienList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        thanhVienDAO = new ThanhVienDAO(context);
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_qlthanhvien,parent,false);
            viewHolder.qltv_adapter_matv = convertView.findViewById(R.id.qltv_adapter_matv);
            viewHolder.qltv_adapter_hoten = convertView.findViewById(R.id.qltv_adapter_hoten);
            viewHolder.qltv_adapter_namsinh = convertView.findViewById(R.id.qltv_adapter_namsinh);
            viewHolder.thanhvien_edit = convertView.findViewById(R.id.thanhvien_edit);
            viewHolder.thanhvien_delete = convertView.findViewById(R.id.thanhvien_delete);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.qltv_adapter_matv.setText("Mã thành viên: "+thanhVienList.get(position).getMaTV());
        viewHolder.qltv_adapter_hoten.setText("Họ tên: "+thanhVienList.get(position).getHoTen());
        viewHolder.qltv_adapter_namsinh.setText("Năm sinh: "+thanhVienList.get(position).getNamSinh());

        viewHolder.thanhvien_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.fab_thanhvien);

                thanhvien_ed_hoten = dialog.findViewById(R.id.thanhvien_ed_hoten);
                thanhvien_ed_namsinh = dialog.findViewById(R.id.thanhvien_ed_namsinh);
                thanhvien_btn_xacnhan = dialog.findViewById(R.id.thanhvien_btn_xacnhan);
                thanhvien_btn_huy = dialog.findViewById(R.id.thanhvien_btn_huy);
                thanhvien_tv_title = dialog.findViewById(R.id.thanhvien_tv_title);

                thanhvien_tv_title.setText("Sửa thành viên");

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
                            thanhvien_ed_hoten.setError("Họ tên không được để trống");
                            err+=1;
                        }

                        if(thanhvien_ed_namsinh.getEditText().getText().toString().trim().matches("")){
                            thanhvien_ed_namsinh.setError("Năm sinh không được để trống");
                            err+=1;
                        }

                        if(err>0){
                            Toast.makeText(context, "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            ThanhVien thanhVien = new ThanhVien(
                                    thanhVienList.get(position).getMaTV(),
                                    thanhvien_ed_hoten.getEditText().getText().toString(),
                                    thanhvien_ed_namsinh.getEditText().getText().toString()
                            );

                            if(thanhVienDAO.update(thanhVien)>0){
                                dialog.dismiss();
                                ((ContainerActivity)context).recreateThanhVien();
                            }
                            else{
                                Toast.makeText(context, "Hệ thống đang lỗi vui lòng kiểm tra lại sau", Toast.LENGTH_LONG).show();
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

        viewHolder.thanhvien_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhVienDAO.delete(thanhVienList.get(position).getMaTV());
                ((ContainerActivity)context).recreateThanhVien();
            }
        });

        return convertView;
    }
    class ViewHolder{
        TextView qltv_adapter_matv;
        TextView qltv_adapter_hoten;
        TextView qltv_adapter_namsinh;
        Button thanhvien_edit;
        Button thanhvien_delete;
    }
}
