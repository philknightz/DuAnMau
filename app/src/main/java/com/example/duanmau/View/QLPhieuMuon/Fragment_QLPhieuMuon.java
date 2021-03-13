package com.example.duanmau.View.QLPhieuMuon;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.Activities.ContainerActivity;
import com.example.duanmau.Adapter.AdapterQLPhieuMuon;
import com.example.duanmau.Object.LoaiSach;
import com.example.duanmau.Object.PhieuMuon;
import com.example.duanmau.Object.Sach;
import com.example.duanmau.Object.ThanhVien;
import com.example.duanmau.Object.ThuThu;
import com.example.duanmau.R;
import com.example.duanmau.SQLite.DAO.LoaiSachDAO;
import com.example.duanmau.SQLite.DAO.PhieuMuonDAO;
import com.example.duanmau.SQLite.DAO.SachDAO;
import com.example.duanmau.SQLite.DAO.ThanhVienDAO;
import com.example.duanmau.SQLite.DAO.ThuThuDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class Fragment_QLPhieuMuon extends Fragment {
    ListView qlpm_lv;
    List<PhieuMuon> phieuMuonList;
    PhieuMuonDAO phieuMuonDAO;
    ThuThuDAO thuThuDAO;
    ThanhVienDAO thanhVienDAO;
    LoaiSachDAO loaiSachDAO;
    SachDAO sachDAO;

    FloatingActionButton fab_phieumuon;
    TextInputLayout phieumuon_ed_matt,phieumuon_ed_matv,phieumuon_ed_masach,phieumuon_ed_ngaymuon,phieumuon_ed_trasach,phieumuon_ed_tienthue;
    Button phieumuon_btn_xacnhan,phieumuon_btn_huy;
    TextView phieumuon_tv_title;
    Dialog dialog ;
    public Fragment_QLPhieuMuon() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__q_l_phieu_muon, container, false);


        thuThuDAO = new ThuThuDAO(getActivity());
        thanhVienDAO = new ThanhVienDAO(getActivity());
        loaiSachDAO = new LoaiSachDAO(getActivity());
        sachDAO = new SachDAO(getActivity());
        phieuMuonDAO = new PhieuMuonDAO(getActivity());

        if(thuThuDAO.getAllThuThu().size()<=1){
            thuThuDAO.insert(new ThuThu("trungkien","Kien","123456"));
            thuThuDAO.insert(new ThuThu("xuantrang","Trang","123456"));
//            thuThuDAO.insert(new ThuThu("trungkien","Kien","Nam","123456"));
//            thuThuDAO.insert(new ThuThu("xuantrang","Trang","Nu","123456"));

            thanhVienDAO.insert(new ThanhVien(1,"Nguyen Thi Thuy Linh","18-12-1997"));
            thanhVienDAO.insert(new ThanhVien(2,"Nguyen Van My","10-02-1994"));
            thanhVienDAO.insert(new ThanhVien(3,"Ta Dinh Phong","11-05-1998"));
            thanhVienDAO.insert(new ThanhVien(4,"Truong Tan Kiet","15-07-2000"));
            thanhVienDAO.insert(new ThanhVien(5,"Truong Ve Kien","30-01-1995"));

            loaiSachDAO.insert(new LoaiSach(1,"Cong Nghe Thong Tin"));
            loaiSachDAO.insert(new LoaiSach(2,"Ngoai Ngu"));
            loaiSachDAO.insert(new LoaiSach(3,"Co Ban"));

            sachDAO.insert(new Sach(1,"JAVA",11000,1));
            sachDAO.insert(new Sach(2,"Tieng Anh",12000,2));
            sachDAO.insert(new Sach(3,"PYTHON",13000,1));
            sachDAO.insert(new Sach(4,"JAVASCRIPTS",14000,1));
            sachDAO.insert(new Sach(5,"Toan Cao Cap",15000,3));
            sachDAO.insert(new Sach(6,"REACTJS",16000,1));
            sachDAO.insert(new Sach(7,"NODEJS",17000,1));
            sachDAO.insert(new Sach(8,"Tieng Nhat",18000,2));
            sachDAO.insert(new Sach(9,"C++",19000,1));
            sachDAO.insert(new Sach(10,"Phap Luat",20000,3));
            sachDAO.insert(new Sach(11,"Tieng Han",21000,2));
            sachDAO.insert(new Sach(12,"SWIFT",22000,1));

            phieuMuonDAO.insert(new PhieuMuon(1,"trungkien",1,1,"01-02-2020",1,11000));
            phieuMuonDAO.insert(new PhieuMuon(2,"trungkien",1,2,"02-02-2020",1,12000));
            phieuMuonDAO.insert(new PhieuMuon(3,"trungkien",2,3,"01-02-2020",0,13000));
            phieuMuonDAO.insert(new PhieuMuon(4,"xuantrang",3,4,"10-02-2020",1,14000));
            phieuMuonDAO.insert(new PhieuMuon(5,"xuantrang",5,5,"15-02-2020",0,15000));
            phieuMuonDAO.insert(new PhieuMuon(6,"xuantrang",4,6,"01-02-2020",0,16000));
            phieuMuonDAO.insert(new PhieuMuon(7,"trungkien",2,7,"14-02-2020",1,17000));
            phieuMuonDAO.insert(new PhieuMuon(8,"xuantrang",5,8,"20-02-2020",0,18000));
            phieuMuonDAO.insert(new PhieuMuon(9,"trungkien",4,9,"27-02-2020",1,19000));
            phieuMuonDAO.insert(new PhieuMuon(10,"trungkien",1,10,"24-02-2020",1,20000));
            phieuMuonDAO.insert(new PhieuMuon(11,"trungkien",1,1,"05-02-2020",1,22000));
            phieuMuonDAO.insert(new PhieuMuon(12,"trungkien",2,3,"03-02-2020",0,13000));
            phieuMuonDAO.insert(new PhieuMuon(13,"trungkien",1,10,"02-02-2020",1,20000));
            phieuMuonDAO.insert(new PhieuMuon(14,"trungkien",1,7,"07-02-2020",1,17000));
            phieuMuonDAO.insert(new PhieuMuon(15,"xuantrang",5,8,"01-02-2020",0,18000));
            phieuMuonDAO.insert(new PhieuMuon(16,"trungkien",4,9,"08-02-2020",1,19000));
            phieuMuonDAO.insert(new PhieuMuon(17,"trungkien",1,10,"15-02-2020",1,20000));
            phieuMuonDAO.insert(new PhieuMuon(18,"trungkien",1,1,"10-02-2020",1,22000));
            phieuMuonDAO.insert(new PhieuMuon(19,"trungkien",2,3,"11-02-2020",0,13000));
            phieuMuonDAO.insert(new PhieuMuon(20,"trungkien",1,10,"20-02-2020",1,20000));
            phieuMuonDAO.insert(new PhieuMuon(21,"trungkien",1,7,"22-02-2020",1,17000));
            phieuMuonDAO.insert(new PhieuMuon(22,"xuantrang",4,6,"02-02-2020",0,16000));
            phieuMuonDAO.insert(new PhieuMuon(23,"trungkien",2,7,"03-02-2020",1,17000));
            phieuMuonDAO.insert(new PhieuMuon(24,"xuantrang",5,8,"05-02-2020",0,18000));
            phieuMuonDAO.insert(new PhieuMuon(25,"trungkien",4,9,"07-02-2020",1,19000));
            phieuMuonDAO.insert(new PhieuMuon(26,"trungkien",1,10,"17-02-2020",1,20000));
            phieuMuonDAO.insert(new PhieuMuon(27,"trungkien",1,1,"15-02-2020",1,22000));
            phieuMuonDAO.insert(new PhieuMuon(28,"trungkien",2,3,"11-02-2020",0,13000));
            phieuMuonDAO.insert(new PhieuMuon(29,"trungkien",1,10,"24-02-2020",1,20000));
            phieuMuonDAO.insert(new PhieuMuon(30,"trungkien",1,7,"14-02-2020",1,17000));
            phieuMuonDAO.insert(new PhieuMuon(31,"xuantrang",5,8,"27-02-2020",0,18000));
        }

        qlpm_lv = view.findViewById(R.id.qlpm_lv);
        phieuMuonList = new ArrayList<>();
        phieuMuonDAO = new PhieuMuonDAO(getActivity());
        phieuMuonList = phieuMuonDAO.getAllPhieuMuon();
        AdapterQLPhieuMuon qlpmAdapter = new AdapterQLPhieuMuon(getActivity(),phieuMuonList);
        qlpm_lv.setAdapter(qlpmAdapter);

        fab_phieumuon = view.findViewById(R.id.fab_phieumuon);
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.fab_phieumuon);
        fab_phieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phieumuon_ed_matt = dialog.findViewById(R.id.phieumuon_ed_matt);
                phieumuon_ed_matv = dialog.findViewById(R.id.phieumuon_ed_matv);
                phieumuon_ed_masach = dialog.findViewById(R.id.phieumuon_ed_masach);
                phieumuon_ed_ngaymuon = dialog.findViewById(R.id.phieumuon_ed_ngaymuon);
                phieumuon_ed_trasach = dialog.findViewById(R.id.phieumuon_ed_trasach);
                phieumuon_ed_tienthue = dialog.findViewById(R.id.phieumuon_ed_tienthue);
                phieumuon_btn_xacnhan = dialog.findViewById(R.id.phieumuon_btn_xacnhan);
                phieumuon_btn_huy = dialog.findViewById(R.id.phieumuon_btn_huy);



                phieumuon_ed_matt.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        phieumuon_ed_matt.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {}
                });

                phieumuon_ed_matv.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        phieumuon_ed_matv.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {}
                });

                phieumuon_ed_masach.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        phieumuon_ed_masach.setError("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {}
                });

                phieumuon_ed_ngaymuon.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        phieumuon_ed_ngaymuon.setError("");
                    }
                    @Override
                    public void afterTextChanged(Editable s) {}
                });

                phieumuon_ed_trasach.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        phieumuon_ed_trasach.setError("");
                    }
                    @Override
                    public void afterTextChanged(Editable s) {}
                });

                phieumuon_ed_tienthue.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        phieumuon_ed_tienthue.setError("");
                    }
                    @Override
                    public void afterTextChanged(Editable s) {}
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
                        else{
                            try{
                                Integer.parseInt(phieumuon_ed_matv.getEditText().getText().toString());
                            }catch (NumberFormatException error){
                                err+=1;
                                phieumuon_ed_matv.setError("Mã thành viên phải là kiểu số nguyên");
                            }
                        }


                        if(phieumuon_ed_masach.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_masach.setError("Mã sách không được để trống");
                            err+=1;
                        }
                        else{
                            try{
                                Integer.parseInt(phieumuon_ed_masach.getEditText().getText().toString());
                            }catch (NumberFormatException error){
                                err+=1;
                                phieumuon_ed_masach.setError("Mã sách phải là kiểu số nguyên");
                            }
                        }


                        if(phieumuon_ed_ngaymuon.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_ngaymuon.setError("Năm sinh không được để trống");
                            err+=1;
                        }

                        if(phieumuon_ed_trasach.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_masach.setError("Vui lòng chọn trạng thái trả sách");
                            err+=1;
                        }
                        else{
                            if(!(phieumuon_ed_trasach.getEditText().getText().toString().equalsIgnoreCase("0")
                                    ||phieumuon_ed_trasach.getEditText().getText().toString().equalsIgnoreCase("1"))){
                                phieumuon_ed_trasach.setError("Vui lòng chọn trạng thái trả sách là 0 hoặc 1");
                                err+=1;
                            }
                        }


                        if(phieumuon_ed_trasach.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_ngaymuon.setError("Năm sinh không được để trống");

                            err+=1;
                        }
                        if(phieumuon_ed_tienthue.getEditText().getText().toString().trim().matches("")){
                            phieumuon_ed_tienthue.setError("Tiền thuê không được để trống");
                            err+=1;
                        }else{
                            try{
                                Integer.parseInt(phieumuon_ed_tienthue.getEditText().getText().toString());
                            }catch (NumberFormatException error){
                                err+=1;
                                phieumuon_ed_tienthue.setError("Tiền thuê phải là kiểu số nguyên");
                            }
                        }

                        if(err>0){
                            Toast.makeText(getActivity(), "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            PhieuMuon phieuMuon = new PhieuMuon(
                                    1,
                                    phieumuon_ed_matt.getEditText().getText().toString(),
                                    Integer.parseInt(phieumuon_ed_matv.getEditText().getText().toString()),
                                    Integer.parseInt(phieumuon_ed_masach.getEditText().getText().toString()),
                                    phieumuon_ed_ngaymuon.getEditText().getText().toString(),
                                    Integer.parseInt(phieumuon_ed_trasach.getEditText().getText().toString()),
                                    Integer.parseInt(phieumuon_ed_tienthue.getEditText().getText().toString())
                            );

                            if(phieuMuonDAO.insert(phieuMuon)>0){
                                dialog.dismiss();
                                ((ContainerActivity)getActivity()).recreatePhieuMuon();
                            }
                            else{
                                Toast.makeText(getActivity(), "Hệ thống đang lỗi vui lòng kiểm tra lại sau", Toast.LENGTH_LONG).show();
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

        return view;
    }
}