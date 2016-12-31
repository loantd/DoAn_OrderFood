package com.example.docnhan.da_orderfood.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.docnhan.da_orderfood.DAO.BanAnDAO;
import com.example.docnhan.da_orderfood.DAO.GoiMonDAO;
import com.example.docnhan.da_orderfood.DTO.BanAnDTO;
import com.example.docnhan.da_orderfood.DTO.GoiMonDTO;
import com.example.docnhan.da_orderfood.FlagmentApp.HienThiThucDonFagment;
import com.example.docnhan.da_orderfood.R;
import com.example.docnhan.da_orderfood.ThanhToanActivity;
import com.example.docnhan.da_orderfood.TrangChuActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by DOCNHAN on 14/12/2016.
 */

public class AdapterHienThiBanAn extends BaseAdapter implements View.OnClickListener{

    Context context;
    int layout;
    List<BanAnDTO>banAnDTOList;
    ViewHolderBanAn viewHolderBanAn;
    BanAnDAO banAnDAO;
    GoiMonDAO goiMonDAO;
    FragmentManager fragmentManager;

    public AdapterHienThiBanAn(Context context, int layout, List<BanAnDTO> banAnDTOList){

        this.context=context;
        this.layout=layout;
        this.banAnDTOList=banAnDTOList;
        banAnDAO=new BanAnDAO(context);
        goiMonDAO=new GoiMonDAO(context);
        fragmentManager=((TrangChuActivity)context).getSupportFragmentManager();
    }

    @Override
    public int getCount() {
        return banAnDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return banAnDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return banAnDTOList.get(position).getMaBan();
    }


    public class ViewHolderBanAn{
        ImageView imBanAn,imGoiMon,imThanhToan,imAnButton;
        TextView txtTenBanAn;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View view=convertView;
        if(view==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           viewHolderBanAn=new ViewHolderBanAn();
            view=inflater.inflate(R.layout.custom_layout_hienthibanan,parent,false);
            viewHolderBanAn.imBanAn=(ImageView) view.findViewById(R.id.imBanAn);
            viewHolderBanAn.imGoiMon=(ImageView) view.findViewById(R.id.imGoiMon);
            viewHolderBanAn.imThanhToan=(ImageView) view.findViewById(R.id.imThanhToan);
            viewHolderBanAn.imAnButton=(ImageView) view.findViewById(R.id.imAnButton);
            viewHolderBanAn.txtTenBanAn=(TextView)view.findViewById(R.id.txtTenbanAn);

            view.setTag(viewHolderBanAn);
        }else {
            viewHolderBanAn=(ViewHolderBanAn) view.getTag();
        }
        if(banAnDTOList.get(position).isDuocChon()){
            HienThiButon();
        }
        else {
            AnButon();
        }


        BanAnDTO banAnDTO=banAnDTOList.get(position);
        String kttinhTrang=banAnDAO.LayTinhTrangBanTheoMa(banAnDTO.getMaBan());
        if(kttinhTrang.equals("true")){
                viewHolderBanAn.imBanAn.setImageResource(R.drawable.banan_true);
        }else {
            viewHolderBanAn.imBanAn.setImageResource(R.drawable.banan);
        }

        viewHolderBanAn.txtTenBanAn.setText(banAnDTO.getTenBan());

        viewHolderBanAn.imBanAn.setTag(position);

        viewHolderBanAn.imBanAn.setOnClickListener(this);
        viewHolderBanAn.imGoiMon.setOnClickListener(this);
        viewHolderBanAn.imThanhToan.setOnClickListener(this);
        viewHolderBanAn.imAnButton.setOnClickListener(this);

        return view;
    }
    private void HienThiButon(){
        viewHolderBanAn.imGoiMon.setVisibility(View.VISIBLE);
        viewHolderBanAn.imThanhToan.setVisibility(View.VISIBLE);
        viewHolderBanAn.imAnButton.setVisibility(View.VISIBLE);
    }
    private void AnButon(){
        viewHolderBanAn.imGoiMon.setVisibility(View.INVISIBLE);
        viewHolderBanAn.imThanhToan.setVisibility(View.INVISIBLE);
        viewHolderBanAn.imAnButton.setVisibility(View.INVISIBLE);
    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        viewHolderBanAn=(ViewHolderBanAn)((View)v.getParent()).getTag();
        int vitri1=(int)viewHolderBanAn.imBanAn.getTag();
        int maban=banAnDTOList.get(vitri1).getMaBan();
        switch (id){
            case R.id.imBanAn:
                int viTri=(int)v.getTag();
                banAnDTOList.get(viTri).setDuocChon(true);
                HienThiButon();
                ;break;
            case R.id.imGoiMon:


                Intent layIDTrangchu=((TrangChuActivity)context).getIntent();
                int manhanvien=layIDTrangchu.getIntExtra("manhanvien",0);
                String sTinhTrang=banAnDAO.LayTinhTrangBanTheoMa(maban);
                if(sTinhTrang.equals("false")){

                    //thuc hien them bang goi mon cap nhat tinh trang ban
                    Calendar calendar=Calendar.getInstance();
                    SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyy");
                    String ngaygoi=dateFormat.format(calendar.getTime());

                    GoiMonDTO goiMonDTO=new GoiMonDTO();
                    goiMonDTO.setMaBan(maban);
                    goiMonDTO.setMaNV(manhanvien);
                    goiMonDTO.setNgayGoi(ngaygoi);
                    goiMonDTO.setTinhTrang("false");
                    long kiemtra=goiMonDAO.ThemGoiMon(goiMonDTO);
                    banAnDAO.CapNhatTinhTrangBan(maban,"true");
                    if(kiemtra==0){
                        Toast.makeText(context,context.getResources().getString(R.string.themthatbai),Toast.LENGTH_SHORT).show();
                    }

                }
                FragmentTransaction tranThucDonTransaction=fragmentManager.beginTransaction();
                HienThiThucDonFagment hienThiThucDonFagment=new HienThiThucDonFagment();

                Bundle bDulieuThucDon=new Bundle();
                bDulieuThucDon.putInt("maban",maban);

                hienThiThucDonFagment.setArguments(bDulieuThucDon);
                tranThucDonTransaction.replace(R.id.content,hienThiThucDonFagment).addToBackStack("hienthibanan");
                tranThucDonTransaction.commit();

                ;break;
            case R.id.imThanhToan:
                Intent iThanhToan=new Intent(context, ThanhToanActivity.class);
                iThanhToan.putExtra("maban",maban);
                context.startActivity(iThanhToan);
                ;break;
            case R.id.imAnButton:
                AnButon();
                ;break;
        }

    }

}
