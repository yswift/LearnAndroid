package cn.edu.uoh.learnandroid.ui;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.uoh.learnandroid.R;

public class ListDemo2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demo2);
        setTitle("复杂ListView");
        initListView();
    }

    private void initListView() {
        ListView listView = (ListView) findViewById(R.id.listView);

        List<Map<String, Object>> dataList = getData();
        SimpleAdapter adapter = new SimpleAdapter(this, dataList,
                R.layout.list_demo2_item,
                new String[]{"image", "date", "weather"},
                new int[]{R.id.imageView, R.id.textView, R.id.textView2,}
        );

        listView.setAdapter(adapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> data = new HashMap<>();
        data.put("image", R.drawable.sunny);
        data.put("date", "星期一");
        data.put("weather", "晴");
        list.add(data);

        data = new HashMap<>();
        data.put("image", R.drawable.cloudy3);
        data.put("date", "星期二");
        data.put("weather", "多云");
        list.add(data);

        data = new HashMap<>();
        data.put("image", R.drawable.hail);
        data.put("date", "星期三");
        data.put("weather", "雨");
        list.add(data);

        data = new HashMap<>();
        data.put("image", R.drawable.snow4);
        data.put("date", "星期四");
        data.put("weather", "雪");
        list.add(data);

        return list;
    }
}
