package cn.edu.uoh.learnandroid.net;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.edu.uoh.learnandroid.R;

public class VolleyDemoActivity extends AppCompatActivity {
    private static final String TAG = "VolleyDemoActivity";
    private static final String host = "https://cn.bing.com";
//    private static final String host = "https://www.baidu.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_demo);
        setTitle("Volley获取Bing信息");
    }

    public void clickGetBtn(View view) throws AuthFailureError {
        Log.i(TAG, "clickGetBtn: ");
        TextView txtBing = findViewById(R.id.txt_bing);

        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, host,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 200 characters of the response string.
                        txtBing.setText(response.substring(0, 200));
                        showImage(response, queue);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtBing.setText("获取cn.bing.com失败," + error);
                    }
                }) {
            // 覆盖获取请求头方法，设置User-Agent，实现桌面端请求
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new LinkedHashMap<>();
                headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void showImage(String html, RequestQueue requestQueue) {
        // 解析出image url
        String imgUrl = host + BingImageUrlTools.parseImageUrl(html);
        Log.i(TAG, "showImage: " + imgUrl);

        NetworkImageView imgBing = findViewById(R.id.img_bing);
        VolleyImageCache cache = new VolleyImageCache(2);
        ImageLoader loader = new ImageLoader(requestQueue, cache);
        // 设置NetworkImageView的 url和 ImageLoader
        imgBing.setImageUrl(imgUrl, loader);
    }
}
