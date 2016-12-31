package com.example.docnhan.da_orderfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.docnhan.da_orderfood.FlagmentApp.HienThiBanAnFagment;
import com.example.docnhan.da_orderfood.FlagmentApp.HienThiNhanVienFagment;
import com.example.docnhan.da_orderfood.FlagmentApp.HienThiThucDonFagment;


/**
 * Created by DOCNHAN on 12/12/2016.
 */

public class TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView txtTenNhanVien_Navigation;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationview_trangchu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        View view = navigationView.inflateHeaderView(R.layout.layout_header_navigation_trangchu);
        txtTenNhanVien_Navigation = (TextView) view.findViewById(R.id.txtTenNhanVien_navigation);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.mo,R.string.dong){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        String tendn = intent.getStringExtra("tendn");
        txtTenNhanVien_Navigation.setText(tendn);

        fragmentManager=getSupportFragmentManager();
        FragmentTransaction tranHienThiBanAn = fragmentManager.beginTransaction();
        HienThiBanAnFagment hienThiBanAnFagment = new HienThiBanAnFagment();
        tranHienThiBanAn.replace(R.id.content,hienThiBanAnFagment);
        tranHienThiBanAn.commit();

    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itTrangchu:
                FragmentTransaction tranHienThiBanAn = fragmentManager.beginTransaction();
                HienThiBanAnFagment hienThiBanAnFagment = new HienThiBanAnFagment();
                tranHienThiBanAn.replace(R.id.content,hienThiBanAnFagment);
                tranHienThiBanAn.commit();

                item.setChecked(true);
                drawerLayout.closeDrawers();
                ;break;
            case R.id.itThucDon:
                FragmentTransaction tranHienThiThucDon = fragmentManager.beginTransaction();
                HienThiThucDonFagment hienThiThucDonFagment = new HienThiThucDonFagment();
                tranHienThiThucDon.replace(R.id.content,hienThiThucDonFagment);
                tranHienThiThucDon.commit();

                item.setChecked(true);
                drawerLayout.closeDrawers();

                ;break;
            case  R.id.itNhanVien:
                FragmentTransaction tranNhanVien=fragmentManager.beginTransaction();
                HienThiNhanVienFagment hienThiNhanVienFagment=new HienThiNhanVienFagment();
                tranNhanVien.replace(R.id.content,hienThiNhanVienFagment);
                tranNhanVien.commit();

                item.setChecked(true);
                drawerLayout.closeDrawers();
                ;break;
        }
        return false;
    }
}


