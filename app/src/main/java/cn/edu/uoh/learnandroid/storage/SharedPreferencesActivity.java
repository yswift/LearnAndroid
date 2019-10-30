package cn.edu.uoh.learnandroid.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cn.edu.uoh.learnandroid.R;

public class SharedPreferencesActivity extends AppCompatActivity {
    public static final String saveKey = "cn.edu.uoh.learnandroid.save.key";

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
        SharedPreferences sharedPref = getSharedPreferences(saveKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(saveKey, message);
        editor.apply();
    }

    public void load() {
        // 获取值
        SharedPreferences sharedPref = getSharedPreferences(saveKey, Context.MODE_PRIVATE);
        String message = sharedPref.getString(saveKey, "");
        // 填写到输入框
        EditText editText = findViewById(R.id.txt_input);
        editText.setText(message);
    }

}
