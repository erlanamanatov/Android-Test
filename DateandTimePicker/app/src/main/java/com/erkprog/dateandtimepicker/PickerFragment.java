package com.erkprog.dateandtimepicker;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PickerFragment extends DialogFragment {
  private static final String ARG_DATE = "arg date";
  public static final String EXTRA_DATE = "extra date";
  private DatePicker mDatePicker;
  private TimePicker mTimePicker;

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Date date = (Date) getArguments().getSerializable(ARG_DATE);

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog, null);

    mDatePicker = v.findViewById(R.id.date_picker);
    mDatePicker.init(year, month, day, null);
    mTimePicker = v.findViewById(R.id.time_picker);
    return new AlertDialog.Builder(getActivity())
        .setView(v)
        .setTitle("Date and Time Picker")
        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            int year = mDatePicker.getYear();
            int month = mDatePicker.getMonth();
            int day = mDatePicker.getDayOfMonth();
            int hour = 10;
            int minute = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
              hour = mTimePicker.getHour();
              minute = mTimePicker.getMinute();
            } else {
              hour = mTimePicker.getCurrentHour();
              minute = mTimePicker.getCurrentMinute();
            }
            Date date = new GregorianCalendar(year, month, day, hour, minute).getTime();
            sendResult(Activity.RESULT_OK, date);
          }
        })
        .create();
  }

  public static PickerFragment newInstance(Date date) {
    Bundle args = new Bundle();
    args.putSerializable(ARG_DATE, date);
    PickerFragment fragment = new PickerFragment();
    fragment.setArguments(args);
    return fragment;
  }

  private void sendResult(int resultCode, Date date) {
    if (getTargetFragment() == null) {
      return;
    }

    Intent intent = new Intent();
    intent.putExtra(EXTRA_DATE, date);
    getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
  }
}
