package com.zp.gossiptripe.main.personal.regist;


import com.zp.gossiptripe.common.Response;
import com.zp.gossiptripe.http.BaseSubscriber;
import com.zp.gossiptripe.http.CommonService;
import com.zp.gossiptripe.http.CommonServiceManager;
import com.zp.gossiptripe.main.personal.PersonBean;
import com.zp.gossiptripe.main.personal.regist.model.RegistBean;
import com.zp.gossiptripe.test.PersonInfoData;
import com.zp.gossiptripe.utils.PengLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by uiprj on 11/29/16.
 */

public class IRegistImpl implements IRegistInf {

    CommonServiceManager mCSM;
    public IRegistImpl() {
        mCSM = new CommonServiceManager();
    }

    @Override
    public void registAccount(RegistBean registBean, IOnRequestListener requestListener) {

        String username = registBean.getUsername();
        String password = registBean.getPassword();
        String name = registBean.getName();
        String birthday = registBean.getBirthday();
        String address = registBean.getAddress();
        String detialaddress = registBean.getDetialaddress();
        String mechanism = registBean.getMechanism();
        String email = registBean.getEmail();

        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("name",name);
        map.put("birthday",birthday);
        map.put("address",address);
        map.put("detialaddress",detialaddress);
        map.put("mechanism",mechanism);
        map.put("email",email);
        mCSM.getRegistParam(map).subscribe(new BaseSubscriber<Response<String>>(){
            @Override
            public void onNext(Response<String> stringResponse) {
                PengLogger.v(stringResponse.getData());
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


}
