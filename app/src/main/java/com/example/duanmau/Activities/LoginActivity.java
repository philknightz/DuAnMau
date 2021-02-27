package com.example.duanmau.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.Object.ThuThu;
import com.example.duanmau.R;
import com.example.duanmau.SQLite.DAO.ThuThuDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout login_ed_matt,login_ed_matkhau;
    Button login_btn_dangnhap;
    Intent login_intent;
    ThuThuDAO thuThuDAO;
    TextView login_error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_ed_matt = findViewById(R.id.login_ed_matt);
        login_ed_matkhau = findViewById(R.id.login_ed_matkhau);
        login_btn_dangnhap = findViewById(R.id.login_btn_dangnhap);
        login_error = findViewById(R.id.login_err);

        thuThuDAO = new ThuThuDAO(LoginActivity.this);
        thuThuDAO.insert(new ThuThu("kiendtph12640","Kien","123456"));
        login_ed_matt.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                login_ed_matt.setError("");
                login_error.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        login_ed_matkhau.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                login_ed_matkhau.setError("");
                login_error.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        login_btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThuThu thuThu = thuThuDAO.getOneThuThu(login_ed_matt.getEditText().getText().toString());
                int err=0;

                if(login_ed_matt.getEditText().getText().toString().trim().matches("")){
                    login_ed_matt.setError("Vui lòng nhập vào tên đăng nhập");
                    err+=1;
                }
                if(login_ed_matkhau.getEditText().getText().toString().trim().matches("")){
                    login_ed_matkhau.setError("Vui lòng nhập vào tên đăng nhập");
                    err+=1;
                }

                if(err>0){
                    Toast.makeText(LoginActivity.this, "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    if (thuThu.getMatKhau().matches(login_ed_matkhau.getEditText().getText().toString())){
                        login_intent = new Intent(LoginActivity.this,ContainerActivity.class);
                        startActivity(login_intent);
                    }else{
                        login_error.setText("Tên đăng nhập hoặc mật khẩu không đúng");
                    }
                }

            }
        });
    }


}