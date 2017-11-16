package com.zp.gossiptripe.main.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.orhanobut.logger.Logger;
import com.zp.gossiptripe.R;
import com.zp.gossiptripe.utils.DateUitls;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonAgeEditActivity extends PersonInfoEditActivity {

    private TextView mEditAge;
    private TimePickerView mTimePickerView;
    String mBirthday;
    public static final String MODITY_BIRTHDAY = "modify_brithday";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_age_edit);
        mTitleInfo.setText(getResources().getString(R.string.selectBirthday));
        initViews();
    }

    private void initViews() {
        mEditAge = (TextView) findViewById(R.id.editAge);
        mEditAge.setText(getResources().getString(R.string.personAge, 7));
        mEditAge.setOnClickListener(this);
        initDatePicker();
        mTimePickerView.show();
    }

    private void initDatePicker(){
        mTimePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        mTimePickerView.setTime(new Date());
        mTimePickerView.setCyclic(false);
        mTimePickerView.setCancelable(false);
        mTimePickerView.setTitle(getResources().getString(R.string.selectBirthdayTitle));
        mTimePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                try {
                    mBirthday = getTime(date);
                    int age = DateUitls.getAge(date);

                    if (age < 0) {
                        String toast = getResources().getString(R.string.toastOverCurrentDate);
                        Toast.makeText(PersonAgeEditActivity.this, toast, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (age == 0) {
                        age = 1;
                    }
                    mEditAge.setText(getResources().getString(R.string.personAge, age));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.editAge:
                mTimePickerView.show();
                break;
            case R.id.positive:
                Intent birthdayIntent = new Intent();
                birthdayIntent.putExtra(MODITY_BIRTHDAY, mBirthday);
                setResult(PersonConstants.AGE_REQUEST_CODE, birthdayIntent);
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    protected void doPostModifyData() {

    }

    public String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
