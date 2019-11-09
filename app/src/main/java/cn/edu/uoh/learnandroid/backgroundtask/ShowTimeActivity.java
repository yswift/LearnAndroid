package cn.edu.uoh.learnandroid.backgroundtask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.uoh.learnandroid.R;

public class ShowTimeActivity extends AppCompatActivity {
    private static final String TAG = "ShowTimeActivity";

    TextView txtTime;

    BroadcastReceiver timeTick = new BroadcastReceiver() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "show current time");
            date.setTime(System.currentTimeMillis());
            txtTime.setText(format.format(date));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_time);
        setTitle("广播Demo：显示时间");
        txtTime = findViewById(R.id.txt_time);
    }

    public void show(View view) {
        Log.i(TAG, "begin show current time");
        // 先触发一次，显示当前时间
        timeTick.onReceive(this, null);
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_TIME_TICK);
        registerReceiver(timeTick, intentFilter);
    }

    public void stop(View view) {
        Log.i(TAG, "stop show current time");
        unregisterReceiver(timeTick);
        txtTime.setText("显示时间");
    }

}
