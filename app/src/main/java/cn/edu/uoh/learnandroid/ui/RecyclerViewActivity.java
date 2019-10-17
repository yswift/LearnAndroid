package cn.edu.uoh.learnandroid.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import cn.edu.uoh.learnandroid.R;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView weatherRecycler = findViewById(R.id.weather_recycler);

        // 线性布局
//        LinearLayoutManager lm = new LinearLayoutManager(this);
//        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        // 网格布局
        GridLayoutManager lm = new GridLayoutManager(this, 3);
        // 不规则布局
//        StaggeredGridLayoutManager lm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        // 应用布局
        weatherRecycler.setLayoutManager(lm);

        WeatherInfoAdapter adapter = new WeatherInfoAdapter(WeatherInfo.weatherInfoList);
        weatherRecycler.setAdapter(adapter);
    }
}
