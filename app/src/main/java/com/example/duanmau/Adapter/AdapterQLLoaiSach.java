package com.example.duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmau.Object.LoaiSach;
import com.example.duanmau.R;

import java.util.List;

public class AdapterQLLoaiSach extends BaseAdapter {
    Context context;
    List<LoaiSach> loaiSachList;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_qlloaisach,parent,false);
            viewHolder.qlls_adapter_maloai = convertView.findViewById(R.id.qlls_adapter_maloai);
            viewHolder.qlls_adapter_tenloai = convertView.findViewById(R.id.qlls_adapter_tenloai);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.qlls_adapter_maloai.setText("Mã loại: "+loaiSachList.get(position).getMaLoai());
        viewHolder.qlls_adapter_tenloai.setText("Tên loại: "+loaiSachList.get(position).getTenLoai());
        return convertView;
    }
    class ViewHolder{
        TextView qlls_adapter_maloai;
        TextView qlls_adapter_tenloai;
    }
}
