package com.zp.gossiptripe.main.personal.regist;

import com.zp.gossiptripe.main.personal.PersonBean;
import com.zp.gossiptripe.main.personal.regist.model.RegistBean;

/**
 * Created by uiprj on 11/29/16.
 */

public interface IRegistView {

    public RegistBean getRegistBean();

    public void onRegistSuccess(PersonBean personBean);

    public void onFailed(int errorCode);

}
