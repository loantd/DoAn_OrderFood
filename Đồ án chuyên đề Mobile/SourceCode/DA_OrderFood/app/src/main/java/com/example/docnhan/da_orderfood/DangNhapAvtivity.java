package com.example.docnhan.da_orderfood;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.docnhan.da_orderfood.DAO.NhanVienDAO;

/**
 * Created by DOCNHAN on 12/12/2016.
 */

public class DangNhapAvtivity extends AppCompatActivity implements View.OnClickListener{
    Button btnDongYDN,btnDangKyDN;
    EditText edTenDangNhap,edMatKhau;
    NhanVienDAO nhanVienDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        btnDongYDN=(Button)findViewById(R.id.btnDangNhapDN);
        btnDangKyDN=(Button)findViewById(R.id.btnDangKyDN);

        edTenDangNhap=(EditText)findViewById(R.id.edTenDangNhapDN);
        edMatKhau=(EditText)findViewById(R.id.edMatKhauDN);
        nhanVienDAO =new NhanVienDAO(this);
        btnDongYDN.setOnClickListener(this);
        btnDangKyDN.setOnClickListener(this);

        HienThiBTNDangKy();

    }
    public void HienThiBTNDangKy(){
        boolean kiemtra=nhanVienDAO.KiemTraNhanVien();
        if(kiemtra){
            btnDangKyDN.setVisibility(View.GONE);
            btnDongYDN.setVisibility(View.VISIBLE);
        }else {
            btnDangKyDN.setVisibility(View.VISIBLE);
            btnDongYDN.setVisibility(View.GONE);
        }
    }
    private void btnDongYDN(){
        String sTenDangNhap=edTenDangNhap.getText().toString();
        String sMatKhau=edMatKhau.getText().toString();
        int kiemTra= nhanVienDAO.KiemTraDangNhap(sTenDangNhap,sMatKhau);
        if(kiemTra !=0){
            Intent iTrangchu=new Intent(DangNhapAvtivity.this,TrangChuActivity.class);
            iTrangchu.putExtra("tendn",edTenDangNhap.getText().toString());
            iTrangchu.putExtra("manhanvien",kiemTra);
            startActivity(iTrangchu);
            Toast.makeText(DangNhapAvtivity.this,getResources().getString(R.string.dangnhapThanhCong),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(DangNhapAvtivity.this,getResources().getString(R.string.dangnhapThatBai),Toast.LENGTH_SHORT).show();
        }
    }
    private void btnDangKy(){
        Intent iDangKy=new Intent(DangNhapAvtivity.this,DangKyActivity.class);
        startActivity(iDangKy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        HienThiBTNDangKy();
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.btnDangNhapDN:
                btnDongYDN();
                ;break;
            case R.id.btnDangKyDN:
                    btnDangKy();
                ;break;
        }
    }
}
