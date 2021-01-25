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
import com.example.duanmau.Object.Sach;
import com.example.duanmau.Object.ThanhVien;
import com.example.duanmau.R;
import com.example.duanmau.SQLite.DAO.SachDAO;
import com.example.duanmau.SQLite.DAO.ThanhVienDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class AdapterQLSach extends BaseAdapter {
    Context context;
    List<Sach> sachList;
    SachDAO sachDAO;
    Dialog dialog ;
    TextInputLayout sach_ed_tensach,sach_ed_giathue,sach_ed_maloai;
    Button sach_btn_xacnhan,sach_btn_huy;
    TextView sach_tv_title;
    public AdapterQLSach(Context context, List<Sach> sachList) {
        this.context = context;
        this.sachList = sachList;
    }

    @Override
    public int getCount() {
        return sachList.size();
    }

    @Override
    public Object getItem(int position) {
        return sachList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        sachDAO = new SachDAO(context);
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_qlsach,parent,false);
            viewHolder.qlsach_adapter_masach = convertView.findViewById(R.id.qlsach_adapter_masach);
            viewHolder.qlsach_adapter_tensach = convertView.findViewById(R.id.qlsach_adapter_tensach);
            viewHolder.qlsach_adapter_giathue = convertView.findViewById(R.id.qlsach_adapter_giathue);
            viewHolder.qlsach_adapter_maloai = convertView.findViewById(R.id.qlsach_adapter_maloai);
            viewHolder.sach_edit = convertView.findViewById(R.id.sach_edit);
            viewHolder.sach_delete = convertView.findViewById(R.id.sach_delete);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.qlsach_adapter_masach.setText("Mã sách: "+sachList.get(position).getMaSach());
        viewHolder.qlsach_adapter_tensach.setText("Tên sách: "+sachList.get(position).getTenSach());
        viewHolder.qlsach_adapter_giathue.setText("Giá thuê: "+sachList.get(position).getGiaThue());
        viewHolder.qlsach_adapter_maloai.setText("Mã loại: "+sachList.get(position).getMaLoai());

        viewHolder.sach_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.fab_sach);

                sach_ed_tensach = dialog.findViewById(R.id.sach_ed_tensach);
                sach_ed_giathue = dialog.findViewById(R.id.sach_ed_giathue);
                sach_ed_maloai = dialog.findViewById(R.id.sach_ed_maloai);
                sach_btn_xacnhan = dialog.findViewById(R.id.sach_btn_xacnhan);
                sach_btn_huy = dialog.findViewById(R.id.sach_btn_huy);
                sach_tv_title = dialog.findViewById(R.id.sach_tv_title);

                sach_tv_title.setText("Sửa sách");

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
                            Toast.makeText(context, "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            Sach sach = new Sach(
                                    sachList.get(position).getMaSach(),
                                    sach_ed_tensach.getEditText().getText().toString(),
                                    Integer.parseInt(sach_ed_giathue.getEditText().getText().toString()),
                                    Integer.parseInt(sach_ed_maloai.getEditText().getText().toString())
                            );

                            if(sachDAO.update(sach)>0){
                                dialog.dismiss();
                                ((ContainerActivity)context).recreateSach();
                            }
                            else{
                                Toast.makeText(context, "Hệ thống đang lỗi vui lòng kiểm tra lại sau", Toast.LENGTH_LONG).show();
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

        viewHolder.sach_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sachDAO.delete(sachList.get(position).getMaSach());
                ((ContainerActivity)context).recreateSach();
            }
        });

        return convertView;
    }
    class ViewHolder{
        TextView qlsach_adapter_masach;
        TextView qlsach_adapter_tensach;
        TextView qlsach_adapter_giathue;
        TextView qlsach_adapter_maloai;
        Button sach_delete;
        Button sach_edit;
    }
}
