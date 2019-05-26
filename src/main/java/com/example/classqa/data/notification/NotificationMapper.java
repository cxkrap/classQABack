package com.example.classqa.data.notification;

import com.example.classqa.po.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface NotificationMapper {
    /**
     * 新建通知
     * @param content
     * @return
     */
    int insertNotification(@Param("content")String content);

    /**
     * 根据ID查找通知
     * @param id
     * @return
     */
    Notification selectNotificationByID(@Param("id")int id);

    /**
     * 根据课程ID查找通知
     * @param classID
     * @return
     */
    Notification selectNotificationByClassID(@Param("classID")int classID);
}
