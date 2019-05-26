package com.example.classqa.vo;

import lombok.Data;
@Data
public class QuestionVO {
    int id;
    int course_id;
    String content;
    int unable_num;
    int user_id;
    String userType;
}
