package cn.edu.uoh.learnandroid.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.uoh.learnandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Demo2Fragment extends Fragment {


    public Demo2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demo2, container, false);
    }

}
