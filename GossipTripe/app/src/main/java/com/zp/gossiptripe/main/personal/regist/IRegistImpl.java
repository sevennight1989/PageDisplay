package com.zp.gossiptripe.main.personal.regist;


import com.zp.gossiptripe.main.personal.PersonBean;
import com.zp.gossiptripe.test.PersonInfoData;

/**
 * Created by uiprj on 11/29/16.
 */

public class IRegistImpl implements IRegistInf {

    @Override
    public void registAccount(RegisterBean registerBean, IOnRequestListener requestListener) {

        String userName = registerBean.getUserName();
        String password = registerBean.getPassword();
        String name = registerBean.getName();
        String birthday = registerBean.getBirthday();
        String province = registerBean.getProvince();
        String address = registerBean.getAddress();
        String organization = registerBean.getOrganization();
        String iconPath = registerBean.getHeadPath();
        if (userName.equals("") || password.equals("") || name.equals("") || birthday.equals("") ||
        province.equals("") || address.equals("") || organization.equals("") || iconPath.equals("")) {
            requestListener.onFailed(1);
            return;
        }
        if (userName.equals("zhangsan")) {
            requestListener.onFailed(0);
        } else {
            PersonBean pBean = PersonInfoData.getPersonData();
            if (pBean != null) {
                requestListener.onSuccess(pBean);
            }
        }
    }
}
