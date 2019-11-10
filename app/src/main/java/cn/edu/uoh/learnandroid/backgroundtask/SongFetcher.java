package cn.edu.uoh.learnandroid.backgroundtask;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class SongFetcher {
    public static ArrayList<Song> fetchSong(Context context) {
        ArrayList<Song> songList = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (cursor == null) {
            return songList;
        }
        while (cursor.moveToNext()) {
            Song song = new Song();
            song.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            song.id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
            song.singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
            song.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
            song.duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
            song.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
            song.albumId = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
            songList.add(song);
        }
        cursor.close();

        return songList;
    }

    public static void scanFolder(Context context, String path) {
        File file = new File(path);

        if (!file.isDirectory()) {
            return;
        }
        // 获取文件夹下的所有文件
        File[] files = file.listFiles();
        // 得到所有mp3文件的文件名
        String[] filePaths = Arrays.stream(files)
                .map(f -> f.getAbsolutePath())
                .filter(fn -> fn.endsWith(".mp3"))
                .toArray(String[]::new);
        // 扫描这些MP3文件
        MediaScannerConnection.scanFile(context,
                filePaths,
                null,
                null);
        // 扫描子文件夹
        Arrays.stream(files)
                .filter(f -> f.isDirectory())
                .forEach(f -> scanFolder(context, f.getAbsolutePath()));
    }
}
