package com.zp.gossiptripe.main.personal.login;

import com.zp.gossiptripe.main.personal.PersonBean;

/**
 * Created by uiprj on 11/28/16.
 */

public interface IOnActionListener {

    void loginSuccess(PersonBean personBean);

    void loginFailed();

    void logOffSuccess();

    void logOffFailed();
}
