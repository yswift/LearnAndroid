package cn.edu.uoh.learnandroid.fragment.fakenews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cn.edu.uoh.learnandroid.R;

public class NewsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setTitle("假新闻案例(使用Fragment)");
    }
}
