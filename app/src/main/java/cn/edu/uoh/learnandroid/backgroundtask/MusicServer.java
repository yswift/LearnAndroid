package cn.edu.uoh.learnandroid.backgroundtask;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.IOException;
import java.util.ArrayList;

import cn.edu.uoh.learnandroid.R;

public class MusicServer extends IntentService {
    private static final String TAG = "MusicServer";
    // notification channel id
    static final String CHANNEL_ID = "song";
    static final int notificationId = 1;
    // 用于播放，暂停的intent action
    public final static String PLAY_MUSIC = "cn.edu.uoh.learnandroid.music.play";
    public final static String PAUSE_MUSIC = "cn.edu.uoh.learnandroid.music.pause";

    ArrayList<Song> songList;
    // 当前播放的音乐index
    int currentSongIndex = -1;

    MediaPlayer mediaPlayer = new MediaPlayer();

    // 播放广播接收器
    BroadcastReceiver playReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive: play");
            if (!mediaPlayer.isPlaying()) mediaPlayer.start();
        }
    };

    // 暂停广播接收器
    BroadcastReceiver pauseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive: pause");
            if (mediaPlayer.isPlaying()) mediaPlayer.pause();
        }
    };

    public MusicServer() {
        super("MusicServer");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        // 建立通知channel
        createNotificationChannel();
        // 获取音乐列表
        songList = SongFetcher.fetchSong(this);
        Log.i(TAG, "Fetch song: " + songList.toString());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        // 建立通知channel
//        createNotificationChannel();
//        // 获取音乐列表
//        songList = SongFetcher.fetchSong(this);
//        Log.i(TAG, "Fetch song: " + songList.toString());
//        // 准备播放
//        createMediaPlayer();
//        // 注册播放intent
//        IntentFilter playIntentFilter = new IntentFilter(PLAY_MUSIC);
//        registerReceiver(playReceiver, playIntentFilter);
//        // 注册暂停intent
//        IntentFilter pauseIntentFilter = new IntentFilter(PAUSE_MUSIC);
//        registerReceiver(pauseReceiver, pauseIntentFilter);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) mediaPlayer.stop();
        mediaPlayer.release();
        clearNotification();
    }



    private void createMediaPlayer() {
        if (songList.size() < 1) {
            return;
        }
        mediaPlayer.setOnCompletionListener((mp) -> {
            playNextSong();
        });
        playNextSong();
    }

    private void playNextSong() {
        if (songList.size() < 1) {
            return;
        }
        // 下一首
        ++currentSongIndex;
        // 循环播放
        currentSongIndex %= songList.size();
        Song song = songList.get(currentSongIndex);
        try {
            mediaPlayer.setDataSource(song.path);
        } catch (IOException e) {
            Log.e(TAG, "playNextSong: failed, name = " + song.title, e);
            playNextSong();
        }
        notifyCurrentSong(song);
        mediaPlayer.start();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "channel name", importance);
            channel.setDescription("channel description");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // 在通知显示当前播放的音乐
    void notifyCurrentSong(Song song) {
        // 播放广播
        Intent playIntent = new Intent(PLAY_MUSIC);
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(this, 0, playIntent, 0);
        // 暂停广播
        Intent pauseIntent = new Intent(PLAY_MUSIC);
        PendingIntent pausePendingIntent = PendingIntent.getBroadcast(this, 0, pauseIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.cloudy5)
                .setContentTitle(song.title)
                .setContentText(song.singer)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.cloudy5, "播放", playPendingIntent)
                .addAction(R.drawable.cloudy5, "暂停", pausePendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, builder.build());
    }

    void clearNotification() {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.cancel(notificationId);
    }


}
