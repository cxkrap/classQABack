package com.example.classqa.po;

import java.util.List;

public class Notification {
    private int id;

    private String notificationContent;

    private List<Student> haveReadStudent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public List<Student> getHaveReadStudent() {
        return haveReadStudent;
    }

    public void setHaveReadStudent(List<Student> haveReadStudent) {
        this.haveReadStudent = haveReadStudent;
    }

}
