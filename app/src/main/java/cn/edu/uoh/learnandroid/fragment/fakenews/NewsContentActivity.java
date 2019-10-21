package cn.edu.uoh.learnandroid.fragment.fakenews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cn.edu.uoh.learnandroid.R;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        // 获取新闻信息
        String newsTitle = getIntent().getStringExtra("news_title");
        String newsContent = getIntent().getStringExtra("news_content");
        // 获取fragment
        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.content_fragment);
        // 显示新闻
        newsContentFragment.showNews(newsTitle, newsContent);
    }

    static void actionStart(Context context, News news) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", news.getTitle());
        intent.putExtra("news_content", news.getContent());
        context.startActivity(intent);
    }
}
