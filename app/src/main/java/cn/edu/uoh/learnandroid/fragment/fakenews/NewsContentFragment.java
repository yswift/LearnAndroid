package cn.edu.uoh.learnandroid.fragment.fakenews;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.uoh.learnandroid.R;
import cn.edu.uoh.learnandroid.databinding.ActivityDataBindBinding;
import cn.edu.uoh.learnandroid.databinding.FragmentNewsContentBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsContentFragment extends Fragment {
    FragmentNewsContentBinding binding;
    News news = new News("test title", "test content");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_content, container, false);
        binding.setNews(news);
        return binding.getRoot();
    }

    void showNews(News news) {
        showNews(news.getTitle(), news.getContent());
    }

    void showNews(String title, String content) {
        news.setTitle(title);
        news.setContent(content);
    }

}
