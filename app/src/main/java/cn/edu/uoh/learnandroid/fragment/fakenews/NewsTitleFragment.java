package cn.edu.uoh.learnandroid.fragment.fakenews;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.stream.Collectors;

import cn.edu.uoh.learnandroid.R;

public class NewsTitleFragment extends Fragment {
    // 横向标记
    boolean dualPane;

    List<News> newsList = NewsFactory.getNews();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.content_fragment) != null) {
            dualPane = true; // 可以找到news_content_layout布局时，为双页模式
        } else {
            dualPane = false; // 找不到news_content_layout布局时，为单页模式
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_title, container, false);
        // 获取新闻标题
        List<String> titles = newsList.stream().map((news) -> news.getTitle()).collect(Collectors.toList());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),android.R.layout.simple_list_item_1, titles);

        ListView titleListView = view.findViewById(R.id.title_list_view);
        titleListView.setAdapter(adapter);
        // 处理单击
        titleListView.setOnItemClickListener((AdapterView<?> parent, View v, int position, long id) -> {
            showContent(position);
        });
        return view;
    }

    void showContent(int position) {
        News news = newsList.get(position);
        if (dualPane) {
            NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.content_fragment);
            // 显示新闻
            newsContentFragment.showNews(news);
        } else {
            NewsContentActivity.actionStart(getContext(), news);
        }
    }

}
