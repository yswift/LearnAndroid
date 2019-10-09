package cn.edu.uoh.learnandroid.ui;

import cn.edu.uoh.learnandroid.R;

public class WeatherInfo {
    private String date;
    private int imageResourceId;

    public static final WeatherInfo[] weatherInfoList = {
            new WeatherInfo("星期一", R.drawable.sunny),
            new WeatherInfo("星期二", R.drawable.cloudy3),
            new WeatherInfo("星期三", R.drawable.hail),
            new WeatherInfo("星期四", R.drawable.snow4),
    };

    public WeatherInfo(String date, int imageResourceId) {
        this.date = date;
        this.imageResourceId = imageResourceId;
    }

    public String getDate() {
        return date;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
