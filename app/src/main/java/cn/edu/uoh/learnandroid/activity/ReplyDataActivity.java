package cn.edu.uoh.learnandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cn.edu.uoh.learnandroid.R;

public class ReplyDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_data);
        receiveMsg();
    }

    private void receiveMsg() {
        Intent intent = getIntent();
        String msg = intent.getStringExtra("MSG");
        TextView txtMsg = findViewById(R.id.receiveMsg);
        txtMsg.setText("接收到：" + msg);
    }

    public void clickOk(View view) {
        //  1. 获取要发送的消息
        EditText sendMsg = findViewById(R.id.replyMsg);
        String msg = sendMsg.getText().toString();
        //  2. 建立Intent
        Intent intent = new Intent(this, ReplyDataActivity.class);
        intent.putExtra("MSG", msg);
        // 3 回传
        setResult(RESULT_OK, intent);
        finish();
    }

    public void clickCancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
