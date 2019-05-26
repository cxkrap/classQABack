package com.example.classqa.blImpl.answer;

import com.example.classqa.bl.answer.Answerbl;
import com.example.classqa.data.answer.AnswerMapper;
import com.example.classqa.vo.AnswerForm;
import com.example.classqa.vo.AnswerVO;
import com.example.classqa.vo.ResponseVO;
import com.example.classqa.po.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@Service
public class AnswerImpl implements Answerbl {

    @Autowired
    AnswerMapper answerMapper;

    @Override
    public ResponseVO searchAllAnswer(int question_id){
        try {
            List<Answer>answerList = answerMapper.selectAllAnswers(question_id);
            List<AnswerVO>answerVOList = this.answerList2AnswerVOList(answerList);
            return ResponseVO.buildSuccess(answerList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO searchOneAnswer(int id){
        try {
            Answer answer = answerMapper.selectQuestionByID(id);
            AnswerVO answerVO = new AnswerVO();
            answerVO.setId(answer.getId());
            answerVO.setContent(answer.getAnswerContent());
            answerVO.setThumb_num(answer.getThumb_num());
            answerVO.setUser_id(answer.getUserId());
            answerVO.setUserType(answer.getUserType());
            return ResponseVO.buildSuccess(answerVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO addAnswer(AnswerForm answerForm){
        try {
            int question_id = answerForm.getQuestion_id();
            int user_id = answerForm.getUser_id();
            String content = answerForm.getContent();
            String userType = answerForm.getUserType();
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            Answer answer = new Answer();
            answer.setQuestionId(question_id);
            answer.setUserId(user_id);
            answer.setAnswerContent(content);
            answer.setUserType(userType);
            answer.setThumb_num(0);
            answer.setTimestamp(timestamp);
            int answer_id=answerMapper.insertAnswer(answer);
            answerMapper.insertQuestionAnswer(question_id,answer_id);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO deleteAnswer(int id){
        try {
            answerMapper.deleteAnswer(id);
            answerMapper.deleteQuestionAnswer(id);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO updateAnswerContent(AnswerVO answerVO){
        try {
            int id = answerVO.getId();
            String content = answerVO.getContent();
            answerMapper.updateAnswerByID(id,content);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO changeAnswerUnable(int user_id,int answer_id){
        try {
            List<Integer>thumb_users = answerMapper.getAnswersUser(answer_id);
            boolean flag = true;
            //true的时候添加，否则删除
            int num = answerMapper.getThumbNum(answer_id);
            for(int id:thumb_users){
                if(id==user_id){
                    flag=false;
                    break;
                }
            }
            if(flag){
                answerMapper.addAnswerUser(answer_id,user_id);
                answerMapper.updateThumbNum(answer_id,num+1);
            }
            else{
                answerMapper.deleteAnswerUser(answer_id,user_id);
                answerMapper.updateThumbNum(answer_id,num-1);
            }
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    public List<AnswerVO>answerList2AnswerVOList(List<Answer>answerList){
        List<AnswerVO>answerVOList = new ArrayList<>();
        for(Answer answer:answerList){
            AnswerVO answerVO = new AnswerVO();
            answerVO.setId(answer.getId());
            answerVO.setContent(answer.getAnswerContent());
            answerVO.setThumb_num(answer.getThumb_num());
            answerVO.setUser_id(answer.getUserId());
            answerVO.setUserType(answer.getUserType());
            answerVOList.add(answerVO);
        }
        return answerVOList;
    }
}
