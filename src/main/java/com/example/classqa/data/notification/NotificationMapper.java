package com.example.classqa.data.notification;

import com.example.classqa.po.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotificationMapper {
    /**
     * 新建通知
     * @param notification
     * @return
     */
    int insertNotification(Notification notification);

    int insertCourseNotification(@Param("notificationID")int notificationID,
                                 @Param("courseID")int courseID);

    /**
     * 根据ID查找通知
     * @param id
     * @return
     */
    Notification selectNotificationByID(@Param("id")int id);

    /**
     * 根据课程ID查找全部通知
     * @param classID
     * @return
     */
    List<Notification> selectNotificationByClassID(@Param("classID")int classID);

    /**
     * 根据notification的id删除公告
     * @param id
     */
    void deleteNotificationByID(@Param("id")int id);

    /**
     *
     * @param notification_id
     */
    void deleteCourseNotification(@Param("notification_id")int notification_id);

    /**
     * 已读公告数加1
     * @param id
     */
    void addNotificationRead(@Param("id")int id);

    /**
     * 更新已读公告内容
     * @param id
     * @param content
     */
    void updateNotification(@Param("id")int id,@Param("content")String content);
}
