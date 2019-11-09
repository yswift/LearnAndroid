package cn.edu.uoh.learnandroid.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import cn.edu.uoh.learnandroid.R;

public class HttpDemoActivity extends AppCompatActivity {
    private static final String TAG = "HttpDemoActivity";
    private static final String host = "https://cn.bing.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_demo);
        setTitle("获取Bing信息");
    }

    public void clickGetBtn(View view) {
        Log.i(TAG, "clickGetBtn: ");
        HtmlTask htmlTask = new HtmlTask();
        htmlTask.execute(host);
    }

    class HtmlTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            Log.i(TAG, "doInBackground: url = " + url);
            try {
                String html = HttpTools.getString(url);
                Log.i(TAG, "doInBackground: html = " + html);
                return html;
            } catch (Throwable e) {
                Log.e(TAG, "获取cn.bing.com失败", e);
                return "获取cn.bing.com失败," + e;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            TextView txtBing = findViewById(R.id.txt_bing);
            // 显示前100个字符
            txtBing.setText(s.substring(0, 100));
            // 解析出image url
            String imgUrl = host + BingImageUrlTools.parseImageUrl(s);
            // 启动获取图像的任务
            ImageTask imageTask = new ImageTask();
            imageTask.execute(imgUrl);
        }
    }

    class ImageTask extends AsyncTask<String, Void, byte[]> {
        @Override
        protected byte[] doInBackground(String... strings) {
            String url = strings[0];
            Log.i(TAG, "ImageTask: url = " + url);
            try {
                byte[] image = HttpTools.getBytes(url);
                Log.i(TAG, "ImageTask: finished");
                return image;
            } catch (IOException e) {
                Log.e(TAG, "获取图像失败", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            if (bytes == null) {
                return;
            }
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            ImageView imgBing = findViewById(R.id.img_bing);
            imgBing.setImageBitmap(bitmap);
        }
    }
}
