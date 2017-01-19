package com.project.is.sportlink.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import com.project.is.sportlink.R;
import java.util.Calendar;

/**
 * Created by luciogrimaldi on 18/01/17.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private Calendar calendar;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        TextView dataSelezionata = (TextView) getActivity().findViewById(R.id.textViewDataSelezionata);
        dataSelezionata.setText(view.getDayOfMonth()+ "/" + view.getMonth() + "/" + view.getYear() );

    }
}
