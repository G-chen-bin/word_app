package com.jay.word_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment_setting extends Fragment {

    public MyFragment_setting() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_layout,container,false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_setting_content);
        txt_content.setText("第四个Fragment");
        return view;
    }
}
