package com.example.docnhan.da_orderfood.DTO;

/**
 * Created by DOCNHAN on 15/12/2016.
 */

public class MonAnDTO {
    int MaMonAN,MaLoai;
    String TenMonAn,GiaTien,HinhAnh;

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getMaMonAN() {

        return MaMonAN;
    }

    public void setMaMonAN(int maMonAN) {
        MaMonAN = maMonAN;
    }

    public String getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(String giaTien) {
        GiaTien = giaTien;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }
}
