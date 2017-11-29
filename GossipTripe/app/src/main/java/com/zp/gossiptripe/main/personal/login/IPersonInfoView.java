package com.zp.gossiptripe.main.personal.login;


import com.zp.gossiptripe.main.personal.regist.model.UserBaseInfoBean;

/**
 * Created by uiprj on 11/28/16.
 */

public interface IPersonInfoView {

    public String getUserName();

    public String getPassword();

    void loginSuccess(UserBaseInfoBean userBaseInfoBean);

    void showFailedError(int errorcode);

    void logoffSuccess();

    public String getLoginUserName();
}
