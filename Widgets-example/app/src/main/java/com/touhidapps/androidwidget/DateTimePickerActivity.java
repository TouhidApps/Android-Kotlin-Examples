package com.touhidapps.androidwidget;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class DateTimePickerActivity extends AppCompatActivity {

    static EditText editTextDate, editTextTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_picker);
        setTitle("Date time picker");

        // date picker
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        // time picker
        editTextTime = (EditText) findViewById(R.id.editTextTime);
        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

    }

    // date picker class
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            //    dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            return dialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            editTextDate.setText(day + "-" + (month + 1) + "-" + year);
        }
    }

    // time picker class
    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            TimePickerDialog dialog = new TimePickerDialog(getActivity(), this, hourOfDay, minute, false);
            return dialog;
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String amPm = "A.M";
            if (hourOfDay > 12) {
                amPm = "P.M";
                hourOfDay = hourOfDay - 12;
            }
            editTextTime.setText(hourOfDay + "-" + minute + " " + amPm);
        }
    }
}
