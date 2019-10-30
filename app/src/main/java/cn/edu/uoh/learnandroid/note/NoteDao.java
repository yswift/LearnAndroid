package cn.edu.uoh.learnandroid.note;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Query("SELECT * FROM note WHERE id = :id")
    Note findById(String id);

    @Insert
    void insertAll(Note... notes);

    @Delete
    void delete(Note note);

}
