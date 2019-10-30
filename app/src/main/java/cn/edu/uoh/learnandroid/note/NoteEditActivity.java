package cn.edu.uoh.learnandroid.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cn.edu.uoh.learnandroid.R;
import cn.edu.uoh.learnandroid.databinding.ActivityNoteEditBinding;

public class NoteEditActivity extends AppCompatActivity {
    private static final String TAG = "NoteEditActivity";
    private static final String IdKey = "NoteId";
    static final int NewNoteId = 0;

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> content = new ObservableField<>();

    // 当前的Note id
    private int id = NewNoteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNoteEditBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_note_edit);
        binding.setTitle(title);
        binding.setContent(content);
        getNote();
    }

    private void getNote() {
        Intent intent = getIntent();
        id = intent.getIntExtra(IdKey, NewNoteId);
        Log.i(TAG, "getNote: id = " + id);
        if (id == NewNoteId) {
            return;
        }
        NoteDatabase noteDb = NoteDatabase.getInstance(getApplicationContext());
        NoteDao dao = noteDb.getNoteDao();
        Note note = dao.findById(id);
        Log.i(TAG, "getNote: " + note);

        title.set(note.title);
        content.set(note.content);
    }

    public void save(View view) {
        // 取用户输入，创建Note对象
        Note note = new Note();
        note.id = id;
        note.title = title.get();
        note.content = content.get();
        Log.i(TAG, "save: " + note);
        // 保存
        NoteDatabase noteDb = NoteDatabase.getInstance(getApplicationContext());
        NoteDao dao = noteDb.getNoteDao();
        dao.insertAll(note);
        Log.i(TAG, "save: finished");
        setResult(RESULT_OK);
        finish();
    }

    public void cancel(View view) {
        // 结束当前Activity
        setResult(RESULT_CANCELED);
        finish();
    }

    /**
     * 启动NoteEditActivity
     *
     * @param noteId 要编辑的NoteId，如果是0代表新建一个
     */
    static void startActivity(Activity context, int noteId) {
        Intent intent = new Intent(context, NoteEditActivity.class);
        intent.putExtra(IdKey, noteId);
        context.startActivityForResult(intent, 1);
    }
}
