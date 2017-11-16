package com.zp.gossiptripe.main.personal.login;

/**
 * Created by uiprj on 11/28/16.
 */

public interface IAction {

    public void login(String username, String password, IOnActionListener onActionListener);

    public void logoff(String username, IOnActionListener onActionListener);
}
