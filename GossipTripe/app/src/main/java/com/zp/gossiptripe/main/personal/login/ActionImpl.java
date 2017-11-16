package com.zp.gossiptripe.main.personal.login;

import com.orhanobut.logger.Logger;
import com.zp.gossiptripe.test.PersonInfoData;

/**
 * Created by uiprj on 11/28/16.
 */

public class ActionImpl implements IAction {

    @Override
    public void login(String username, String password, IOnActionListener onloginListener) {
        Logger.d("username = " + username + "  password = " + password);
        if (username.equals("zhangsan") || password.equals("123456")) {
            onloginListener.loginSuccess(PersonInfoData.getPersonData());
        } else {
            onloginListener.loginFailed();
        }
    }

    @Override
    public void logoff(String username, IOnActionListener onActionListener) {
        if (!username.equals("zhangsan1")) {
            onActionListener.logOffSuccess();
        } else {
            onActionListener.logOffFailed();
        }
    }
}
