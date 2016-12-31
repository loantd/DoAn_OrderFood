package com.example.docnhan.da_orderfood;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.docnhan.da_orderfood.CustomAdapter.AdapterHienThiNhanVien;
import com.example.docnhan.da_orderfood.DAO.NhanVienDAO;
import com.example.docnhan.da_orderfood.DTO.NhanVienDTO;
import com.example.docnhan.da_orderfood.FlagmentApp.DatePickerFragment;
import com.example.docnhan.da_orderfood.FlagmentApp.HienThiNhanVienFagment;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener,View.OnFocusChangeListener {

    EditText edTenDangNhapDK,edMatKhauDK,edNgaySinhDK,edCMNDDK;
    Button btnDongYDK,btnThoatDK;
    TextView txtTieuDeDangKy;
    RadioGroup rgGioiTinh;
    RadioButton rdNam,rdNu;
    String sGioiTinh;
    NhanVienDAO nhanVienDAO;
    FragmentManager fragmentManager;
    int manv=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

        edTenDangNhapDK=(EditText)findViewById(R.id.edTenDangNhapDK);
        edMatKhauDK=(EditText)findViewById(R.id.edMatKhauDK);
        edNgaySinhDK=(EditText)findViewById(R.id.edNgaySinhDK);
        edCMNDDK=(EditText)findViewById(R.id.edCMNDDK);
        txtTieuDeDangKy=(TextView) findViewById(R.id.txtTieuDeDangKy);
        rdNam=(RadioButton)findViewById(R.id.rdNam);
        rdNu=(RadioButton)findViewById(R.id.rdNu);

        btnDongYDK=(Button)findViewById(R.id.btnDongYDK);
        btnThoatDK=(Button)findViewById(R.id.btnThoatDK);
        rgGioiTinh=(RadioGroup)findViewById(R.id.rgGioiTinh);

        btnThoatDK.setOnClickListener(this);
        btnDongYDK.setOnClickListener(this);
        edNgaySinhDK.setOnFocusChangeListener(this);
        nhanVienDAO=new NhanVienDAO(this);

        manv=getIntent().getIntExtra("manv",0);
        if(manv!=0){
            txtTieuDeDangKy.setText(getResources().getString(R.string.capnhatnhanvien));
            NhanVienDTO nhanVienDTO=nhanVienDAO.LayDanhSachNhanVienTheoMa(manv);

            edTenDangNhapDK.setText(nhanVienDTO.getTENDN());
            edMatKhauDK.setText(nhanVienDTO.getMATKHAU());
            edNgaySinhDK.setText(nhanVienDTO.getNGAYSINH());
            edCMNDDK.setText(String.valueOf(nhanVienDTO.getCMND()));

            String gioitinh=nhanVienDTO.getGIOITINH();
            if(gioitinh.equals("Nam")){
                rdNam.setChecked(true);
            }else {
                rdNu.setChecked(true);
            }

        }
    }
    private void DongYThemNhanVien(){
        String sTenDangNhap = edTenDangNhapDK.getText().toString();
        String sMatKhau = edMatKhauDK.getText().toString();
        switch (rgGioiTinh.getCheckedRadioButtonId()){
            case R.id.rdNam:
                sGioiTinh = "Nam";
                break;

            case R.id.rdNu:
                sGioiTinh = "Nữ";
                break;
        }
        String sNgaySinh = edNgaySinhDK.getText().toString();
        int sCMND = Integer.parseInt(edCMNDDK.getText().toString());

        if(sTenDangNhap == null || sTenDangNhap.equals(" ")){
            Toast.makeText(DangKyActivity.this,getResources().getString(R.string.loinhaptendangnhap), Toast.LENGTH_SHORT).show();
        }else if(sMatKhau == null || sMatKhau.equals(" ")){
            Toast.makeText(DangKyActivity.this,getResources().getString(R.string.loinhapmatkhau), Toast.LENGTH_SHORT).show();
        }else{
            NhanVienDTO nhanVienDTO = new NhanVienDTO();
            nhanVienDTO.setTENDN(sTenDangNhap);
            nhanVienDTO.setMATKHAU(sMatKhau);
            nhanVienDTO.setCMND(sCMND);
            nhanVienDTO.setNGAYSINH(sNgaySinh);
            nhanVienDTO.setGIOITINH(sGioiTinh);

            long kiemtra = nhanVienDAO.ThemNhanVien(nhanVienDTO);
            if(kiemtra != 0){
                Toast.makeText(DangKyActivity.this,getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(DangKyActivity.this,getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public  void SuaNhanVien(){

        String sTenDangNhap = edTenDangNhapDK.getText().toString();
        String sMatKhau = edMatKhauDK.getText().toString();
        int sCMND = Integer.parseInt(edCMNDDK.getText().toString());
        String sNgaySinh = edNgaySinhDK.getText().toString();
        switch (rgGioiTinh.getCheckedRadioButtonId()){
            case R.id.rdNam:
                sGioiTinh = "Nam";
                break;

            case R.id.rdNu:
                sGioiTinh = "Nữ";
                break;
        }
        NhanVienDTO nhanVienDTO=new NhanVienDTO();
        nhanVienDTO.setMANV(manv);
        nhanVienDTO.setTENDN(sTenDangNhap);
        nhanVienDTO.setMATKHAU(sMatKhau);
        nhanVienDTO.setCMND(sCMND);
        nhanVienDTO.setNGAYSINH(sNgaySinh);
        nhanVienDTO.setGIOITINH(sGioiTinh);
        boolean kiemtra=nhanVienDAO.SuaNhanVien(nhanVienDTO);
        if(kiemtra){
            Toast.makeText(DangKyActivity.this,getResources().getString(R.string.suadulieu),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(DangKyActivity.this,getResources().getString(R.string.suathatbai),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnDongYDK:
                if(manv != 0){
                    //thua hien sua
                    SuaNhanVien();
                }else {
                    //thuc hien them moi
                    DongYThemNhanVien();
                }

                ;break;

            case R.id.btnThoatDK:


                finish()

                ;break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if(hasFocus){
            DatePickerFragment datePickerFragment=new DatePickerFragment();
            datePickerFragment.show(getFragmentManager(),"Ngay Sinh");
        }
    }
}
