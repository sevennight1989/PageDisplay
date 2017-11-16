package com.zp.gossiptripe.main.personal;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zp.gossiptripe.R;

/**
 * Created by uiprj on 11/21/16.
 */

public abstract class PersonInfoEditActivity extends AppCompatActivity implements View.OnClickListener {
    protected Button mCancel;
    protected Button mSave;
    protected TextView mTitleInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        View actionBarLayout = LayoutInflater.from(this).inflate(R.layout.addproduct_actionbar, null);
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(actionBarLayout);
        }
        mCancel = (Button) actionBarLayout.findViewById(R.id.negative);
        mSave = (Button) actionBarLayout.findViewById(R.id.positive);
        mTitleInfo = (TextView) actionBarLayout.findViewById(R.id.titleInfo);
        mCancel.setOnClickListener(this);
        mSave.setOnClickListener(this);
        mCancel.setText(getResources().getText(R.string.cancel));
        mSave.setText(getResources().getText(R.string.save));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.negative:
                finish();

            case R.id.positive:
                doPostModifyData();
                break;
        }
    }

    protected abstract void doPostModifyData();
}
