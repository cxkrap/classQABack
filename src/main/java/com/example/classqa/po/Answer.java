package com.example.classqa.po;

import lombok.Data;
@Data
public class Answer {
    private int id;

    private String answerContent;

    private String userType;

    private int userId;

    private int questionId;

    private int thumb_num;
}
