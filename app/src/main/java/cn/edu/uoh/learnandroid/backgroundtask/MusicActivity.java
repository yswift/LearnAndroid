package cn.edu.uoh.learnandroid.backgroundtask;

import android.Manifest;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import cn.edu.uoh.learnandroid.R;
import kr.co.namee.permissiongen.PermissionGen;

public class MusicActivity extends AppCompatActivity {
    private static final String TAG = "MusicActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        // 获取音乐需要如下的权限
        PermissionGen.with(this)
                .addRequestCode(200) // 请求码
                .permissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .request();

        Log.i(TAG, "onCreate: scan /sdcard/Music to find mp3");
        SongFetcher.scanFolder(this, "/sdcard/Music");
    }

    public void startServer(View view) {
        Log.i(TAG, "startServer: ");
        Intent intent = new Intent(this, MusicServer.class);
        startForegroundService()
        startService(intent);
    }

    public void stopServer(View view) {
        Log.i(TAG, "stopServer: ");
        Intent intent = new Intent(this, MusicServer.class);
        stopService(intent);
    }
}
