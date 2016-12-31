package com.example.docnhan.da_orderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.docnhan.da_orderfood.DAO.LoaiMonAnDAO;

/**
 * Created by DOCNHAN on 14/12/2016.
 */

public class ThemLoaiThucDonActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnDongYThemLoaiThucDon;
    EditText edTenLoai;
    LoaiMonAnDAO loaiMonAnDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themloaithucdon);
        loaiMonAnDAO=new LoaiMonAnDAO(this);

        btnDongYThemLoaiThucDon=(Button)findViewById(R.id.btnDongYThemLoaiThucDon);
        edTenLoai=(EditText)findViewById(R.id.edThemLoaiThucDon);

        btnDongYThemLoaiThucDon.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        String sTenLoaiThucDon=edTenLoai.getText().toString();
        if(sTenLoaiThucDon != null || sTenLoaiThucDon.equals("")==false){
            boolean kiemTra=loaiMonAnDAO.ThemLoaiMonAn(sTenLoaiThucDon);
            Intent iDuLieu=new Intent();
            iDuLieu.putExtra("kiemtraloaithucdon",kiemTra);
            setResult(Activity.RESULT_OK,iDuLieu);
            finish();

        }else {
            Toast.makeText(this,getResources().getString(R.string.vuilongnhapdulieu),Toast.LENGTH_SHORT).show();
        }
    }
}
