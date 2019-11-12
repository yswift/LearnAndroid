package cn.edu.uoh.learnandroid.neteasenews;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import cn.edu.uoh.learnandroid.R;

public class NeteaseNewsActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;

    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netease_news);
        setTitle("网易新闻");
        // 下拉刷新
        swipeRefreshLayout = findViewById(R.id.main_layout);
        swipeRefreshLayout.setOnRefreshListener(this::updateNews);
        // RecyclerView adapter
        newsAdapter = new NewsAdapter(this);
        newsAdapter.setCompleteRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
        });
        // RecyclerView布局方式
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置RecyclerView
        RecyclerView newsRecyclerView = findViewById(R.id.news_recycler);
        newsRecyclerView.setLayoutManager(lm);
        newsRecyclerView.setAdapter(newsAdapter);
        // 启动时刷新一次
        newsAdapter.refresh();
    }

    private void updateNews() {
        newsAdapter.refresh();
    }
}
