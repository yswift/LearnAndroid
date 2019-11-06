package cn.edu.uoh.learnandroid.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.edu.uoh.learnandroid.R;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class ContactsActivity extends AppCompatActivity {
    private final static int CONTACTS_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        PermissionGen.with(this)
                .addRequestCode(CONTACTS_CODE)
                .permissions(Manifest.permission.READ_CONTACTS)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = CONTACTS_CODE)
    private void showContacts() {
        // // 读取联系人
        List<DemoContacts> contacts = getContacts();
        // 为listview准备数据
        List<Map<String, Object>> data = prepareData(contacts);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                data,
                android.R.layout.simple_expandable_list_item_2, new String[]{"name","number"},
                new int[]{android.R.id.text1,android.R.id.text2});

        ListView listView = findViewById(R.id.contacts_list_view);
        listView.setAdapter(simpleAdapter);
        // TODO 处理listview的单击事件，单击时拨打电话
    }

    @PermissionFail(requestCode = CONTACTS_CODE)
    private void notifyUser(){
        Snackbar.make(findViewById(R.id.main_layout), "权限禁止", Snackbar.LENGTH_LONG).show();
    }

    // 读取联系人
    List<DemoContacts> getContacts() {
        ContentResolver contentResolver = this.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        int count = 0;
        List<DemoContacts> contacts = new ArrayList<>(50);
        while(cursor.moveToNext() && count++ < 50) {
            DemoContacts dc = new DemoContacts();
            dc.id = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
            dc.name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            dc.number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contacts.add(dc);
        }
        cursor.close();
        return contacts;
    }

    // 为listview准备数据，即：把联系人list加工成SimpleAdapter需要的数据格式
    List<Map<String, Object>> prepareData(List<DemoContacts> contacts) {
        List<Map<String, Object>> data = contacts.stream().map(c -> {
            Map<String, Object> d = new HashMap<>();
            d.put("name", c.name);
            d.put("number", c.number);
            return d;
        }).collect(Collectors.toList());
        return data;
    }

}
