package com.example.docnhan.da_orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.docnhan.da_orderfood.DTO.BanAnDTO;
import com.example.docnhan.da_orderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DOCNHAN on 13/12/2016.
 */

public class    BanAnDAO {
    SQLiteDatabase database;

    public BanAnDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean ThemBanAn(String tenbanan) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TENBAN, tenbanan);
        contentValues.put(CreateDatabase.TB_BANAN_TINHTRANG, "false");

        long kiemTra = database.insert(CreateDatabase.TB_BANAN, null, contentValues);
        if (kiemTra != 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<BanAnDTO> LayTatCaBanAn() {
        List<BanAnDTO> banAnDTOList = new ArrayList<BanAnDTO>();

        String struyVan = " SELECT * FROM " + CreateDatabase.TB_BANAN;
        Cursor cursor = database.rawQuery(struyVan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BanAnDTO banAnDTO = new BanAnDTO();
            banAnDTO.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_BANAN_MABAN)));
            banAnDTO.setTenBan(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TENBAN)));
            banAnDTOList.add(banAnDTO);
            cursor.moveToNext();
        }
        return banAnDTOList;
    }

    public String LayTinhTrangBanTheoMa(int maban) {
        String stinhTrang = "";
        String struyVan = " SELECT * FROM " + CreateDatabase.TB_BANAN + " WHERE " + CreateDatabase.TB_BANAN_MABAN + " = '" + maban + " '";
        Cursor cursor = database.rawQuery(struyVan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            stinhTrang = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TINHTRANG));

            cursor.moveToNext();
        }


        return stinhTrang;
    }
    public boolean XoaBanAnTheoMa(int maban){

        long kiemtra=database.delete(CreateDatabase.TB_BANAN,CreateDatabase.TB_BANAN_MABAN + " = " +maban,null);
        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean CapNhatTenBan(int maban, String tenban) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TENBAN, tenban);
        long kiemTra = database.update(CreateDatabase.TB_BANAN, contentValues, CreateDatabase.TB_BANAN_MABAN + " = '" + maban + "'", null);
        if (kiemTra != 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean CapNhatTinhTrangBan(int maban, String tinhtrang) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TINHTRANG, tinhtrang);
        long kiemTra = database.update(CreateDatabase.TB_BANAN, contentValues, CreateDatabase.TB_BANAN_MABAN + " = '" + maban + "'", null);
        if (kiemTra != 0) {
            return true;
        } else {
            return false;
        }
    }
}
