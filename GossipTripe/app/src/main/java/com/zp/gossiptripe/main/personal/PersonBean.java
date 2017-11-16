package com.zp.gossiptripe.main.personal;

import java.io.Serializable;

/**
 * Created by uiprj on 11/25/16.
 */

public class PersonBean implements Serializable {


    private static final long serialVersionUID = 7247714666080613254L;

    @Override
    public String toString() {
        return "PersonBean{" +
        "mAge=" + mAge +
        ", mName='" + mName + '\'' +
        ", mBirthday='" + mBirthday + '\'' +
        ", mLocation='" + mLocation + '\'' +
        ", mOrganization='" + mOrganization + '\'' +
        ", mIconPath='" + mIconPath + '\'' +
        ", mProvince='" + mProvince + '\'' +
        '}';
    }

    private String mName;
    private String mBirthday;
    private String mLocation;
    private String mOrganization;
    private String mIconPath;
    private int mAge;

    public String getmProvince() {
        return mProvince;
    }

    public void setmProvince(String mProvince) {
        this.mProvince = mProvince;
    }

    public int getmAge() {
        return mAge;
    }

    public void setmAge(int mAge) {
        this.mAge = mAge;
    }

    private String mProvince;

    public String getmBirthday() {
        return mBirthday;
    }

    public void setmBirthday(String mBirthday) {
        this.mBirthday = mBirthday;
    }

    public String getmIconPath() {
        return mIconPath;
    }

    public void setmIconPath(String mIconPath) {
        this.mIconPath = mIconPath;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getmOrganization() {
        return mOrganization;
    }

    public void setmOrganization(String mOrganization) {
        this.mOrganization = mOrganization;
    }


}
