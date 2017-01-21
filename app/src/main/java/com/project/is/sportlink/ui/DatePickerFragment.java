package com.project.is.sportlink.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.project.is.sportlink.R;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by luciogrimaldi on 18/01/17.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private Calendar calendar;
    private int currentYear;
    private int selectedYear;
    private int currentMonth;
    private int selectedMonth;
    private int currentDay;
    private int selectedDay;
    private Calendar currentDate;
    private Calendar selectedDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        currentDate = Calendar.getInstance();
        currentDate.set(currentYear, currentMonth, currentDay);

        return new DatePickerDialog(getActivity(), this, currentYear, currentMonth, currentDay);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        selectedYear = year;
        selectedMonth = month;
        selectedDay = dayOfMonth;

        selectedDate = Calendar.getInstance();
        selectedDate.set(selectedYear, selectedMonth, selectedDay);

        if (selectedDate.before(currentDate)) {
            Toast.makeText(getActivity().getApplicationContext(), "La data che ha inserito non è valida.", Toast.LENGTH_LONG).show();
        } else {
            // non so perchè ma si deve aggiungere +1 al numero del mese
            TextView dataSelezionata = (TextView) getActivity().findViewById(R.id.textViewDataSelezionata);
            dataSelezionata.setText(view.getDayOfMonth() + "/" + view.getMonth() + 1 + "/" + view.getYear());
        }
    }
}