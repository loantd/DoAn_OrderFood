package com.example.docnhan.da_orderfood.CustomAdapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.docnhan.da_orderfood.DAO.LoaiMonAnDAO;
import com.example.docnhan.da_orderfood.DTO.LoaiMonAnDTO;
import com.example.docnhan.da_orderfood.R;

import java.net.URL;
import java.util.List;

/**
 * Created by DOCNHAN on 15/12/2016.
 */

public class AdapterHienThiLoaiMonAnThucDon extends BaseAdapter {
    Context context;
    int layout;
    List<LoaiMonAnDTO>loaiMonAnDTOs;
    ViewHolderHienThiHinhLoaiThucDon viewHolderHienThiHinhLoaiThucDon;
    LoaiMonAnDAO loaiMonAnDAO;
    public AdapterHienThiLoaiMonAnThucDon(Context context, int layout, List<LoaiMonAnDTO>loaiMonAnDTOs){
        this.context=context;
        this.layout=layout;
        this.loaiMonAnDTOs=loaiMonAnDTOs;
        loaiMonAnDAO=new LoaiMonAnDAO(context);
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
    public class ViewHolderHienThiHinhLoaiThucDon{
        ImageView imHinhLoaiThucDon;
        TextView txtTenLoaiThucDon;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view==null){
            viewHolderHienThiHinhLoaiThucDon=new ViewHolderHienThiHinhLoaiThucDon();
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,parent,false);

            viewHolderHienThiHinhLoaiThucDon.imHinhLoaiThucDon=(ImageView)view.findViewById(R.id.imHienThiMonAn);
            viewHolderHienThiHinhLoaiThucDon.txtTenLoaiThucDon=(TextView)view.findViewById(R.id.txtTenLoaiThucDon);

            view.setTag(viewHolderHienThiHinhLoaiThucDon);

        }else {
            viewHolderHienThiHinhLoaiThucDon=(ViewHolderHienThiHinhLoaiThucDon)view.getTag();
        }

        LoaiMonAnDTO loaiMonAnDTO=loaiMonAnDTOs.get(position);
        int maloai=loaiMonAnDTO.getMaLoai();
        String hinhanh=loaiMonAnDAO.LayHinhMaLoai(maloai);
        Uri uri=Uri.parse(hinhanh);
        viewHolderHienThiHinhLoaiThucDon.txtTenLoaiThucDon.setText(loaiMonAnDTO.getTenLoai());
        viewHolderHienThiHinhLoaiThucDon.imHinhLoaiThucDon.setImageURI(uri);

        return view;
    }
}
