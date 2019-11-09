package cn.edu.uoh.learnandroid.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import cn.edu.uoh.learnandroid.R;

public class NotifyMsgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_msg);
        setTitle("提示、通知消息");
    }

    public void showToasts(View view) {
        Toast.makeText(this,"消息文本", Toast.LENGTH_LONG)
                .show();
    }

    public void showSnackbar(View view) {
        Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Snackbar消息", Snackbar.LENGTH_SHORT)
                .show();
    }

    public void showSnackbarWithAction(View view) {
        Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Snackbar消息", Snackbar.LENGTH_LONG)
                .setAction("显示对话框", (v) -> {
                    new AlertDialog.Builder(this).setTitle("对话框").setMessage("snckbar 消息").show();
                })
                .show();
    }
}
