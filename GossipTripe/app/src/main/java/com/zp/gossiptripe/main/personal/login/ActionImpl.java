package com.zp.gossiptripe.main.personal.login;

import android.text.TextUtils;

import com.zp.gossiptripe.common.Response;
import com.zp.gossiptripe.http.BaseSubscriber;
import com.zp.gossiptripe.http.CommonServiceManager;
import com.zp.gossiptripe.main.personal.regist.model.UserBaseInfoBean;
import com.zp.gossiptripe.utils.PengLogger;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by uiprj on 11/28/16.
 */

public class ActionImpl implements IAction {
    CommonServiceManager mCSM;

    public ActionImpl(){
        mCSM  =CommonServiceManager.getCommonServiceManager();
    }
    @Override
    public void login(String username, String password, final IOnActionListener onloginListener) {
        PengLogger.v("username = " + username + "  password = " + password);
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        mCSM.getLoginParam(map).subscribe(new BaseSubscriber<Response<UserBaseInfoBean>>(){
            @Override
            public void onNext(Response<UserBaseInfoBean> userBaseInfoBeanResponse) {
                onloginListener.loginSuccess(userBaseInfoBeanResponse.getData());
            }
        });


//        if (username.equals("zhangsan") || password.equals("123456")) {
//            onloginListener.loginSuccess();
//        } else {
//            onloginListener.loginFailed();
//        }
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
