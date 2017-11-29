package com.zp.gossiptripe.main.personal.login;

import com.zp.gossiptripe.main.personal.regist.model.UserBaseInfoBean;

/**
 * Created by uiprj on 11/28/16.
 */

public interface IOnActionListener {

    void loginSuccess(UserBaseInfoBean userBaseInfoBean);

    void loginFailed();

    void logOffSuccess();

    void logOffFailed();
}
