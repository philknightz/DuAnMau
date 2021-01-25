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
import com.example.duanmau.R;
import com.example.duanmau.SQLite.DAO.LoaiSachDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class AdapterQLLoaiSach extends BaseAdapter {
    Context context;
    List<LoaiSach> loaiSachList;

    LoaiSachDAO loaiSachDAO;
    Dialog dialog ;
    TextInputLayout loaisach_ed_tenloai;
    Button loaisach_btn_xacnhan,loaisach_btn_huy;
    TextView loaisach_tv_title;
    public AdapterQLLoaiSach(Context context, List<LoaiSach> loaiSachList) {
        this.context = context;
        this.loaiSachList = loaiSachList;
    }

    @Override
    public int getCount() {
        return loaiSachList.size();
    }

    @Override
    public Object getItem(int position) {
        return loaiSachList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        loaiSachDAO = new LoaiSachDAO(context);
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_qlloaisach,parent,false);
            viewHolder.qlls_adapter_maloai = convertView.findViewById(R.id.qlls_adapter_maloai);
            viewHolder.qlls_adapter_tenloai = convertView.findViewById(R.id.qlls_adapter_tenloai);
            viewHolder.loaisach_edit = convertView.findViewById(R.id.loaisach_edit);
            viewHolder.loaisach_delete = convertView.findViewById(R.id.loaisach_delete);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.qlls_adapter_maloai.setText("Mã loại: "+loaiSachList.get(position).getMaLoai());
        viewHolder.qlls_adapter_tenloai.setText("Tên loại: "+loaiSachList.get(position).getTenLoai());
        viewHolder.loaisach_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.fab_loaisach);

                loaisach_ed_tenloai = dialog.findViewById(R.id.loaisach_ed_tenloai);
                loaisach_btn_xacnhan = dialog.findViewById(R.id.loaisach_btn_xacnhan);
                loaisach_btn_huy = dialog.findViewById(R.id.loaisach_btn_huy);
                loaisach_tv_title = dialog.findViewById(R.id.loaisach_tv_title);

                loaisach_tv_title.setText("Sửa loại sách");

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
                            Toast.makeText(context, "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            LoaiSach loaiSach = new LoaiSach(
                                    loaiSachList.get(position).getMaLoai(),
                                    loaisach_ed_tenloai.getEditText().getText().toString()
                            );

                            if(loaiSachDAO.update(loaiSach)>0){
                                dialog.dismiss();
                                ((ContainerActivity)context).recreateLoaiSach();
                            }
                            else{
                                Toast.makeText(context, "Hệ thống đang lỗi vui lòng kiểm tra lại sau", Toast.LENGTH_LONG).show();
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

        viewHolder.loaisach_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiSachDAO.delete(loaiSachList.get(position).getMaLoai());
                ((ContainerActivity)context).recreateLoaiSach();
            }
        });

        return convertView;
    }
    class ViewHolder{
        TextView qlls_adapter_maloai;
        TextView qlls_adapter_tenloai;
        Button loaisach_edit;
        Button loaisach_delete;
    }
}
