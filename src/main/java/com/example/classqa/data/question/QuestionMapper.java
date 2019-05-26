package com.example.classqa.data.question;

import com.example.classqa.po.QuestionReadItem;
import com.example.classqa.po.QuestionUnableItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.classqa.po.Question;

import java.sql.Timestamp;
import java.util.List;
@Mapper
public interface QuestionMapper {
    /**
     * 根据课堂的id查找所有问题并根据日期和不会人数排序
     * @return
     */
    List<Question> selectAllQuestions(int course_id);

    /**
     * 根据ID查找问题
     * @param id
     * @return
     */
    Question selectQuestionByID(@Param("id") int id);

    /**
     * 插入一个新的问题，插入时默认unableNum为1，userType为student
     * @return
     */
    int insertQuestion(@Param("question") Question question);


    /**
     *
     * @param course_id
     * @param question_id
     * @return
     */
    int insertCourseQuestion(@Param("course_id")int course_id,@Param("question_id") int question_id);

    /**
     * 通过ID删除问题
     * @param id
     */
    void deleteQuestion(@Param("id")int id);

    /**
     *
     * @param question_id
     */
    void deleteCourseQuestion(@Param("question_id")int question_id);

    /**
     * 更改问题描述
     * @param id
     * @param content
     * @return
     */

    void updateQuestionDescription(@Param("id")int id,@Param("content") String content);

    /**
     * 通过问题的ID查找所有标记该问题为不会的用户ID
     * @param question_id
     * @return
     */
    List<Integer> getQuestionsUser(@Param("question_id")int question_id);

    /**
     * 删除用户的不会记录
     * @param question_id
     * @param user_id
     */
    void deleteQuestionUser(@Param("question_id")int question_id,@Param("user_id")int user_id);

    /**
     * 添加用户的不会记录
     * @param question_id
     * @param user_id
     */

    void addQuestionUser(@Param("question_id")int question_id,@Param("user_id")int user_id);

    /**
     * 添加用户的读记录
     * @param question_id
     * @param user_id
     */
    void addQuestionRead(@Param("question_id")int question_id,@Param("user_id")int user_id);
    /**
     * 获取问题的不会人数
     * @param question
     * @return
     */
    int getUnableNum(@Param("question_id")int question);

    /**
     * 更改问题的不会人数
     * @param id
     * @param unableNum
     */
    void updateQuestionUnable(@Param("id")int id,@Param("unableNum")int unableNum);

    /**
     * 返回QuestionReadItem
     * @param question_id
     * @param user_id
     * @return
     */
    QuestionReadItem selectMarkQuestion(@Param("question_id")int question_id,@Param("user_id")int user_id);

    /**
     * 返回QuestionUnableItem
     * @param question_id
     * @param user_id
     * @return
     */
    QuestionUnableItem selectUnableItem(@Param("question_id")int question_id,@Param("user_id")int user_id);
    /**
     * 标记问题已读
     * @param question_id
     * @param user_id
     * @param state
     */
    void markQuestion(@Param("question_id")int question_id,@Param("user_id")int user_id,@Param("state")int state);

    /**
     * 返回用户已读的问题数
     * @param user_id
     * @return
     */
    int getUserReadQuestionNum(@Param("user_id")int user_id);
}
