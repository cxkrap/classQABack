package com.example.classqa.data.answer;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.classqa.po.Answer;

import java.util.List;
@Mapper
public interface AnswerMapper {
    /**
     * 查找所有答案并根据日期和点赞数排序
     * @return
     */
    List<Answer> selectAllAnswers();

    /**
     * 根据ID查找答案
     * @param id
     * @return
     */
    Answer selectQuestionByID(@Param("id") int id);

    /**
     * 插入一个新的答案
     * @param content
     * @param questionID
     * @param userID
     * @param userType
     * @return
     */
    int insertAnswer(@Param("content") String content,@Param("questionID")int questionID, 
                     @Param("userID")int userID, @Param("userType")String userType);

    /**
     * 更改问题描述
     * @param id
     * @param content
     * @return
     */
    void updateAnswerByID(@Param("id")int id,@Param("content") String content);
}
