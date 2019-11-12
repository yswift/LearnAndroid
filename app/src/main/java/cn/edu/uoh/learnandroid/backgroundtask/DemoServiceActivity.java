package cn.edu.uoh.learnandroid.backgroundtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import cn.edu.uoh.learnandroid.R;

public class DemoServiceActivity extends AppCompatActivity {
    int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_service);
        setTitle("服务Demo");
    }

    public void startDemoIntentServer(View view) {
        DemoIntentService.startAction(this, "DemoIntentServer", num++);
    }

    // 绑定服务相关
    DemoBindService mService;
    boolean mBound = false;
    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            DemoBindService.DemoBinder binder = (DemoBindService.DemoBinder) service;
            mService = binder.getService();
            mBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    public void startDemoBindServer(View view) {
        if (!mBound) {
            Intent intent = new Intent(this, DemoBindService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    }

    public void stopDemoBindServer(View view) {
        if (mBound) {
            unbindService(connection);
            mBound = false;
        }
    }

    public void getNumber(View view) {
        if (mBound) {
            int num = mService.getRandomNumber();
            Toast.makeText(this, "number: " + num, Toast.LENGTH_SHORT).show();
        }
    }


}
