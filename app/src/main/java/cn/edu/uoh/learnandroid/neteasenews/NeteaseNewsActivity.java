package cn.edu.uoh.learnandroid.neteasenews;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import cn.edu.uoh.learnandroid.R;

public class NeteaseNewsActivity extends AppCompatActivity {
    // 下滑更新layout
    private SwipeRefreshLayout swipeRefreshLayout;
    // 新闻获取工具
    private NewsFetcher newFetcher;
    // recyclerview的数据adapter
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netease_news);
        setTitle("网易新闻");
        // 新闻获取工具
        initNewFetcher();
        // RecyclerView
        initRecyclerView();
        // 设置下拉刷新
        swipeRefreshLayout = findViewById(R.id.main_layout);
        swipeRefreshLayout.setOnRefreshListener(() -> newFetcher.fetch());
        // 刷新一次
        swipeRefreshLayout.setRefreshing(true);
        newFetcher.fetch();
    }

    private void initNewFetcher() {
        newFetcher = new NewsFetcher(this);
        newFetcher.setCompleteListener(new NewsFetcher.CompleteListener() {
            @Override
            public void onSuccess(ArrayList<NeteaseNews> newsList) {
                swipeRefreshLayout.setRefreshing(false);
                newsAdapter.setNewsList(newsList);
            }

            @Override
            public void onError(Throwable e) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(NeteaseNewsActivity.this, "失败：" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecyclerView() {
        newsAdapter = new NewsAdapter(this, newFetcher);
        // RecyclerView布局方式
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置RecyclerView
        RecyclerView newsRecyclerView = findViewById(R.id.news_recycler);
        newsRecyclerView.setLayoutManager(lm);
        newsRecyclerView.setAdapter(newsAdapter);
    }
}
