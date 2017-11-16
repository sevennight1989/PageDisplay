package com.zp.gossiptripe.main.personal.regist;

import com.zp.gossiptripe.main.personal.PersonBean;

/**
 * Created by uiprj on 11/29/16.
 */

public interface IRegistView {

    public RegisterBean getRegistBean();

    public void onRegistSuccess(PersonBean personBean);

    public void onFailed(int errorCode);

}
