package cn.edu.uoh.learnandroid.note;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note order by id desc")
    List<Note> getAll();

    @Query("SELECT * FROM note WHERE id = :id")
    Note findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Note... notes);

    @Delete
    void delete(Note note);

}
