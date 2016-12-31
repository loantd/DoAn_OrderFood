package com.example.docnhan.da_orderfood.FlagmentApp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.example.docnhan.da_orderfood.CustomAdapter.AdapterHienThiBanAn;
import com.example.docnhan.da_orderfood.DAO.BanAnDAO;
import com.example.docnhan.da_orderfood.DTO.BanAnDTO;
import com.example.docnhan.da_orderfood.R;
import com.example.docnhan.da_orderfood.SuaBanAnActivity;
import com.example.docnhan.da_orderfood.ThemBanAnActivity;
import com.example.docnhan.da_orderfood.TrangChuActivity;

import java.util.List;

/**
 * Created by DOCNHAN on 13/12/2016.
 */

public class HienThiBanAnFagment extends Fragment {

    public  static int RESQUEST_CODE_THEM=111;
    public  static int RESQUEST_CODE_SUA=112;
    GridView gvHienThiBanAn;
    List<BanAnDTO> banAnDTOList;
    BanAnDAO banAnDAO;
    AdapterHienThiBanAn adapterHienThiBanAn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.layout_hienthibanan,container,false);
        setHasOptionsMenu(true);
        ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.banan);
        gvHienThiBanAn=(GridView)view.findViewById(R.id.gvHienThiBanAn);


        banAnDAO=new BanAnDAO(getActivity());
        banAnDTOList = banAnDAO.LayTatCaBanAn();

        HienThiDanhSachGV();

        registerForContextMenu(gvHienThiBanAn);

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
        int maban=banAnDTOList.get(vitri).getMaBan();

        switch (id){
            case R.id.itSua:
                Intent intent=new Intent(getActivity(), SuaBanAnActivity.class);
                intent.putExtra("maban",maban);
                startActivityForResult(intent,RESQUEST_CODE_SUA);


                ;break;
            case R.id.itXoa:
                boolean kiemtra=banAnDAO.XoaBanAnTheoMa(maban);
                if(kiemtra){
                    HienThiDanhSachGV();
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.xoadulieu),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.Xoathatbai)+maban,Toast.LENGTH_SHORT).show();
                }


                ;break;
        }
        return super.onContextItemSelected(item);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itThemBanAn=menu.add(1,R.id.itThemBanAn,1,R.string.thembanan);
        itThemBanAn.setIcon(R.drawable.thembanan);
        itThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        switch (id){
            case R.id.itThemBanAn:
                Intent iThemBanAn=new Intent(getActivity(), ThemBanAnActivity.class);
                startActivityForResult(iThemBanAn,RESQUEST_CODE_THEM);
                ;break;
        }

        return true;
    }
    private void HienThiDanhSachGV(){
        banAnDTOList=banAnDAO.LayTatCaBanAn();
        adapterHienThiBanAn=new AdapterHienThiBanAn(getActivity(),R.layout.custom_layout_hienthibanan,banAnDTOList);
        gvHienThiBanAn.setAdapter(adapterHienThiBanAn);
        adapterHienThiBanAn.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESQUEST_CODE_THEM){
            if(resultCode== Activity.RESULT_OK){
                Intent intent=data;
                boolean kiemtra=intent.getBooleanExtra("ketquathem",false);
                if (kiemtra){

                    HienThiDanhSachGV();
                    Toast.makeText(getActivity(),getResources().getString(R.string.themthanhcong),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.themthatbai),Toast.LENGTH_SHORT).show();
                }
            }
        }else if(requestCode==RESQUEST_CODE_SUA){
            if(resultCode==Activity.RESULT_OK){
                Intent intent=data;
                boolean kiemtra=intent.getBooleanExtra("kiemtra",false);
                HienThiDanhSachGV();
                if(kiemtra){
                        Toast.makeText(getActivity(),getResources().getString(R.string.suadulieu),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.suathatbai),Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
