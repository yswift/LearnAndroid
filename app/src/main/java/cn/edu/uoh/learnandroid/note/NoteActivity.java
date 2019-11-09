package cn.edu.uoh.learnandroid.note;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cn.edu.uoh.learnandroid.R;

public class NoteActivity extends AppCompatActivity {
    private static final String TAG = "NoteActivity";

    ListView listView;
    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle("记事本案例");

        listView = findViewById(R.id.note_list);
        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Note note = notes.get(position);
            Log.i(TAG, "on click, edit note: " + note);
            NoteEditActivity.startActivity(this, note.id);
        });
        updateTitleList();
    }

    // 更新记事本列表
    void updateTitleList() {
        notes = getNotes();
        Log.i(TAG, "initTitleList: " + notes);
        String[] titles = notes.stream().map((n) -> n.title).toArray(String[]::new);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(adapter);
    }

    List<Note> getNotes() {
        NoteDatabase noteDb = NoteDatabase.getInstance(getApplicationContext());
        NoteDao dao = noteDb.getNoteDao();
        return dao.getAll();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            updateTitleList();
        }
    }

    public void crateNote(View view) {
        NoteEditActivity.startActivity(this, NoteEditActivity.NewNoteId);
    }
}
