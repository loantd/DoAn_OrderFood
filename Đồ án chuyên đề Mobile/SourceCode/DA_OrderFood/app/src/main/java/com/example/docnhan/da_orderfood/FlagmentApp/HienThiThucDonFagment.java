package com.example.docnhan.da_orderfood.FlagmentApp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.docnhan.da_orderfood.CustomAdapter.AdapterHienThiLoaiMonAnThucDon;
import com.example.docnhan.da_orderfood.DAO.LoaiMonAnDAO;
import com.example.docnhan.da_orderfood.DTO.LoaiMonAnDTO;
import com.example.docnhan.da_orderfood.R;
import com.example.docnhan.da_orderfood.ThemMonAnActivity;
import com.example.docnhan.da_orderfood.TrangChuActivity;

import java.util.List;

/**
 * Created by DOCNHAN on 14/12/2016.
 */

public class HienThiThucDonFagment extends Fragment {

    GridView gridView;
    List<LoaiMonAnDTO>loaiMonAnDTOs;
    LoaiMonAnDAO loaiMonAnDAO;
    FragmentManager fragmentManager;
    int maban;
    int maloaimonan;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthithucdon, container, false);
        setHasOptionsMenu(true);
        ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.thucdon);

        gridView= (GridView) view.findViewById(R.id.gvHienThiThucDon);

        fragmentManager=getActivity().getSupportFragmentManager();

        loaiMonAnDAO=new LoaiMonAnDAO(getActivity());
        HienThiThucDon();
        Bundle bDuLieuThucDon=getArguments();
        if(bDuLieuThucDon != null){
             maban=bDuLieuThucDon.getInt("maban");

        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int sMaLoai=loaiMonAnDTOs.get(position).getMaLoai();

                HienThiDanhSachMonAnFagment hienThiDanhSachMonAnFagment=new HienThiDanhSachMonAnFagment();
                Bundle bundle=new Bundle();
                bundle.putInt("maloai",sMaLoai);
                bundle.putInt("maban",maban);
                hienThiDanhSachMonAnFagment.setArguments(bundle);
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.content, hienThiDanhSachMonAnFagment).addToBackStack("hienthiloai");

                transaction.commit();
            }
        });

        registerForContextMenu(gridView);
        return view;

    }
    public void HienThiThucDon(){
        loaiMonAnDTOs=loaiMonAnDAO.LayDanhSachLoaiMonAn();
        AdapterHienThiLoaiMonAnThucDon adapter=new AdapterHienThiLoaiMonAnThucDon(getActivity(),R.layout.custom_layout_hienthiloaimonan,loaiMonAnDTOs);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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
        maloaimonan=loaiMonAnDTOs.get(vitri).getMaLoai();
        switch (id){
            case R.id.itSua:
                ;break;
            case R.id.itXoa:
                boolean kiemtra=loaiMonAnDAO.XoaMaLoai(maloaimonan);
                if(kiemtra){
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.xoadulieu),Toast.LENGTH_SHORT).show();
                    HienThiThucDon();
                }else {
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.Xoathatbai),Toast.LENGTH_SHORT).show();
                }

                ;break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itThemBanAn=menu.add(1,R.id.itThemThucDon,1,R.string.thembanan);
        itThemBanAn.setIcon(R.drawable.thembanan);
        itThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.itThemThucDon:
                Intent iThemThucDon=new Intent(getActivity(), ThemMonAnActivity.class);
                startActivity(iThemThucDon);
                ;break;
        }
        return super.onOptionsItemSelected(item);
    }
}

