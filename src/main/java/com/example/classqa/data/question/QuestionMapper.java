package com.example.classqa.data.question;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.classqa.po.Question;
import java.util.List;
@Mapper
public interface QuestionMapper {
    /**
     * 查找所有问题并根据日期和不会人数排序
     * @return
     */
    List<Question> selectAllQuestions();

    /**
     * 根据ID查找问题
     * @param id
     * @return
     */
    Question selectQuestionByID(@Param("id") int id);

    /**
     * 插入一个新的问题，插入时默认unableNum为1，userType为student
     * @param content
     * @param unableNum
     * @param userID
     * @param userType
     * @return
     */
    int insertQuestion(@Param("content") String content,@Param("unableNum")int unableNum,
                       @Param("userID")int userID,@Param("userType")String userType);

    /**
     * 更改问题描述
     * @param id
     * @param content
     * @return
     */
    void updateQuestionByID(@Param("id")int id,@Param("content") String content);
}
