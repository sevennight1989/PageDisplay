package com.zp.gossiptripe.product;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.orhanobut.logger.Logger;
import com.zp.gossiptripe.R;
import com.zp.gossiptripe.event.ProductEvent;
import com.zp.gossiptripe.main.personal.PersonConstants;
import com.zp.gossiptripe.viewutils.ScreenDialogUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    Button mCancel;
    Button mPublish;
    TextView mTitleInfo;
    ImageView mAddImage;
    ImageView mCleanImage;
    Bitmap uploadBp;

    EditText mProductNameEt;
    EditText mCreateAgeEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        ActionBar actionBar = getSupportActionBar();
        View actionBarLayout = LayoutInflater.from(this).inflate(R.layout.addproduct_actionbar, null);
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(actionBarLayout);
        }
        mCancel = (Button) actionBarLayout.findViewById(R.id.negative);
        mPublish = (Button) actionBarLayout.findViewById(R.id.positive);
        mTitleInfo = (TextView) actionBarLayout.findViewById(R.id.titleInfo);
        mCancel.setOnClickListener(this);
        mPublish.setOnClickListener(this);
        mAddImage = (ImageView) findViewById(R.id.addimage);
        mAddImage.setOnClickListener(this);
        mCleanImage = (ImageView) findViewById(R.id.cleanimage);
        mCleanImage.setOnClickListener(this);
        mCleanImage.setVisibility(View.GONE);
        mProductNameEt = (EditText) findViewById(R.id.product_name);
        mCreateAgeEt = (EditText) findViewById(R.id.create_age);
        mTitleInfo.setText(getResources().getString(R.string.publishProduct));
        mCancel.setText(getResources().getString(R.string.cancel));
        mPublish.setText(getResources().getText(R.string.publish));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.negative:
                EventBus.getDefault().post(new ProductEvent("publish failed", "error"));
                finish();
                break;
            case R.id.addimage:
                ScreenDialogUtils.showDialog(this);
                break;
            case R.id.cleanimage:
                mCleanImage.setVisibility(View.GONE);
                mAddImage.setImageResource(R.mipmap.add);
                break;
            case R.id.positive:
                if (mProductNameEt.getText().toString().equals("") || mCreateAgeEt.getText()
                .toString().equals("")) {
                    showWarringDialog();
                } else {
                    publishProduct();
                }
                break;
            default:
                break;
        }

    }

    private void showWarringDialog() {
        Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("作品未填写完整");
        builder.setNegativeButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    private void publishProduct() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case PersonConstants.IMAGE_REQUEST_CODE:
                    ScreenDialogUtils.startPhotoZoom(data.getData(), this);
                    break;

                case PersonConstants.CAMERA_REQUEST_CODE:
                    File file = new File(Environment.getExternalStorageDirectory(), "/" +
                    PersonConstants.IMAGE_FILE_NAME);
                    ScreenDialogUtils.startPhotoZoom(Uri.fromFile(file), this);
                    break;

                case PersonConstants.RESULT_REQUEST_CODE:
                    if (data != null) {
                        getImageToView(data);
                    }
                    break;
            }
        }
    }


    private void getImageToView(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            uploadBp = bundle.getParcelable("data");
            String path = SDCardUtils.getSDCardPaths(false).get(0);
            if (SDCardUtils.isSDCardEnable()) {
                ImageUtils.save(uploadBp, path + File.separator + PersonConstants.PRODUCT_UPDATE_NAME, Bitmap.CompressFormat.JPEG);
            }
            mAddImage.setImageBitmap(uploadBp);
            mCleanImage.setVisibility(View.VISIBLE);
        }
    }

}

