package cn.edu.uoh.learnandroid;

import cn.edu.uoh.learnandroid.activity.*;
import cn.edu.uoh.learnandroid.contacts.ContactsActivity;
import cn.edu.uoh.learnandroid.contacts.TelFormatActivity;
import cn.edu.uoh.learnandroid.fragment.*;
import cn.edu.uoh.learnandroid.fragment.fakenews.NewsActivity;
import cn.edu.uoh.learnandroid.net.HttpDemoActivity;
import cn.edu.uoh.learnandroid.net.VolleyDemoActivity;
import cn.edu.uoh.learnandroid.note.NoteActivity;
import cn.edu.uoh.learnandroid.permission.CallPhoneActivity;
import cn.edu.uoh.learnandroid.ui.*;
import cn.edu.uoh.learnandroid.storage.*;

public class DemoItem {
    public static DemoItem[] items = {
            new DemoItem("First Activity", FirstDemoActivity.class),
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
            new DemoItem("Fragment 状态保存", FragmentStatesActivity.class),
            new DemoItem("假新闻案例(使用Fragment)", NewsActivity.class),
            new DemoItem("存储SharedPreferences", SharedPreferencesActivity.class),
            new DemoItem("使用内部存储Internal", InternalStorageActivity.class),
            new DemoItem("使用外部存储External", ExternalStorageActivity.class),
            new DemoItem("记事本案例", NoteActivity.class),
            new DemoItem("拨打电话", CallPhoneActivity.class),
            new DemoItem("读取联系人", ContactsActivity.class),
            new DemoItem("电话号码格式化", TelFormatActivity.class),
            new DemoItem("获取Bing信息", HttpDemoActivity.class),
            new DemoItem("Volley获取Bing信息", VolleyDemoActivity.class),
    };

    String name;
    String title;
    Class targetActivity;

    public DemoItem(String title, Class targetActivity) {
        this.title = title;
        this.targetActivity = targetActivity;
    }
}
