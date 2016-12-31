package com.example.docnhan.da_orderfood.DTO;

/**
 * Created by DOCNHAN on 14/12/2016.
 */

public class BanAnDTO {
    int MaBan;
    String TenBan;
    boolean duocChon;

    public boolean isDuocChon() {
        return duocChon;
    }

    public void setDuocChon(boolean duocChon) {
        this.duocChon = duocChon;
    }

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int maBan) {
        MaBan = maBan;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String tenBan) {
        TenBan = tenBan;
    }
}
