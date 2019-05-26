package com.example.classqa.vo;

import lombok.Data;
import java.util.List;
@Data
public class CourseVO{
    int id;
    String content;
    List<Integer> question_id;
    List<Integer> student_id;
    List<Integer> teacher_id;
}