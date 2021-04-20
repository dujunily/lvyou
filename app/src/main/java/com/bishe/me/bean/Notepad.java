package com.bishe.me.bean;

import org.litepal.crud.LitePalSupport;

public class Notepad extends LitePalSupport {

    String time;
    String title;
    String content;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

