package com.example.multi_recyclerview;

/**
 * author: zhijunhong
 * created on: 2021/4/25 10:59 AM
 * version: 1.0
 * description:
 */
public class IOSBean implements Vistable{
    private String desc;
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int type(TypeFactory factory) {
        return factory.type(this);
    }
}
