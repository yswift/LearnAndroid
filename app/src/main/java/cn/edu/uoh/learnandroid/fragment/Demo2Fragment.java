package cn.edu.uoh.learnandroid.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.edu.uoh.learnandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Demo2Fragment extends Fragment {
    TextView showMsg;

    public Demo2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo2, container, false);
        showMsg = view.findViewById(R.id.show_msg);
        return view;
    }

    public void receiveMsg(String msg) {
        showMsg.setText(msg);
    }

}
