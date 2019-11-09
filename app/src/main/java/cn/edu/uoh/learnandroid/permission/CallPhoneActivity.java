package cn.edu.uoh.learnandroid.permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import cn.edu.uoh.learnandroid.R;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class CallPhoneActivity extends AppCompatActivity {
    // 拨打电话请求码
    public final static int CALL_PHONE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);
        setTitle("拨打电话");
    }

    public void clickCallPhoneBtn(View view) {
        callPhone();
    }

    private void callPhone() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.CALL_PHONE},
                    0);
            return;
        }
        callPhoneWithNumber("10010");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhoneWithNumber("10010");
                } else {
                    Snackbar.make(findViewById(R.id.main_layout), "权限禁止", Snackbar.LENGTH_LONG).show();
                }
        }
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    private void callPhoneWithNumber(String tel) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel: " + tel);
        intent.setData(data);
        startActivity(intent);
    }

    // 使用权限框架
    public void clickCallPhoneBtn2(View view) {
        PermissionGen.with(this)
                .addRequestCode(CALL_PHONE_CODE)
                .permissions(Manifest.permission.CALL_PHONE)
                .request();
    }

    @PermissionSuccess(requestCode = CALL_PHONE_CODE)
    public void callPhone2(){
        callPhoneWithNumber("10086");
    }

    @PermissionFail(requestCode = CALL_PHONE_CODE)
    public void notifyUser(){
        Snackbar.make(findViewById(R.id.main_layout), "权限禁止", Snackbar.LENGTH_LONG).show();
    }
}
