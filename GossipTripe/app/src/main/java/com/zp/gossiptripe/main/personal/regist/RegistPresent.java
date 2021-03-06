package com.zp.gossiptripe.main.personal.regist;

import com.zp.gossiptripe.main.personal.regist.model.UserBaseInfoBean;

/**
 * Created by uiprj on 11/29/16.
 */

public class RegistPresent {

    private IRegistView mRegistView;
    private IRegistImpl mRegistImpl;

    RegistPresent(IRegistView iRegistView) {

        mRegistView = iRegistView;
        mRegistImpl = new IRegistImpl();
    }

    public void registAccount() {
        mRegistImpl.registAccount(mRegistView.getRegistBean(), new IOnRequestListener() {
            @Override
            public void onSuccess(UserBaseInfoBean userBaseInfoBean) {
                mRegistView.onRegistSuccess(userBaseInfoBean);
            }

            @Override
            public void onFailed(int errorCode) {
                mRegistView.onFailed(errorCode);
            }
        });
    }

}
