package com.example.docnhan.da_orderfood.CustomAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.docnhan.da_orderfood.DTO.LoaiMonAnDTO;
import com.example.docnhan.da_orderfood.R;

import java.util.List;

/**
 * Created by DOCNHAN on 14/12/2016.
 */

public class AdapterHienThiLoaiMonAn extends BaseAdapter {
    Context context;
    int layout;
    List<LoaiMonAnDTO>loaiMonAnDTOs;
    ViewHolderLoaiMonAn viewHolderLoaiMonAn;

    public AdapterHienThiLoaiMonAn(Context context, int layout, List<LoaiMonAnDTO>loaiMonAnDTOs){
        this.context=context;
        this.layout=layout;
        this.loaiMonAnDTOs=loaiMonAnDTOs;
    }
    @Override
    public int getCount() {
        return loaiMonAnDTOs.size();
    }

    @Override
    public Object getItem(int position) {
        return loaiMonAnDTOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return loaiMonAnDTOs.get(position).getMaLoai();
    }
    public  class ViewHolderLoaiMonAn{
        TextView txtTenLoai;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view==null){
            viewHolderLoaiMonAn=new ViewHolderLoaiMonAn();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.layout_custom_loaithucdon_spiner,parent,false);

            viewHolderLoaiMonAn.txtTenLoai=(TextView)view.findViewById(R.id.txtTenLoai);
            view.setTag(viewHolderLoaiMonAn);
        }else {
            viewHolderLoaiMonAn=(ViewHolderLoaiMonAn)view.getTag();
        }
        LoaiMonAnDTO loaiMonAnDTO=loaiMonAnDTOs.get(position);
        viewHolderLoaiMonAn.txtTenLoai.setText(loaiMonAnDTO.getTenLoai());
        viewHolderLoaiMonAn.txtTenLoai.setTag(loaiMonAnDTO.getMaLoai());
        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view==null){
           viewHolderLoaiMonAn=new ViewHolderLoaiMonAn();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.layout_custom_loaithucdon_spiner,parent,false);

            viewHolderLoaiMonAn.txtTenLoai=(TextView)view.findViewById(R.id.txtTenLoai);
            view.setTag(viewHolderLoaiMonAn);
        }else {
            viewHolderLoaiMonAn=(ViewHolderLoaiMonAn)view.getTag();
        }
        LoaiMonAnDTO loaiMonAnDTO=loaiMonAnDTOs.get(position);
        viewHolderLoaiMonAn.txtTenLoai.setText(loaiMonAnDTO.getTenLoai());
        viewHolderLoaiMonAn.txtTenLoai.setTag(loaiMonAnDTO.getMaLoai());
        return view;
    }
}
