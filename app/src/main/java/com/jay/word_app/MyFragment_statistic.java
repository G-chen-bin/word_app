package com.jay.word_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MyFragment_statistic extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistic_layout,container,false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_statistic_content);
        txt_content.setText("第三个Fragment");

        return view;
    }
}
