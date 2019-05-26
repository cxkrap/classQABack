package com.example.classqa.blImpl.question;

import com.example.classqa.bl.question.Questionbl;
import com.example.classqa.data.question.QuestionMapper;
import com.example.classqa.po.Question;
import com.example.classqa.po.QuestionReadItem;
import com.example.classqa.vo.QuestionForm;
import com.example.classqa.vo.QuestionVO;
import com.example.classqa.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuestionImpl implements Questionbl {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public ResponseVO searchAllQuestion(int course_id){
        try {
            List<Question>questionList = questionMapper.selectAllQuestions(course_id);
            List<QuestionVO>questionVOList = this.questionList2QuestionVOList(questionList);
            return ResponseVO.buildSuccess(questionVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }


    @Override
    public ResponseVO searchOneQuestion(int id){
        try {
            Question question = questionMapper.selectQuestionByID(id);
            QuestionVO questionVO = new QuestionVO();
            questionVO.setId(question.getId());
            questionVO.setContent(question.getQuestionContent());
            questionVO.setUnable_num(question.getQueryNum());
            questionVO.setUser_id(question.getUserID());
            questionVO.setUserType(question.getUserType());
            return ResponseVO.buildSuccess(questionVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO addQuestion(QuestionForm questionForm){
        try {
            String content = questionForm.getContent();
            int unableNum = 1;
            int course_id = questionForm.getCourse_id();
            int user_id = questionForm.getUser_id();
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            int question_id=questionMapper.insertQuestion(content,unableNum,user_id,timestamp);
            questionMapper.insertCourseQuestion(course_id,question_id);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO deleteQuestion(int id){
        try {
            questionMapper.deleteQuestion(id);
            questionMapper.deleteCourseQuestion(id);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("问题不存在");
        }
    }

    @Override
    public ResponseVO updateQuestionContent(QuestionVO questionVO){
        try {
            int id = questionVO.getId();
            String content = questionVO.getContent();
            questionMapper.updateQuestionDescription(id,content);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("问题不存在");
        }
    }

    @Override
    public ResponseVO changeQuestionUnable(int user_id,int question_id){
        try {
            List<Integer>unable_users = questionMapper.getQuestionsUser(question_id);
            boolean flag = true;
            //true的时候添加，否则删除
            int num = questionMapper.getUnableNum(question_id);
            for(int id:unable_users){
                if(id==user_id){
                    flag=false;
                    break;
                }
            }
            if(flag){
                questionMapper.addQuestionUser(question_id,user_id);
                questionMapper.updateQuestionUnable(question_id,num+1);
            }
            else{
                questionMapper.deleteQuestionUser(question_id,user_id);
                questionMapper.updateQuestionUnable(question_id,num-1);
            }
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    public List<QuestionVO>questionList2QuestionVOList(List<Question>questionList){
        List<QuestionVO>questionVOList = new ArrayList<>();
        for(Question question:questionList){
            QuestionVO questionVO = new QuestionVO();
            questionVO.setId(question.getId());
            questionVO.setContent(question.getQuestionContent());
            questionVO.setUnable_num(question.getQueryNum());
            questionVO.setUser_id(question.getUserID());
            questionVO.setUserType(question.getUserType());
            questionVOList.add(questionVO);
        }
        return questionVOList;
    }
    @Override
    public ResponseVO markQuestion(int user_id,int question_id){
        //1是已读，0是未读
        try {
            QuestionReadItem questionReadItem = questionMapper.selectMarkQuestion(question_id,user_id);
            if(questionReadItem.getState()==0){
                questionMapper.markQuestion(question_id,user_id,1);
            }
            else{
                questionMapper.markQuestion(question_id,user_id,0);
            }
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO getQuestionMark(int user_id,int question_id){
        try {
            //true是已读，false是未读，默认为未读
            QuestionReadItem questionReadItem = questionMapper.selectMarkQuestion(question_id,user_id);
            if(questionReadItem==null||questionReadItem.getState()==0)return ResponseVO.buildSuccess(false);
            else return ResponseVO.buildSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO getQuestionUnable(int user_id,int question_id){
        try {
            //true是会，false是不会，默认为会
            List<Integer>unable_users = questionMapper.getQuestionsUser(question_id);
            boolean flag = true;
            //true的时候添加，否则删除
            for(int id:unable_users){
                if(id==user_id){
                    flag=false;
                    break;
                }
            }
            return ResponseVO.buildSuccess(flag);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }
}
