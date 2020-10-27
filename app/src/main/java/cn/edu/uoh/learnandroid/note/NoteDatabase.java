package cn.edu.uoh.learnandroid.note;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// fix error:You can either provide `room.schemaLocation` annotation processor argument OR set exportSchema to false.
@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static volatile NoteDatabase instance;

    static synchronized NoteDatabase getInstance(Context context) {
        // 在生产环境中不推荐使用allowMainThreadQueries
        // 应该在线程中访问数据库
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    NoteDatabase.class,
                    "notes")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract NoteDao getNoteDao();
}


