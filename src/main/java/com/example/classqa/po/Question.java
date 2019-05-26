package com.example.classqa.po;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Question {

    private int id;

    private int course_id;

    private String content;

    private int unableNum;

    private String userType;

    private int userID;

    private Timestamp timestamp;
}
