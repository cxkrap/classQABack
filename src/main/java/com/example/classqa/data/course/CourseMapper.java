package com.example.classqa.data.course;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.classqa.po.Course;
@Mapper
public interface CourseMapper {
    /**
     * 添加课程内容
     * @param content
     * @return
     */
    int insertCourse(@Param("content")String content);

    /**
     * 添加课程老师
     * @param courseID
     * @param teacherID
     * @return
     */
    int insertCourseTeacher(@Param("courseID")int courseID,@Param("teacherID")int teacherID);

    /**
     * 添加课程学生
     * @param courseID
     * @param studentID
     * @return
     */
    int insertCourseStudent(@Param("courseID")int courseID,@Param("studentID")int studentID);

    /**
     * 添加课程问题
     * @param courseID
     * @param questionID
     * @return
     */
    int insertCourseQuestion(@Param("courseID")int courseID,@Param("questionID")int questionID);

    /**
     * 根据ID查找课程
     * @param id
     * @return
     */
    Course selectCourseByID(@Param("id")int id);

    /**
     * 根据ID查找对应课程学生人数
     * @param id
     * @return
     */
    int getStudentNum(@Param("id")int id);

    /**
     * 根据ID删除课程
     * @param courseID
     */
    void deleteCourseQuestion(@Param("courseID")int courseID);
}
