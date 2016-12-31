package com.example.docnhan.da_orderfood.DTO;

/**
 * Created by DOCNHAN on 16/12/2016.
 */

public class ThanhToanDTO {
    String TenMonAn;
    int SoLuong;

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int giaTien) {
        GiaTien = giaTien;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }

    int GiaTien;
}
