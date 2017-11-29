package com.zp.gossiptripe.main.personal.regist;

import com.zp.gossiptripe.main.personal.regist.model.UserBaseInfoBean;

/**
 * Created by uiprj on 11/29/16.
 */

public interface IOnRequestListener {

    public void onSuccess(UserBaseInfoBean userBaseInfoBean);

    public void onFailed(int errorCode);
}
