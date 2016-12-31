package com.example.docnhan.da_orderfood.DTO;

/**
 * Created by DOCNHAN on 14/12/2016.
 */

public class LoaiMonAnDTO {
    int MaLoai;
    String TenLoai;

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    String HinhAnh;

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }
}
