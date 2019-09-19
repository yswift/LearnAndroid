package cn.edu.uoh.learnandroid.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import cn.edu.uoh.learnandroid.R;
import cn.edu.uoh.learnandroid.databinding.ActivityDataBindBinding;

public class DataBindActivity extends AppCompatActivity {

    DataModel2 m2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_data_bind);
        ActivityDataBindBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind);
        DataModel1 m1 = new DataModel1("张三", 20);
        m2 = new DataModel2("李四", 21);
        binding.setM1(m1);
        binding.setM2(m2);
    }

    public void updateM2(View view) {
        m2.setName("王五");
        m2.setAge(40);
    }
}
