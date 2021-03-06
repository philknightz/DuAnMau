package com.example.duanmau.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.duanmau.R;
import com.example.duanmau.View.DoanhThu.Fragment_DoanhThu;
import com.example.duanmau.View.QLLoaiSach.Fragment_QLLoaiSach;
import com.example.duanmau.View.QLPhieuMuon.Fragment_QLPhieuMuon;
import com.example.duanmau.View.QLSach.Fragment_QLSach;
import com.example.duanmau.View.QLThanhVien.Fragment_QLThanhVien;
import com.example.duanmau.View.ThemNguoiDung.Fragment_ThemNguoiDung;
import com.example.duanmau.View.XepHang.Fragment_XepHang;
import com.google.android.material.navigation.NavigationView;

public class ContainerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_fragment, new Fragment_QLPhieuMuon());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.quanlyphieumuon:{
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new Fragment_QLPhieuMuon());
            fragmentTransaction.commit();
                break;
            }
            case R.id.quanlyloaisach:{
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new Fragment_QLLoaiSach());
            fragmentTransaction.commit();
                break;
            }
            case R.id.quanlysach:{
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new Fragment_QLSach());
            fragmentTransaction.commit();
                break;
            }
            case R.id.quanlythanhvien:{
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new Fragment_QLThanhVien());
            fragmentTransaction.commit();
                break;
            }
            case R.id.xephang:{
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new Fragment_XepHang());
            fragmentTransaction.commit();
                break;
            }
            case R.id.doanhthu:{
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new Fragment_DoanhThu());
            fragmentTransaction.commit();
                break;
            }
            case R.id.themnguoidung:{
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new Fragment_ThemNguoiDung());
            fragmentTransaction.commit();
                break;
            }
            case R.id.dangxuat:{
                Intent container_intent = new Intent(ContainerActivity.this,LoginActivity.class);
                startActivity(container_intent);
                break;
            }
            default:{
                Toast.makeText(this, "Vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
                break;
            }
        }

        return true;
    }


    public void recreateThemNguoiDung(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,new Fragment_ThemNguoiDung());
        fragmentTransaction.commit();
    }

    public void recreateThanhVien(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,new Fragment_QLThanhVien());
        fragmentTransaction.commit();
    }

    public void recreateLoaiSach(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,new Fragment_QLLoaiSach());
        fragmentTransaction.commit();
    }

    public void recreateSach(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,new Fragment_QLSach());
        fragmentTransaction.commit();
    }

    public void recreatePhieuMuon(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,new Fragment_QLPhieuMuon());
        fragmentTransaction.commit();
    }
}