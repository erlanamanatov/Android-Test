package com.erkprog.dateandtimepicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

public class MainFragment extends Fragment implements View.OnClickListener{
  private static final String DIALOG = "Date and time dialog";
  public static final int REQUEST_CODE = 0;
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_main, container, false);
    Button dbutton = v.findViewById(R.id.dialog_button);
    dbutton.setOnClickListener(this);
    return v;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.dialog_button:
        FragmentManager fm = getFragmentManager();
        PickerFragment dialogFragment = PickerFragment.newInstance(new Date());
        dialogFragment.setTargetFragment(MainFragment.this, REQUEST_CODE);
        dialogFragment.show(fm, DIALOG);
        break;
      default:
        break;
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode != Activity.RESULT_OK){
      return;
    }

    if (requestCode == REQUEST_CODE){
      Date date = (Date) data.getSerializableExtra(PickerFragment.EXTRA_DATE);
      Toast.makeText(getActivity(), date.toString(), Toast.LENGTH_SHORT).show();
    }
  }
}
