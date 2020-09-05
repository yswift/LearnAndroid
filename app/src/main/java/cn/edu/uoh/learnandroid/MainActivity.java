package cn.edu.uoh.learnandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDemoList();
    }

    void initDemoList() {
        ListView demoListView = findViewById(R.id.demoList);
        // 逆序
        List<DemoItem> demoItemList = Stream.of(DemoItem.items).collect(Collectors.toList());
//        Collections.reverse(demoItemList);
        // 取标题，生成菜单数据
        String[] titles = demoItemList.stream().map(di -> di.title).toArray(String[]::new);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titles);
        demoListView.setAdapter(adapter);
        // 处理单击事件
        demoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String title = DemoItem.items[position].title;
//                Toast.makeText(MainActivity.this,"您点击了"+title,Toast.LENGTH_SHORT).show();

                // 启动另一个Activity
                DemoItem item = demoItemList.get(position);
                Class target = item.targetActivity;
                Intent intent = new Intent(MainActivity.this, target);
                startActivity(intent);
            }
        });
    }
}
