package cn.edu.uoh.learnandroid.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import cn.edu.uoh.learnandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Demo1Fragment extends Fragment {
    public Demo1Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo1, container, false);
        // 传递消息到activity
        EditText txtMsg = view.findViewById(R.id.txt_msg);
        Button btn2Activity = view.findViewById(R.id.btn_send_activity);
        btn2Activity.setOnClickListener((v) -> {
            String msg = txtMsg.getText().toString();
            StaticFragmentActivity sfa = (StaticFragmentActivity) getActivity();
            sfa.receiveMsg(msg);
        });
        // 传递消息到 fragment
        Button btn2Fragment = view.findViewById(R.id.btn_send_fragment);
        btn2Fragment.setOnClickListener((v) -> {
            String msg = txtMsg.getText().toString();
            Demo2Fragment fragment = (Demo2Fragment) getFragmentManager().findFragmentById(R.id.fragment2);
            fragment.receiveMsg(msg);
        });

        return view;
    }

}
