package com.bishe.me.bean;

import cn.bmob.v3.BmobObject;

public class PinglunBean extends BmobObject {
    private String name;
    private String user;
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
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
}
