package com.bishe.me.bean;

import cn.bmob.v3.BmobObject;

public class userdenglu extends BmobObject {
    private String name;
    private String pass;
    private String like;

    public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
}
