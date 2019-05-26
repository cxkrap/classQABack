package com.example.classqa.blImpl.course;

import com.example.classqa.bl.course.Coursebl;
import com.example.classqa.data.course.CourseMapper;
import com.example.classqa.data.question.QuestionMapper;
import com.example.classqa.vo.*;
import com.example.classqa.po.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class CourseImpl implements Coursebl {

    @Autowired
    CourseMapper courseMapper;
    @Autowired
    QuestionMapper questionMapper;

    @Override
    public ResponseVO addNewCourse(int user_id,CourseForm courseForm){
        try {
            String content = courseForm.getCourse_content();
            String name = courseForm.getCourse_name();
            String teacherName = courseForm.getTeacher_name();
            int course_code = (int)(1+Math.random()*10000);
            int course_id=courseMapper.insertCourse(content,name,teacherName,course_code);
            courseMapper.insertCourseUser(course_id,user_id);
            return ResponseVO.buildSuccess(course_code);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO searchCourseByCourseCode(int course_code){
        try {
            Course course=courseMapper.selectCoursesByCourseCode(course_code);
            CourseVO courseVO = new CourseVO();
            courseVO.setId(course.getId());
            courseVO.setCourse_code(course.getCourse_code());
            courseVO.setCourse_content(course.getContent());
            courseVO.setCourse_name(course.getHeadline());
            courseVO.setTeacher_name(course.getTeacher_name());
            return ResponseVO.buildSuccess(courseVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }



    @Override
    public ResponseVO joinCourse(int course_code,int user_id){
        try {
            courseMapper.insertCourseUser(course_code,user_id);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO getStudentCourseForm(int user_id,int course_id){
        try {
            Course course = courseMapper.selectCourseByID(course_id);
            CourseFormForStudent courseFormForStudent = new CourseFormForStudent();
            courseFormForStudent.setCourse_headline(course.getHeadline());
            int total_num = courseMapper.getQuestionNum(course_id);
            int user_read_num = questionMapper.getUserReadQuestionNum(user_id);
            courseFormForStudent.setQuestion_num(total_num-user_read_num);
            return ResponseVO.buildSuccess(courseFormForStudent);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO getTeacherCourseForm(int course_id){
        try {
            Course course = courseMapper.selectCourseByID(course_id);
            CourseFormForTeacher courseFormForTeacher = new CourseFormForTeacher();
            courseFormForTeacher.setCourse_headline(course.getHeadline());
            courseFormForTeacher.setQuestion_num(courseMapper.getQuestionNum(course_id));
            return ResponseVO.buildSuccess(courseFormForTeacher);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }


    @Override
    public ResponseVO searchByUserID(int user_id) {
        try {
            List<Course>courseList = courseMapper.selectCourseByUserID(user_id);
            List<CourseVO>courseVOList = this.courseList2courseVOList(courseList);
            return ResponseVO.buildSuccess(courseVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }

    }

    @Override
    public ResponseVO searchByID(int id){
        try {
            Course course = courseMapper.selectCourseByID(id);
            CourseVO courseVO = new CourseVO();
            courseVO.setId(course.getId());
            courseVO.setTeacher_name(course.getTeacher_name());
            courseVO.setCourse_name(course.getHeadline());
            courseVO.setCourse_content(course.getContent());
            courseVO.setCourse_code(course.getCourse_code());
            return ResponseVO.buildSuccess(courseVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    public List<CourseVO>courseList2courseVOList(List<Course>courseList){
        List<CourseVO>courseVOList = new ArrayList<>();
        for(Course course:courseList){
            CourseVO courseVO = new CourseVO();
            courseVO.setId(course.getId());
            courseVO.setTeacher_name(course.getTeacher_name());
            courseVO.setCourse_name(course.getHeadline());
            courseVO.setCourse_content(course.getContent());
            courseVO.setCourse_code(course.getCourse_code());
            courseVOList.add(courseVO);
        }
        return courseVOList;
    }
}
