package com.example.docnhan.da_orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.docnhan.da_orderfood.DTO.MonAnDTO;
import com.example.docnhan.da_orderfood.DTO.NhanVienDTO;
import com.example.docnhan.da_orderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DOCNHAN on 15/12/2016.
 */

public class MonAnDAO {
    SQLiteDatabase database;
    public  MonAnDAO(Context context){
        CreateDatabase createDatabase=new CreateDatabase(context);
        database=createDatabase.open();
    }
    public boolean ThemMonAn(MonAnDTO monAnDTO)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(CreateDatabase.TB_MONAN_TENMONAN,monAnDTO.getTenMonAn());
        contentValues.put(CreateDatabase.TB_MONAN_GIATIEN,monAnDTO.getGiaTien());
        contentValues.put(CreateDatabase.TB_MONAN_MALOAI,monAnDTO.getMaLoai());
        contentValues.put(CreateDatabase.TB_MONAN_HINHANH,monAnDTO.getHinhAnh());

        long kiemtra=database.insert(CreateDatabase.TB_MONAN,null,contentValues);
        if(kiemtra!=0) {
            return true;
        }else {
            return  false;
        }
    }
    public  boolean XoaMonAn(int mamonan){
        long kiemtra=database.delete(CreateDatabase.TB_MONAN,CreateDatabase.TB_MONAN_MAMON + " = " + mamonan,null);
        if(kiemtra!=0){
            return  true;
        }else
        {
            return  false;
        }
    }
    public boolean SuaMonAn(MonAnDTO monAnDTO){
        ContentValues contentValues = new ContentValues();

        contentValues.put(CreateDatabase.TB_MONAN_MALOAI,monAnDTO.getMaLoai());
        contentValues.put(CreateDatabase.TB_MONAN_TENMONAN,monAnDTO.getTenMonAn());
        contentValues.put(CreateDatabase.TB_MONAN_GIATIEN,monAnDTO.getGiaTien());
        contentValues.put(CreateDatabase.TB_MONAN_HINHANH,monAnDTO.getHinhAnh());

        long kiemtra=database.update(CreateDatabase.TB_MONAN,contentValues,CreateDatabase.TB_MONAN_MAMON + " = " + monAnDTO.getMaMonAN(),null);
        if(kiemtra!=0){
            return  true;
        }else
        {
            return  false;
        }
    }
    public MonAnDTO LayDanhSachMonAnTheoMa(int mamonan){
       MonAnDTO monAnDTO=new MonAnDTO();
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_MONAN + " WHERE " + CreateDatabase.TB_MONAN_MAMON + " = " + mamonan;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            monAnDTO.setMaMonAN(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MAMON)));
            monAnDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MALOAI)));
            monAnDTO.setHinhAnh(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_HINHANH)));
            monAnDTO.setGiaTien(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_GIATIEN)));
            monAnDTO.setTenMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_TENMONAN)));



            cursor.moveToNext();
        }

        return monAnDTO;
    }
    public List<MonAnDTO> LayDanhSachMonAnTheoLoai(int maLoai){
        List<MonAnDTO>monAnDTOs=new ArrayList<MonAnDTO>();

        String struVan=" SELECT * FROM " + CreateDatabase.TB_MONAN + " WHERE " + CreateDatabase.TB_MONAN_MALOAI + " = '" + maLoai + "' ";

        Cursor cursor=database.rawQuery(struVan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            MonAnDTO monAnDTO=new MonAnDTO();

            monAnDTO.setHinhAnh(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_HINHANH)) + "");
            monAnDTO.setTenMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_TENMONAN)));
            monAnDTO.setGiaTien(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_GIATIEN)));
            monAnDTO.setMaMonAN(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MAMON)));
            monAnDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MALOAI)));

            monAnDTOs.add(monAnDTO);
            cursor.moveToNext();
        }
        return monAnDTOs;
    }
}
