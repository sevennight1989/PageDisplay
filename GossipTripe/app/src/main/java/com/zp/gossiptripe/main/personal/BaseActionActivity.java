package com.zp.gossiptripe.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.zp.gossiptripe.R;

/**
 * Created by uiprj on 11/28/16.
 */

public abstract class BaseActionActivity extends AppCompatActivity {

    protected TextView mActionBarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        View actionBarLayout = LayoutInflater.from(this).inflate(R.layout.actionbar_action, null);
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(actionBarLayout);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mActionBarTitle = (TextView) actionBarLayout.findViewById(R.id.action_title);
        mActionBarTitle.setText(getActionBarTitle());
    }

    public abstract String getActionBarTitle();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
