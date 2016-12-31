package com.example.docnhan.da_orderfood.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.docnhan.da_orderfood.DTO.ThanhToanDTO;
import com.example.docnhan.da_orderfood.R;

import java.util.List;

/**
 * Created by DOCNHAN on 16/12/2016.
 */

public class AdapterHienThiThanhToan extends BaseAdapter{
    Context context;
    int layout;
    List<ThanhToanDTO>thanhToanDTOs;
    ViewHolderThanhToan viewHolderThanhToan;

    public AdapterHienThiThanhToan(Context context, int layout, List<ThanhToanDTO>thanhToanDTOs){
        this.context=context;
        this.layout=layout;
        this.thanhToanDTOs=thanhToanDTOs;
    }
    @Override
    public int getCount() {
        return thanhToanDTOs.size();
    }

    @Override
    public Object getItem(int position) {
        return thanhToanDTOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public  class ViewHolderThanhToan{
        TextView txtTenMonAn,txtSoLuong,txtGiaTien;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view==null){
            viewHolderThanhToan=new ViewHolderThanhToan();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,parent,false);

            viewHolderThanhToan.txtTenMonAn=(TextView) view.findViewById(R.id.txtTenMonAnThanhToan);
            viewHolderThanhToan.txtGiaTien=(TextView)view.findViewById(R.id.txtGiaTienThanhToan);
            viewHolderThanhToan.txtSoLuong=(TextView)view.findViewById(R.id.txtSLThanhToan);

            view.setTag(viewHolderThanhToan);
        }else {
            viewHolderThanhToan=(ViewHolderThanhToan)view.getTag();
        }
        ThanhToanDTO thanhToanDTO=thanhToanDTOs.get(position);
        viewHolderThanhToan.txtTenMonAn.setText(thanhToanDTO.getTenMonAn());
        viewHolderThanhToan.txtSoLuong.setText(String.valueOf(thanhToanDTO.getSoLuong()));
        viewHolderThanhToan.txtGiaTien.setText(String.valueOf(thanhToanDTO.getGiaTien()));
        return view;
    }
}
