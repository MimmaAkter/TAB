package com.example.intent.mobileappdevelop.tab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForecaseWatherFragment extends Fragment implements LocationFoundListiner {


    public ForecaseWatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecase_wather, container, false);
    }

    @Override
    public void getLocation(double latitude, double longitude) {
        Toast.makeText(getActivity(), "forcatt", Toast.LENGTH_SHORT).show();
    }
}
