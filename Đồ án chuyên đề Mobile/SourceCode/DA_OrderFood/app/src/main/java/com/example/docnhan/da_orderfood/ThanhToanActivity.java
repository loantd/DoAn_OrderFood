package com.example.docnhan.da_orderfood;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.docnhan.da_orderfood.CustomAdapter.AdapterHienThiThanhToan;
import com.example.docnhan.da_orderfood.DAO.BanAnDAO;
import com.example.docnhan.da_orderfood.DAO.GoiMonDAO;
import com.example.docnhan.da_orderfood.DTO.ThanhToanDTO;
import com.example.docnhan.da_orderfood.FlagmentApp.HienThiBanAnFagment;

import java.util.List;

/**
 * Created by DOCNHAN on 16/12/2016.
 */

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtTongTien;
    GridView gridView;
    Button btnThanhToan,btnThoat;
    GoiMonDAO goiMonDAO;
    List<ThanhToanDTO>thanhToanDTOs;
    AdapterHienThiThanhToan adapterHienThiThanhToan;
    long tongtien=0;
    BanAnDAO banAnDAO;
    int maban;

    FragmentManager fragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);

        gridView= (GridView) findViewById(R.id.gvThanhToan);
        btnThanhToan= (Button) findViewById(R.id.btnThanhToan);
        btnThoat=(Button)findViewById(R.id.btnThoatThanhToan);
        txtTongTien=(TextView)findViewById(R.id.txtTongTien);

        goiMonDAO=new GoiMonDAO(this);
        banAnDAO=new BanAnDAO(this);

        fragmentManager=getSupportFragmentManager();

        maban=getIntent().getIntExtra("maban",0);
        if(maban!=0){

            HienThiThanhtoan();
            for(int i=0;i<thanhToanDTOs.size();i++){
                int sl=thanhToanDTOs.get(i).getSoLuong();
                int giatien=thanhToanDTOs.get(i).getGiaTien();
                tongtien+=(sl*giatien);
            }

            txtTongTien.setText(getResources().getString(R.string.tongtien) +     tongtien  + "   VNĐ");

        }

        btnThanhToan.setOnClickListener(this);
        btnThoat.setOnClickListener(this);

    }
    private  void HienThiThanhtoan(){
        int magoimon= (int) goiMonDAO.LayMaGoiMonTheoBan(maban,"false");
        thanhToanDTOs=goiMonDAO.LayDanhSachMonAnTheoMaGoiMon(magoimon);
        adapterHienThiThanhToan=new AdapterHienThiThanhToan(this,R.layout.custom_layout_thanhtoan,thanhToanDTOs);
        gridView.setAdapter(adapterHienThiThanhToan);
        adapterHienThiThanhToan.notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {
            int id=v.getId();
        switch (id){
            case R.id.btnThanhToan:
                boolean kiemtraBanAn=banAnDAO.CapNhatTinhTrangBan(maban,"false");
               boolean kiemtragoimon= goiMonDAO.CapNhatTrangThaiGoiMonTheoMaBan(maban,"true");
                if(kiemtraBanAn && kiemtragoimon){
                    Toast.makeText(ThanhToanActivity.this,"Thanh toán thành công !",Toast.LENGTH_SHORT).show();

                    HienThiThanhtoan();
                }else {
                    Toast.makeText(ThanhToanActivity.this,"Thanh toán thất bại !",Toast.LENGTH_SHORT).show();
                }
                ;break;
            case R.id.btnThoatThanhToan:
                    finish();
                ;break;
        }
    }
}
