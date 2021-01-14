package com.example.duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.Object.PhieuMuon;
import com.example.duanmau.R;

import java.util.List;

public class AdapterQLPhieuMuon extends BaseAdapter {
    Context context;
    List<PhieuMuon> phieuMuonList;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_qlphieumuon,parent,false);
            viewHolder.qlpm_adapter_mapm = convertView.findViewById(R.id.qlpm_adapter_mapm);
            viewHolder.qlpm_adapter_trasach = convertView.findViewById(R.id.qlpm_adapter_trasach);
            viewHolder.qlpm_adapter_ngaymuon = convertView.findViewById(R.id.qlpm_adapter_ngaymuon);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
            String trasach = phieuMuonList.get(position).getTraSach()==0?"Chưa trả":"Đã trả";
            viewHolder.qlpm_adapter_mapm.setText("Mã phiếu mượn: "+phieuMuonList.get(position).getMaPM());
            viewHolder.qlpm_adapter_trasach.setText("Trả sách: "+trasach);
            viewHolder.qlpm_adapter_ngaymuon.setText("Ngày mượn: "+phieuMuonList.get(position).getNgayMuon());
        return convertView;
    }
    class ViewHolder {
        TextView qlpm_adapter_mapm;
        TextView qlpm_adapter_trasach;
        TextView qlpm_adapter_ngaymuon;
    }
}
