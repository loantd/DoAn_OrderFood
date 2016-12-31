package com.example.docnhan.da_orderfood.CustomAdapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.docnhan.da_orderfood.DTO.MonAnDTO;
import com.example.docnhan.da_orderfood.R;

import java.util.List;

/**
 * Created by DOCNHAN on 15/12/2016.
 */

public class AdapterHienThiDanhSachMonAn extends BaseAdapter {
    Context context;
    int layout;
    List<MonAnDTO>monAnDTOList;
    ViewHolderDanhSachMonAn viewHolderDanhSachMonAn;

    public AdapterHienThiDanhSachMonAn(Context context, int layout, List<MonAnDTO>monAnDTOList){
        this.context=context;
        this.layout=layout;
        this.monAnDTOList=monAnDTOList;
    }

    @Override
    public int getCount() {
        return monAnDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return monAnDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return monAnDTOList.get(position).getMaLoai();
    }

    public class ViewHolderDanhSachMonAn{
        ImageView imHinhMonAn;
        TextView txtTenMonAn,txtGiaTien;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderDanhSachMonAn = new ViewHolderDanhSachMonAn();
            view = inflater.inflate(layout, parent, false);
            viewHolderDanhSachMonAn.imHinhMonAn=(ImageView) view.findViewById(R.id.imHienThiHinhMonAn);
            viewHolderDanhSachMonAn.txtTenMonAn=(TextView)view.findViewById(R.id.txtTenMonAn);
            viewHolderDanhSachMonAn.txtGiaTien=(TextView)view.findViewById(R.id.txtGiaTien);

            view.setTag(viewHolderDanhSachMonAn);

        }else {
            viewHolderDanhSachMonAn=(ViewHolderDanhSachMonAn)view.getTag();
        }
        MonAnDTO monAnDTO=monAnDTOList.get(position);
        String hinhanh=monAnDTO.getHinhAnh().toString();
        if(hinhanh == null || hinhanh.equals("")){
            viewHolderDanhSachMonAn.imHinhMonAn.setImageResource(R.drawable.hinhmacdinh);
        }else {

            Uri uri = Uri.parse(hinhanh);
            viewHolderDanhSachMonAn.imHinhMonAn.setImageURI(uri);

        }

        viewHolderDanhSachMonAn.txtTenMonAn.setText(monAnDTO.getTenMonAn());
        viewHolderDanhSachMonAn.txtGiaTien.setText(" Giá: " + monAnDTO.getGiaTien());

        return view;
    }
}
