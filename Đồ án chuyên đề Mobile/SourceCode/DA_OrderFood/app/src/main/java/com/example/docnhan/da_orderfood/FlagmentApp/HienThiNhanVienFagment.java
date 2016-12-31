package com.example.docnhan.da_orderfood.FlagmentApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.docnhan.da_orderfood.CustomAdapter.AdapterHienThiNhanVien;
import com.example.docnhan.da_orderfood.DAO.NhanVienDAO;
import com.example.docnhan.da_orderfood.DTO.NhanVienDTO;
import com.example.docnhan.da_orderfood.DangKyActivity;
import com.example.docnhan.da_orderfood.R;
import com.example.docnhan.da_orderfood.TrangChuActivity;

import java.util.List;

/**
 * Created by DOCNHAN on 29/12/2016.
 */

public class HienThiNhanVienFagment extends Fragment {
    ListView listNhanVien;
    NhanVienDAO nhanVienDAO;
    List<NhanVienDTO> nhanVienDTOList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthinhanvien, container, false);
        setHasOptionsMenu(true);
        ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.nhanvien);
        listNhanVien = (ListView) view.findViewById(R.id.idlistViewNhanVien);


        nhanVienDAO = new NhanVienDAO(getActivity());

        HienThiDanhSachNhanVien();

        registerForContextMenu(listNhanVien);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.edit_context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int id=item.getItemId();
        AdapterView.AdapterContextMenuInfo menuInfo= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int vitri=menuInfo.position;
        int manhanvien=nhanVienDTOList.get(vitri).getMANV();
        switch (id){
            case R.id.itSua:
                Intent iDangKy=new Intent(getActivity(),DangKyActivity.class);
                iDangKy.putExtra("manv",manhanvien);
                startActivity(iDangKy);
                ;break;

            case R.id.itXoa:
                boolean kiemtra=nhanVienDAO.XoaNhanVien(manhanvien);
                if(kiemtra){
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.xoadulieu),Toast.LENGTH_SHORT).show();
                    HienThiDanhSachNhanVien();
                }else {
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.Xoathatbai),Toast.LENGTH_SHORT).show();
                }

                ;break;
        }
        return true;
    }

    private void HienThiDanhSachNhanVien() {
        nhanVienDTOList = nhanVienDAO.LayDanhSachNhanVien();

        AdapterHienThiNhanVien adapterHienThiNhanVien = new AdapterHienThiNhanVien(getActivity(), R.layout.custom_layout_hienthinhanvien, nhanVienDTOList);
        listNhanVien.setAdapter(adapterHienThiNhanVien);
        adapterHienThiNhanVien.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itThemBanAn=menu.add(1,R.id.itThemNhanVien,1,R.string.themnhanvien);
        itThemBanAn.setIcon(R.drawable.nhanvien);
        itThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.itThemNhanVien:
                Intent iDangky=new Intent(getActivity(), DangKyActivity.class);
                startActivity(iDangky);
                ;break;
        }
        return true;
    }
}
