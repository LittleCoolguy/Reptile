package com.example.reptile.entity;

/**
 * @description:
 * @author: XiaoGao
 * @time: 2022/5/22 14:33
 */
public class Review {
    int readingNum;
    int commentNum;
    String title;
    String time;
    String url;

    public int getReadingNum() {
        return readingNum;
    }

    public void setReadingNum(int readingNum) {
        this.readingNum = readingNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Review(int readingNum, int commentNum, String title, String time){
        this.readingNum = readingNum;
        this.commentNum = commentNum;
        this.title = title;
        this.time = time;
    };
}
