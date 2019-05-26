package com.example.classqa.bl.course;

import com.example.classqa.vo.CourseForm;
import com.example.classqa.vo.ResponseVO;
public interface Coursebl {

    /**
     * 添加一个新的课程,成功时返回一个CourseVO（要生成随机四位数的课程码）
     * @param courseForm
     * @return
     */
    ResponseVO addNewCourse(int user_id,CourseForm courseForm);

    /**
     * 根据课程码查找课程
     * @param course_code
     * @return
     */
    ResponseVO searchCourseByCourseCode(int course_code);

    /**
     * 学生加入课程(通过课程码)
     * @param course_code
     * @param user_id
     * @return
     */
    ResponseVO joinCourse(int course_code,int user_id);

    /**
     * 成功时返回一个CourseFormForStudent
     * @param user_id
     * @param course_id
     * @return
     */
    ResponseVO getStudentCourseForm(int user_id,int course_id);

    /**
     * 成功时返回一个CourseFormForTeacher
     * @param course_id
     * @return
     */
    ResponseVO getTeacherCourseForm(int course_id);


    /**
     * 查找用户加入的全部课程
     * @param user_id
     * @return
     */
    ResponseVO searchByUserID(int user_id);

    /**
     * 通过ID查找课程
     * @param id
     * @return
     */
    ResponseVO searchByID(int id);

}
