package com.zp.gossiptripe.main.personal.regist;

import com.zp.gossiptripe.main.personal.regist.model.RegistBean;

/**
 * Created by uiprj on 11/29/16.
 */

public interface IRegistInf {

    public void registAccount(RegistBean registeBean, IOnRequestListener requestListener);
}
