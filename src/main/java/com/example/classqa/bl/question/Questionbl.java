package com.example.classqa.bl.question;

import com.example.classqa.po.Question;
import com.example.classqa.vo.QuestionForm;
import com.example.classqa.vo.QuestionVO;
import com.example.classqa.vo.ResponseVO;

public interface Questionbl {

    /**
     * 根据课程id
     * 搜索所有问题，成功时返回一个Question列表
     * @return
     */
    ResponseVO searchAllQuestion(int course_id);

    /**
     * 搜索某个特定的问题，成功时返回一个QuestionVO
     * @param id
     * @return
     */
    ResponseVO searchOneQuestion(int id);

    /**
     * 添加新问题
     * @param questionForm
     * @return
     */
    ResponseVO addQuestion(QuestionForm questionForm);

    /**
     * 根据id删除问题
     * @param id
     * @return
     */
    ResponseVO deleteQuestion(int id);

    /**
     * 更新问题的内容，成功时返回questionVO
     * @param questionVO
     * @return
     */
    ResponseVO updateQuestionContent(QuestionVO questionVO);

    /**
     * 若用户已经标记问题不会，则不会的数量减1，否则加1
     * @param user_id
     * @return
     */
    ResponseVO changeQuestionUnable(int user_id,int question_id);

    /**
     * 若用户已经标记问题已读，则问题被标记为未读，否则被标记为已读
     * @param user_id
     * @param question_id
     * @return
     */
    ResponseVO markQuestion(int user_id,int question_id);

    /**
     * 查看question的标记已读状态
     * @param user_id
     * @param question_id
     * @return
     */
    ResponseVO getQuestionMark(int user_id,int question_id);

    /**
     * 查看question的标记不会状态
     * @param user_id
     * @param question_id
     * @return
     */
    ResponseVO getQuestionUnable(int user_id,int question_id);
}
