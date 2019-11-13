package cn.edu.uoh.learnandroid.neteasenews;

import java.util.ArrayList;

/**
 * 元新闻，网易新闻有一层包裹，
 * 为了能用gson做解析，定义了这个类
 */
public class PrimitiveNews {
    private ArrayList<NeteaseNews> T1348647853363 = new ArrayList<>();

    public ArrayList<NeteaseNews> getT1348647853363() {
        return T1348647853363;
    }

    public void setT1348647853363(ArrayList<NeteaseNews> t1348647853363) {
        T1348647853363 = t1348647853363;
    }

    // 更友好的接口
    public ArrayList<NeteaseNews> getNews() {
        return T1348647853363;
    }
}
