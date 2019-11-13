package cn.edu.uoh.learnandroid.neteasenews;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

import cn.edu.uoh.learnandroid.net.VolleyImageCache;

// 新闻获取工具
public class NewsFetcher {
    private static final String TAG = "NewsFetcher";
    // 新闻url模板
    private static final String UrlTemplate = "http://c.m.163.com/nc/article/headline/T1348647853363/%d-%d.html";
    // 当前新闻位置
    private int currentPosition = 0;
    // 每次获取的新闻数量
    private int fetchSize = 10;

    // Volley 相关类
    // 请求队列
    private RequestQueue requestQueue;
    // 图像读取
    private ImageLoader imageLoader;

    private CompleteListener completeListener;

    NewsFetcher(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        // image cache
        VolleyImageCache volleyImageCache = new VolleyImageCache(10);
        imageLoader = new ImageLoader(requestQueue, volleyImageCache);
    }

    // 读取完成的listener，用于更新UI
    public interface CompleteListener {
        void onSuccess(ArrayList<NeteaseNews> newsList);

        void onError(Throwable e);
    }

    void setCompleteListener(CompleteListener completeListener) {
        this.completeListener = completeListener;
    }

    // 读取新闻列表，完成后用completeListener通知到监听器
    void fetch() {
        String url = String.format(UrlTemplate, currentPosition, fetchSize);
        Log.d(TAG, "refresh: url=" + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // 更新位置，为下次刷新准备
                    currentPosition += fetchSize;
                    Gson gson = new Gson();
                    PrimitiveNews primitiveNews = gson.fromJson(response, PrimitiveNews.class);
                    ArrayList<NeteaseNews> newsList = primitiveNews.getNews();
                    Log.d(TAG, "onResponse: " + newsList);
                    // 获取完成通知
                    if (completeListener != null) {
                        completeListener.onSuccess(newsList);
                    }
                },
                error -> {
                    // 出错时通知
                    if (completeListener != null) {
                        completeListener.onError(error);
                    }
                });
        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
    }

    // 设置imageView的参数，以获取网络图像
    void loadImage(NeteaseNews news, NetworkImageView imageView) {
        // 设置NetworkImageView的 url和 ImageLoader
        imageView.setImageUrl(news.getImgsrc(), imageLoader);
    }
}
