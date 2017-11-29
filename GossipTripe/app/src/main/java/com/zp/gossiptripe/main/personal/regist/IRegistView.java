package com.zp.gossiptripe.main.personal.regist;

import com.zp.gossiptripe.main.personal.regist.model.RegistBean;
import com.zp.gossiptripe.main.personal.regist.model.UserBaseInfoBean;

/**
 * Created by uiprj on 11/29/16.
 */

public interface IRegistView {

    public RegistBean getRegistBean();

    public void onRegistSuccess(UserBaseInfoBean userBaseInfoBean);

    public void onFailed(int errorCode);

}
