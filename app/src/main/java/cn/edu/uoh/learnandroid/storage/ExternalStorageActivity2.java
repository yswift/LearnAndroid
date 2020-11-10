package cn.edu.uoh.learnandroid.storage;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import cn.edu.uoh.learnandroid.R;

public class ExternalStorageActivity2 extends AppCompatActivity {
    private static final String TAG = "ExternalStorageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        setTitle("使用外部存储External字符流");
        load();
    }

    public void save(View view) {
        // 获取用户输入
        EditText editText = findViewById(R.id.txt_input);
        String message = editText.getText().toString();
        // 保存
        File file = new File(getExternalFilesDir(null), "exFile2");
        Log.i(TAG, "save: fileName: " + file.getPath());
        try (FileOutputStream outputStream = new FileOutputStream(file);
             Writer writer = new OutputStreamWriter(outputStream)) {
            // 包装成字符流
//            Writer writer = new OutputStreamWriter(outputStream);
            // 直接写入String
            writer.write(message);
            // outputStream.write(message.getBytes());
//            writer.close();
        } catch (Exception e) {
            Log.e(TAG, "failed: save to internal file", e);
        }
    }

    public void load() {
        // 从文件读取
        File file = new File(getExternalFilesDir(null), "exFile2");
        Log.i(TAG, "read: fileName: " + file.getPath());
        try (FileInputStream inputStream = new FileInputStream(file)) {
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String message = bufferedReader.readLine();

//            byte[] buf = new byte[100];
//            int len = inputStream.read(buf);
//            String message = new String(buf, 0, len);
            // 填写到输入框
            EditText editText = findViewById(R.id.txt_input);
            editText.setText(message);
        } catch (Exception e) {
            Log.e(TAG, "failed: read from internal file", e);
        }
    }
}

