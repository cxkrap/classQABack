package com.example.classqa.vo;

import lombok.Data;
@Data
public class AnswerForm {
    String content;
    int question_id;
    int user_id;
    String userType;
}
