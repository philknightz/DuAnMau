package com.example.duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmau.Object.Sach;
import com.example.duanmau.R;

import java.util.List;

public class AdapterQLSach extends BaseAdapter {
    Context context;
    List<Sach> sachList;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_qlsach,parent,false);
            viewHolder.qlsach_adapter_masach = convertView.findViewById(R.id.qlsach_adapter_masach);
            viewHolder.qlsach_adapter_tensach = convertView.findViewById(R.id.qlsach_adapter_tensach);
            viewHolder.qlsach_adapter_giathue = convertView.findViewById(R.id.qlsach_adapter_giathue);
            viewHolder.qlsach_adapter_maloai = convertView.findViewById(R.id.qlsach_adapter_maloai);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.qlsach_adapter_masach.setText("Mã sách: "+sachList.get(position).getMaSach());
        viewHolder.qlsach_adapter_tensach.setText("Tên sách: "+sachList.get(position).getTenSach());
        viewHolder.qlsach_adapter_giathue.setText("Giá thuê: "+sachList.get(position).getGiaThue());
        viewHolder.qlsach_adapter_maloai.setText("Mã loại: "+sachList.get(position).getMaLoai());
        return convertView;
    }
    class ViewHolder{
        TextView qlsach_adapter_masach;
        TextView qlsach_adapter_tensach;
        TextView qlsach_adapter_giathue;
        TextView qlsach_adapter_maloai;
    }
}
