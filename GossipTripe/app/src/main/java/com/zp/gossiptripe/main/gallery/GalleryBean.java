package com.zp.gossiptripe.main.gallery;

import java.io.Serializable;

/**
 * Created by ZhangPeng on 10-9-0009.
 */
public class GalleryBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String headPicSrc;
    private String name;
    private int age;
    private String location;
    private String organization;
    private String data;
    private String productPreviewSrc;
    private String productName;
    private String productContent;
    private int number;
    private int praiseTag;


    public String getHeadPicSrc() {
        return headPicSrc;
    }

    public void setHeadPicSrc(String headPicSrc) {
        this.headPicSrc = headPicSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getProductPreviewSrc() {
        return productPreviewSrc;
    }

    public void setProductPreviewSrc(String productPreviewSrc) {
        this.productPreviewSrc = productPreviewSrc;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPraiseTag() {
        return praiseTag;
    }

    public void setPraiseTag(int praiseTag) {
        this.praiseTag = praiseTag;
    }
}
