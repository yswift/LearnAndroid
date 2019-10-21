package cn.edu.uoh.learnandroid.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import cn.edu.uoh.learnandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatesFragment extends Fragment {
    private int count=0;
    TextView infoTextView=null;
    Button clickMeButton=null;
    CheckBox saveStateBox=null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_states, container,false);
        infoTextView=(TextView)rootView.findViewById(R.id.fragment_txtInfo);
        clickMeButton=(Button)rootView.findViewById(R.id.btnClickMe);
        clickMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                infoTextView.setText("Click count:"+count);
            }
        });

        saveStateBox=(CheckBox)rootView.findViewById(R.id.chkSaveState);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null && savedInstanceState.containsKey("count")){
            count=savedInstanceState.getInt("count");
            infoTextView.setText("Click count:"+count);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(saveStateBox.isChecked()){
            outState.putInt("count", count);
        }
    }
}
