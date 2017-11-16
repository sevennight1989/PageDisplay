package com.zp.gossiptripe.main.personal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lljjcoder.citypickerview.widget.CityPicker;
import com.orhanobut.logger.Logger;
import com.zp.gossiptripe.R;
import com.zp.gossiptripe.viewutils.view.ClearEditText;

public class PersonAddressEditActivity extends PersonInfoEditActivity {

    private TextView mProvince;
    private ClearEditText mAddress;
    private CityPicker mCityPicker;

    public static final String MODIFY_ADDRESS = "modify_address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_address_edit);
        mTitleInfo.setText(getResources().getString(R.string.personInfoAddress));
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initViews() {
        mProvince = (TextView) findViewById(R.id.provinceEdit);
        mAddress = (ClearEditText) findViewById(R.id.edit_address);
        mProvince.setText("江苏苏州");
        mAddress.setHint("观前街23号");
        mProvince.setOnClickListener(this);
        initCityPicker();

        mCityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                StringBuilder sb = new StringBuilder();
                sb.append(citySelected[0]);
                sb.append(citySelected[1]);
                sb.append(citySelected[2]);
                mProvince.setText(sb);
            }
        });
    }

    private void initCityPicker(){
        mCityPicker = new CityPicker.Builder(this).textSize(20)
        .title("选择地区")
        .titleBackgroundColor("#FFFFFF")
        .confirTextColor("#000000")
        .cancelTextColor("#000000")
        .province("江苏省")
        .city("常州市")
        .district("新北区")
        .textColor(Color.parseColor("#000000"))
        .provinceCyclic(true)
        .cityCyclic(false)
        .districtCyclic(false)
        .visibleItemsCount(7)
        .itemPadding(10)
        .build();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.provinceEdit:
                mCityPicker.show();
                break;

            case R.id.positive:
                Intent addressIntent = new Intent();
                StringBuilder sb = new StringBuilder();
                sb.append(mProvince.getText());
                sb.append(mAddress.getText());
                addressIntent.putExtra(MODIFY_ADDRESS, sb.toString());
                Logger.d("sb "+sb);
                setResult(PersonConstants.ADDRESS_REQUEST_CODE, addressIntent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPostModifyData() {

    }
}
