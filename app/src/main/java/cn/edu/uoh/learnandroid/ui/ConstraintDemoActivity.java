package cn.edu.uoh.learnandroid.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import cn.edu.uoh.learnandroid.R;

public class ConstraintDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_demo);
        setTitle("Constraint layout");
    }

    public void showDialog(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Android 对话框")
                .setMessage("通过对话框的提醒消息")
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                            }
                        }).show();
    }
}
