package com.zp.gossiptripe.main.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zp.gossiptripe.R;

public class PersonOrganizationActivity extends PersonInfoEditActivity {
    private TextView mEidtOrganization;
    public static final String MODIFY_ORGANIZATION = "modify_organization";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_organization);
        mTitleInfo.setText(getResources().getString(R.string.personInfoOrganization));
        mEidtOrganization = (TextView) findViewById(R.id.edit_organization);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.positive:
                Intent addressIntent = new Intent();
                addressIntent.putExtra(MODIFY_ORGANIZATION, mEidtOrganization.getText().toString());
                setResult(PersonConstants.ORGANIZATION_REQUEST_CODE, addressIntent);
                finish();
                break;

        }
    }

    @Override
    protected void doPostModifyData() {

    }
}
