package cn.edu.uoh.learnandroid.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import cn.edu.uoh.learnandroid.R;

public class ExternalStorageActivity extends AppCompatActivity {
    private static final String TAG = "ExternalStorageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        load();
    }

    public void save(View view) {
        // 获取用户输入
        EditText editText = findViewById(R.id.txt_input);
        String message = editText.getText().toString();
        // 保存
        File file = new File(getExternalFilesDir(null), "exFile");
        Log.i(TAG, "save: fileName: " + file.getPath());
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(message.getBytes());
        } catch (Exception e) {
            Log.e(TAG, "failed: save to internal file", e);
        }
    }

    public void load() {
        // 从文件读取
        File file = new File(getExternalFilesDir(null), "exFile");
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] buf = new byte[100];
            int len = inputStream.read(buf);
            String message = new String(buf, 0, len);
            // 填写到输入框
            EditText editText = findViewById(R.id.txt_input);
            editText.setText(message);
        } catch (Exception e) {
            Log.e(TAG, "failed: read from internal file", e);
        }
    }
}
