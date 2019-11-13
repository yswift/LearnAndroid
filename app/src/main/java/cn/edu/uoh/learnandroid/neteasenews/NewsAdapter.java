package cn.edu.uoh.learnandroid.neteasenews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

import cn.edu.uoh.learnandroid.R;
import cn.edu.uoh.learnandroid.net.VolleyImageCache;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private static final String TAG = "NewsAdapter";

    // 单击新闻，启动activity时需要
    private Context context;
    private NewsFetcher newsFetcher;

    // 新闻list
    private ArrayList<NeteaseNews> newsList = new ArrayList<>();

    NewsAdapter(Context context, NewsFetcher newsFetcher) {
        this.context = context;
        this.newsFetcher = newsFetcher;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        ViewHolder(@NonNull CardView itemView) {
            super(itemView);
            cardView = itemView;
        }
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_news, parent, false);
        return new NewsAdapter.ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: position = " + position);
        CardView cardView = holder.cardView;
        // 获取数据
        NeteaseNews news = newsList.get(position);
        showNews(news, cardView);
        // 处理单击
        cardView.setOnClickListener((v) -> {
            // TODO 单击时显示新闻内容
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + newsList.size());
        return newsList.size();
    }

    // 修改新闻列表，获取到新闻列表后使用此方法修改adapter的新闻列表
    void setNewsList(ArrayList<NeteaseNews> newsList) {
        this.newsList = newsList;
        // 数据已更改，更新UI
        notifyDataSetChanged();
    }

    private void showNews(NeteaseNews news, CardView cardView) {
        // 显示文本
        TextView title = cardView.findViewById(R.id.news_title);
        title.setText(news.getTitle());
        TextView source = cardView.findViewById(R.id.news_source);
        source.setText(news.getSource() + ", " + news.getMtime());
        // 显示图像
        NetworkImageView imgBing = cardView.findViewById(R.id.news_img);
        newsFetcher.loadImage(news, imgBing);
    }
}
