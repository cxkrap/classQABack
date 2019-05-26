package com.example.classqa.vo;

import lombok.Data;
@Data
public class AnswerVO{
    int id;
    String content;
    int question_id;
    int user_id;
    String userType;
    int thumb_num;
}