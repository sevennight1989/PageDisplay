package com.zp.gossiptripe.main.personal.regist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.orhanobut.logger.Logger;
import com.zp.gossiptripe.R;
import com.zp.gossiptripe.main.personal.BaseActionActivity;
import com.zp.gossiptripe.main.personal.PersonBean;
import com.zp.gossiptripe.main.personal.PersonConstants;
import com.zp.gossiptripe.main.personal.PersonalFragment;
import com.zp.gossiptripe.main.personal.regist.model.RegistBean;
import com.zp.gossiptripe.utils.DateUitls;
import com.zp.gossiptripe.viewutils.ScreenDialogUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistAccountActivity extends BaseActionActivity implements View.OnClickListener, IRegistView {

    public static final int ERROR_CODE_REPEAT = 0;
    public static final int ERROT_CODE_NULL = 1;
    private RegistPresent mRegistPresent;
    private TimePickerView mTimePickerView;
    private CityPicker mCityPicker;
    private Button mRegistNextBt;
    private CircleImageView mHeadIcon;
    private EditText mUserName;
    private EditText mPassword;
    private EditText mName;
    private EditText mEmail;
    private TextView mBirthday;
    private TextView mProvince;
    private EditText mAddress;
    private EditText mOrganization;
    Bitmap uploadBp;
    private static final String TAG ="PengLog";
    private String url = "http://192.168.1.166:8080/PDServer/common/regist.do";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_account);
        mRegistPresent = new RegistPresent(this);
        initViews();
    }


    private void initViews() {
        mRegistNextBt = (Button) findViewById(R.id.nextBt);
        mRegistNextBt.setOnClickListener(this);
        mHeadIcon = (CircleImageView) findViewById(R.id.registHead);
        mUserName = (EditText) findViewById(R.id.registUserName);
        mPassword = (EditText) findViewById(R.id.registUserPassword);
        mEmail = (EditText) findViewById(R.id.registEmail);
        mName = (EditText) findViewById(R.id.registPersonName);
        mProvince = (TextView) findViewById(R.id.registProvince);
        mBirthday = (TextView) findViewById(R.id.registBirthday);
        mAddress = (EditText) findViewById(R.id.registAddress);
        mOrganization = (EditText) findViewById(R.id.registOrganization);
        mHeadIcon.setOnClickListener(this);
        mBirthday.setOnClickListener(this);
        mProvince.setOnClickListener(this);
        initDatePicker();
        initCityPicker();

    }

    private void initDatePicker() {
        mTimePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        mTimePickerView.setTime(new Date());
        mTimePickerView.setCyclic(false);
        mTimePickerView.setCancelable(false);
        mTimePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                try {
                    int age = DateUitls.getAge(date);
                    if (age < 0) {
                        String toast = getResources().getString(R.string.toastOverCurrentDate);
                        Toast.makeText(RegistAccountActivity.this, toast, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mBirthday.setText(getTime(date));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void initCityPicker() {
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

    public String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public String getActionBarTitle() {
        return getResources().getString(R.string.actionbartitlefillininfo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registHead:
                ScreenDialogUtils.showDialog(this);
                break;
            case R.id.nextBt:
                mRegistPresent.registAccount();
                break;
            case R.id.registBirthday:
                KeyboardUtils.hideSoftInput(this);
                mTimePickerView.show();
                break;
            case R.id.registProvince:
                KeyboardUtils.hideSoftInput(this);
                mCityPicker.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
            if (SDCardUtils.isSDCardEnable()) {
                ImageUtils.save(uploadBp, getIconPath(), Bitmap.CompressFormat.JPEG);
            }
            mHeadIcon.setImageBitmap(uploadBp);
//            updateImage(url,getIconPath());
        }
    }

    private void updateImage(String url,String path){
        File file = new File(path);
        if(!file.exists()){
            Logger.d("上传文件不存在");
            return;
        }
        RequestParams params = new RequestParams(url);
        params.addParameter("fileName",file.getName());
//        params.addBodyParameter(file.getName(),file,"image/*"); 图片格式
        params.addBodyParameter(file.getName(),file,"multipart/form-data");//视频格式


        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Logger.d(TAG,"onSuccess " + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Logger.d(TAG,"onError " + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public RegistBean getRegistBean() {
        RegistBean registBean = new RegistBean();
        registBean.setUsername(mUserName.getText().toString());
        registBean.setPassword(mPassword.getText().toString());
        registBean.setEmail(mEmail.getText().toString());
        registBean.setName(mName.getText().toString());
        registBean.setBirthday(mBirthday.getText().toString());
        registBean.setAddress(mProvince.getText().toString());
        registBean.setDetialaddress(mAddress.getText().toString());
        registBean.setMechanism(mOrganization.getText().toString());
        return registBean;
    }

    @Override
    public void onRegistSuccess(PersonBean personBean) {
        Toast.makeText(this, getResources().getText(R.string.registSuccess), Toast.LENGTH_SHORT).show();
        Intent backIntent = new Intent();
        Bundle b = new Bundle();
        b.putSerializable(PersonalFragment.REQUEST_REGIST_INFO, personBean);
        b.putString(PersonalFragment.LOGIN_USER_INFO, mUserName.getText().toString());
        backIntent.putExtras(b);
        setResult(PersonalFragment.REGIST_REQUEST_CODE, backIntent);
        finish();
    }

    private String getIconPath() {
        return SDCardUtils.getSDCardPaths(false).get(0)  + "headicon.jpeg";
    }

    @Override
    public void onFailed(int errorCode) {
        String errorMsg = "";
        switch (errorCode) {
            case ERROR_CODE_REPEAT:
                errorMsg = getResources().getString(R.string.hasRepeatUserName);
                break;
            case ERROT_CODE_NULL:
                errorMsg = getResources().getString(R.string.hasNullItem);
                break;
        }
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }
}
