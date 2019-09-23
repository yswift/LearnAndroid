package cn.edu.uoh.learnandroid.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import cn.edu.uoh.learnandroid.R;

public class ListDemo1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demo1);

        setListView(android.R.layout.simple_list_item_1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_demo1_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 处理动作按钮的点击事件
        switch (item.getItemId()) {
            case R.id.simple_list_item_1:
                setListView(android.R.layout.simple_list_item_1);
                return true;
            case R.id.simple_list_item_activated_1:
                setListView(android.R.layout.simple_list_item_activated_1);
                return true;
            case R.id.simple_list_item_checked:
                setListView(android.R.layout.simple_list_item_checked);
                return true;
            case R.id.simple_list_item_multiple_choice:
                setListView(android.R.layout.simple_list_item_multiple_choice);
                return true;
            case R.id.simple_list_item_single_choice:
                setListView(android.R.layout.simple_list_item_single_choice);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setListView(int templateId) {
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> data = getData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, templateId, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clicked = parent.getItemAtPosition(position).toString();
                Toast.makeText(ListDemo1Activity.this, "单击：" + clicked, Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                String clicked = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(ListDemo1Activity.this, "长按：" + clicked, Toast.LENGTH_SHORT).show();
                return true;
                //如果回调函数处理了长按事件，返回真；否则返回假。
            }
        });
    }

    private ArrayList<String> getData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("演示数据：" + i);
        }
        return list;
    }
}
