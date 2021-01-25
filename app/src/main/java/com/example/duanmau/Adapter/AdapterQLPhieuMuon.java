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
import com.example.duanmau.Object.ThanhVien;
import com.example.duanmau.R;
import com.example.duanmau.SQLite.DAO.PhieuMuonDAO;
import com.example.duanmau.SQLite.DAO.ThanhVienDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class AdapterQLPhieuMuon extends BaseAdapter {
    Context context;
    List<PhieuMuon> phieuMuonList;
    PhieuMuonDAO phieuMuonDAO;
    Dialog dialog ;
    TextInputLayout phieumuon_ed_matt,phieumuon_ed_matv,phieumuon_ed_masach,phieumuon_ed_ngaymuon,phieumuon_ed_trasach,phieumuon_ed_tienthue;
    Button phieumuon_btn_xacnhan,phieumuon_btn_huy;
    TextView phieumuon_tv_title;
    public AdapterQLPhieuMuon(Context context,List<PhieuMuon> phieuMuonList) {
        this.context = context;
        this.phieuMuonList = phieuMuonList;
    }

    @Override
    public int getCount() {
        return phieuMuonList.size();
    }

    @Override
    public PhieuMuon getItem(int position) {
        return phieuMuonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        phieuMuonDAO = new PhieuMuonDAO(context);
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_qlphieumuon,parent,false);
            viewHolder.qlpm_adapter_mapm = convertView.findViewById(R.id.qlpm_adapter_mapm);
            viewHolder.qlpm_adapter_matt = convertView.findViewById(R.id.qlpm_adapter_matt);
            viewHolder.qlpm_adapter_matv = convertView.findViewById(R.id.qlpm_adapter_matv);
            viewHolder.qlpm_adapter_masach = convertView.findViewById(R.id.qlpm_adapter_masach);
            viewHolder.qlpm_adapter_ngaymuon = convertView.findViewById(R.id.qlpm_adapter_ngaymuon);
            viewHolder.qlpm_adapter_trasach = convertView.findViewById(R.id.qlpm_adapter_trasach);
            viewHolder.qlpm_adapter_tienthue = convertView.findViewById(R.id.qlpm_adapter_tienthue);
            viewHolder.phieumuon_edit = convertView.findViewById(R.id.phieumuon_edit);
            viewHolder.phieumuon_delete = convertView.findViewById(R.id.phieumuon_delete);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
            String trasach = phieuMuonList.get(position).getTraSach()==0?"Chưa trả":"Đã trả";
            viewHolder.qlpm_adapter_mapm.setText("Mã phiếu mượn: "+phieuMuonList.get(position).getMaPM());
            viewHolder.qlpm_adapter_matt.setText("Mã thủ thư: "+phieuMuonList.get(position).getMaTT());
            viewHolder.qlpm_adapter_matv.setText("Mã thành viên: "+phieuMuonList.get(position).getMaTV());
            viewHolder.qlpm_adapter_masach.setText("Mã thủ thư: "+phieuMuonList.get(position).getMaSach());
            viewHolder.qlpm_adapter_ngaymuon.setText("Ngày mượn: "+phieuMuonList.get(position).getNgayMuon());
            viewHolder.qlpm_adapter_trasach.setText("Trả sách: "+trasach);
            viewHolder.qlpm_adapter_tienthue.setText("Tiền thuê: "+phieuMuonList.get(position).getTienThue());

        viewHolder.phieumuon_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.fab_phieumuon);

                phieumuon_ed_matt = dialog.findViewById(R.id.phieumuon_ed_matt);
                phieumuon_ed_matv = dialog.findViewById(R.id.phieumuon_ed_matv);
                phieumuon_ed_masach = dialog.findViewById(R.id.phieumuon_ed_masach);
                phieumuon_ed_ngaymuon = dialog.findViewById(R.id.phieumuon_ed_ngaymuon);
                phieumuon_ed_trasach = dialog.findViewById(R.id.phieumuon_ed_trasach);
                phieumuon_ed_tienthue = dialog.findViewById(R.id.phieumuon_ed_tienthue);
                phieumuon_btn_xacnhan = dialog.findViewById(R.id.phieumuon_btn_xacnhan);
                phieumuon_btn_huy = dialog.findViewById(R.id.phieumuon_btn_huy);
                phieumuon_tv_title = dialog.findViewById(R.id.phieumuon_tv_title);

                phieumuon_tv_title.setText("Sửa phiếu mượn");

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
                            phieumuon_ed_matt.setError("Mã thủ thư không được để trống");
                            err+=1;
                        }

                        if(phieumuon_ed_matv.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_matv.setError("Mã thành viên không được để trống");
                            err+=1;
                        }

                        if(phieumuon_ed_masach.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_masach.setError("Mã sách không được để trống");
                            err+=1;
                        }
                        if(phieumuon_ed_ngaymuon.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_ngaymuon.setError("Ngày mượn không được để trống");
                            err+=1;
                        }
                        if(phieumuon_ed_trasach.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_trasach.setError("Trả sách không được để trống");
                            err+=1;
                        }

                        if(phieumuon_ed_tienthue.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_tienthue.setError("Tiền thuê không được để trống");
                            err+=1;
                        }

                        if(err>0){
                            Toast.makeText(context, "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            PhieuMuon phieuMuon = new PhieuMuon(
                                    phieuMuonList.get(position).getMaPM(),
                                    phieumuon_ed_matt.getEditText().getText().toString(),
                                    Integer.parseInt(phieumuon_ed_matv.getEditText().getText().toString()),
                                    Integer.parseInt(phieumuon_ed_masach.getEditText().getText().toString()),
                                    phieumuon_ed_ngaymuon.getEditText().getText().toString(),
                                    Integer.parseInt(phieumuon_ed_trasach.getEditText().getText().toString()),
                                    Integer.parseInt(phieumuon_ed_tienthue.getEditText().getText().toString())
                            );

                            if(phieuMuonDAO.update(phieuMuon)>0){
                                dialog.dismiss();
                                ((ContainerActivity)context).recreatePhieuMuon();
                            }
                            else{
                                Toast.makeText(context, "Hệ thống đang lỗi vui lòng kiểm tra lại sau", Toast.LENGTH_LONG).show();
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

        viewHolder.phieumuon_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phieuMuonDAO.delete(phieuMuonList.get(position).getMaPM());
                ((ContainerActivity)context).recreatePhieuMuon();
            }
        });

        return convertView;
    }
    class ViewHolder {
        TextView qlpm_adapter_mapm;
        TextView qlpm_adapter_matt;
        TextView qlpm_adapter_matv;
        TextView qlpm_adapter_masach;
        TextView qlpm_adapter_ngaymuon;
        TextView qlpm_adapter_trasach;
        TextView qlpm_adapter_tienthue;
        Button phieumuon_edit;
        Button phieumuon_delete;

    }
}
