package com.example.docnhan.da_orderfood.FlagmentApp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.docnhan.da_orderfood.CustomAdapter.AdapterHienThiDanhSachMonAn;
import com.example.docnhan.da_orderfood.DAO.MonAnDAO;
import com.example.docnhan.da_orderfood.DTO.MonAnDTO;
import com.example.docnhan.da_orderfood.R;
import com.example.docnhan.da_orderfood.SoLuongActivity;
import com.example.docnhan.da_orderfood.ThemMonAnActivity;

import java.util.List;

/**
 * Created by DOCNHAN on 15/12/2016.
 */

public class HienThiDanhSachMonAnFagment extends Fragment {

    GridView gridView;
    MonAnDAO monAnDAO;
    List<MonAnDTO>monAnDTOs;
    int maban;
    int maloai;
    AdapterHienThiDanhSachMonAn adapterHienThiDanhSachMonAn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthithucdon, container, false);

        gridView= (GridView) view.findViewById(R.id.gvHienThiThucDon);

        monAnDAO=new MonAnDAO(getActivity());


        Bundle bundle=getArguments();
        if(bundle!=null){
            maloai=bundle.getInt("maloai");
            maban=bundle.getInt("maban");
           HienThiDanhSachMonAn();

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(maban!=0){
                        Intent iSoLuong=new Intent(getActivity(), SoLuongActivity.class);
                        iSoLuong.putExtra("maban",maban);
                        iSoLuong.putExtra("mamonan",monAnDTOs.get(position).getMaMonAN());
                        startActivity(iSoLuong);
                    }

                }
            });

        }




        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()==KeyEvent.ACTION_DOWN){
                    getFragmentManager().popBackStack("hienthiloai", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });

        registerForContextMenu(gridView);
        return view;

        }
    public void HienThiDanhSachMonAn(){
        monAnDTOs=monAnDAO.LayDanhSachMonAnTheoLoai(maloai);
        adapterHienThiDanhSachMonAn=new AdapterHienThiDanhSachMonAn(getActivity(),R.layout.custom_layout_hienthidanhsachmonan,monAnDTOs);
        gridView.setAdapter(adapterHienThiDanhSachMonAn);
        adapterHienThiDanhSachMonAn.notifyDataSetChanged();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.edit_context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int vitri = menuInfo.position;
        int mamonan = monAnDTOs.get(vitri).getMaMonAN();
            switch (id){
                case R.id.itSua:
                    Intent iMonAn=new Intent(getActivity(), ThemMonAnActivity.class);
                    iMonAn.putExtra("mamonan",mamonan);
                    startActivity(iMonAn);

                    ;break;

                case R.id.itXoa:
                    boolean kiemtra=monAnDAO.XoaMonAn(mamonan);
                    if(kiemtra){
                        Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.xoadulieu),Toast.LENGTH_SHORT).show();
                        HienThiDanhSachMonAn();
                    }else {
                        Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.Xoathatbai),Toast.LENGTH_SHORT).show();
                    }

                    ;break;
            }

            return super.onContextItemSelected(item);
        }

}
