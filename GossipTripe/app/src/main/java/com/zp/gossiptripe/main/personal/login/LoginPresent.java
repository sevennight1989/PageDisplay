package com.zp.gossiptripe.main.personal.login;

import com.blankj.utilcode.util.EncryptUtils;
import com.zp.gossiptripe.main.personal.regist.model.UserBaseInfoBean;

/**
 * Created by uiprj on 11/28/16.
 */

public class LoginPresent {

    IPersonInfoView mPersonInfoView;
    ActionImpl action;

    public LoginPresent(IPersonInfoView personInfoView) {
        mPersonInfoView = personInfoView;
        action = new ActionImpl();
    }

    public void login() {

        String userName = mPersonInfoView.getUserName();
        String password = mPersonInfoView.getPassword();
        if (userName.equals("") || password.equals("")) {
            mPersonInfoView.showFailedError(1);
            return;
        }
        action.login(userName, EncryptUtils.encryptMD5ToString(password), new IOnActionListener() {
            @Override
            public void loginSuccess(UserBaseInfoBean userBaseInfoBean) {
                mPersonInfoView.loginSuccess(userBaseInfoBean);
            }

            @Override
            public void loginFailed() {
                mPersonInfoView.showFailedError(0);
            }

            @Override
            public void logOffSuccess() {

            }

            @Override
            public void logOffFailed() {

            }
        });
    }

    public void logOff() {
        action.logoff(mPersonInfoView.getLoginUserName(), new IOnActionListener() {
            @Override
            public void loginSuccess(UserBaseInfoBean userBaseInfoBean) {

            }

            @Override
            public void loginFailed() {

            }

            @Override
            public void logOffSuccess() {
                mPersonInfoView.logoffSuccess();
            }

            @Override
            public void logOffFailed() {
                mPersonInfoView.showFailedError(2);
            }
        });
    }
}
