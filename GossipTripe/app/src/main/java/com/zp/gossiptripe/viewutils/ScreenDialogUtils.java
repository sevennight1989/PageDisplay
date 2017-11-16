package com.zp.gossiptripe.viewutils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;

import com.zp.gossiptripe.main.personal.PersonConstants;

import java.io.File;

/**
 * Created by uiprj on 11/21/16.
 */

public class ScreenDialogUtils {


    public static void showDialog(final Activity atv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(atv);
        builder.setTitle("设置头像");
        builder.setItems(PersonConstants.items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int which) {
                switch (which) {
                    case 0:
                        Intent picIntent = new Intent(Intent.ACTION_PICK);
                        picIntent.setType("image/*");
                        atv.startActivityForResult(picIntent, PersonConstants.IMAGE_REQUEST_CODE);
                        break;

                    case 1:
                        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        camIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File
                        (Environment.getExternalStorageDirectory(), PersonConstants.IMAGE_FILE_NAME)));
                        atv.startActivityForResult(camIntent, PersonConstants.CAMERA_REQUEST_CODE);
                        break;
                }
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    public static void startPhotoZoom(Uri uri ,final Activity atv) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 480);
        intent.putExtra("outputY", 480);
        intent.putExtra("return-data", true);
        atv.startActivityForResult(intent,PersonConstants.RESULT_REQUEST_CODE);
    }
}
