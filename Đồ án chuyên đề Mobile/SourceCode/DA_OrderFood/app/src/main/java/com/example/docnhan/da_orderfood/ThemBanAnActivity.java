package com.example.docnhan.da_orderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.docnhan.da_orderfood.DAO.BanAnDAO;

/**
 * Created by DOCNHAN on 13/12/2016.
 */

public class ThemBanAnActivity extends AppCompatActivity implements View.OnClickListener {


    EditText edThemTenBanAn;
    Button btnDongYThemBanAn;
    BanAnDAO banAnDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thembanan);

        edThemTenBanAn=(EditText)findViewById(R.id.edThemTenBanAn);
        btnDongYThemBanAn=(Button)findViewById(R.id.btnDongYThemBanAn);

        banAnDAO=new BanAnDAO(this);
        btnDongYThemBanAn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String sTenBanAn=edThemTenBanAn.getText().toString();
        if(sTenBanAn!= null || sTenBanAn.equals("")){
           boolean kiemtra= banAnDAO.ThemBanAn(sTenBanAn);
            Intent intent=new Intent();
            intent.putExtra("ketquathem",kiemtra);
            setResult(Activity.RESULT_OK,intent);
            finish();

        }
    }
}
