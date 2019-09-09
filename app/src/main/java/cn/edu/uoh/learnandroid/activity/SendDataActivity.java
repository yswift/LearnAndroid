package cn.edu.uoh.learnandroid.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cn.edu.uoh.learnandroid.R;

public class SendDataActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);
    }

    public void sendData(View view) {
//        1. 获取要发送的消息
        EditText sendMsg = findViewById(R.id.sendMsg);
        String msg = sendMsg.getText().toString();
//        2. 建立Intent
        Intent intent = new Intent(this, ReplyDataActivity.class);
        intent.putExtra("MSG", msg);
//        3. 启动接收消息的Activity
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                TextView receiveMsg = findViewById(R.id.receiveMsg);
                if (resultCode == RESULT_OK) {
                    // 获取接收的信息
                    String msg = data.getStringExtra("MSG");
                    // 显示
                    receiveMsg.setText("接收到回传消息：" + msg);
                } else {
                    receiveMsg.setText("已取消，无数据");
                }
                break;
            default:
        }
    }
}
