package com.zp.gossiptripe.main.personal.forgetpsd;

import android.os.Bundle;

import com.zp.gossiptripe.R;
import com.zp.gossiptripe.main.personal.BaseActionActivity;

public class ForgetPasswordActivity extends BaseActionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }

    @Override
    public String getActionBarTitle() {
        return getResources().getString(R.string.actionbartitleforgetpsd);
    }
}
