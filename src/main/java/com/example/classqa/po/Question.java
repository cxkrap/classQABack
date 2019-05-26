package com.example.classqa.po;

public class Question {

    private int id;

    private String content;

    private int unableNum;

    private String userType;

    private int userID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionContent() {
        return content;
    }

    public void setQuestionContent(String questionContent) {
        this.content = questionContent;
    }

    public int getQueryNum() {
        return unableNum;
    }

    public void setQueryNum(int queryNum) {
        this.unableNum = queryNum;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
