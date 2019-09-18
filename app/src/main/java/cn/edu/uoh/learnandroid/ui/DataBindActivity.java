package cn.edu.uoh.learnandroid.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import cn.edu.uoh.learnandroid.R;
import cn.edu.uoh.learnandroid.databinding.ActivityDataBindBinding;

public class DataBindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_data_bind);
        ActivityDataBindBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind);
        DataModel1 m1 = new DataModel1("张三", 20);
        binding.setM1(m1);
    }
}
