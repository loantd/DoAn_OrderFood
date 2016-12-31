package com.example.docnhan.da_orderfood.FlagmentApp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.docnhan.da_orderfood.R;

import java.util.Calendar;

/**
 * Created by DOCNHAN on 12/12/2016.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar calendar=Calendar.getInstance();
        int iNam=calendar.get(Calendar.YEAR);
        int iThang=calendar.get(Calendar.MONTH);
        int iNgay=calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this,iNgay,iThang,iNam);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfyear, int dayOfmonth) {
        EditText edNgaySinh=(EditText) getActivity().findViewById(R.id.edNgaySinhDK);
        String sNgaySinh=dayOfmonth + "/" +monthOfyear + "/" + year;
        edNgaySinh.setText(sNgaySinh);
    }
}
