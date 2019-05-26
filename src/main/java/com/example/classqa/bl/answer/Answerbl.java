package com.example.classqa.bl.answer;
import com.example.classqa.vo.AnswerForm;
import com.example.classqa.vo.AnswerVO;
import com.example.classqa.vo.ResponseVO;

public interface Answerbl {

    /**
     * 搜索对应问题所有回答，成功时返回一个Answer列表
     * @return
     */
    ResponseVO searchAllAnswer(int question_id);

    /**
     * 搜索某个特定的回答，成功时返回一个AnswerVO
     * @param id
     * @return
     */
    ResponseVO searchOneAnswer(int id);

    /**
     * 添加新回答
     * @param answerForm
     * @return
     */
    ResponseVO addAnswer(AnswerForm answerForm);

    /**
     * 根据id删除回答
     * @param id
     * @return
     */
    ResponseVO deleteAnswer(int id);

    /**
     * 更新问题的内容，成功时返回answerVO
     * @param answerVO
     * @return
     */
    ResponseVO updateAnswerContent(AnswerVO answerVO);

    /**
     * 若用户已经标记回答点赞，则点赞数量减1，否则加1
     * @param user_id
     * @return
     */
    ResponseVO changeAnswerUnable(int user_id,int answer_id);

}
