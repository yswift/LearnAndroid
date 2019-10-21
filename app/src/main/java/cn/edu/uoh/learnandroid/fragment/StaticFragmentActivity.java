package cn.edu.uoh.learnandroid.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cn.edu.uoh.learnandroid.R;

public class StaticFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_fragment);
    }

    public void receiveMsg(String msg) {
        TextView tv = findViewById(R.id.show_msg);
        tv.setText(msg);
    }

    public void sendMsg(View view) {
        EditText et = findViewById(R.id.txt_msg);
        String msg = et.getText().toString();
        Demo2Fragment fragment = (Demo2Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        fragment.receiveMsg(msg);
    }
}
