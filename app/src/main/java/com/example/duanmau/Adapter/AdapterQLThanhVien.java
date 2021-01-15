package com.example.duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmau.Object.ThanhVien;
import com.example.duanmau.R;

import java.util.List;

public class AdapterQLThanhVien extends BaseAdapter {
    Context context;
    List<ThanhVien> thanhVienList;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_qlthanhvien,parent,false);
            viewHolder.qltv_adapter_matv = convertView.findViewById(R.id.qltv_adapter_matv);
            viewHolder.qltv_adapter_hoten = convertView.findViewById(R.id.qltv_adapter_hoten);
            viewHolder.qltv_adapter_namsinh = convertView.findViewById(R.id.qltv_adapter_namsinh);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.qltv_adapter_matv.setText("Mã thành viên: "+thanhVienList.get(position).getMaTV());
        viewHolder.qltv_adapter_hoten.setText("Họ tên: "+thanhVienList.get(position).getHoTen());
        viewHolder.qltv_adapter_namsinh.setText("Năm sinh: "+thanhVienList.get(position).getNamSinh());
        return convertView;
    }
    class ViewHolder{
        TextView qltv_adapter_matv;
        TextView qltv_adapter_hoten;
        TextView qltv_adapter_namsinh;
    }
}
