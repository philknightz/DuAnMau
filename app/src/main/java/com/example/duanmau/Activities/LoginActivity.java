package com.example.duanmau.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.duanmau.R;

public class LoginActivity extends AppCompatActivity {
    EditText login_ed_id,login_ed_password;
    Button login_btn_dangnhap;
    Intent login_intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_ed_id = findViewById(R.id.login_ed_id);
        login_ed_password = findViewById(R.id.login_ed_password);
        login_btn_dangnhap = findViewById(R.id.login_btn_dangnhap);

        login_btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_intent = new Intent(LoginActivity.this,ContainerActivity.class);
                startActivity(login_intent);
            }
        });
    }


}