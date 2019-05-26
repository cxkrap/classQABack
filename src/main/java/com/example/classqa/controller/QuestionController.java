package com.example.classqa.controller;

import com.example.classqa.bl.question.Questionbl;
import com.example.classqa.po.Question;
import com.example.classqa.vo.QuestionForm;
import com.example.classqa.vo.ResponseVO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
import java.util.jar.JarException;

@RestController
public class QuestionController {
    @Autowired
    private Questionbl questionblservice;

    @RequestMapping(value="/question/{courseId}", method = RequestMethod.POST)
    public ResponseVO searchAllQuestion(@RequestBody String requestBody){
        int courseId = 0;
        try {
            JSONObject jsonObject = new JSONObject(requestBody);
            courseId = jsonObject.getInt("course_id");
        } catch (JSONException e){
            e.printStackTrace();
        }
        return questionblservice.searchAllQuestion(courseId);
    }

    @RequestMapping(value = "/question/add")
    public ResponseVO addQuestion(@RequestBody String requestBody){
        QuestionForm questionForm = new QuestionForm();
        try{
            JSONObject jsonObject = new JSONObject(requestBody);
            questionForm.setUser_id(jsonObject.getInt("user_id"));
            questionForm.setContent(jsonObject.getString("content"));
            questionForm.setCourse_id(jsonObject.getInt("course_id"));
            questionForm.setUserType("student");
        } catch (JSONException e){
            e.printStackTrace();
        }
        return questionblservice.addQuestion(questionForm);
    }

}
