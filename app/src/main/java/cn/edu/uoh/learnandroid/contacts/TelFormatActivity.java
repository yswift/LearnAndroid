package cn.edu.uoh.learnandroid.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import cn.edu.uoh.learnandroid.R;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class TelFormatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel_format);
    }

    public void clickFormatBtn(View view) {
        // 动态获取权限
        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_CONTACTS)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    private void doFormat() {
        Snackbar.make(findViewById(R.id.main_layout), "正在格式化", Snackbar.LENGTH_LONG).show();
        TelNumberFormater.format(this);
        Snackbar.make(findViewById(R.id.main_layout), "格式化完成", Snackbar.LENGTH_LONG).show();
    }

    @PermissionFail(requestCode = 100)
    private void notifyUser(){
        Snackbar.make(findViewById(R.id.main_layout), "权限禁止", Snackbar.LENGTH_LONG).show();
    }
}
