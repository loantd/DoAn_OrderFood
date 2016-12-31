package com.example.docnhan.da_orderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.docnhan.da_orderfood.CustomAdapter.AdapterHienThiLoaiMonAn;
import com.example.docnhan.da_orderfood.DAO.LoaiMonAnDAO;
import com.example.docnhan.da_orderfood.DAO.MonAnDAO;
import com.example.docnhan.da_orderfood.DTO.LoaiMonAnDTO;
import com.example.docnhan.da_orderfood.DTO.MonAnDTO;

import java.util.List;

/**
 * Created by DOCNHAN on 14/12/2016.
 */

public class ThemMonAnActivity extends AppCompatActivity implements View.OnClickListener{
    public  static int RESQUES_CODE_THEMLOAITHUCDON=113;
    public  static int RESQUES_CODE_MOHINH=123;
    Button btnDongYThemMonAn,btnThoatThemMonAn;
    ImageButton imThemLoaiThucDon;
    Spinner spinLoaiThucDon;
    LoaiMonAnDAO loaiMonAnDAO;
    MonAnDAO monAnDAO;
    List<LoaiMonAnDTO>loaiMonAnDTOs;
    AdapterHienThiLoaiMonAn adapterHienThiLoaiMonAn;
    ImageView imHinhThucDon;
    String sduongdanHinh;
    EditText edTenMonAn,edGiaTien;
    TextView txtThemThucDon;
    int mamonan;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themmonan);

        loaiMonAnDAO=new LoaiMonAnDAO(this);
        monAnDAO=new MonAnDAO(this);

        btnThoatThemMonAn=(Button)findViewById(R.id.btnThoatThemMonAn);
        btnDongYThemMonAn=(Button)findViewById(R.id.btnDongYThemMonAn);
        edTenMonAn=(EditText)findViewById(R.id.edThemTenMonAn);
        edGiaTien=(EditText)findViewById(R.id.edThemGiaTien);
        txtThemThucDon=(TextView)findViewById(R.id.txtThemThucDon);

        imThemLoaiThucDon=(ImageButton)findViewById(R.id.imThemLoaiThucDon);
        spinLoaiThucDon=(Spinner)findViewById(R.id.spinLoaiMonAn);
        imHinhThucDon=(ImageView)findViewById(R.id.imHinhThucDon);

        HienThiSpinerLoaiMonAn();
        imThemLoaiThucDon.setOnClickListener(this);
        btnDongYThemMonAn.setOnClickListener(this);
        btnThoatThemMonAn.setOnClickListener(this);
        imHinhThucDon.setOnClickListener(this);

        mamonan=getIntent().getIntExtra("mamonan",0);
        if(mamonan!=0){
            txtThemThucDon.setText("Cập nhật món ăn");
           MonAnDTO monAnDTO=monAnDAO.LayDanhSachMonAnTheoMa(mamonan);
            edTenMonAn.setText(monAnDTO.getTenMonAn());
            edGiaTien.setText(monAnDTO.getGiaTien());


        }

    }

    private void HienThiSpinerLoaiMonAn() {
        loaiMonAnDTOs =loaiMonAnDAO.LayDanhSachLoaiMonAn();
        adapterHienThiLoaiMonAn=new AdapterHienThiLoaiMonAn(ThemMonAnActivity.this,R.layout.layout_custom_loaithucdon_spiner,loaiMonAnDTOs);
        spinLoaiThucDon.setAdapter(adapterHienThiLoaiMonAn);
        adapterHienThiLoaiMonAn.notifyDataSetChanged();
    }
    public void ThemMonAn(){
        int vitri=spinLoaiThucDon.getSelectedItemPosition();
        int maLoai=loaiMonAnDTOs.get(vitri).getMaLoai();
        String sTenMonAn=edTenMonAn.getText().toString();
        String sGiaTien=edGiaTien.getText().toString();

        if(sTenMonAn!=null && sGiaTien!=null && !sTenMonAn.equals("") && !sGiaTien.equals("")){
            MonAnDTO monAnDTO=new MonAnDTO();
            monAnDTO.setGiaTien(sGiaTien);
            monAnDTO.setTenMonAn(sTenMonAn);
            monAnDTO.setHinhAnh(sduongdanHinh);
            monAnDTO.setMaLoai(maLoai);
            boolean kiemTra=monAnDAO.ThemMonAn(monAnDTO);
            if(kiemTra){
                Toast.makeText(this,getResources().getString(R.string.themthanhcong),Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,getResources().getString(R.string.themthatbai),Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this,getResources().getString(R.string.loithemmonan),Toast.LENGTH_SHORT).show();
        }
    }
    public  void SuaMonAn(){
        int vitri=spinLoaiThucDon.getSelectedItemPosition();
        int maLoai=loaiMonAnDTOs.get(vitri).getMaLoai();
        String sTenMonAn=edTenMonAn.getText().toString();
        String sGiaTien=edGiaTien.getText().toString();

            MonAnDTO monAnDTO=new MonAnDTO();
            monAnDTO.setMaMonAN(mamonan);
            monAnDTO.setGiaTien(sGiaTien);
            monAnDTO.setTenMonAn(sTenMonAn);
            monAnDTO.setHinhAnh(sduongdanHinh);
            monAnDTO.setMaLoai(maLoai);
            boolean kiemTra=monAnDAO.SuaMonAn(monAnDTO);
            if(kiemTra){
                Toast.makeText(ThemMonAnActivity.this,getResources().getString(R.string.suadulieu),Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(ThemMonAnActivity.this,getResources().getString(R.string.suathatbai),Toast.LENGTH_SHORT).show();
            }
    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.imThemLoaiThucDon:
                Intent iThemLoaiMonAn=new Intent(ThemMonAnActivity.this,ThemLoaiThucDonActivity.class);
                startActivityForResult(iThemLoaiMonAn,RESQUES_CODE_THEMLOAITHUCDON);

                ;break;
            case R.id.imHinhThucDon:
                Intent iMohinh=new Intent();
                iMohinh.setType("image/*");
                iMohinh.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(iMohinh,"Chon hinh thuc don"),RESQUES_CODE_MOHINH);
                ;break;
            case R.id.btnDongYThemMonAn:
                if(mamonan != 0){
                    SuaMonAn();
                }else {
                    ThemMonAn();
                }
                ;break;
            case R.id.btnThoatThemMonAn:

                finish();
                ;break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESQUES_CODE_THEMLOAITHUCDON){
            if(resultCode== Activity.RESULT_OK){
                Intent dulieu=data;
                boolean kiemtra=dulieu.getBooleanExtra("kiemtraloaithucdon",false);
                if(kiemtra){
                    HienThiSpinerLoaiMonAn();
                    Toast.makeText(this,getResources().getString(R.string.themthanhcong),Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(this,getResources().getString(R.string.themthatbai),Toast.LENGTH_SHORT).show();
                }
            }
        }else if(requestCode == RESQUES_CODE_MOHINH){
            if(resultCode==Activity.RESULT_OK && data!=null){
               sduongdanHinh=data.getData().toString();
                imHinhThucDon.setImageURI(data.getData());
            }
        }
    }
}
