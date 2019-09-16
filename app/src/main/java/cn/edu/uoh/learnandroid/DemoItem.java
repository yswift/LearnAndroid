package cn.edu.uoh.learnandroid;

import cn.edu.uoh.learnandroid.activity.*;

public class DemoItem {
    public static DemoItem[] items = {
            new DemoItem("First Activiity", FirstDemoActivity.class),
            new DemoItem("菜单", MenuActivity.class),
            new DemoItem("Intent", IntentActivity.class),
            new DemoItem("生命周期", LifeCycleActivity.class),
    };

    String name;
    String title;
    Class targetActivity;

    public DemoItem(String title, Class targetActivity) {
        this.title = title;
        this.targetActivity = targetActivity;
    }
}
