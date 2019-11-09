package cn.edu.uoh.learnandroid.net;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

public class VolleyImageCache implements ImageLoader.ImageCache {
    private final LruCache<String, Bitmap> cache;

    public VolleyImageCache(int maxSize) {
        cache = new LruCache<String, Bitmap>(maxSize);
    }

    @Override
    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url, bitmap);
    }
}
