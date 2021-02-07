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
import com.example.duanmau.Object.PhieuMuon;
import com.example.duanmau.Object.ThuThu;
import com.example.duanmau.R;
import com.example.duanmau.SQLite.DAO.PhieuMuonDAO;
import com.example.duanmau.SQLite.DAO.ThuThuDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AdapterQLThuThu extends BaseAdapter {
    List<ThuThu> thuThuList = new ArrayList<>();
    Context context;
    ThuThuDAO thuThuDAO;
    Dialog dialog;
    TextInputLayout themnguoidung_ed_matt,themnguoidung_ed_hoten,themnguoidung_ed_matkhau,themnguoidung_ed_nhaplaimatkhau;
    Button themnguoidung_btn_xacnhan,themnguoidung_btn_huy;
    TextView themnguoidung_tv_title;
    public AdapterQLThuThu(List<ThuThu> thuThuList, Context context) {
        this.thuThuList = thuThuList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return thuThuList.size();
    }

    @Override
    public Object getItem(int position) {
        return thuThuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_qlthuthu,parent,false);
            viewHolder.qltt_adapter_matt = convertView.findViewById(R.id.qltt_adapter_matt);
            viewHolder.qltt_adapter_hoten = convertView.findViewById(R.id.qltt_adapter_hoten);
            viewHolder.qltt_adapter_matkhau = convertView.findViewById(R.id.qltt_adapter_matkhau);
            viewHolder.thuthu_edit = convertView.findViewById(R.id.thuthu_edit);
            viewHolder.thuthu_delete = convertView.findViewById(R.id.thuthu_delete);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String hidePass = "";
        for(int i=0;i<thuThuList.get(position).getMatKhau().length();i++){
            hidePass = hidePass + "*";
        }
        viewHolder.qltt_adapter_matt.setText("Mã thủ thư: " + thuThuList.get(position).getMaTT());
        viewHolder.qltt_adapter_hoten.setText("Họ tên: " + thuThuList.get(position).getHoTen());
        viewHolder.qltt_adapter_matkhau.setText("Mật khẩu: " + hidePass);

        thuThuDAO= new ThuThuDAO(context);
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.fab_themnguoidung);

        viewHolder.thuthu_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themnguoidung_ed_matt = dialog.findViewById(R.id.themnguoidung_ed_matt);
                themnguoidung_ed_hoten = dialog.findViewById(R.id.themnguoidung_ed_hoten);
                themnguoidung_ed_matkhau = dialog.findViewById(R.id.themnguoidung_ed_matkhau);
                themnguoidung_ed_nhaplaimatkhau = dialog.findViewById(R.id.themnguoidung_ed_nhaplaimatkhau);
                themnguoidung_btn_xacnhan = dialog.findViewById(R.id.themnguoidung_btn_xacnhan);
                themnguoidung_btn_huy = dialog.findViewById(R.id.themnguoidung_btn_huy);
                themnguoidung_tv_title = dialog.findViewById(R.id.themnguoidung_tv_title);

                themnguoidung_tv_title.setText("Đổi mật khẩu");
                themnguoidung_ed_matt.getEditText().setText(thuThuList.get(position).getMaTT());
                themnguoidung_ed_matt.getEditText().setEnabled(false);


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
                            Toast.makeText(context, "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            ThuThu thuThu = new ThuThu(
                                    themnguoidung_ed_matt.getEditText().getText().toString(),
                                    themnguoidung_ed_hoten.getEditText().getText().toString(),
                                    themnguoidung_ed_matkhau.getEditText().getText().toString()
                            );

                            if(thuThuDAO.update(thuThu)>0){
                                dialog.dismiss();
                                ((ContainerActivity)context).recreateThemNguoiDung();
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

        viewHolder.thuthu_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thuThuDAO.delete(thuThuList.get(position).getMaTT());
                ((ContainerActivity)context).recreateThemNguoiDung();
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView qltt_adapter_matt;
        TextView qltt_adapter_hoten;
        TextView qltt_adapter_matkhau;
        Button thuthu_edit;
        Button thuthu_delete;
    }
}
