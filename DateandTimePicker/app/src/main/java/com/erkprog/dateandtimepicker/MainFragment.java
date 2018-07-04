package com.erkprog.dateandtimepicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainFragment extends Fragment implements View.OnClickListener{
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
        Toast.makeText(getActivity(), "CLicked", Toast.LENGTH_SHORT).show();
        break;
      default:
        break;
    }

  }
}
