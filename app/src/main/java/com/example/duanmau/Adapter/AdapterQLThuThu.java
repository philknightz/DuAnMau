package com.example.duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmau.Object.ThuThu;
import com.example.duanmau.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterQLThuThu extends BaseAdapter {
    List<ThuThu> thuThuList = new ArrayList<>();
    Context context;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_qlthuthu,parent,false);
            viewHolder.qltt_adapter_matt = convertView.findViewById(R.id.qltt_adapter_matt);
            viewHolder.qltt_adapter_hoten = convertView.findViewById(R.id.qltt_adapter_hoten);
            viewHolder.qltt_adapter_matkhau = convertView.findViewById(R.id.qltt_adapter_matkhau);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.qltt_adapter_matt.setText(thuThuList.get(position).getMaTT());
        viewHolder.qltt_adapter_hoten.setText(thuThuList.get(position).getHoTen());
        viewHolder.qltt_adapter_matkhau.setText(thuThuList.get(position).getMatKhau());

        return convertView;
    }

    class ViewHolder {
        TextView qltt_adapter_matt;
        TextView qltt_adapter_hoten;
        TextView qltt_adapter_matkhau;
    }
}
