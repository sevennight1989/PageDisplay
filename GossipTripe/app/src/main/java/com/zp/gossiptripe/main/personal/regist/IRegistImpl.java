package com.zp.gossiptripe.main.personal.regist;


import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.SPUtils;
import com.zp.gossiptripe.common.Constant;
import com.zp.gossiptripe.common.Response;
import com.zp.gossiptripe.http.BaseSubscriber;
import com.zp.gossiptripe.http.CommonServiceManager;
import com.zp.gossiptripe.main.personal.regist.model.RegistBean;
import com.zp.gossiptripe.main.personal.regist.model.UserBaseInfoBean;
import com.zp.gossiptripe.utils.PengLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by uiprj on 11/29/16.
 */

public class IRegistImpl implements IRegistInf {
    IOnRequestListener requestListener;
    CommonServiceManager mCSM;
    SPUtils mSP;
    public IRegistImpl() {
        mCSM =CommonServiceManager.getCommonServiceManager();
        mSP = SPUtils.getInstance(Constant.SP_LEY);
    }

    @Override
    public void registAccount(RegistBean registBean, IOnRequestListener requestListener) {
        this.requestListener = requestListener;
        String username = registBean.getUsername();
        String password = registBean.getPassword();
        String name = registBean.getName();
        String birthday = registBean.getBirthday();
        String address = registBean.getAddress();
        String detialaddress = registBean.getDetialaddress();
        String mechanism = registBean.getMechanism();
        String email = registBean.getEmail();

        if (username.equals("") || password.equals("") || name.equals("") || email.equals("")) {
            requestListener.onFailed(1);
            return;
        }

        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password", EncryptUtils.encryptMD5ToString(password));
        map.put("name",name);
        map.put("birthday",birthday);
        map.put("address",address);
        map.put("detialaddress",detialaddress);
        map.put("mechanism",mechanism);
        map.put("email",email);
        mCSM.getRegistParam(map).subscribe(new BaseSubscriber<Response<String>>(){
            @Override
            public void onNext(Response<String> stringResponse) {
                String session = stringResponse.getData();
                mSP.put(Constant.SP_SESSION,session);
                PengLogger.v(stringResponse.getData());
                sessionlogin(session);
            }
        });



//        if (userName.equals("") || password.equals("") || name.equals("") || email.equals("") ) {
//            requestListener.onFailed(1);
//            return;
//        }
//        if (userName.equals("zhangsan")) {
//            requestListener.onFailed(0);
//        } else {
//            PersonBean pBean = PersonInfoData.getPersonData();
//            if (pBean != null) {
//                requestListener.onSuccess(pBean);
//            }
//        }
    }


    private void sessionlogin(String session){
        Map<String,String> map = new HashMap<>();
        map.put("session",session);
        mCSM.getLoginParamBySession(map).subscribe(new BaseSubscriber<Response<UserBaseInfoBean>>(){
            @Override
            public void onNext(Response<UserBaseInfoBean> userBaseInfoBeanResponse) {
                requestListener.onSuccess(userBaseInfoBeanResponse.getData());
            }
        });
    }

}
