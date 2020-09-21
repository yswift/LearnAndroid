package cn.edu.uoh.learnandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.edu.uoh.learnandroid.R;

public class FirstDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_demo);
        setTitle("First Activity");

        //找到按钮对象
        Button btn = findViewById(R.id.button);

        // 注册监听

        // 方法1：内部类
//        BtnListener btnListener = new BtnListener();
//        btn.setOnClickListener(btnListener);

        // 方法2：匿名内部类
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(FirstDemoActivity.this, "按钮被按下", Toast.LENGTH_SHORT).show();
//            }
//        });

        // 方法3：Lambda 表达式
        btn.setOnClickListener((v) -> {
            Toast.makeText(FirstDemoActivity.this, "Lambda按钮被按下", Toast.LENGTH_SHORT).show();
        });

    }

    class BtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(FirstDemoActivity.this, "按钮被按下", Toast.LENGTH_LONG).show();
            Toast.makeText(FirstDemoActivity.this, "", Toast.LENGTH_SHORT).show();
        }
    }
}
