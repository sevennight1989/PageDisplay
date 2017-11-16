package com.zp.gossiptripe;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

import java.util.ArrayList;

public class BaseActivity extends SlidingActivity {
    static String PERMISSIONS[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBehindContentView(R.layout.leftmenu);
        // configure the SlidingMenu
        final SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);

        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (!checkPermissionResult(permissions, grantResults)) {
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        if (sdkVersion > 23) {
            checkPermissionsForRequest(this, PERMISSIONS, PERMISSION_REQUEST_CODE);
        }

    }

    private void checkPermissionsForRequest(Activity activity, String[] permissions,
                                            int operationHandle) {
//        if (permissions == null)
//            return;
//        boolean isPermissionGranted = true;
//        ArrayList<String> permissionList = new ArrayList<>();
//        for (String permission : permissions) {
//            if (PackageManager.PERMISSION_GRANTED != activity.checkSelfPermission(permission)) {
//                permissionList.add(permission);
//                isPermissionGranted = false;
//            }
//        }
//        if (!isPermissionGranted) {
//            String[] permissionArray = new String[permissionList.size()];
//            permissionList.toArray(permissionArray);
//            activity.requestPermissions(permissionArray, operationHandle);
//        }
    }

    private boolean checkPermissionResult(String[] permissions, int[] grantResults) {
        if (permissions == null || grantResults == null || permissions.length == 0
        || grantResults.length == 0) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
