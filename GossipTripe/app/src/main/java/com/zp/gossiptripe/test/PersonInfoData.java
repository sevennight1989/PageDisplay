package com.zp.gossiptripe.test;

import com.zp.gossiptripe.main.personal.PersonBean;

/**
 * Created by uiprj on 11/25/16.
 */

public class PersonInfoData {

    public static PersonBean getPersonData(){
        PersonBean pBean = new PersonBean();
        pBean.setmName("小小鸟儿");
        pBean.setmBirthday("2007-1-23");
        pBean.setmLocation("上海市闵行区");
        pBean.setmOrganization("闵行区少年宫");
        pBean.setmIconPath("http://img4.duitang.com/uploads/item/201607/06/20160706203006_EjxmS.thumb.700_0.png");
        pBean.setmAge(9);
        pBean.setmProvince("上海");
        return pBean;
    }
}
