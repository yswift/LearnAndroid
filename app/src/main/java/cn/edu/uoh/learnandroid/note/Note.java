package cn.edu.uoh.learnandroid.note;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    public String content;
}
