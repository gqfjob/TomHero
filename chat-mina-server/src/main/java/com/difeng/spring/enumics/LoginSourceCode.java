package com.difeng.spring.enumics;

/**
 * Created by apple on 16/9/23.
 */
public enum LoginSourceCode {
    web("网页"),
    android("安卓手机"),
    iphone("IPhone"),
    ipad("Ipad");

    private String description;

    LoginSourceCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
