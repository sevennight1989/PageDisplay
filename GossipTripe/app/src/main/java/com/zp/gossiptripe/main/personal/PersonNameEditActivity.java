package com.zp.gossiptripe.main.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zp.gossiptripe.R;
import com.zp.gossiptripe.viewutils.view.ClearEditText;

public class PersonNameEditActivity extends PersonInfoEditActivity {

    ClearEditText mEditName;
    public static final String MODITY_NAME = "modifyName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_name_edit);
        mTitleInfo.setText(getResources().getString(R.string.personInfoName));
        mEditName = (ClearEditText) findViewById(R.id.edit_name);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.positive:
                Intent resultIntent = new Intent();
                resultIntent.putExtra(MODITY_NAME, mEditName.getText().toString());
                setResult(PersonConstants.NAME_REQUEST_CODE, resultIntent);
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    protected void doPostModifyData() {
        Toast.makeText(this, "Modify Name", Toast.LENGTH_SHORT).show();
    }
}
