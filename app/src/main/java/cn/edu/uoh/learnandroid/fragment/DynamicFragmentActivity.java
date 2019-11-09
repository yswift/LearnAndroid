package cn.edu.uoh.learnandroid.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.edu.uoh.learnandroid.R;

public class DynamicFragmentActivity extends AppCompatActivity {
    Demo1Fragment demo1 = new Demo1Fragment();
    Demo2Fragment demo2 = new Demo2Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment);
        setTitle("动态Fragment");
    }

    public void startDemo1(View view) {
        replaceFragment(demo1);
    }

    public void startDemo2(View view) {
        replaceFragment(demo2);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
