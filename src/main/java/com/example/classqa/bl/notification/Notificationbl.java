package com.example.classqa.bl.notification;

import com.example.classqa.vo.NotificationForm;
import com.example.classqa.vo.NotificationVO;
import com.example.classqa.vo.ResponseVO;

public interface Notificationbl {
    /**
     * 搜索课程内所有公告，成功时返回一个Notification列表
     * @return
     */
    ResponseVO searchAllNotification(int class_id);

    /**
     * 搜索某个特定的公告，成功时返回一个NotificationVO
     * @param id
     * @return
     */
    ResponseVO searchOneNotification(int id);

    /**
     * 添加新公告
     * @param notificationForm
     * @return
     */
    ResponseVO addNotification(NotificationForm notificationForm);

    /**
     * 根据id删除公告
     * @param id
     * @return
     */
    ResponseVO deleteNotification(int id);

    /**
     * 更新公告的内容，成功时返回notificationVO
     * @param notificationVO
     * @return
     */
    ResponseVO updateNotificationContent(NotificationVO notificationVO);

    /**
     * 更新公告的已读人数
     * @param user_id
     * @return
     */
    ResponseVO markReaded(int user_id);
}
