package com.zp.gossiptripe.main.personal;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.orhanobut.logger.Logger;
import com.zp.gossiptripe.R;
import com.zp.gossiptripe.main.personal.forgetpsd.ForgetPasswordActivity;
import com.zp.gossiptripe.main.personal.login.IPersonInfoView;
import com.zp.gossiptripe.main.personal.login.LoginPresent;
import com.zp.gossiptripe.main.personal.regist.RegistAccountActivity;
import com.zp.gossiptripe.main.personal.regist.model.UserBaseInfoBean;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_CANCELED;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment implements View.OnClickListener, IPersonInfoView {

    private View root;
    private CircleImageView mHead;
    private TextView mName;
    private TextView mAgeAndLocation;
    private TextView mOrganizaion;
    private LinearLayout mPersonInfoSet;
    private UserBaseInfoBean mPersonBeam;

    private boolean isLogin;
    private static final String LOGIN_TAG = "login_tag";
    public static final String TOPERSON_INFO = "to_person_info";
    public static final String LOGIN_USER_INFO = "login_user_info";
    private SPUtils mSp;
    private RelativeLayout mLoginRoot;
    private RelativeLayout mPersonInfoRoot;

    private EditText mUserName;
    private EditText mPassword;
    private Button mLoginBt;
    private Button mRegistBt;
    private Button mForgetBt;
    private TextView mLogOff;
    boolean defaultLoginStatus;

    private static final int LOGIN_FAILED_ERROR = 0;
    private static final int LOGIN_FAILED_EMPTY = 1;
    private static final int LOGOFF_FAILED = 2;
    public static final int REGIST_REQUEST_CODE = 2 << 1;
    public static final String REQUEST_REGIST_INFO = "regist_info";


    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }

    LoginPresent mPresent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSp = SPUtils.getInstance(this.getClass().getSimpleName());
        mPresent = new LoginPresent(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_personal, container, false);
        defaultLoginStatus = getResources().getBoolean(R.bool.defaultLoginStatus);
        isLogin = mSp.getBoolean(LOGIN_TAG, defaultLoginStatus);
        initViews();
        if (isLogin) {
            setPersonInfoData();
        } else {
            setLoginData();
        }
        initViews();
        updateView();
        return root;
    }


    private void initViews() {
        mPersonInfoSet = (LinearLayout) root.findViewById(R.id.personInfoSet);
        mHead = (CircleImageView) root.findViewById(R.id.personHead);
        mName = (TextView) root.findViewById(R.id.personName);
        mAgeAndLocation = (TextView) root.findViewById(R.id.personAgeAndLocation);
        mOrganizaion = (TextView) root.findViewById(R.id.personOrganization);
        mLoginRoot = (RelativeLayout) root.findViewById(R.id.person_login_guide_root);
        mPersonInfoRoot = (RelativeLayout) root.findViewById(R.id.person_info_set_root);
        mUserName = (EditText) root.findViewById(R.id.usernameValue);
        mPassword = (EditText) root.findViewById(R.id.passwordValue);
        mLoginBt = (Button) root.findViewById(R.id.loginBt);
        mRegistBt = (Button) root.findViewById(R.id.registAccount);
        mForgetBt = (Button) root.findViewById(R.id.forgetPassword);
        mLogOff = (TextView) root.findViewById(R.id.logoff);
        mForgetBt.setOnClickListener(this);
        mRegistBt.setOnClickListener(this);
        mLoginBt.setOnClickListener(this);
        mPersonInfoSet.setOnClickListener(this);
        mLogOff.setOnClickListener(this);
    }

    private void updateView() {
        mLoginRoot.setVisibility(isLogin ? View.GONE : View.VISIBLE);
        mPersonInfoRoot.setVisibility(isLogin ? View.VISIBLE : View.GONE);
    }

    private void setLoginData() {
    }

    private void setPersonInfoData() {
//        Picasso.with(getContext()).load(mPersonBeam.getmIconPath()).placeholder(R.mipmap.touxiang).into(mHead);
        mName.setText(mPersonBeam.getName());
        mAgeAndLocation.setText(getResources().getString(R.string.ageAndLocation, 5, mPersonBeam.getAddress()));
        mOrganizaion.setText(mPersonBeam.getMechanism());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personInfoSet:
                Intent personInfoIntent = new Intent(getContext(), PersonInfoActivity.class);
                Bundle b = new Bundle();
                b.putParcelable(TOPERSON_INFO, mPersonBeam);
                personInfoIntent.putExtras(b);
                startActivity(personInfoIntent);
                break;

            case R.id.loginBt:
                mPresent.login();
                break;

            case R.id.registAccount:
                Intent registAccountIntent = new Intent(getContext(), RegistAccountActivity.class);
                startActivityForResult(registAccountIntent,REGIST_REQUEST_CODE);
                break;

            case R.id.forgetPassword:
                Intent forgetPasswordIntent = new Intent(getContext(), ForgetPasswordActivity.class);
                startActivity(forgetPasswordIntent);
                break;

            case R.id.logoff:
                mPresent.logOff();
                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case REGIST_REQUEST_CODE:
                    mPersonBeam = data.getParcelableExtra(REQUEST_REGIST_INFO);
                    mSp.put(LOGIN_TAG, true);
                    mSp.put(LOGIN_USER_INFO, data.getStringExtra(LOGIN_USER_INFO));
                    Logger.d(data.getStringExtra(LOGIN_USER_INFO));
                    isLogin = mSp.getBoolean(LOGIN_TAG, defaultLoginStatus);
                    updateView();
                    setPersonInfoData();
                    break;
            }


            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public String getUserName() {
        return mUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString();
    }

    @Override
    public void loginSuccess(UserBaseInfoBean userBaseInfoBean) {
        Toast.makeText(getContext(), getResources().getString(R.string.loginSuccess), Toast.LENGTH_SHORT).show();
        KeyboardUtils.hideSoftInput(getActivity());
        mSp.put(LOGIN_TAG, true);
        mSp.put(LOGIN_USER_INFO, getUserName());
        isLogin = mSp.getBoolean(LOGIN_TAG, defaultLoginStatus);
        mPersonBeam = userBaseInfoBean;
        updateView();
        setPersonInfoData();
    }

    @Override
    public void logoffSuccess() {
        Toast.makeText(getContext(), getResources().getString(R.string.logoffSuccess), Toast.LENGTH_SHORT).show();
        mSp.put(LOGIN_TAG, false);
        mSp.put(LOGIN_USER_INFO, "");
        isLogin = mSp.getBoolean(LOGIN_TAG, defaultLoginStatus);
        updateView();
    }

    @Override
    public void showFailedError(int errorCode) {
        String errorMessage = "";
        switch (errorCode) {
            case LOGIN_FAILED_ERROR:
                errorMessage = getResources().getString(R.string.loginFailed);
                break;

            case LOGIN_FAILED_EMPTY:
                errorMessage = getResources().getString(R.string.loginFailedInputError);
                break;

            case LOGOFF_FAILED:
                errorMessage = getResources().getString(R.string.logofffailed);
                break;
        }

        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }


    @Override
    public String getLoginUserName() {
        return mSp.getString(LOGIN_USER_INFO, "");
    }
}
