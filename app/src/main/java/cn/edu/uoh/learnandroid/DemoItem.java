package cn.edu.uoh.learnandroid;

public class DemoItem {
    public static DemoItem[] items = {
            new DemoItem("First Activiity", FirstDemoActivity.class)
    };

    String name;
    String title;
    Class targetActivity;

    public DemoItem(String title, Class targetActivity) {
        this.title = title;
        this.targetActivity = targetActivity;
    }
}
