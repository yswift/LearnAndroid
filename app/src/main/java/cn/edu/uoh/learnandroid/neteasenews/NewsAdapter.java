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

    private Context context;

    // 当前新闻位置
    private int currentPosition = 0;
    // 每次获取的新闻数量
    private int fetchSize = 10;
    // 新闻list
    private ArrayList<NeteaseNews> newsList = new ArrayList<>();

    // Volley 相关类
    // 请求队列
    private RequestQueue requestQueue;
    // image cache
    private VolleyImageCache volleyImageCache = new VolleyImageCache(20);

    // 刷新完成listener
    private Runnable completeRefreshListener;

    NewsAdapter(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_news, parent, false);
        return new NewsAdapter.ViewHolder(cv);
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
            // TODO 显示新闻内容
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + newsList.size());
        return newsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        ViewHolder(@NonNull CardView itemView) {
            super(itemView);
            cardView = itemView;
        }
    }

    private void showNews(NeteaseNews news, CardView cardView) {
        // 绑定文本
        TextView title = cardView.findViewById(R.id.news_title);
        title.setText(news.getTitle());
        TextView source = cardView.findViewById(R.id.news_source);
        source.setText(news.getSource() + ", " + news.getMtime());
        // 绑定图像
        NetworkImageView imgBing = cardView.findViewById(R.id.news_img);
        ImageLoader loader = new ImageLoader(requestQueue, volleyImageCache);
        // 设置NetworkImageView的 url和 ImageLoader
        imgBing.setImageUrl(news.getImgsrc(), loader);
    }

    void setCompleteRefreshListener(Runnable listener) {
        completeRefreshListener = listener;
    }

    void refresh() {
        String urlTemplate = "http://c.m.163.com/nc/article/headline/T1348647853363/%d-%d.html";
        String url = String.format(urlTemplate, currentPosition, fetchSize);
        Log.d(TAG, "refresh: url=" + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // 更新位置，为下次刷新准备
                    currentPosition += fetchSize;
                    Gson gson = new Gson();
                    PrimitiveNews primitiveNews = gson.fromJson(response, PrimitiveNews.class);
                    newsList = primitiveNews.getNews();
                    Log.d(TAG, "onResponse: " + newsList);
                    // 更新通知
                    NewsAdapter.this.notifyDataSetChanged();
                    // 刷新完成通知
                    if (completeRefreshListener != null) {
                        completeRefreshListener.run();
                    }
                },
                error -> {
                    // 刷新完成通知
                    if (completeRefreshListener != null) {
                        completeRefreshListener.run();
                    }
                    Toast.makeText(context, "获取新闻失败", Toast.LENGTH_SHORT).show();
                });
        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
    }
}
