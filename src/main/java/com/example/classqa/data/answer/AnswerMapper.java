package com.example.classqa.data.answer;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.classqa.po.Answer;

import java.sql.Timestamp;
import java.util.List;
@Mapper
public interface AnswerMapper {
    /**
     * 根据questionID查找所有答案并根据日期和点赞数排序
     * @return
     */
    List<Answer> selectAllAnswers(@Param("questionID") int questionID);

    /**
     * 根据ID查找答案
     * @param id
     * @return
     */
    Answer selectQuestionByID(@Param("id") int id);

    /**
     * 插入一个新的答案
     * @return
     */
    int insertAnswer(@Param("answer") Answer answer);

    /**
     *
     * @param question_id
     * @param answer_id
     * @return
     */
    int insertQuestionAnswer(@Param("question_id")int question_id,@Param("answer_id")int answer_id);

    /**
     * 更改问题描述
     * @param id
     * @param content
     * @return
     */
    void updateAnswerByID(@Param("id")int id,@Param("content") String content);

    /**
     *
     * @param id
     */
    void deleteAnswer(@Param("id")int id);

    /**
     *
     * @param id
     */
    void deleteQuestionAnswer(@Param("answer_id")int id);

    /**
     *
     * @param answer_id
     * @return
     */
    List<Integer> getAnswersUser(@Param("answer_id")int answer_id);

    /**
     *
     * @param answer_id
     * @return
     */
    int getThumbNum(@Param("answer_id")int answer_id);

    /**
     *
     * @param answer_id
     * @param user_id
     */
    void addAnswerUser(@Param("answer_id")int answer_id,@Param("user_id")int user_id);

    /**
     *
     * @param answer_id
     * @param user_id
     */
    void deleteAnswerUser(@Param("answer_id")int answer_id,@Param("user_id")int user_id);

    /**
     *
     * @param answer_id
     * @param thumb_num
     */
    void updateThumbNum(@Param("answer_id")int answer_id,@Param("thumb_num")int thumb_num);
}
