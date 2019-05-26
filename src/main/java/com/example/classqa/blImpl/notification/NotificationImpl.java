package com.example.classqa.blImpl.notification;

import com.example.classqa.bl.notification.Notificationbl;
import com.example.classqa.data.notification.NotificationMapper;
import com.example.classqa.po.Notification;
import com.example.classqa.po.Question;
import com.example.classqa.vo.NotificationForm;
import com.example.classqa.vo.NotificationVO;
import com.example.classqa.vo.QuestionVO;
import com.example.classqa.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class NotificationImpl implements Notificationbl {

    @Autowired
    NotificationMapper notificationMapper;

    @Override
    public ResponseVO searchAllNotification(int class_id){
        try {
            List <Notification>notificationList = notificationMapper.selectNotificationByClassID(class_id);
            List <NotificationVO> notificationVOList = this.notificationList2NotificationVOList(notificationList);
            return ResponseVO.buildSuccess(notificationVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO searchOneNotification(int id){
        try {
            Notification notification = new Notification();
            NotificationVO notificationVO = new NotificationVO();
            notification.setId(notification.getId());
            notification.setHaveReadStudent(notification.getHaveReadStudent());
            notification.setNotificationContent(notification.getNotificationContent());
            return ResponseVO.buildSuccess(notificationVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO addNotification(NotificationForm notificationForm){
        try {
            int classID = notificationForm.getClass_id();
            String content = notificationForm.getContent();
            int courseID = notificationForm.getClass_id();
            Notification notification = new Notification();
            notification.setNotificationContent(content);
            notification.setCourse_id(classID);
            notification.setHaveReadStudent(0);
            int notification_id=notificationMapper.insertNotification(notification);
            notificationMapper.insertCourseNotification(notification_id,courseID);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO deleteNotification(int id){
        try {
            notificationMapper.deleteNotificationByID(id);
            notificationMapper.deleteCourseNotification(id);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }


    @Override
    public ResponseVO updateNotificationContent(NotificationVO notificationVO){
        try {
            int id = notificationVO.getId();
            String content = notificationVO.getContent();
            notificationMapper.updateNotification(id,content);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }


    @Override
    public ResponseVO markReaded(int notification_id){
        try {
            notificationMapper.addNotificationRead(notification_id);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    public List<NotificationVO>notificationList2NotificationVOList(List<Notification>notificationList){
        List<NotificationVO>notificationVOList = new ArrayList<>();
        for(Notification notification:notificationList){
            NotificationVO notificationVO = new NotificationVO();
            notification.setId(notification.getId());
            notification.setHaveReadStudent(notification.getHaveReadStudent());
            notification.setNotificationContent(notification.getNotificationContent());
            notificationVOList.add(notificationVO);
        }
        return notificationVOList;
    }

}
