package com.example.docnhan.da_orderfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.docnhan.da_orderfood.DAO.GoiMonDAO;
import com.example.docnhan.da_orderfood.DTO.ChiTietGoiMonDTO;

/**
 * Created by DOCNHAN on 16/12/2016.
 */

public class SoLuongActivity extends AppCompatActivity implements View.OnClickListener{
    int maban,mamonan;
    Button btnDongYSL;
    EditText edThemSL;
    GoiMonDAO goiMonDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themsoluong);

        btnDongYSL=(Button)findViewById(R.id.btnDongYThemSL);
        edThemSL=(EditText)findViewById(R.id.edThemSLMonAn);

        goiMonDAO=new GoiMonDAO(this);

        Intent intent=getIntent();
        maban=intent.getIntExtra("maban",0);
        mamonan=intent.getIntExtra("mamonan",0);




        btnDongYSL.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int magoimon=(int)goiMonDAO.LayMaGoiMonTheoBan(maban,"false");
        boolean kiemtra=goiMonDAO.KiemTraMonTonTai(mamonan,magoimon);
        if(kiemtra){
                int slcu=goiMonDAO.LaySLMonAnCu(magoimon,mamonan);
                int slmoi=Integer.parseInt(edThemSL.getText().toString());
            int tongSl=slcu+slmoi;
            ChiTietGoiMonDTO chiTietGoiMonDTO = new ChiTietGoiMonDTO();
            chiTietGoiMonDTO.setMaGoiMon(magoimon);
            chiTietGoiMonDTO.setMaMonAn(mamonan);
            chiTietGoiMonDTO.setSoLuong(tongSl);


            boolean kiemtraSL=goiMonDAO.CapNhatSL(chiTietGoiMonDTO);
            if(kiemtraSL){
                Toast.makeText(this,getResources().getString(R.string.themthanhcong),Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this,getResources().getString(R.string.themthatbai),Toast.LENGTH_SHORT).show();
            }
        }else {
            int sl=Integer.parseInt(edThemSL.getText().toString());
            ChiTietGoiMonDTO chiTietGoiMonDTO = new ChiTietGoiMonDTO();
            chiTietGoiMonDTO.setMaGoiMon(magoimon);
            chiTietGoiMonDTO.setMaMonAn(mamonan);
            chiTietGoiMonDTO.setSoLuong(sl);

            boolean kiemtraSL=goiMonDAO.ThemChiTietGoiMon(chiTietGoiMonDTO);
            if(kiemtraSL){
                Toast.makeText(this,getResources().getString(R.string.themthanhcong),Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                Toast.makeText(this,getResources().getString(R.string.themthatbai),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
