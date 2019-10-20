package cn.edu.uoh.learnandroid;

import cn.edu.uoh.learnandroid.activity.*;
import cn.edu.uoh.learnandroid.fragment.*;
import cn.edu.uoh.learnandroid.ui.*;

public class DemoItem {
    public static DemoItem[] items = {
            new DemoItem("First Activiity", FirstDemoActivity.class),
            new DemoItem("菜单", MenuActivity.class),
            new DemoItem("Intent", IntentActivity.class),
            new DemoItem("生命周期", LifeCycleActivity.class),
            new DemoItem("Linear layout", LinearDemoActivity.class),
            new DemoItem("Constraint layout", ConstraintDemoActivity.class),
            new DemoItem("数据绑定", DataBindActivity.class),
            new DemoItem("提示、通知消息", NotifyMsgActivity.class),
            new DemoItem("简单ListView", ListDemo1Activity.class),
            new DemoItem("复杂ListView", ListDemo2Activity.class),
            new DemoItem("RecyclerView", RecyclerViewActivity.class),
            new DemoItem("静态Fragment", StaticFragmentActivity.class),
            new DemoItem("动态Fragment", DynamicFragmentActivity.class),
    };

    String name;
    String title;
    Class targetActivity;

    public DemoItem(String title, Class targetActivity) {
        this.title = title;
        this.targetActivity = targetActivity;
    }
}
