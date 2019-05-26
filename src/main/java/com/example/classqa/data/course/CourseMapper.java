package com.example.classqa.data.course;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.classqa.po.Course;
import java.util.List;
@Mapper
public interface CourseMapper {
    /**
     * 添加课程内容
     * @param content
     * @return
     */
    int insertCourse(@Param("content")String content,@Param("headline")String headaline,
                     @Param("teacherName")String teacherName,@Param("course_code")int course_code);

    /**
     * 添加课程成员
     * @param courseCode
     * @param userID
     * @return
     */
    int insertCourseUser(@Param("courseCode")int courseCode,@Param("userID")int userID);

    /**
     * 根据ID查找课程
     * @param id
     * @return
     */
    Course selectCourseByID(@Param("id")int id);

    List<Course>selectCourseByUserID(@Param("user_id")int user_id);
    /**
     * 根据课程码查找课程
     * @param course_code
     * @return
     */
    Course selectCoursesByCourseCode(@Param("course_code")int course_code);

    /**
     * 根据课程ID查找问题总数
     * @param course_id
     * @return
     */
    int getQuestionNum(@Param("course_id")int course_id);

    /**
     * 根据ID查找对应课程学生人数
     * @param id
     * @return
     */
    int getStudentNum(@Param("id")int id);

}
